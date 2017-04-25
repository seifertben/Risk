package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

  public ValidAction getFirstSetup() {
    if (!gameStarted) {
      gameStarted = true;
      return validMove;
    }
    return null;
  }

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
   * @return
   */
  protected RiskPlayer getWinner() {
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

  protected ValidAction switchPlayer() {
    int index = turnOrder.indexOf(currPlayer);
    index = (index + 1) % turnOrder.size();
    currPlayer = turnOrder.get(index);
    validMove = this.getValidReinforceMove(currPlayer);
    return validMove;
  }

  protected boolean emptyCardDeck() {
    return cardPool.isEmpty();
  }

  protected ValidAction getValidMove() {
    return validMove;
  }

  /**
   * Sets the valid moves for reinforce.
   *
   * @return ValidReinforceMove
   */
  protected ValidReinforceAction getValidReinforceMove(RiskPlayer player) {
    validMove = new ValidReinforceAction(player, board);
    return (ValidReinforceAction) validMove;
  }

  /**
   * Gets the next valid action after a player has reinforced. If current player
   * does not have any valid moves left, the method returns null.
   *
   * @param player
   * @return ValidAction - next set of valid actions
   */
  protected ValidAction getValidMoveAfterReinforce() {
    if (currPlayer.getCards().size() != 0) {
      validMove = new ValidCardAction(currPlayer);
      return validMove;
    }
    ValidAttackAction move = new ValidAttackAction(currPlayer, board);
    if (move.actionAvailable()) {
      validMove = move;
      return move;
    }
    ValidMoveTroopsAction troopMove = new ValidMoveTroopsAction(currPlayer,
        board);
    if (troopMove.getReachableTerritores().size() != 0) {
      validMove = troopMove;
      return troopMove;
    }
    return null;
  }

  protected ValidAction getValidMoveAfterCardTurnIn() {
    ValidAttackAction move = new ValidAttackAction(currPlayer, board);
    if (move.actionAvailable()) {
      validMove = move;
      return validMove;
    }
    ValidMoveTroopsAction troopMove = new ValidMoveTroopsAction(currPlayer,
        board);
    if (troopMove.actionAvailable()) {
      validMove = troopMove;
      return validMove;
    }
    return null;
  }

  protected ValidAction getValidMoveAfterAttack() {
    TerritoryEnum defending = lastAttack.getDefendingTerritory();
    RiskPlayer defender = board.getTerritory(defending).getOwner();
    ValidDieDefendAction move = new ValidDieDefendAction(defender, board,
        lastAttack.getDefendingTerritory());
    validMove = move;
    return null;
  }

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
    ValidMoveTroopsAction moveTroops = new ValidMoveTroopsAction(currPlayer,
        board);
    if (moveTroops.actionAvailable()) {
      lastAttack = null;
      validMove = moveTroops;
      return validMove;
    }
    lastAttack = null;
    return null;
  }

  protected ValidAction getValidMoveAfterClaimTerritory() {
    ValidAttackAction attack = new ValidAttackAction(currPlayer, board);
    if (attack.actionAvailable()) {
      validMove = attack;
      return validMove;
    }
    ValidMoveTroopsAction moveTroops = new ValidMoveTroopsAction(currPlayer,
        board);
    if (moveTroops.actionAvailable()) {
      validMove = moveTroops;
      return validMove;
    }
    return null;
  }

  protected Action getValidDefendMoveAfterTroopMove(RiskPlayer player,
      DefendAction move) {
    return null;
  }

  protected Action getValidMoveAfterSetup(RiskPlayer player, SetupAction move) {
    return null;
  }

  public ValidAction getValidMoveAfterReinforceSetup(RiskPlayer riskPlayer,
      SetupReinforceAction setupReinforceMove) {
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
    if (validAttack.validAttackMove(move)) {
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
    ValidSetupReinforceAction setupReinforceMove = (ValidSetupReinforceAction) validMove;
    return setupReinforceMove.validSetupReinforceMove(move);
  }

  protected boolean playersTurn(RiskPlayer player) {
    if (player.equals(currPlayer)) {
      return true;
    }
    return false;
  }
}
