package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.CardPool;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.AttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.CardTurnInMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.DefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.GameUpdate;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.Move;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.MoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ReinforceMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ValidAttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ValidCardMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ValidClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ValidDieDefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ValidMoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.ValidReinforceMove;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentInterface;

/**
 * Controls the rules of the game.
 *
 * @author Ben
 *
 */
public class Referee {
  private RiskBoard board;
  private Turn turn;

  private Move lastMove;
  private Map<UUID, RiskPlayer> players;
  private boolean validLastMove = true;

  private ValidReinforceMove validReinforce = null;
  private ValidCardMove validCard = null;
  private ValidAttackMove validAttack = null;
  private ValidDieDefendMove validDefend = null;
  private ValidClaimTerritoryMove validClaim = null;
  private ValidMoveTroopsMove validMove = null;

  /**
   * Initializes the referee.
   */
  public Referee(Turn turn, RiskBoard board) {
    this.board = board;
    this.turn = turn;
  }

  public GameUpdate setRestrictions() {
    GameUpdate toSend = new GameUpdate();
<<<<<<< HEAD
    Move availableMoves;
    UUID playerId = turn.getPlayerId();
    switch (turn.getPhase()) {
      case REINFORCE:
        // call a method to fill in valid Reinforce Move
        availableMoves = //
            toSend.setValidMoves(availableMoves);
        break;
      case TURN_IN_CARD:
        availableMoves = //
            toSend.setValidMoves(availableMoves);
        break;
      case CHOOSE_ATTACK_DIE:
        availableMoves = // .put(playerId, new ReinforceMove(playerId, null));
            toSend.setValidMoves(availableMoves);
        break;
      case CHOOSE_DEFEND_DIE:
        availableMoves = //
            toSend.setValidMoves(availableMoves);
        break;
      case CLAIM_TERRITORY:
        availableMoves = //
            toSend.setValidMoves(availableMoves);
        break;
      case MOVE_TROOPS:
        availableMoves = //
            toSend.setValidMoves(availableMoves);
        break;
    }
    return toSend;
  }

  private ReinforceMove getReinforceMove() {
    // TODO : calculate valid move
  }

  public GameUpdate getNextGameUpdate(Move currMove) {
    if (validLastMove) {
      // updateNextAvailableMoves based on currMove
      return null;
    } else {
      // return same old valid moves/ gameupdate
      return null;
    }

  }

  public boolean validateMove(Move currMove) {
    MoveType type = currMove.getMoveType();
    switch (type) {
      case REINFORCE:
        validLastMove = this.validateReinforce((ReinforceMove) currMove);
        break;
      case TURN_IN_CARD:
        validLastMove = this.validateCardTurnIn((CardTurnInMove) currMove);
      case CHOOSE_ATTACK_DIE:
        validLastMove = this.validateAttackMove((AttackMove) currMove);
        break;
      case CHOOSE_DEFEND_DIE:
        validLastMove = this.validateDefendMove((DefendMove) currMove);
        break;
      case CLAIM_TERRITORY:
        validLastMove = this
            .validateClaimTerritory((ClaimTerritoryMove) currMove);
        break;
      case MOVE_TROOPS:
        validLastMove = this.validateMoveTroopsMove((MoveTroopsMove) currMove);
        break;
      default:
        validLastMove = this.validateEndMove((EndMove) currMove);
        break;
    }
    return validLastMove;
  }

  private boolean validateReinforce(ReinforceMove move) {
    // check can do so
    if (validReinforce == null) {
      return false;
    }
    return validReinforce.validReinforceMove(move);
  }

  private boolean validateCardTurnIn(CardTurnInMove move) {
    if (validCard == null) {
      return false;
    }
    return validCard.validateCardMove(move);
  }

  private boolean validateAttackMove(AttackMove move) {
    if (validAttack == null) {
      return false;
    }
    return validAttack.validAttackMove(move);
  }

  private boolean validateDefendMove(DefendMove move) {
    // TODO : Fill in
    return false;
  }

  private boolean validateClaimTerritory(ClaimTerritoryMove move) {
    if (validClaim == null) {
      return false;
    }
    return validClaim.validClaimTerritory(move);
  }

  private boolean validateMoveTroopsMove(MoveTroopsMove move) {
    if (validMove == null) {
      return false;
    }
    return validMove.validMoveTroopMove(move);
  }

  private boolean validateEndMove(EndMove move) {
    // TODO : fill in
    return false;
  }

  public boolean checkLastMove() {
    // depending on ENUM value check move
    return false;
  }

  public RiskPlayer nextPlayer() {
    return null;
  }

  private void setUpHandInCardsPhase() {
    RiskPlayer player = turn.getPlayer();
    cardsToTurnIn = player.getCards();
    if (cardsToTurnIn.size() != 0) {
      canTurnInCard = true;
    }
  }

  private void setUpReinforcementPhase() {
    RiskPlayer player = turn.getPlayer();
    int reinforce = player.getNumberTerritories() / 3;
    Set<TerritoryEnum> territories = player.getTerritories();
    Collection<ContinentInterface> conts = board.getContinents();
    for (ContinentInterface cont : conts) {
      Set<TerritoryEnum> territoriesInCont = cont.getTerritories();
      if (territories.containsAll(territoriesInCont)) {
        reinforce += cont.getBonusValue();
      }
    }
    reinforceNumber = reinforce;
  }

