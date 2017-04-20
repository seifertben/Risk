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
  public GameUpdate executeReinforceAction(UUID playerId,
      Map<TerritoryEnum, Integer> reinforce) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    ReinforceMove move = new ReinforceMove(playerId, reinforce);
    boolean isValidMove = referee.validateReinforce(move);
    if (isValidMove) {
      for (Entry<TerritoryEnum, Integer> entry : reinforce.entrySet()) {
        Territory terr = gameBoard.getTerritory(entry.getKey());
        terr.addTroops(entry.getValue());
      }
      Move nextValidMove = referee
          .getValidMoveAfterReinforce(idToPlayer.get(playerId));
      if (nextValidMove == null) {
        return this.switchPlayers();
      }
      turnState.changePhase(nextValidMove.getMoveType());
      update.setPrevMove(move);
      update.setValidMoves(nextValidMove);
      return update;
    } else {
      Move validMove = referee.getValidMove();
      update.setError();
      update.setValidMoves(validMove);
      return update;
    }
  }

  public GameUpdate executeCardTurnInAction(UUID playerId, int card,
      Map<TerritoryEnum, Integer> troopsAdded) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    CardTurnInMove move = new CardTurnInMove(playerId, card, troopsAdded);
    boolean validMove = referee.validateCardTurnIn(move);
    if (validMove) {
      RiskPlayer player = idToPlayer.get(move.getMovePlayer());
      player.removeCard(move.getCard());
      for (Entry<TerritoryEnum, Integer> entry : troopsAdded.entrySet()) {
        Territory terr = gameBoard.getTerritory(entry.getKey());
        terr.addTroops(entry.getValue());
      }
      Move nextValidMove = referee
          .getValidMoveAfterCardTurnIn(idToPlayer.get(playerId));
      if (nextValidMove == null) {
        return this.switchPlayers();
      }
      turnState.changePhase(nextValidMove.getMoveType());
      update.setPrevMove(move);
      update.setValidMoves(nextValidMove);
      return update;
    }
    update.setValidMoves(referee.getValidMove());
    update.setError();
    return update;
  }

  public GameUpdate executeAttackAction(UUID playerId, TerritoryEnum fromTerr,
      TerritoryEnum toTerr, int numberDie) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    attack = new AttackMove(playerId, fromTerr, toTerr, numberDie);
    boolean validMove = referee.validateAttackMove(attack);
    if (validMove) {
      List<Integer> roll = new ArrayList<>();
      for (int i = 0; i < numberDie; i++) {
        roll.add(die.roll());
      }
      Collections.sort(roll, dieComparator);
      attack.setDieResult(roll);
      turnState.changePhase(MoveType.CHOOSE_DEFEND_DIE);
      Move move = referee.getValidMoveAfterAttack(idToPlayer.get(playerId));
      update.setValidMoves(move);
      return update;
    } else {
      attack = null;
      Move move = referee.getValidMove();
      update.setValidMoves(move);
      update.setError();
      return update;
    }
  }

  public GameUpdate executeDefendAction(UUID playerId, TerritoryEnum defend,
      int numberDie) {
    GameUpdate update = new GameUpdate();
    if (winner != null) {
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    DefendMove move = new DefendMove(new Pair<>(playerId, defend), numberDie,
        attack);
    boolean validMove = referee.validateDefendMove(move);
    if (validMove) {
      boolean claim = this.attack(move);
      if (claim) {
        if (this.lostGame(idToPlayer.get(playerId))) {
          if (this.gameOver()) {
            // TODO : game over
            // set state to game over
            // send update
          }
        }
      } else {
        turnState.changePhase(MoveType.CLAIM_TERRITORY);
      }
    } else {
      // determine what the next valid turn phase is is
    }
    GameUpdate update = referee.setRestrictions();
    return update;
  }

  public GameUpdate executeClaimTerritory(UUID playerId, TerritoryEnum from,
      TerritoryEnum to, int number) {
    if (winner != null) {
      GameUpdate update = new GameUpdate();
      update.setWonGame(winner.getPlayerId());
      return update;
    }
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
      // TODO : figure out state

      GameUpdate update = referee.setRestrictions();
      return update;
    } else {
      GameUpdate update = referee.setRestrictions();
      return update;
    }
  }

  public GameUpdate executeMoveTroops(UUID playerId, int numberTroops,
      TerritoryEnum from, TerritoryEnum to) {
    if (winner != null) {
      GameUpdate update = new GameUpdate();
      update.setWonGame(winner.getPlayerId());
      return update;
    }
    MoveTroopsMove move = new MoveTroopsMove(playerId, from, to, numberTroops);
    boolean validMove = referee.validateMoveTroopsMove(move);
    if (validMove) {
      Territory terr = gameBoard.getTerritory(from);
      Territory terr2 = gameBoard.getTerritory(to);
      terr.removeTroops(numberTroops);
      terr2.addTroops(numberTroops);
      // TODO : figure out state

      GameUpdate update = referee.setRestrictions();
      if (cardToHandOut != -1) {
        update.setCardToHandOut(playerId, cardToHandOut, !cardPool.isEmpty());
      }
      return update;
    } else {
      GameUpdate update = referee.setRestrictions();
      return update;
    }
  }

  public GameUpdate executeSkipPhase(UUID playerId) {
    MoveType phase = turnState.getPhase();
    switch (phase) {
      case CHOOSE_ATTACK_DIE:

        break;
      case TURN_IN_CARD:
        // switch
        break;
      case MOVE_TROOPS:
        // switch to next turn
        break;
      default:
        // cannot switch
        // return same state
    }
    return null;
  }

  /**
   * At the beginning of the game, players choose countries.
   */
  public GameUpdate executeSelectTerritory(UUID playerId,
      TerritoryEnum territory) {

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
  public GameUpdate executeReinforceClaimedTerritory(UUID playerId,
      TerritoryEnum territory) {

    // check to see if it's a valid move
    // execute move

    Territory territoryObject = gameBoard.getTerritory(territory);
    RiskPlayer player = idToPlayer.get(playerId);
    player.conqueredTerritory(territory);
    territoryObject.changePlayer(playerId, 1);

    // update turn / move (next player)
  }

  /**
   * Runs the attack.
   *
   * @param defend
   * @return
   */
  private boolean attack(DefendMove defend) {
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
    if (lost) {
      RiskPlayer player = idToPlayer.get(defend.getMovePlayer());
      player.lostTerritory(defend.getDefendedTerritory());
      if (!cardPool.isEmpty()) {
        cardToHandOut = cardPool.handOutCard();
      }
    }
    return lost;
  }

  private GameUpdate switchPlayers() {
    GameUpdate update = new GameUpdate();
    if (cardToHandOut > 0) {
      update.setCardToHandOut(turnState.getPlayerId(), cardToHandOut,
          !cardPool.isEmpty());
    }
    RiskPlayer player = turnState.getPlayer();
    int index = players.indexOf(player);
    turnState.setPlayer(players.get((index + 1) / players.size()));
    Move validMove = referee.getValidReinforceMove(turnState.getPlayer());
    update.setValidMoves(validMove);
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

  private void getNextPlayer() {

  }
}
