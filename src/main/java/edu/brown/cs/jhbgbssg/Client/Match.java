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

import edu.brown.cs.jhbgbssg.Game.risk.GameUpdate;
import edu.brown.cs.jhbgbssg.Game.risk.MessageAPI;
import edu.brown.cs.jhbgbssg.Game.risk.Referee;
import edu.brown.cs.jhbgbssg.Game.risk.RiskActionProcessor;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
//import edu.brown.cs.jhbgbssg.Game.risk.RiskGame;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.DefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssh.tuple.Pair;

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
  private UUID winner;

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
//    if (started) {
//      myGame.removePlayer(playerId);
//      players = myGame.getPlayerOrder();
//
//      // Otherwise, edit our list
//    } else {
      players.remove(playerId);
//    }

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
      riskPlayers.put(playerId, new RiskPlayer(playerId));
    }
    board = new RiskBoard();
    referee = new Referee(board, riskPlayers.values());
    actionProcessor = new RiskActionProcessor(referee);
    players = referee.getPlayerOrder();
//    GameUpdate initial = myGame.startGame();
//    return messageApi.getJsonObjectMessage(initial);
  }

  public JsonObject getUpdate(JsonObject received) {
    // get oject type and construct object.
    // run action processor
    // convert message to json
    // return message list
    //SetupAction move = (SetupAction) messageApi.jsonToMove(received.toString());
    // GameUpdate update = myGame.executeSetupChoiceAction(move);
    // return messageApi.getJsonObjectMessage(update);
    return null;
  }

  private ReinforceAction createReinforceAction(JsonObject received) {
    Map<TerritoryEnum, Integer> reinforced = messageApi
        .getNumberReinforced(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    return new ReinforceAction(player, board, reinforced);
  }

  private CardTurnInAction createCardAction(JsonObject received) {
    Map<TerritoryEnum, Integer> reinforced = messageApi
        .getNumberReinforced(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int card = messageApi.getCardTurnedIn(received);
    return new CardTurnInAction(player, board, card, reinforced);
  }

  private SetupAction createSetupAction(JsonObject received) {
    TerritoryEnum selected = messageApi.getSelectedTerritory(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    return new SetupAction(player, board, selected);
  }

  private AttackAction createAttackAction(JsonObject received) {
    Pair<TerritoryEnum, TerritoryEnum> attackPair = messageApi
        .getAttackingDefendingTerritory(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int numberDie = messageApi.getNumberDieToRoll(received);
    return new AttackAction(player, attackPair.getFirstElement(),
        attackPair.getSecondElement(), numberDie);
  }

  private DefendAction createDefendAction(JsonObject received) {
    Pair<TerritoryEnum, TerritoryEnum> attackPair = messageApi
        .getAttackingDefendingTerritory(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int numberDie = messageApi.getNumberDieToRoll(received);
    return new DefendAction(player, board, referee.getCurrentAttack(),
        numberDie);
  }

  private ClaimTerritoryAction createClaimTerritoryAction(JsonObject received) {
    Pair<TerritoryEnum, TerritoryEnum> attackPair = messageApi
        .getAttackClaimingTerritory(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int numberTroops = messageApi.getNumberTroopsToMove(received);
    return new ClaimTerritoryAction(player, board, attackPair.getFirstElement(),
        attackPair.getSecondElement(), numberTroops);
  }

  private MoveTroopsAction createMoveTroopsAction(JsonObject received) {
    Pair<TerritoryEnum, TerritoryEnum> movePair = messageApi
        .getMoveTroopsTerritories(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int numberTroops = messageApi.getNumberTroopsToMove(received);
    return new MoveTroopsAction(player, board, movePair.getFirstElement(),
        movePair.getSecondElement(), numberTroops);
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