  public boolean checkHandInCards(Multiset<Integer> cardsHandedIn,
      Map<TerritoryEnum, Integer> addedTroops) {
    RiskPlayer player = turn.getPlayer();
    int added = 0;
    for (Entry<TerritoryEnum, Integer> entry : addedTroops.entrySet()) {
      if (!player.hasTerritory(entry.getKey())) {
        return false;
      }
      added += entry.getValue();
    }
    int allowed = 0;
    for (int card : cardsToTurnIn) {
      allowed = allowed + card;
    }
    return allowed >= added;
  }

  public boolean checkReinforceTroops(Map<TerritoryEnum, Integer> addedTroops) {
    RiskPlayer player = turn.getPlayer();
    int number = 0;
    for (Entry<TerritoryEnum, Integer> entry : addedTroops.entrySet()) {
      if (!player.hasTerritory(entry.getKey())) {
        return false;
      }
      number += entry.getValue();
    }
    if (number != reinforceNumber) {
      return false;
    }
    return true;
  }

  /**
   * Checks that the Turn's player is this player.
   *
   * @param turn - turn
   * @param playerID - id of player
   * @return true if this player is the turn player.
   */
  public static boolean checkPlayerTurn(Turn turn, UUID playerID) {
    return turn.getPlayerId().equals(playerID);
  }

  /**
   * Checks that the current phase of the turn is attacking
   *
   * @param turn - current turn
   * @return true if the it is the attacking phase; false otherwise
   */
  public static boolean checkAttackPhase(Turn turn) {
    return turn.getAttacking();
  }

  /**
   * Checks that the current phase of the turn is reinforcement.
   *
   * @param turn - current turn
   * @return true if it is the reinforcement phase; false otherwise
   */
  public static boolean checkReinforcementPhase(Turn turn) {
    return turn.getReinforcement();
  }

  /**
   * Checks that the current phase of the turn is movement of troops to a
   * conquered territory
   *
   * @param turn - current turn
   * @return true if it is the movement phase; false otherwise
   */
  public static boolean checkMovementPhase(Turn turn) {
    return turn.getMovement();
  }

  /**
   * Checks that the current phase of the turn is beginning.
   *
   * @param turn - current turn
   * @return true if it is the beginning phase; false otherwise
   */
  public static boolean checkBeginningPhase(Turn turn) {
    return turn.getBeginning();
  }

  /**
   * Checks that the two territories are adjacent.
   *
   * @param board - current board of the game
   * @param terr1 - id of territory 1
   * @param terr2 - id of territory 2
   * @return true if the territories are adjacent; false otherwise.
   */
  public static boolean checkAdjacentTerritory(RiskBoard board,
      TerritoryEnum terr1, TerritoryEnum terr2) {
    return board.isNeighbor(terr1, terr2);
  }

  /**
   * Checks the player owns the territory.
   *
   * @param player - player
   * @param terr - id of territory
   * @return true if the player owns the territory; false otherwise
   */
  public static boolean checkTerritoryOwner(RiskPlayer player,
      TerritoryEnum terr) {
    return player.hasTerritory(terr);
  }

  /**
   * checks the territory has more than 1 troop.
   *
   * @param terr - territory
   * @return true if the territory has more than one troop; false otherwise
   */
  public static boolean checkTerritoryAttack(Territory terr) {
    return terr.getNumberTroops() > 1;
  }

  /**
   * Checks that the chosen number of Die is less than the number of troops
   *
   * @param terr - territory
   * @param numberDie - number of die to roll
   * @return true if number of die to roll is less than the number of troops in
   *         the territory; false otherwise
   */
  public static boolean checkNumberDieAttack(Territory terr, int numberDie) {
    return terr.getNumberTroops() > numberDie;
  }

  /**
   * Checks that the chosen number of die to defend with is equal to or less
   * than the number of troops.
   *
   * @param terr - territory
   * @param numberDie - chosen number of die
   * @return true if check is true; false otherwise
   */
  public static boolean checkNumberDieDefend(Territory terr, int numberDie) {
    return terr.getNumberTroops() >= numberDie;
  }

  /**
   * Checks the number of troops chosen to move is less than the total number of
   * troops in the territory.
   *
   * @param terr
   * @param numberMove
   * @return
   */
  public static boolean checkMovementNumber(Territory terr, int numberMove) {
    return terr.getNumberTroops() > numberMove;
  }

  // Bundle the checks for actions.

  /**
   * Checks if the territory has been lost.
   *
   * @param terr - territory
   * @return
   */
  public static boolean checkTerritoryLoss(Territory terr) {
    return terr.getNumberTroops() == 0;
  }

  /**
   * Checks if the Player lost the game.
   *
   * @param player
   * @return
   */
  public static boolean checkGameLoss(RiskPlayer player) {
    return !player.hasTerritories();
  }

  /**
   * Checks if the Player can attack.
   *
   * @param attackMap
   * @return
   */
  public static boolean canAttack(
      Multimap<TerritoryEnum, TerritoryEnum> attackMap) {
    return attackMap.size() != 0;
  }

  /**
   * Checks that the territory can be claimed.
   *
   * @param terr - territory trying to be claimed
   * @return
   */
  public static boolean checkClaimTerritory(Territory terr) {
    return terr.occuppied();
  }

  /**
   * Checks if the player has won the game.
   *
   * @param player - player
   * @param board - risk board
   * @return true if hte player won the game; false otherwise
   */
  public static boolean checkWonGame(RiskPlayer player, RiskBoard board) {
    return player.getTerritories().containsAll(board.getTerritoryIds());
  }

  /**
   * Checks if the caredPool is empty.
   *
   * @param cardPool - cared pool
   * @return
   */
  public static boolean checkCardPool(CardPool cardPool) {
    return cardPool.isEmpty();
  }
}
