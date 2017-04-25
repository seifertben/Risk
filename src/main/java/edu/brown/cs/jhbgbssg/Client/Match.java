package edu.brown.cs.jhbgbssg.Client;

// Import resources
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import com.google.gson.JsonObject;

import edu.brown.cs.jhbgbssg.Game.risk.MessageAPI;
import edu.brown.cs.jhbgbssg.Game.risk.Referee;
import edu.brown.cs.jhbgbssg.Game.risk.RiskActionProcessor;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 * Handles players and game updates for an individual match. Acts as a proxy for
 * the actual Risk Game.
 * 
 * @author user
 */
public class Match {

  // Match id, list of player ids and names,
  // started boolean, match name, lobby size,
  // and actual risk game
  private final UUID id;
  private List<UUID> players = Collections.synchronizedList(new ArrayList<>());
  private Map<UUID, String> names = Collections
      .synchronizedMap(new HashMap<>());
  private boolean started = false;
  private final String matchName;
  private final Integer lobbySize;
  private MessageAPI messageApi = new MessageAPI();

  private RiskBoard board;
  private RiskActionProcessor actionProcessor;
  private Referee referee;
  private Map<UUID, RiskPlayer> riskPlayers;

  /**
   * Create a match.
   *
   * @param uid This match's unique id.
   * @param max This match's max player number.
   * @param title This match's name.
   */
  public Match(UUID uid, Integer max, String title) {
    id = uid;
    lobbySize = max;
    matchName = title;
  }

  /**
   * Match id getter.
   *
   * @return This match's id, as a string.
   */
  public String getId() {
    return id.toString();
  }

  /**
   * Max player number getter.
   *
   * @return Maximum number of players for this match.
   */
  public Integer lobbySize() {
    return lobbySize;
  }

  /**
   * Match title getter.
   *
   * @return This match's name.
   */
  public String matchName() {
    return matchName;
  }

  /**
   * Current lobby population getter.
   *
   * @return The number of players currently in this lobby.
   */
  public int playerNum() {
    return players.size();
  }

  /**
   * Adds players to the lobby, as long as the match has no started.
   *
   * @param playerId Player id to add.
   * @param name Player name.
   */
  public void addPlayer(UUID playerId, String name) {
    if (players.size() < lobbySize && !players.contains(playerId) && !started) {
      players.add(playerId);
      names.put(playerId, name);
    }
  }

  /**
   * Removes a player from the match.
   *
   * @param playerId Player to remove.
   */
  public void removePlayer(UUID playerId) {
    // If the match has started, update back end
    if (started) {
      myGame.removePlayer(playerId);
      players = myGame.getPlayerOrder();

      // Otherwise, edit our list
    } else {
      players.remove(playerId);
    }

    // Remove this player's name
    names.put(playerId, null);
  }

  /**
   * Get players in this match.
   *
   * @return A list of players in this match.
   */
  public List<UUID> getPlayers() {
    return new ArrayList<UUID>(players);
  }

  /**
   * Request the name of a given player. An index must be given since player
   * order will eventually be randomized.
   *
   * @param index Index of the player whose name we want.
   * @return Name of the requested player as a string.
   */
  public String getPlayerName(Integer index) {
    UUID playerId = players.get(index);
    return names.get(playerId);
  }

  /**
   * Request the id of a given player. An index must be given since player order
   * will eventually be randomized.
   *
   * @param index Index of the player whose id we want.
   * @return Id of the requested player as a string.
   */
  public UUID getPlayerId(Integer index) {
    return players.get(index);
  }

  /**
   * Returns whether or not the match has started.
   *
   * @return True if the match has begun, false otherwise.
   */
  public boolean started() {
    return started;
  }

  /**
   * Initiate this match and create our risk game.
   */
  public void start() {
    started = true;
    Set<UUID> idSet = Collections.synchronizedSet(new TreeSet<>(players));
    riskPlayers = new HashMap<>();
    for (UUID playerId : idSet) {
      riskPlayers.put(id, new RiskPlayer(playerId));
    }
    referee = new Referee(board, riskPlayers.values());
    actionProcessor = new RiskActionProcessor(referee);
    players = referee.getPlayerOrder();
  }

  public JsonObject getUpdate(JsonObject received) {
    SetupMove move = (SetupMove) messageApi.jsonToMove(received.toString());

    // GameUpdate update = myGame.executeSetupChoiceAction(move);
    // return messageApi.getJsonObjectMessage(update);
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    Match objMatch = (Match) obj;
    return id.toString().equals(objMatch.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
