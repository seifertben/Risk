package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidAction;
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
  private List<RiskPlayer> players = Collections
      .synchronizedList(new ArrayList<>());
  private Map<UUID, RiskPlayer> idToPlayer = Collections
      .synchronizedMap(new HashMap<>());
  private Referee referee;
  private AttackMove attack;
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

  // public GameUpdate executeSetupPhase(UUID playerId, TerritoryEnum selected)
  // {
  // GameUpdate update = new GameUpdate();
  // SetupMove setupMove = new SetupMove(playerId, selected);
  // boolean isValidMove = referee.validateSetupMove(setupMove);
  // if (!isValidMove) {
  // ValidAction validMove = referee.getValidMove();
  // update.setValidMoves(validMove, null, true);
  // return update;
  // }
  // ValidAction nextValidMove = referee
  // .getValidMoveAfterSetup(idToPlayer.get(playerId));
  // if (nextValidMove == null) {
  // return this.switchPlayers(move);
  // }
  // turnState.changePhase(nextValidMove.getMoveType());
  // update.setValidMoves(nextValidMove, move, false);
  // return update;
  // }

  /**
   * This method executes a reinforce action. It first checks that the given
   * player can make such an action; if so, it executes it. Otherwise, it does
   * not and returns an error messaging indicating the move was not valid.
   *
   * @param move of troops to place on the territory
   * @return GameUpdate object representing what happened
   */
  public GameUpdate executeReinforceAction(ReinforceMove move) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateReinforce(move);
    if (!isValidMove) { // not a valid move
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }

    // execute reinforce move
    for (Entry<TerritoryEnum, Integer> entry : move.getReinforcedTerritories()
        .entrySet()) {
      Territory terr = gameBoard.getTerritory(entry.getKey());
      terr.addTroops(entry.getValue());
    }

    // for (Entry<TerritoryEnum, Integer> entry : reinforce.entrySet()) {
    // gameBoard.addTroops(entry.getValue(), entry.getKey())
    // }
    ValidAction nextValidMove = referee
        .getValidMoveAfterReinforce(idToPlayer.get(move.getMovePlayer()));
    if (nextValidMove == null) {
      return this.switchPlayers(move);
    }
    turnState.changePhase(nextValidMove.getMoveType());
    update.setValidMoves(nextValidMove, move, false);
    return update;
  }

  /**
   * Executes a card turn in. If the the move is valid, the game will execute
   * it. Otherwise, it will return an error.
   *
   * @param move
   * @return game update
   */
  public GameUpdate executeCardTurnInAction(CardTurnInMove move) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateCardTurnIn(move);
    if (!isValidMove) { // move is not valid
      update.setValidMoves(referee.getValidMove(), null, true);
      return update;
    }

    // execute card turn in
    RiskPlayer player = idToPlayer.get(move.getMovePlayer());
    player.removeCard(move.getCard()); // removes the card
    for (Entry<TerritoryEnum, Integer> entry : move.getTerritoriesReinforced()
        .entrySet()) {
      Territory terr = gameBoard.getTerritory(entry.getKey());
      terr.addTroops(entry.getValue());
    }
    ValidAction nextValidMove = referee
        .getValidMoveAfterCardTurnIn(idToPlayer.get(move.getMovePlayer()));
    if (nextValidMove == null) {
      return this.switchPlayers(move);
    }
    turnState.changePhase(nextValidMove.getMoveType());
    update.setValidMoves(nextValidMove, move, false);
    return update;
  }

  /**
   * Executes an attack.
   *
   * @param move
   * @return game update
   */
  public GameUpdate executeAttackAction(AttackMove move) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    attack = move;
    boolean isValidMove = referee.validateAttackMove(attack);
    if (isValidMove) {
      attack = null;
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    ValidAction validMove = referee.getValidMoveAfterAttack(
        idToPlayer.get(move.getMovePlayer()), move.getAttackTo());
    turnState.changePhase(MoveType.CHOOSE_DEFEND_DIE);
    update.setValidMoves(validMove, attack, false);
    return update;
  }

  /**
   *
   * @return game update
   */
  public GameUpdate executeDefendAction(DefendMove move) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateDefendMove(move);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    this.attack(move);
    if (move.getDefenderLostTerritory()) {
      if (this.lostGame(idToPlayer.get(move.getMovePlayer()))) {
        update.setLostGame(move.getMovePlayer());
      }
      ValidAction nextValidMove = referee.getValidMoveAfterDefend(
          idToPlayer.get(attack.getMovePlayer()), move, attack);
      turnState.changePhase(MoveType.CLAIM_TERRITORY);
      update.setValidMoves(nextValidMove, move, false);
      attack = null;
      return update;
    } else {
      ValidAction nextValidMove = referee.getValidMoveAfterDefend(
          idToPlayer.get(attack.getMovePlayer()), move, attack);
      if (nextValidMove == null) {
        return this.switchPlayers(move);
      } else {
        turnState.changePhase(nextValidMove.getMoveType());
      }
      attack = null;
      update.setValidMoves(nextValidMove, move, false);
      return update;
    }
  }

  /**
   * Executes a claim territory move. A player claims a territory if, during an
   * attack, the number of troops on the defending territory decreases to 0.
   * This move checks that the claim territory move is valid, and executes if
   * so.
   *
   * @param move
   * @return update specifying what happened
   */
  public GameUpdate executeClaimTerritory(ClaimTerritoryMove move) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateClaimTerritory(move);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, move, true);
      return update;
    }
    RiskPlayer player = idToPlayer.get(move.getMovePlayer());
    Territory terr = gameBoard.getTerritory(move.getTerritoryFrom());
    Territory terr2 = gameBoard.getTerritory(move.getTerritoryClaimed());
    terr.removeTroops(move.getNumberTroops());
    terr2.changePlayer(move.getMovePlayer(), move.getNumberTroops());
    player.conqueredTerritory(move.getTerritoryClaimed());
    if (this.gameOver(player)) {
      update.setWonGame(move.getMovePlayer());
      update.setValidMoves(null, move, false);
      return update;
    }
    ValidAction validNextMove = referee.getValidMoveAfterClaimTerritory(player);
    if (validNextMove == null) {
      return this.switchPlayers(move);
    }
    update.setValidMoves(validNextMove, move, false);
    return update;
  }

  /**
   * This method moves the specified number of troops from a player's territory
   * to another an adjacent one. If the move is valid, it will execute.
   * Otherwise, the move will not be executed and RiskGame will return an error
   * in the GameUpdate object.
   *
   * @param move
   * @return GameUpdate specifying what happend and the next possible move
   */
  public GameUpdate executeMoveTroops(MoveTroopsMove move) {
    GameUpdate update = new GameUpdate();
    if (winner != null) { // nothing should happen, just broadcast winner
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateMoveTroopsMove(move);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove(); // gets the current valid
      update.setValidMoves(validMove, null, true); // sets error message
      return update;
    }
    // gameBoard.removeTroops(from, numberTroops);
    // gameBoard.addTroops(to, numberTroops)
    // return this.switchPlayers;
    Territory terr = gameBoard.getTerritory(move.getFromTerritory());
    Territory terr2 = gameBoard.getTerritory(move.getToTerrtiory());
    assert (!terr.removeTroops(move.troopsMoved())); // remove troops - not
                                                     // empty
    terr2.addTroops(move.troopsMoved()); // add troops
    return this.switchPlayers(move); // switches players
  }

  public GameUpdate executeSkipPhase(UUID playerId) {
    MoveType phase = turnState.getPhase();
    GameUpdate update = new GameUpdate();
    switch (phase) {
      case TURN_IN_CARD:
        // find next valid move
        break;
      case CHOOSE_ATTACK_DIE:
        // find next valid move
        break;
      case MOVE_TROOPS:
        return this.switchPlayers(null);
      default:
        ValidAction valid = referee.getValidMove();
        update.setValidMoves(valid, null, true);
        return update;
    }
    return null;
  }

  /**
   * At the beginning of the game, players choose countries.
   */
  // public GameUpdate executeSelectTerritory(UUID playerId,
  // TerritoryEnum territory) {
  //
  // // check to see if it's a valid move
  // // execute move
  //
  // Territory territoryObject = gameBoard.getTerritory(territory);
  // RiskPlayer player = idToPlayer.get(playerId);
  // player.conqueredTerritory(territory);
  // territoryObject.changePlayer(playerId, 1);
  //
  // // update turn / move (next player)
  // }
  //
  // /**
  // * At the beginning of the game, players choose countries.
  // */
  // public GameUpdate executeReinforceClaimedTerritory(UUID playerId,
  // TerritoryEnum territory) {
  //
  // // check to see if it's a valid move
  // // execute move
  //
  // Territory territoryObject = gameBoard.getTerritory(territory);
  // RiskPlayer player = idToPlayer.get(playerId);
  // player.conqueredTerritory(territory);
  // territoryObject.changePlayer(playerId, 1);
  //
  // // update turn / move (next player)
  // }

  /**
   * Runs the attack.
   *
   * @param defend
   * @return
   */
  private void attack(DefendMove defend) {
    List<Integer> attackRolls = attack.getDieResults();
    List<Integer> defendRolls = defend.getRoll();
    int compare = Math.min(attackRolls.size(), defendRolls.size());
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
    assert (!attackTerr.removeTroops(attackTroopsLost));
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
    ValidAction validMove = referee
        .getValidReinforceMove(turnState.getPlayer());
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
  private boolean gameOver(RiskPlayer player) {
    if (player.getTerritories().containsAll(gameBoard.getTerritoryIds())) {
      winner = player;
      return true;
    }
    return false;
  }

  public void removePlayer(UUID playerId) {
    if (players.contains(playerId)) {
      players.remove(playerId);
    }
  }
}
