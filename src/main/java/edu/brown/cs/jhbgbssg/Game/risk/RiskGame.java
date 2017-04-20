package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.Player;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.AttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.CardTurnInMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.DefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.Move;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ReinforceMove;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssh.tuple.Pair;

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
  private int cardToHandOut = -1;
  private RiskCardPool cardPool;
  private Player winner;

  /**
   * Initializes the game state.
   *
   * @param ids the player ids.
   */
  public RiskGame(Set<UUID> ids) {
    if (ids == null) {
      throw new IllegalArgumentException("ERROR: null set");
    }
    if (ids.size() < 2 || ids.size() > 6) {
      throw new IllegalArgumentException("ERROR: illegal number of players");
    }
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
    turnState.setPlayer(players.get(0));
    die = new Die();
    cardPool = new RiskCardPool();
  }

  /**
   * Returns the turn order.
   *
   * @return list of UUIDs
   */
  public List<UUID> getPlayerOrder() {
    List<UUID> order = new ArrayList<>();
    for (int i = 0; i < players.size(); i++) {
      order.add(players.get(i).getPlayerId());
    }
    return Collections.unmodifiableList(order);
  }

  public GameUpdate startGame() {
    // set initial restrictions called

    return null;
  }

  /**
   * This method executes a reinforce action. It first checks that the given
   * player can make such an action; if so, it executes it. Otherwise, it does
   * not and returns an error messaging indicating the move was not valid.
   *
   * @param playerId - id of the player trying to make the move
   * @param reinforce - a map of territories to numbers representing the number
   *          of troops to place on the territory
   * @return GameUpdate object representing what happened
   */
