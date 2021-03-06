package edu.brown.cs.jhbgbssg.Client;

// Importing resources
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.brown.cs.jhbgbssg.Game.risk.RiskMessageType;

/**
 * This class handles lobbies, player connections, starting matches, and
 * relaying messages to matches.
 *
 * @author bgabinet
 */
@WebSocket
public class Matches {

  // Session queue and GSON
  private static final Gson GSON = new Gson();
  private static final Queue<Session> SESSIONS = new ConcurrentLinkedQueue<>();

  // Caching
  private List<Match> matches = Collections.synchronizedList(new ArrayList<>());
  private Map<UUID, Match> matchIdToClass =
      Collections.synchronizedMap(new HashMap<>());
  private Map<UUID, Session> playerToSession =
      Collections.synchronizedMap(new HashMap<>());
  private Map<Session, UUID> sessionToPlayer =
      Collections.synchronizedMap(new HashMap<>());
  private Map<UUID, UUID> playerToGame =
      Collections.synchronizedMap(new HashMap<>());

  /**
   * Connect a player and update their match list.
   *
   * @param session Player session.
   */
  @OnWebSocketConnect
  public synchronized void connected(Session session) {

    try {
      // Add session to queue, generate id,
      // and give that id to the new session
      SESSIONS.add(session);

      UUID playerId = UUID.randomUUID();

      JsonObject jj = new JsonObject();
      jj.addProperty("type", RiskMessageType.CONNECT.ordinal());
      jj.addProperty("id", playerId.toString());
      playerToSession.put(playerId, session);
      sessionToPlayer.put(session, playerId);
      session.getRemote().sendString(jj.toString());

      // Update this session with any pre-existing lobbies
      for (Match game : matches) {
        if (!game.started()) {
          JsonObject update = new JsonObject();
          update.addProperty("type", RiskMessageType.CREATE.ordinal());
          update.addProperty("gameId", game.getId());
          update.addProperty("matchName", game.matchName());
          update.addProperty("playerNum", game.playerNum());
          update.addProperty("lobbySize", game.lobbySize());
          session.getRemote().sendString(update.toString());
        }
      }
    } catch (IOException ex) {
      System.out
          .println("ERROR: IOexception caught" + " while connecting session.");
    }
  }

  /**
   * Handle a player disconnect or reload.
   *
   * @param session The disconnecting player.
   * @param statusCode Exit code.
   * @param reason Exit reason.
   */
  @OnWebSocketClose
  public synchronized void closed(Session session, int statusCode, String reason) {
    // Update the lobbies and remove
    // this player from our list
    removePlayer(session);
    SESSIONS.remove(session);
  }

  /**
   * Handle lobby and game related messages.
   *
   * @param session Player who is sending a message.
   * @param message Stringified JsonObject with information on the player and
   *          what they want to do.
   * @throws IOException When there is an error sending a response message to
   *           players.
   */
  @OnWebSocketMessage
  public synchronized void message(Session session, String message) throws IOException {

    try {
      // Get received message
      JsonObject received = GSON.fromJson(message, JsonObject.class);

      // If this message is a request to join a lobby...
      if (received.get("type").getAsInt() == RiskMessageType.JOIN.ordinal()) {
        joinPlayer(session, message);
      }

      // If this message is a request to create a lobby...
      if (received.get("type").getAsInt() == RiskMessageType.CREATE.ordinal()) {
        createLobby(session, message);
      }

      // Handle a chat message
      if (received.get("type").getAsInt() == RiskMessageType.MESSAGE
          .ordinal()) {

        String idString = received.get("playerId").getAsString();
        UUID playerUUID = UUID.fromString(idString);
        Match game = matchIdToClass.get(playerToGame.get(playerUUID));
        JsonObject chat = new JsonObject();
        chat.addProperty("type", RiskMessageType.MESSAGE.ordinal());
        chat.addProperty("playerId", playerUUID.toString());
        chat.addProperty("message", received.get("message").getAsString());
        for (int index = 0; index < game.playerNum(); index++) {
          Session playerSession = playerToSession.get(game.getPlayerId(index));
          if (playerSession.isOpen()) {
            playerSession.getRemote().sendString(chat.toString());
          }
        }
      }

      // If this message is a risk move...
      if (received.get("type").getAsInt() == RiskMessageType.MOVE.ordinal()) {
        // Verify and enact that move on the back end
        String idString = received.get("playerId").getAsString();
        UUID playerUUID = UUID.fromString(idString);
        Match game = matchIdToClass.get(playerToGame.get(playerUUID));
        List<JsonObject> response = game.getUpdate(received);

        boolean destroy = false;
        // Send the response messages to all players in that match
        for (int index = 0; index < response.size(); index++) {
          List<UUID> playerList = game.getPlayers();
          for (int looper = 0; looper < game.playerNum(); looper++) {
            UUID toAlert = playerList.get(looper);
            playerToSession.get(toAlert).getRemote()
                .sendString(response.get(index).toString());
          }
          if (response.get(index).get("type")
              .getAsInt() == RiskMessageType.WINNER.ordinal()) {
            destroy = true;
          }
        }
        if (destroy) {
          List<UUID> playerList = game.getPlayers();
          for (int looper = 0; looper < game.playerNum(); looper++) {
            UUID leavingGame = playerList.get(looper);
            playerToGame.remove(leavingGame);
            sessionToPlayer.remove(playerToSession.get(leavingGame));
            playerToSession.get(leavingGame).close();
            SESSIONS.remove(playerToSession.get(leavingGame));
            playerToSession.remove(leavingGame);
          }
          matchIdToClass.remove(UUID.fromString(game.getId()));
          matches.remove(game);
        }
      }

      if (received.get("type").getAsInt() == RiskMessageType.PING.ordinal()) {
        JsonObject response = new JsonObject();
        UUID playerId = UUID.fromString(received.get("playerId").getAsString());
        response.addProperty("type", RiskMessageType.PING.ordinal());
        Session playerSess = playerToSession.get(playerId);
        playerSess.getRemote().sendString(response.toString());
      }
    } catch (IOException ex) {
      System.out.println(
          "ERROR: IOexception caught" + " while responding to message.");
    }
  }

