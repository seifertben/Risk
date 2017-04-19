package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.AttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.CardTurnInMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.DefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.GameUpdate;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.MoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ReinforceMove;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Stores the state of the game.
 *
 * @author Ben
 *
 */
public class RiskGame {

  private RiskBoard gameBoard;
  private Turn turnState;
  private List<RiskPlayer> players;
  private Map<UUID, RiskPlayer> idToPlayer;
  private Referee referee;
  private AttackMove attack;
  private Die die;
  private Comparator<Integer> dieComparator = Collections.reverseOrder();
  private int numberOfPlayers;
  private boolean handOutCard = false;

  /**
   * Initializes the game state.
   *
   * @param numPlayers the number of players.
   * @param ids the player ids.
   */
  public RiskGame(int numPlayers, Set<UUID> ids) {
    gameBoard = new RiskBoard();
    turnState = new Turn();
    // Create the RiskPlayers.
    for (UUID i : ids) {
      RiskPlayer player = new RiskPlayer(i);
      players.add(player);
      idToPlayer.put(i, player);
    }
    // Shuffle the players to see who goes first.
    Collections.shuffle(players);
    // Assign the turnState to the first player.
    turnState.setTurnId(players.get(0).getPlayerId());
    die = new Die();
    this.numberOfPlayers = numPlayers;
  }

  public List<UUID> getPlayerOrder() {
    List<UUID> order = new ArrayList<>();
    for (int i = 0; i < numberOfPlayers; i++) {
      order.add(players.get(i).getPlayerId());
    }
    return Collections.unmodifiableList(order);
  }

  public GameUpdate startGame() {
    // set initial restrictions called

    return null;
  }

  public GameUpdate executeReinforceAction(UUID playerId,
      Map<TerritoryEnum, Integer> reinforce) {
    ReinforceMove move = new ReinforceMove(playerId, reinforce);
    boolean validMove = referee.validateReinforce(move);
    if (validMove) {
      for (Entry<TerritoryEnum, Integer> entry : reinforce.entrySet()) {
        Territory terr = gameBoard.getTerritory(entry.getKey());
        terr.addTroops(entry.getValue());
      }
      // execute reinforce
      // need to check next phase
      turnState.changePhase(MoveType.CHOOSE_ATTACK_DIE);
      GameUpdate update = referee.setRestrictions();
      return update;
    } else {
      GameUpdate update = referee.setRestrictions();
      return update;
    }
  }

  public GameUpdate executeCardTurnInAction(UUID playerId, int card,
      Map<TerritoryEnum, Integer> troopsAdded) {
    CardTurnInMove move = new CardTurnInMove(playerId, card, troopsAdded);
    boolean validMove = referee.validateCardTurnIn(move);
    if (validMove) {
      // need to validate next turn is real
      RiskPlayer player = idToPlayer.get(move.getMovePlayer());
      player.removeCard(move.getCard());
      for (Entry<TerritoryEnum, Integer> entry : troopsAdded.entrySet()) {
        Territory terr = gameBoard.getTerritory(entry.getKey());
        terr.addTroops(entry.getValue());
      }
      // determine attack is possible
      turnState.changePhase(MoveType.CHOOSE_ATTACK_DIE);
      GameUpdate update = referee.setRestrictions();
      return update;
    }
    GameUpdate update = referee.setRestrictions();
    return update;
  }

  public GameUpdate executeAttackAction(UUID playerId, TerritoryEnum fromTerr,
      TerritoryEnum toTerr, int numberDie) {
    attack = new AttackMove(playerId, fromTerr, toTerr, numberDie);
    boolean validMove = referee.validateAttackMove(attack);
    if (validMove) {
      // need to store state
      turnState.changePhase(MoveType.CHOOSE_DEFEND_DIE);
      GameUpdate update = referee.setRestrictions();
      return update;
    } else {
      attack = null;
      GameUpdate update = referee.setRestrictions();
      return update;
    }
  }

