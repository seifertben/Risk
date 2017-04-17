package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.CardPool;
import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.Move;
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

  private UUID playerId;
  private RiskPlayer player;
  private boolean canAttack = false;
  private boolean canReinforce = false;
  private boolean canTurnInCard = false;
  private boolean canMove = false;
  private boolean canClaim = true;
  private int reinforceNumber = 0;
  private Map<TerritoryEnum, Integer> canAttackFrom = null;
  private Multimap<TerritoryEnum, TerritoryEnum> canAttackTo = null;
  private Multiset<Integer> cardsToTurnIn = null;
  private Set<TerritoryEnum> reinforceTerritories = null;
  private TerritoryEnum terrToDefend = null;
  private int canDefendWith = 0;
  private int canAttackWith = 0;
  private Multimap<TerritoryEnum, TerritoryEnum> movement = null;
  private Map<TerritoryEnum, Integer> numberTroopsCanMove = null;

  private Move lastMove;
  private boolean lastMoveValidity = true;

  /**
   * Initializes the referee.
   */
  public Referee(Turn turn, RiskBoard board) {
    this.board = board;
    this.turn = turn;
  }

  public void setRestrictions() {
    switch (turn.getPhase()) {
    case BEGIN:
      break;
    case HANDIN_CARDS:
      this.setUpHandInCardsPhase();
      break;
    case PUT_REINFORCEMENTS:
      this.setUpReinforcementPhase();
      break;
    case ATTACK_FROM:
      break;
    case ATTACK_TO:
      canAttackTo = board.getPlayerAttackMap(player);
      break;
    case ROLL_DIE:
      canAttack = false;
      canMove = false;
      canReinforce = false;
      canTurnInCard = false;
      canDefendWith = 0;
      canAttackWith = 0;
      break;
    case CLAIM_TERRITORY:
      canAttack = false;
      canTurnInCard = false;
      canClaim = true;
      break;
    }
  }

  public Move getNextMove() {
    if (lastMoveValidity) {
      // depending on move type of last
      return null;
    } else {
      return lastMove;
    }
  }

  public boolean checkLastMove() {
    // depending on ENUM value check move
    return false;
  }

  public RiskPlayer nextPlayer() {
    return null;
  }

  private void BeginPhase() {

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
   * @param turn
   *          - turn
   * @param playerID
   *          - id of player
   * @return true if this player is the turn player.
   */
  public static boolean checkPlayerTurn(Turn turn, UUID playerID) {
    return turn.getPlayerId().equals(playerID);
  }

  /**
   * Checks that the current phase of the turn is attacking
   *
   * @param turn
   *          - current turn
   * @return true if the it is the attacking phase; false otherwise
   */
  public static boolean checkAttackPhase(Turn turn) {
    return turn.getAttacking();
  }

  /**
   * Checks that the current phase of the turn is reinforcement.
   *
   * @param turn
   *          - current turn
   * @return true if it is the reinforcement phase; false otherwise
   */
  public static boolean checkReinforcementPhase(Turn turn) {
    return turn.getReinforcement();
  }

  /**
   * Checks that the current phase of the turn is movement of troops to a conquered territory
   *
   * @param turn
   *          - current turn
   * @return true if it is the movement phase; false otherwise
   */
  public static boolean checkMovementPhase(Turn turn) {
    return turn.getMovement();
  }

  /**
   * Checks that the current phase of the turn is beginning.
   *
   * @param turn
   *          - current turn
   * @return true if it is the beginning phase; false otherwise
   */
  public static boolean checkBeginningPhase(Turn turn) {
    return turn.getBeginning();
  }

  /**
   * Checks that the two territories are adjacent.
   *
   * @param board
   *          - current board of the game
   * @param terr1
   *          - id of territory 1
   * @param terr2
   *          - id of territory 2
   * @return true if the territories are adjacent; false otherwise.
   */
  public static boolean checkAdjacentTerritory(RiskBoard board,
      TerritoryEnum terr1, TerritoryEnum terr2) {
    return board.isNeighbor(terr1, terr2);
  }

  /**
   * Checks the player owns the territory.
   *
   * @param player
   *          - player
   * @param terr
   *          - id of territory
   * @return true if the player owns the territory; false otherwise
   */
  public static boolean checkTerritoryOwner(RiskPlayer player,
      TerritoryEnum terr) {
    return player.hasTerritory(terr);
  }

  /**
   * checks the territory has more than 1 troop.
   *
   * @param terr
   *          - territory
   * @return true if the territory has more than one troop; false otherwise
   */
  public static boolean checkTerritoryAttack(Territory terr) {
    return terr.getNumberTroops() > 1;
  }

  /**
   * Checks that the chosen number of Die is less than the number of troops
   *
   * @param terr
   *          - territory
   * @param numberDie
   *          - number of die to roll
   * @return true if number of die to roll is less than the number of troops in the territory; false
   *         otherwise
   */
  public static boolean checkNumberDieAttack(Territory terr, int numberDie) {
    return terr.getNumberTroops() > numberDie;
  }

  /**
   * Checks that the chosen number of die to defend with is equal to or less than the number of
   * troops.
   *
   * @param terr
   *          - territory
   * @param numberDie
   *          - chosen number of die
   * @return true if check is true; false otherwise
   */
  public static boolean checkNumberDieDefend(Territory terr, int numberDie) {
    return terr.getNumberTroops() >= numberDie;
  }

  /**
   * Checks the number of troops chosen to move is less than the total number of troops in the
   * territory.
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
   * @param terr
   *          - territory
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
   * @param terr
   *          - territory trying to be claimed
   * @return
   */
  public static boolean checkClaimTerritory(Territory terr) {
    return terr.occuppied();
  }

  /**
   * Checks if the player has won the game.
   *
   * @param player
   *          - player
   * @param board
   *          - risk board
   * @return true if hte player won the game; false otherwise
   */
  public static boolean checkWonGame(RiskPlayer player, RiskBoard board) {
    return player.getTerritories().containsAll(board.getTerritoryIds());
  }

  /**
   * Checks that the player has the card.
   *
   * @param player
   *          - player
   * @param card
   *          - card value
   * @return
   */
  public static boolean checkPlayerHasCard(RiskPlayer player, int card) {
    return player.hasCard(card);
  }

  /**
   * Checks if the caredPool is empty.
   *
   * @param cardPool
   *          - cared pool
   * @return
   */
  public static boolean checkCardPool(CardPool cardPool) {
    return cardPool.isEmpty();
  }
}