  /**
   * Add a player to a game lobby.
   *
   * @param session The player joining a lobby.
   * @param message Stringified JsonObject with info on the player and the lobby
   *          being joined.
   */
  private synchronized void joinPlayer(Session session, String message) {

    try {
      // Get received message
      JsonObject received = GSON.fromJson(message, JsonObject.class);

      // Get the id of the player,
      // and the id of the match they want to join
      UUID gameUUID = UUID.fromString(received.get("gameId").getAsString());
      UUID playerUUID = UUID.fromString(received.get("playerId").getAsString());
      String playerName = received.get("playerName").getAsString();

      // If the player asked to rejoin a lobby, do nothing
      if (playerToGame.get(playerUUID) != null
          && playerToGame.get(playerUUID).equals(gameUUID)) {

        // Remove them from the match
        Match game = matchIdToClass.get(playerToGame.get(playerUUID));
        game.removePlayer(playerUUID);
        JsonObject remove = new JsonObject();
        remove.addProperty("type", RiskMessageType.REMOVE.ordinal());
        remove.addProperty("gameId", game.getId());
        remove.addProperty("playerNum", game.playerNum());
        remove.addProperty("lobbySize", game.lobbySize());
        remove.addProperty("matchName", game.matchName());
        for (Session player : SESSIONS) {
          if (player.isOpen()) {
            player.getRemote().sendString(remove.toString());
          }
        }
        playerToGame.put(playerUUID, null);
        return;
      }

      // Get the match that the player asked to join,
      // and add this player to it.
      Match toAdd = matchIdToClass.get(gameUUID);
      toAdd.addPlayer(playerUUID, playerName);

      Match toChange = null;

      // If this player has selected a lobby for the first time,
      // place him in this lobby, and do nothing else
      if (playerToGame.get(playerUUID) == null) {
        playerToGame.put(playerUUID, gameUUID);
        toChange = matchIdToClass.get(playerToGame.get(playerUUID));

        // If this player was in another lobby, remove him from it
      } else {
        toChange = matchIdToClass.get(playerToGame.get(playerUUID));
        toChange.removePlayer(playerUUID);
      }

      // Send out a message updating the menu
      // with this new lobby change
      JsonObject change = new JsonObject();
      change.addProperty("type", RiskMessageType.CHANGE.ordinal());
      change.addProperty("oldGameId", toChange.getId());
      change.addProperty("oldMatchName", toChange.matchName());
      change.addProperty("oldPlayerNum", toChange.playerNum());
      change.addProperty("oldLobbySize", toChange.lobbySize());
      change.addProperty("newGameId", toAdd.getId());
      change.addProperty("newMatchName", toAdd.matchName());
      change.addProperty("newPlayerNum", toAdd.playerNum());
      change.addProperty("newLobbySize", toAdd.lobbySize());

      // Send that message to all players
      for (Session player : SESSIONS) {
        if (player.isOpen()) {
          player.getRemote().sendString(change.toString());
        }
      }

      // Update the lobby map
      playerToGame.put(playerUUID, gameUUID);

      // If the game is now ready to begin, start it
      if (toAdd.lobbySize() == toAdd.playerNum()) {
        startGame(toAdd);
      }
    } catch (IOException ex) {
      System.out.println(
          "ERROR: IOexception caught" + " while placing player in lobby.");
    }
  }

