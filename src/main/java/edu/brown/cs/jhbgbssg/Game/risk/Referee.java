package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.CardPool;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.Action;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.DefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidCardAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidDieDefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidMoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupReinforceAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Controls the rules of the game.
 *
 * @author Ben
 *
 */
public class Referee {
  private RiskBoard board;
  private RiskPlayer winner;
  private List<RiskPlayer> turnOrder;
  private CardPool cardPool;
  private ValidAction validMove = null;
  private RiskPlayer currPlayer;
  private AttackAction lastAttack;
  private boolean gameStarted = false;

  /**
   * Constructor for Referee. It takes in the RiskBoard and a set of players.
   *
   * @param board - board
   * @param playerSet - set of risk players
   * @throws IllegalArgumentException - if the input is null
   */
  public Referee(RiskBoard board, Collection<RiskPlayer> playerSet)
      throws IllegalArgumentException {
    if (board == null || playerSet == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (playerSet.size() < 2 || playerSet.size() > 6) {
      throw new IllegalArgumentException("ERROR: illegal number of players");
    }
    this.board = board;
    turnOrder = new ArrayList<>(playerSet);
    Collections.shuffle(turnOrder);
    int numberPlayers = playerSet.size();
    for (RiskPlayer player : turnOrder) {
      player.setIntialReinforcement(numberPlayers);
    }
    currPlayer = turnOrder.get(0);
    cardPool = new RiskCardPool();
    validMove = new ValidSetupAction(turnOrder.get(0), board);
  }

  /**
   * Returns the ordered list of player ids in the game.
   *
   * @return list of player ids
   */
  public List<UUID> getPlayerOrder() {
    List<UUID> playerOrder = new ArrayList<>();
    for (int i = 0; i < turnOrder.size(); i++) {
      playerOrder.add(turnOrder.get(i).getPlayerId());
    }
    return Collections.unmodifiableList(playerOrder);
  }

  /**
   * Removes a player with the given UUID from the game.
   *
   * @param playerId Id of player to remove.
   */
  public void removePlayer(UUID playerId) {
    for (int i = 0; i < turnOrder.size(); i++) {
      if (turnOrder.get(i).getPlayerId() == playerId) {
        turnOrder.remove(i);
      }
    }
  }

  /**
   * This method starts the game. It returns the first GameUpdate and sets the
   * gameStarted method to true. It can be called only once. After the first
   * call, it returns null.
   *
   * @return first game update
   */
  public GameUpdate startGame() {
    if (!gameStarted) {
      gameStarted = true;
      GameUpdate update = new GameUpdate();
      update.setValidMoves(validMove, null, false);
      return update;
    }
    return null;
  }

  /**
   * Indicates whether the game has started.
   *
   * @return Started boolean.
   */
  public boolean gameStarted() {
    return gameStarted;
  }

  /**
   * Returns the current attack action.
   *
   * @return AttackAction
   */
  public AttackAction getCurrentAttack() {
    return lastAttack;
  }

  /**
   * Returns the winner if there is one.
   *
   * @return winner of the game
   */
  public RiskPlayer getWinner() {
    return winner;
  }

  /**
   * Determines if there the player is a winner and sets the winner field if so.
   *
   * @param player - player to determine winner
   * @return true if there is a winner; false otherwise
   */
  protected boolean isWinner(RiskPlayer player) {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (player.getTerritories().containsAll(board.getTerritoryIds())) {
      winner = player;
      return true;
    }
    return false;
  }

  /**
   * Determines if the player has lost the game. If so, the player is removed
   * from the turnOrder list and the method returns true.
   *
   * @param player - player to check for losing the game
   * @return true if the player lost the game; false otherwise
   */
  protected boolean playerLost(RiskPlayer player) {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (!player.hasTerritories()) {
      turnOrder.remove(player);
      return true;
    }
    return false;
  }

  /**
   * Hands out a card if the card pool is not empty. Otherwise, the method
   * returns -1.
   *
   * @return card value to hand out
   */
  protected int handOutCard() {
    if (!cardPool.isEmpty()) {
      return cardPool.handOutCard();
    }
    return -1;
  }

  /**
   * Switches the current player and determines which the valid actions the
   * player can do.
   *
   * @return valid action of the next player
   */
  protected ValidAction switchPlayer(Action prevMove) {
    int index = turnOrder.indexOf(currPlayer);
    index = (index + 1) % turnOrder.size();
    currPlayer = turnOrder.get(index);
    validMove = new ValidCardAction(currPlayer);
    if (!validMove.actionAvailable()) {
      validMove =
          new ValidReinforceAction(currPlayer, board, new ArrayList<>());
    }
    if (prevMove.getMoveType() == MoveType.SETUP) {
      validMove = new ValidSetupAction(currPlayer, board);
      if (validMove.actionAvailable()) {
        return validMove;
      } else {
        validMove = new ValidSetupReinforceAction(currPlayer);
        return validMove;
      }
    } else if (prevMove.getMoveType() == MoveType.SETUP_REINFORCE) {
      validMove = new ValidSetupReinforceAction(currPlayer);
      if (validMove.actionAvailable()) {
        return validMove;
      } else {
        currPlayer = turnOrder.get(0);
        validMove =
            new ValidReinforceAction(currPlayer, board, new ArrayList<>());
        return validMove;
      }
    }
    return validMove;
  }

  /**
   * Returns whether or not the card deck is empty.
   *
   * @return true if empty; false otherwise
   */
  protected boolean emptyCardDeck() {
    return cardPool.isEmpty();
  }

  /**
   * Gets the current ValidAction. If the game has not been started, the
   * gameStarted indicator variable is set to true and the first valid action is
   * returned for the first player.
   *
   * @return the current valid action
   */
  public ValidAction getValidMove() {
    if (!gameStarted) {
      gameStarted = true;
    }
    return validMove;
  }

  /**
   * Gets the next valid action after a player has reinforced. If current player
   * does not have any valid moves left, the method returns null.
   *
   * @param player
   * @return ValidAction - next set of valid actions
   */
  protected ValidAction getValidMoveAfterReinforce() {
    ValidAttackAction move = new ValidAttackAction(currPlayer, board);
    if (move.actionAvailable()) {
      validMove = move;
      return move;
    }
    ValidMoveTroopsAction troopMove =
        new ValidMoveTroopsAction(currPlayer, board);
    if (troopMove.getReachableTerritores().size() != 0) {
      validMove = troopMove;
      return troopMove;
    }
    return null;
  }

  protected ValidAction getValidMoveAfterCardTurnIn(Multiset<Integer> cards) {
    List<Integer> cardList = new ArrayList<>(cards);
    ValidReinforceAction move =
        new ValidReinforceAction(currPlayer, board, cardList);
    validMove = move;
    return validMove;
  }

  /**
   * Gets the next valid action after an attack. After an attack, the only valid
   * action is defending.
   *
   * @return next valid action after attacking
   */
  protected ValidAction getValidMoveAfterAttack() {
    TerritoryEnum defending = lastAttack.getDefendingTerritory();
    RiskPlayer defender = board.getTerritory(defending).getOwner();
    ValidDieDefendAction move = new ValidDieDefendAction(defender, board,
        lastAttack.getDefendingTerritory());
    validMove = move;
    return validMove;
  }

  /**
   * Gets the next valid action after defending. If the the defender lost his
   * territory, the next valid action is the attacker claiming the territory.
   * Otherwise, the next valid action is either another attack or a moving
   * troops. If no action is available for the current player, the method
   * returns null.
   *
   * @param defend - defend move
   * @return next valid action after defending
   */
  protected ValidAction getValidMoveAfterDefend(DefendAction defend) {
    if (defend.getDefenderLostTerritory()) {
      validMove = new ValidClaimTerritoryAction(currPlayer, board, lastAttack);
      lastAttack = null;
      return validMove;
    }
    ValidAttackAction validAttack = new ValidAttackAction(currPlayer, board);
    if (validAttack.actionAvailable()) {
      lastAttack = null;
      validMove = validAttack;
      return validMove;
    }
    ValidMoveTroopsAction moveTroops =
        new ValidMoveTroopsAction(currPlayer, board);
    if (moveTroops.actionAvailable()) {
      lastAttack = null;
      validMove = moveTroops;
      return validMove;
    }
    lastAttack = null;
    return null;
  }

  /**
   * Gets the next valid action after claiming a territory. If the current
   * player can attack, a valid attack action is returned. If not, a valid move
   * troops action is returned if the current player can move their troops.
   * Otherwise, null is returned if the current player has no valid action.
   *
   * @return next valid action after claiming a territory
   */
  protected ValidAction getValidMoveAfterClaimTerritory() {
    ValidAttackAction attack = new ValidAttackAction(currPlayer, board);
    if (attack.actionAvailable()) {
      validMove = attack;
      return validMove;
    }
    ValidMoveTroopsAction moveTroops =
        new ValidMoveTroopsAction(currPlayer, board);
    if (moveTroops.actionAvailable()) {
      validMove = moveTroops;
      return validMove;
    }
    return null;
  }

  protected boolean validSkipMove(RiskPlayer player) {
    if (!player.equals(currPlayer)) {
      return false;
    }
    MoveType type = validMove.getMoveType();
    if (type == MoveType.TURN_IN_CARD || type == MoveType.CHOOSE_ATTACK_DIE
        || type == MoveType.MOVE_TROOPS) {
      return true;
    }
    return false;
  }

  protected ValidAction getActionAfterSkip() {
    MoveType type = validMove.getMoveType();
    if (type == MoveType.TURN_IN_CARD) {
      return new ValidReinforceAction(currPlayer, board, new ArrayList<>());
    } else if (type == MoveType.CHOOSE_ATTACK_DIE) {
      ValidMoveTroopsAction move = new ValidMoveTroopsAction(currPlayer, board);
      if (move.actionAvailable()) {
        validMove = move;
        return move;
      } else {
        return null;
      }
    }
    return null;
  }

  protected ValidAction switchPlayersAfterSkip() {
    int index = turnOrder.indexOf(currPlayer);
    index = (index + 1) % turnOrder.size();
    currPlayer = turnOrder.get(index);
    validMove = new ValidCardAction(currPlayer);
    if (!validMove.actionAvailable()) {
      validMove =
          new ValidReinforceAction(currPlayer, board, new ArrayList<>());
    }
    return validMove;
  }

  protected ValidAction getValidMoveAfterSetup() {
    return null;
  }

  /**
   * Returns null.
   *
   * @return null
   */
  public ValidAction getValidMoveAfterReinforceSetup() {
    return null;
  }

  /**
   * Checks that the ReinforceMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateReinforce(ReinforceAction move) {
    if (validMove == null || validMove.getMoveType() != MoveType.REINFORCE) {
      return false;
    }
    ValidReinforceAction reinforce = (ValidReinforceAction) validMove;
    return reinforce.validReinforceMove(move);
  }

  /**
   * Checks that the CardTurnInMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateCardTurnIn(CardTurnInAction move) {
    if (validMove == null || validMove.getMoveType() != MoveType.TURN_IN_CARD) {
      return false;
    }
    ValidCardAction card = (ValidCardAction) validMove;
    return card.validateCardMove(move);
  }

  /**
   * Checks that the AttackMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateAttackMove(AttackAction move) {
    if (validMove == null
        || validMove.getMoveType() != MoveType.CHOOSE_ATTACK_DIE) {
      return false;
    }
    ValidAttackAction validAttack = (ValidAttackAction) validMove;
    if (validAttack.isValidAttackAction(move)) {
      lastAttack = move;
      return true;
    }
    return false;
  }

  /**
   * Checks the the DefendMoveis valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateDefendMove(DefendAction move) {
    if (validMove == null
        || validMove.getMoveType() != MoveType.CHOOSE_DEFEND_DIE) {
      return false;
    }
    ValidDieDefendAction defend = (ValidDieDefendAction) validMove;
    return defend.validateDefendMove(move);
  }

  /**
   * Checks that the ClaimTerritoryMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateClaimTerritory(ClaimTerritoryAction move) {
    if (validMove == null
        || validMove.getMoveType() != MoveType.CLAIM_TERRITORY) {
      return false;
    }
    ValidClaimTerritoryAction claim = (ValidClaimTerritoryAction) validMove;
    return claim.validClaimTerritory(move);
  }

  /**
   * Checks that the MoveTroopsMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateMoveTroopsMove(MoveTroopsAction move) {
    if (validMove == null || validMove.getMoveType() != MoveType.MOVE_TROOPS) {
      return false;
    }
    ValidMoveTroopsAction moveTroops = (ValidMoveTroopsAction) validMove;
    return moveTroops.validMoveTroopMove(move);
  }

  /**
   * Checks that the SetupMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateSetupMove(SetupAction move) {
    if (validMove == null || validMove.getMoveType() != MoveType.SETUP) {
      return false;
    }
    ValidSetupAction setupMove = (ValidSetupAction) validMove;
    return setupMove.validSetupMove(move);
  }

  protected boolean validateSetupReinforceMove(SetupReinforceAction move) {
    if (validMove == null
        || validMove.getMoveType() != MoveType.SETUP_REINFORCE) {
      return false;
    }
    ValidSetupReinforceAction setupReinforceMove =
        (ValidSetupReinforceAction) validMove;
    return setupReinforceMove.validSetupReinforceMove(move);
  }

  protected boolean playersTurn(RiskPlayer player) {
    if (player.equals(currPlayer)) {
      return true;
    }
    return false;
  }
}
