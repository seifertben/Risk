package edu.brown.cs.jhbgbssg.Client;

// Import resources
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import com.google.gson.JsonObject;

import edu.brown.cs.jhbgbssg.Game.GameUpdate;
import edu.brown.cs.jhbgbssg.Game.risk.Referee;
import edu.brown.cs.jhbgbssg.Game.risk.RiskActionProcessor;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMessageAPI;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.DefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupReinforceAction;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.tuple.Pair;

/**
 * Handles players and game updates for an individual match. Acts as a proxy for
 * the actual Risk Game.
 *
 * @author bgabinet
 */
public class Match {

  // Match id, list of player ids and names,
  // started boolean, match name, lobby size,
  // and actual risk game
  private final UUID id;
  private List<UUID> players = Collections.synchronizedList(new ArrayList<>());
  private Map<UUID, String> names =
      Collections.synchronizedMap(new HashMap<>());
  private boolean started = false;
  private final String matchName;
  private final Integer lobbySize;
  private RiskMessageAPI messageApi = new RiskMessageAPI();
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
    synchronized (this) {
      if (players.size() < lobbySize
          && !players.contains(playerId) && !started) {
        players.add(playerId);
        names.put(playerId, name);
      }
    }
  }

  /**
   * Removes a player from the match.
   *
   * @param playerId Player to remove.
   */
  public void removePlayer(UUID playerId) {
    synchronized (this) {
      if (started) {
        referee.removePlayer(playerId);
        players = referee.getPlayerOrder();
      } else {
        players.remove(playerId);
      }
      names.put(playerId, null);
    }
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
    synchronized (this) {
      return started;
    }
  }

  /**
   * Initiates the risk match.
   *
   * @return list of json objects representing messages to send to the players
   */
  public List<JsonObject> start() {
    synchronized (this) {
      started = true;
      Set<UUID> idSet = Collections.synchronizedSet(new TreeSet<>(players));
      riskPlayers = new HashMap<>();
      for (UUID playerId : idSet) {
        riskPlayers.put(playerId, new RiskPlayer(playerId));
      }
      board = new RiskBoard();
      Set<RiskPlayer> playerSet = new HashSet<>(riskPlayers.values());
      referee = new Referee(board, playerSet);
      actionProcessor = new RiskActionProcessor(referee);
      players = referee.getPlayerOrder();
      GameUpdate initial = referee.startGame();
      return messageApi.getUpdateMessages(initial);
    }
  }

  /**
   * This method takes in a jsono bject representing a player action during the
   * game. The action is then translated, validated and executed (if valid). The
   * method returns a list of json objects representing messages to send to the
   * game players, such as the action that was undertaken and the next valid
   * action for a player whose turn it is. Any information the game players need
   * are sent by this method in the list of json object
   *
   * @param received - json objec representing a risk action
   * @return messages to send to the players
   */
  public List<JsonObject> getUpdate(JsonObject received) {
    synchronized (this) {
      try {
        MoveType type = messageApi.getMoveType(received);
        GameUpdate update = null;
        switch (type) {
          case SETUP:
            SetupAction setup = this.createSetupAction(received);
            update = actionProcessor.processSetupAction(setup);
            break;
          case SETUP_REINFORCE:
            SetupReinforceAction setupReinforce =
                this.createSetupReinforceAction(received);
            update =
                actionProcessor.processSetupReinforceAction(setupReinforce);
            break;
          case REINFORCE:
            ReinforceAction reinforce = this.createReinforceAction(received);
            update = actionProcessor.processReinforceAction(reinforce);
            break;
          case TURN_IN_CARD:
            CardTurnInAction cardAction = this.createCardAction(received);
            update = actionProcessor.processCardTurnInAction(cardAction);
            break;
          case CHOOSE_ATTACK_DIE:
            AttackAction attack = this.createAttackAction(received);
            update = actionProcessor.processAttackAction(attack);
            break;
          case CHOOSE_DEFEND_DIE:
            DefendAction defend = this.createDefendAction(received);
            update = actionProcessor.processDefendAction(defend);
            break;
          case CLAIM_TERRITORY:
            ClaimTerritoryAction claim =
                this.createClaimTerritoryAction(received);
            update = actionProcessor.processClaimTerritoryAction(claim);
            break;
          case SKIP:
            RiskPlayer player = this.getSkipPlayer(received);
            update = actionProcessor.processSkipAction(player);
            break;
          default:
            MoveTroopsAction moveAction = this.createMoveTroopsAction(received);
            update = actionProcessor.processMoveTroopsAction(moveAction);
            break;
        }
        List<JsonObject> messages = messageApi.getUpdateMessages(update);
        messages.addAll(
            messageApi.getPlayerInformation(riskPlayers.values(), board));
        return messages;
      } catch (IllegalArgumentException e) {
        GameUpdate update = new GameUpdate();
        if (referee.getWinner() != null) {
          update.setWonGame(referee.getWinner().getPlayerId());
        } else {
          update.setValidMoves(referee.getValidMove(), null);
        }
        return messageApi.getUpdateMessages(update);
      }
    }
  }

  private ReinforceAction createReinforceAction(JsonObject received) {
    Map<TerritoryEnum, Integer> reinforced =
        messageApi.getNumberReinforced(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    return new ReinforceAction(player, board, reinforced);
  }

  private CardTurnInAction createCardAction(JsonObject received) {
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    List<Integer> cards = messageApi.getCardTurnedIn(received);
    return new CardTurnInAction(cards, player);
  }

  private SetupAction createSetupAction(JsonObject received) {
    TerritoryEnum selected = messageApi.getSelectedTerritory(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    return new SetupAction(player, board, selected);
  }

  private SetupReinforceAction createSetupReinforceAction(JsonObject received) {
    TerritoryEnum selected = messageApi.getSelectedTerritory(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    return new SetupReinforceAction(player, board, selected);
  }

  private AttackAction createAttackAction(JsonObject received) {
    Pair<TerritoryEnum, TerritoryEnum> attackPair =
        messageApi.getAttackingDefendingTerritory(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int numberDie = messageApi.getNumberDieToRoll(received);
    return new AttackAction(player, attackPair.getFirstElement(),
        attackPair.getSecondElement(), numberDie);
  }

  private DefendAction createDefendAction(JsonObject received) {
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int numberDie = messageApi.getNumberDieToRoll(received);
    return new DefendAction(player, board, referee.getCurrentAttack(),
        numberDie);
  }

  private ClaimTerritoryAction createClaimTerritoryAction(JsonObject received) {
    Pair<TerritoryEnum, TerritoryEnum> attackPair =
        messageApi.getAttackClaimingTerritory(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int numberTroops = messageApi.getNumberTroopsToMove(received);
    return new ClaimTerritoryAction(player, board, attackPair.getFirstElement(),
        attackPair.getSecondElement(), numberTroops);
  }

  private MoveTroopsAction createMoveTroopsAction(JsonObject received) {
    Pair<TerritoryEnum, TerritoryEnum> movePair =
        messageApi.getMoveTroopsTerritories(received);
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    int numberTroops = messageApi.getNumberTroopsToMove(received);
    return new MoveTroopsAction(player, board, movePair.getFirstElement(),
        movePair.getSecondElement(), numberTroops);
  }

  private RiskPlayer getSkipPlayer(JsonObject received) {
    UUID playerId = messageApi.getPlayerId(received);
    RiskPlayer player = riskPlayers.get(playerId);
    return player;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Match)) {
      return false;
    }
    Match objMatch = (Match) obj;
    return id.toString().equals(objMatch.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
