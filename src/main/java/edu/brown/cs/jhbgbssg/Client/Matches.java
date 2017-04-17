package edu.brown.cs.jhbgbssg.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;

@WebSocket
public class Matches {

  private static final Gson GSON = new Gson();
  private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();
  private List<Match> matches = Collections.synchronizedList(new ArrayList<>());
  private Map<UUID, Match> matchIdToClass = Collections.synchronizedMap(new HashMap<>());
  private Map<UUID, Session> playerToSession = Collections.synchronizedMap(new HashMap<>());
  private Map<Session, UUID> sessionToPlayer = Collections.synchronizedMap(new HashMap<>());
  private Map<UUID, UUID> playerToGame = Collections.synchronizedMap(new HashMap<>());
  private Set<UUID> inGame = Collections.synchronizedSet(new TreeSet<>());

  private static enum MESSAGE_TYPE {
    CONNECT,
    UPDATE,
    START,
    JOIN,
    ADD,
    CREATE,
    CHECK,
    CHANGE
  }

  /**
   * Connect a player and update their match list.
   * @param session Player session.
   * @throws IOException When there is a connection error.
   */
  @OnWebSocketConnect
  public void connected(Session session) throws IOException {
    sessions.add(session);
    UUID playerId = UUID.randomUUID();
    JsonObject jj = new JsonObject();
    jj.addProperty("type", MESSAGE_TYPE.CONNECT.ordinal());
    jj.addProperty("id", playerId.toString());
    playerToSession.put(playerId, session);
    sessionToPlayer.put(session, playerId);
    session.getRemote().sendString(jj.toString());

    for (Match game : matches) {

      JsonObject update = new JsonObject();
      update.addProperty("type", MESSAGE_TYPE.CREATE.ordinal());
      update.addProperty("gameId", game.getId());
      update.addProperty("playerNum", game.playerNum());

      JsonObject players = new JsonObject();
      List<UUID> playerIds = game.getPlayers();
      for (int index = 0; index < game.playerNum(); index++) {
        String prop = "player" + index;
        String id = playerIds.get(index).toString();
        players.addProperty(prop, id);
      }

      update.add("playerList", players);
      session.getRemote().sendString(update.toString());
    }
  }

  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
    // REMOVE PLAYER FROM ALL CACHING, UPDATE BACK END
    sessions.remove(session);
  }

  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
    // FIND OUT WHY MESSAGES ARE STILL BEING SENT TO PLAYERS WHO ARE IN GAME
    // Get received message
    JsonObject received = GSON.fromJson(message, JsonObject.class);

    // If this message is a request to join a lobby...
    if (received.get("type").getAsInt() == MESSAGE_TYPE.JOIN.ordinal()) {
      join_player(session, message);
    }

    if (received.get("type").getAsInt() == MESSAGE_TYPE.CREATE.ordinal()) {
      create_lobby(session, message);
    }

    if (received.get("type").getAsInt() == MESSAGE_TYPE.START.ordinal()) {
      start_game(session, message);
    }
  }

  private void join_player(Session session, String message) throws IOException {

    // Get received message
    JsonObject received = GSON.fromJson(message, JsonObject.class);

    // Update match
    UUID idAsUUID = UUID.fromString(received.get("gameId").getAsString());
    Match toAdd = matchIdToClass.get(idAsUUID);
    toAdd.addPlayer(UUID.fromString(received.get("playerId").getAsString()));

    // Setup response message
    JsonObject update = new JsonObject();
    update.addProperty("type", MESSAGE_TYPE.ADD.ordinal());
    update.addProperty("playerId", received.get("playerId").getAsString());
    update.addProperty("gameId", received.get("gameId").getAsString());
    update.addProperty("playerNum", toAdd.playerNum());

    // Make sure this player is listed in their requested
    // lobby, and is not present in two lobbies at once
    if (playerToGame.get(received.get("playerId").getAsString()) == null) {
      playerToGame.put(UUID.fromString(received.get("playerId").getAsString()),
          UUID.fromString(received.get("gameId").getAsString()));
    } else {
      if (!playerToGame.get(received.get("playerId").getAsString()).toString().equals(received.get("gameId").getAsString())) {
        JsonObject change = new JsonObject();
        change.addProperty("type", MESSAGE_TYPE.CHANGE.ordinal());
        change.addProperty("playerId", received.get("playerId").getAsString());
        change.addProperty("oldGameId", playerToGame.get(received.get("playerId").getAsString()).toString());
        for (Session player : sessions) {
          if (!inGame.contains(sessionToPlayer.get(player))) {
            player.getRemote().sendString(change.toString());
          }
        }
      }
    }

    // Update all players of this lobby change
    for (Session player : sessions) {
      if (!inGame.contains(sessionToPlayer.get(player))) {
        player.getRemote().sendString(update.toString());
      }
    }

    JsonObject checkStart = new JsonObject();
    checkStart.addProperty("type", MESSAGE_TYPE.CHECK.ordinal());
    checkStart.addProperty("gameId", idAsUUID.toString());
    session.getRemote().sendString(checkStart.toString());
  }

  private void start_game(Session session, String message) throws IOException {

    // Get received message
    JsonObject received = GSON.fromJson(message, JsonObject.class);

    JsonObject update = new JsonObject();
    update.addProperty("type", MESSAGE_TYPE.START.ordinal());
    update.addProperty("gameId", received.get("gameId").getAsString());
    // SEND MESSAGE TO REMOVE GAME LOBBY
    // UPDATE PLAYERS IN GAME ID
    UUID idAsUUID = UUID.fromString(received.get("gameId").getAsString());
    Match toStart = matchIdToClass.get(idAsUUID);
    List<UUID> players = toStart.getPlayers();
    for (int index = 0; index < players.size(); index++) {
      inGame.add(players.get(index));
      playerToSession.get(players.get(index)).getRemote().sendString(update.toString());
    }
  }

  private void create_lobby(Session session, String message) throws IOException {

    // Get received message
    JsonObject received = GSON.fromJson(message, JsonObject.class);

    JsonObject update = new JsonObject();
    UUID gameId = UUID.fromString(received.get("gameId").getAsString());
    Match game = new Match(gameId);
    matchIdToClass.put(gameId, game);
    matches.add(game);
    update.addProperty("type", MESSAGE_TYPE.CREATE.ordinal());   
    update.addProperty("gameId", received.get("gameId").getAsString());
    update.addProperty("playerNum", game.playerNum());
    JsonObject players = new JsonObject();
    update.add("playerList", players);
    for (Session player : sessions) {
      if (!inGame.contains(sessionToPlayer.get(player))) {
        player.getRemote().sendString(update.toString());
      }
    }
  }
}