//  public GameUpdate executeReinforceAction(UUID playerId,
//      Map<TerritoryEnum, Integer> reinforce) {
//    GameUpdate update = new GameUpdate();
//    if (winner != null) {
//      update.setWonGame(winner.getPlayerId());
//      return update;
//    }
//    ReinforceMove move = new ReinforceMove(playerId, reinforce);
//    boolean isValidMove = referee.validateReinforce(move);
//    if (isValidMove) {
//      for (Entry<TerritoryEnum, Integer> entry : reinforce.entrySet()) {
//        Territory terr = gameBoard.getTerritory(entry.getKey());
//        terr.addTroops(entry.getValue());
//      }
//      Move nextValidMove = referee
//          .getValidMoveAfterReinforce(idToPlayer.get(playerId));
//      if (nextValidMove == null) {
//        return this.switchPlayers(move);
//      }
//      turnState.changePhase(nextValidMove.getMoveType());
//      update.setValidMoves(nextValidMove, move, false);
//      return update;
//    } else {
//      Move validMove = referee.getValidMove();
//      update.setValidMoves(validMove, null, true);
//      return update;
//    }
//  }
//
//  /**
//   * Executes a card turn in. If the the move is valid, the game will execute
//   * it. Otherwise, it will return an error.
//   *
//   * @param playerId - player turning in the card
//   * @param card - card turning in
//   * @param troopsAdded - map of territories with the number of troops added
//   * @return game update
//   */
//  public GameUpdate executeCardTurnInAction(UUID playerId, int card,
//      Map<TerritoryEnum, Integer> troopsAdded) {
//    GameUpdate update = new GameUpdate();
//    if (winner != null) {
//      update.setWonGame(winner.getPlayerId());
//      return update;
//    }
//    CardTurnInMove move = new CardTurnInMove(playerId, card, troopsAdded);
//    boolean validMove = referee.validateCardTurnIn(move);
//    if (validMove) {
//      RiskPlayer player = idToPlayer.get(move.getMovePlayer());
//      player.removeCard(move.getCard());
//      for (Entry<TerritoryEnum, Integer> entry : troopsAdded.entrySet()) {
//        Territory terr = gameBoard.getTerritory(entry.getKey());
//        terr.addTroops(entry.getValue());
//      }
//      Move nextValidMove = referee
//          .getValidMoveAfterCardTurnIn(idToPlayer.get(playerId));
//      if (nextValidMove == null) {
//        return this.switchPlayers(move);
//      }
//      turnState.changePhase(nextValidMove.getMoveType());
//      update.setValidMoves(nextValidMove, move, false);
//      return update;
//    }
//    update.setValidMoves(referee.getValidMove(), null, true);
//    return update;
//  }
//
//  /**
//   * Executes an attack.
//   *
//   * @param playerId - id of the player attacking
//   * @param fromTerr - territory attacking from
//   * @param toTerr - territory attacking
//   * @param numberDie - number of die to roll
//   * @return game update
//   */
//  public GameUpdate executeAttackAction(UUID playerId, TerritoryEnum fromTerr,
//      TerritoryEnum toTerr, int numberDie) {
//    GameUpdate update = new GameUpdate();
//    if (winner != null) {
//      update.setWonGame(winner.getPlayerId());
//      return update;
//    }
//    attack = new AttackMove(playerId, fromTerr, toTerr, numberDie);
//    boolean validMove = referee.validateAttackMove(attack);
//    if (validMove) {
//      List<Integer> roll = new ArrayList<>();
//      for (int i = 0; i < numberDie; i++) {
//        roll.add(die.roll());
//      }
//      Collections.sort(roll, dieComparator);
//      attack.setDieResult(roll);
//      Move move = referee.getValidMoveAfterAttack(idToPlayer.get(playerId));
//      turnState.changePhase(MoveType.CHOOSE_DEFEND_DIE);
//      update.setValidMoves(move, attack, false);
//      return update;
//    } else {
//      attack = null;
//      Move move = referee.getValidMove();
//      update.setValidMoves(move, null, true);
//      return update;
//    }
//  }
//
//  /**
//   *
//   * @param playerId - id of player defending
//   * @param defend - territory defending
//   * @param numberDie - number of die to roll
//   * @return game update
//   */
//  public GameUpdate executeDefendAction(UUID playerId, TerritoryEnum defend,
//      int numberDie) {
//    GameUpdate update = new GameUpdate();
//    if (winner != null) {
//      update.setWonGame(winner.getPlayerId());
//      return update;
//    }
//    DefendMove move = new DefendMove(new Pair<>(playerId, defend), numberDie,
//        attack);
//    boolean isValidMove = referee.validateDefendMove(move);
//    if (isValidMove) {
//      this.attack(move);
//      if (move.getDefenderLostTerritory()) {
//        if (this.lostGame(idToPlayer.get(playerId))) {
//          update.setLostGame(playerId);
//          if (this.gameOver()) {
//            update.setWonGame(attack.getMovePlayer());
//            return update;
//          }
//        }
//        Move nextValidMove = referee.getValidMoveAfterDefend(
//            idToPlayer.get(attack.getMovePlayer()), move);
//        turnState.changePhase(MoveType.CLAIM_TERRITORY);
//        update.setValidMoves(nextValidMove, move, false);
//        return update;
//      } else {
//        Move nextValidMove = referee.getValidMoveAfterDefend(
//            idToPlayer.get(attack.getMovePlayer()), move);
//        if (nextValidMove == null) {
//          return this.switchPlayers(move);
//        }
//        update.setValidMoves(nextValidMove, move, false);
//        return update;
//      }
//    } else {
//      Move validMove = referee.getValidMove();
//      update.setValidMoves(validMove, null, true);
//      return update;
//    }
//  }
//
//  /**
//   * Executes a claim territory move. A player claims a territory if, during an
//   * attack, the number of troops on the defending territory decreases to 0.
//   * This move checks that the claim territory move is valid, and executes if
//   * so.
//   *
//   * @param playerId - player claiming territory
//   * @param from - territory from which to move troops
//   * @param to - territory to claim
//   * @param number - number of troops
//   * @return update specifying what happened
//   */
//  public GameUpdate executeClaimTerritory(UUID playerId, TerritoryEnum from,
//      TerritoryEnum to, int number) {
//    GameUpdate update = new GameUpdate();
//    if (winner != null) {
//      update.setWonGame(winner.getPlayerId());
//      return update;
//    }
//    ClaimTerritoryMove move = new ClaimTerritoryMove(playerId, from, to,
//        number);
//    boolean isValidMove = referee.validateClaimTerritory(move);
//    if (isValidMove) {
//      RiskPlayer player = idToPlayer.get(playerId);
//      Territory terr = gameBoard.getTerritory(from);
//      Territory terr2 = gameBoard.getTerritory(to);
//      terr.removeTroops(number);
//      terr2.changePlayer(playerId, number);
//      player.conqueredTerritory(to);
//      Move validNextMove = referee.getValidMoveAfterClaimTerritory(player);
//      if (validNextMove == null) {
//        return this.switchPlayers(move);
//      }
//      update.setValidMoves(validNextMove, move, false);
//      return update;
//    } else {
//      Move validMove = referee.getValidMove();
//      update.setValidMoves(validMove, move, true);
//      return update;
//    }
//  }
//
//  /**
//   * This method moves the specified number of troops from a player's territory
//   * to another an adjacent one. If the move is valid, it will execute.
//   * Otherwise, the move will not be executed and RiskGame will return an error
//   * in the GameUpdate object.
//   *
//   * @param playerId - id of the player making the move
//   * @param numberTroops - number of troops to move
//   * @param from - take troops from this territory
//   * @param to - move troops to this territory
//   * @return GameUpdate specifying what happend and the next possible move
//   */
//  public GameUpdate executeMoveTroops(UUID playerId, int numberTroops,
//      TerritoryEnum from, TerritoryEnum to) {
//    GameUpdate update = new GameUpdate();
//    if (winner != null) { // nothing should happen, just broadcast winner
//      update.setWonGame(winner.getPlayerId());
//      return update;
//    }
//    MoveTroopsMove move = new MoveTroopsMove(playerId, from, to, numberTroops);
//    boolean isValidMove = referee.validateMoveTroopsMove(move);
//
//    // move is valid
//    if (isValidMove) {
//      Territory terr = gameBoard.getTerritory(from);
//      Territory terr2 = gameBoard.getTerritory(to);
//      assert (!terr.removeTroops(numberTroops)); // remove troops - not emptying
//      terr2.addTroops(numberTroops); // add troops
//      return this.switchPlayers(move); // switches players
//
//      // move is not valid
//    } else {
//      Move validMove = referee.getValidMove(); // gets the current valid moves
//      update.setValidMoves(validMove, null, true); // sets error message
//      return update;
//    }
//  }
//
//  public GameUpdate executeSkipPhase(UUID playerId) {
//    MoveType phase = turnState.getPhase();
//    GameUpdate update = new GameUpdate();
//    switch (phase) {
//      case CHOOSE_ATTACK_DIE:
//        // find next valid move
//
//        break;
//      case TURN_IN_CARD:
//        // find next valid move
//        break;
//      case MOVE_TROOPS:
//        return this.switchPlayers(null);
//      default:
//        Move valid = referee.getValidMove();
//        update.setValidMoves(valid, null, true);
//        return update;
//    }
//    return null;
//  }
//
//  /**
//   * At the beginning of the game, players choose countries.
//   */
//  public GameUpdate executeSelectTerritory(UUID playerId,
//      TerritoryEnum territory) {
//
//    // check to see if it's a valid move
//    // execute move
//
//    Territory territoryObject = gameBoard.getTerritory(territory);
//    RiskPlayer player = idToPlayer.get(playerId);
//    player.conqueredTerritory(territory);
//    territoryObject.changePlayer(playerId, 1);
//
//    // update turn / move (next player)
//  }
//
//  /**
//   * At the beginning of the game, players choose countries.
//   */
//  public GameUpdate executeReinforceClaimedTerritory(UUID playerId,
//      TerritoryEnum territory) {
//
//    // check to see if it's a valid move
//    // execute move
//
//    Territory territoryObject = gameBoard.getTerritory(territory);
//    RiskPlayer player = idToPlayer.get(playerId);
//    player.conqueredTerritory(territory);
//    territoryObject.changePlayer(playerId, 1);
//
//    // update turn / move (next player)
//  }

  /**
   * Runs the attack.
   *
   * @param defend
   * @return
   */
  private void attack(DefendMove defend) {
    List<Integer> attackRolls = attack.getDieResults();
    int numberDie = defend.getDieRolled();
    List<Integer> defendRolls = new ArrayList<>();
    for (int i = 0; i < numberDie; i++) {
      defendRolls.add(die.roll());
    }
    Collections.sort(defendRolls, dieComparator);
    int compare = Math.min(numberDie, attack.getDieRolled());
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
    defend.setDefendTroopsLost(defendTroopsLost, lost);
    defend.setAttackTroopsLost(attackTroopsLost);
    if (lost) {
      RiskPlayer player = idToPlayer.get(defend.getMovePlayer());
      player.lostTerritory(defend.getDefendedTerritory());
      if (!cardPool.isEmpty()) {
        cardToHandOut = cardPool.handOutCard();
      }
    }
  }

  /**
   * Switches the current player.
   *
   * @param prevMove
   * @return game update
   */
  private GameUpdate switchPlayers(Move prevMove) {
    GameUpdate update = new GameUpdate();
    if (cardToHandOut > 0) {
      update.setCardToHandOut(turnState.getPlayerId(), cardToHandOut,
          !cardPool.isEmpty());
      cardToHandOut = -1;
    }
    RiskPlayer player = turnState.getPlayer();
    int index = players.indexOf(player);
    turnState.setPlayer(players.get((index + 1) / players.size()));
    Move validMove = referee.getValidReinforceMove(turnState.getPlayer());
    update.setValidMoves(validMove, prevMove, false);
    return update;
  }

  /**
   * Tests to see if the player lost the game. If so, the player is removed from
   * the turn list and true is returned.
   */
  private boolean lostGame(RiskPlayer lost) {
    if (!lost.hasTerritories()) {
      players.remove(lost);
      return true;
    }
    return false;
  }

  /**
   * Tests to see if the game is over. If so, it sets the winner instance
   * variable and return true.
   */
  private boolean gameOver() {
    if (players.size() == 1) {
      winner = players.get(1);
      return true;
    }
    return false;
  }
}