  /**
   * Starts the match that a given player is in.
   *
   * @param match - game match.
   */
  private synchronized void startGame(Match toStart) {

    try {
      List<JsonObject> initials = toStart.start();

      // Add this match's info to an update message
      JsonObject update = new JsonObject();
      update.addProperty("type", RiskMessageType.START.ordinal());
      update.addProperty("gameId", toStart.getId());
      for (int index = 0; index < toStart.playerNum(); index++) {
        update.addProperty("player" + index + "name",
            toStart.getPlayerName(index));
        update.addProperty("player" + index + "id",
            toStart.getPlayerId(index).toString());
      }
      update.addProperty("playerNum", toStart.playerNum());

      // Update all players in this match, displaying the risk map
      // and hiding the lobby menu
      List<UUID> players = toStart.getPlayers();
      for (int index = 0; index < players.size(); index++) {
        playerToSession.get(players.get(index)).getRemote()
            .sendString(update.toString());
      }

      // Make a new update to remove this lobby from the menu,
      // so no one else can join it
      update = new JsonObject();
      update.addProperty("type", RiskMessageType.DESTROY.ordinal());
      update.addProperty("gameId", toStart.getId());

      // Send this message to all players
      for (Session player : SESSIONS) {
        if (player.isOpen()) {
          player.getRemote().sendString(update.toString());
        }
      }

      for (int index = 0; index < initials.size(); index++) {
        List<UUID> playerList = toStart.getPlayers();
        for (int looper = 0; looper < toStart.playerNum(); looper++) {
          UUID toAlert = playerList.get(looper);
          playerToSession.get(toAlert).getRemote()
              .sendString(initials.get(index).toString());
        }
      }
    } catch (IOException ex) {
      System.out
          .println("ERROR: IOexception caught" + " while starting a game.");
    }
  }

  /**
   * Create a new lobby on the menu.
   *
   * @param session Player who requested a new lobby.
   * @param message Stringified JsonObject about the lobby the player wanted to
   *          make.
   */
  private synchronized void createLobby(Session session, String message) {

    try {
      // Get received message
      JsonObject received = GSON.fromJson(message, JsonObject.class);

      // Get the id, lobby size, and match name
      // that the player sent us
      UUID gameId = UUID.fromString(received.get("gameId").getAsString());
      Integer lobbySize = received.get("lobbySize").getAsInt();
      String matchName = received.get("matchName").getAsString();

      // Create and log a match with this info
      Match game = new Match(gameId, lobbySize, matchName);
      matchIdToClass.put(gameId, game);
      matches.add(game);

      // Update the menu of all players
      // with the existence of this lobby
      JsonObject update = new JsonObject();
      update.addProperty("type", RiskMessageType.CREATE.ordinal());
      update.addProperty("gameId", gameId.toString());
      update.addProperty("playerNum", 0);
      update.addProperty("lobbySize", lobbySize);
      update.addProperty("matchName", matchName);

      for (Session player : SESSIONS) {
        if (player.isOpen()) {
          player.getRemote().sendString(update.toString());
        }
      }
    } catch (IOException ex) {
      System.out
          .println("ERROR: IOexception caught" + " while creating lobby.");
    }
  }

  /**
   * Remove a player from any lobbies they are in.
   *
   * @param session Player who has disconnected.
   */
  private synchronized void removePlayer(Session session) {

    try {
      // If this player was in a lobby...
      UUID playerId = sessionToPlayer.get(session);
      if (playerToGame.get(playerId) != null) {

        // Remove them from the match
        Match game = matchIdToClass.get(playerToGame.get(playerId));
        game.removePlayer(playerId);

        if (!game.started()) {

          // Update the lobby menu for all remaining players
          JsonObject remove = new JsonObject();
          remove.addProperty("type", RiskMessageType.REMOVE.ordinal());
          remove.addProperty("gameId", game.getId());
          remove.addProperty("playerNum", game.playerNum());
          remove.addProperty("lobbySize", game.lobbySize());
          remove.addProperty("matchName", game.matchName());
          for (Session player : SESSIONS) {
            if (player != session) {
              player.getRemote().sendString(remove.toString());
            }
          }
        } else {
          List<UUID> remaining = game.getPlayers();
          JsonObject leaver = new JsonObject();
          leaver.addProperty("type", RiskMessageType.LEAVER.ordinal());
          for (UUID player : remaining) {
            Session currSession = playerToSession.get(player);
            if (currSession.isOpen() && currSession != session) {
              currSession.getRemote().sendString(leaver.toString());
            }
          }
        }

        // Remove this player from the match cache
        playerToGame.remove(playerId);
      }

      // Remove this player from the general cache
      playerToSession.remove(playerId);
      sessionToPlayer.remove(session);
    } catch (IOException ex) {
      System.out
          .println("ERROR: IOexception caught" + " while removing player.");
    }
  }
}