  public GameUpdate executeDefendAction(UUID playerId, TerritoryEnum attacker,
      TerritoryEnum defend, int numberDie) {
    DefendMove move = new DefendMove(playerId, attacker, defend, numberDie);
    boolean validMove = referee.validateDefendMove(move);
    if (validMove) {
      boolean claim = this.attack(move);
      if (claim) {
        turnState.changePhase(MoveType.CLAIM_TERRITORY);
      } else {
        // determine what the next valid turn phase is is
      }
      GameUpdate update = referee.setRestrictions();
      return update;
    } else {
      GameUpdate update = referee.setRestrictions();
      return update;
    }
  }

  public GameUpdate executeClaimTerritory(UUID playerId, TerritoryEnum from,
      TerritoryEnum to, int number) {
    ClaimTerritoryMove move = new ClaimTerritoryMove(playerId, from, to,
        number);
    boolean validMove = referee.validateClaimTerritory(move);
    if (validMove) {
      RiskPlayer player = idToPlayer.get(playerId);
      Territory terr = gameBoard.getTerritory(from);
      Territory terr2 = gameBoard.getTerritory(to);
      terr.removeTroops(number);
      terr2.changePlayer(playerId, number);
      player.conqueredTerritory(to);
      // change back to attack if can
      // reset turn
      GameUpdate update = referee.setRestrictions();
      return update;
    } else {
      GameUpdate update = referee.setRestrictions();
      return update;
    }
  }

  public GameUpdate executeMoveTroops(UUID playerId, int numberTroops,
      TerritoryEnum from, TerritoryEnum to) {
    MoveTroopsMove move = new MoveTroopsMove(playerId, from, to, numberTroops);
    boolean validMove = referee.validateMoveTroopsMove(move);
    if (validMove) {
      Territory terr = gameBoard.getTerritory(from);
      Territory terr2 = gameBoard.getTerritory(to);
      terr.removeTroops(numberTroops);
      terr2.addTroops(numberTroops);

      // CHANGE TURN, PLAYER
      GameUpdate update = referee.setRestrictions();
      return update;
    } else {
      GameUpdate update = referee.setRestrictions();
      return update;
    }
  }

  /**
   * At the beginning of the game, players choose countries.
   */
  public GameUpdate selectTerritory(UUID playerId, TerritoryEnum territory) {

    // check to see if it's a valid move
    // execute move

    Territory territoryObject = gameBoard.getTerritory(territory);
    RiskPlayer player = idToPlayer.get(playerId);
    player.conqueredTerritory(territory);
    territoryObject.changePlayer(playerId, 1);

    // update turn / move (next player)
  }

  /**
   * At the beginning of the game, players choose countries.
   */
  public GameUpdate setUpReinforce(UUID playerId, TerritoryEnum territory) {

    // check to see if it's a valid move
    // execute move

    Territory territoryObject = gameBoard.getTerritory(territory);
    RiskPlayer player = idToPlayer.get(playerId);
    player.conqueredTerritory(territory);
    territoryObject.changePlayer(playerId, 1);

    // update turn / move (next player)
  }

  private boolean attack(DefendMove defend) {
    int compare = attack.getDieRolled();
    int numberDie = compare;
    List<Integer> attackRolls = new ArrayList<>();
    for (int i = 0; i < numberDie; i++) {
      attackRolls.add(die.roll());
    }
    numberDie = defend.getDieRolled();
    compare = Math.min(numberDie, compare);
    List<Integer> defendRolls = new ArrayList<>();
    for (int i = 0; i < numberDie; i++) {
      defendRolls.add(die.roll());
    }
    Collections.sort(attackRolls, dieComparator);
    Collections.sort(defendRolls, dieComparator);
    int defendTroopsLost = 0;
    int attackTroopsLost = 0;
    for (int i = 0; i < compare; i++) {
      int result = Integer.compare(attackRolls.get(i), defendRolls.get(i));
      if (result > 0) {
        defendTroopsLost++;
      } else {
        attackTroopsLost++;
      }
    }
    Territory attackTerr = gameBoard.getTerritory(attack.getAttackFrom());
    Territory defendTerr = gameBoard.getTerritory(attack.getAttackTo());
    attackTerr.removeTroops(attackTroopsLost);
    boolean lost = defendTerr.removeTroops(defendTroopsLost);
    if (lost) {
      RiskPlayer player = idToPlayer.get(defend.getMovePlayer());
      player.lostTerritory(defend.getDefendedTerritory());
    }
    return lost;
  }

  // Determining phase is a bit harder
}
