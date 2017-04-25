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

  /**
   * Initializes the referee.
   *
   * @param board
   *          - game board
   */
  public Referee(RiskBoard board, Collection<RiskPlayer> playerSet) {
    this.board = board;
    turnOrder = new ArrayList<>(playerSet);
    Collections.shuffle(turnOrder);
    currPlayer = turnOrder.get(0);
    cardPool = new RiskCardPool();
    validMove = new ValidSetupAction(turnOrder.get(0), board);
  }

  public List<UUID> getPlayerOrder() {
    List<UUID> playerOrder = new ArrayList<>();
    for (int i = 0; i < turnOrder.size(); i++) {
      playerOrder.add(turnOrder.get(i).getPlayerId());
    }
    return Collections.unmodifiableList(playerOrder);
  }

  protected ValidAction getFirstSetup() {
    return validMove;
  }

  protected RiskPlayer getWinner() {
    return winner;
  }

  protected boolean isWinner(RiskPlayer player) {
    if (player.getTerritories().containsAll(board.getTerritoryIds())) {
      winner = player;
      return true;
    }
    return false;
  }

  protected boolean playerLost(RiskPlayer player) {
    if (!player.hasTerritories()) {
      turnOrder.remove(player);
      return true;
    }
    return false;
  }

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

  private ValidCardAction getValidCardMove(RiskPlayer player) {
    return new ValidCardAction(player);
  }

  private ValidMoveTroopsAction getValidMoveTroopsMove(RiskPlayer player) {
    validMove = new ValidMoveTroopsAction(player, board);
    return (ValidMoveTroopsAction) validMove;
  }

  private ValidAttackAction getValidAttackMove(RiskPlayer player) {
    validMove = new ValidAttackAction(player, board);
    return (ValidAttackAction) validMove;
  }

  private ValidDieDefendAction getValidDieDefendMove(RiskPlayer player,
      TerritoryEnum toDefend) {
    validMove = new ValidDieDefendAction(player, board, toDefend);
    return (ValidDieDefendAction) validMove;
  }

  private ValidClaimTerritoryAction getValidClaimTerritoryMove(
      RiskPlayer player, AttackAction attack) {
    validMove = new ValidClaimTerritoryAction(player, board, attack);
    return (ValidClaimTerritoryAction) validMove;
  }

  protected ValidAction getValidMoveAfterReinforce(RiskPlayer player) {
    if (player.getCards().size() != 0) {
      validMove = this.getValidCardMove(player);
      return validMove;
    }
    ValidAttackAction move = this.getValidAttackMove(player);
    if (move.actionAvailable()) {
      validMove = move;
      return move;
    }
    ValidMoveTroopsAction troopMove = this.getValidMoveTroopsMove(player);
    if (troopMove.getReachableTerritores().size() != 0) {
      validMove = troopMove;
      return troopMove;
    }
    validMove = null;
    return null;
  }

  protected ValidAction getValidMoveAfterCardTurnIn(RiskPlayer player) {
    ValidAttackAction move = this.getValidAttackMove(player);
    if (move.actionAvailable()) {
      validMove = move;
      return validMove;
    }
    ValidMoveTroopsAction troopMove = this.getValidMoveTroopsMove(player);
    if (troopMove.actionAvailable()) {
      validMove = troopMove;
      return validMove;
    }
    validMove = null;
    return null;
  }

  protected ValidAction getValidMoveAfterAttack(AttackAction attack) {
    TerritoryEnum defending = attack.getDefendingTerritory();
    RiskPlayer defender = board.getTerritory(defending).getOwner();
    ValidDieDefendAction move = this.getValidDieDefendMove(defender,
        attack.getDefendingTerritory());
    validMove = move;
    return validMove;
  }

  protected ValidAction getValidMoveAfterDefend(RiskPlayer player,
      DefendAction defend, AttackAction attack) {
    if (defend.getDefenderLostTerritory()) {
      validMove = this.getValidClaimTerritoryMove(player, attack);
      return validMove;
    }
    ValidAttackAction validAttack = this.getValidAttackMove(player);
    if (validAttack.actionAvailable()) {
      validMove = validAttack;
      return validMove;
    }
    ValidMoveTroopsAction moveTroops = this.getValidMoveTroopsMove(player);
    if (moveTroops.actionAvailable()) {
      validMove = moveTroops;
      return validMove;
    }
    validMove = null;
    return null;
  }

  protected ValidAction getValidMoveAfterClaimTerritory(RiskPlayer player) {
    ValidAttackAction attack = this.getValidAttackMove(player);
    if (attack.actionAvailable()) {
      validMove = attack;
      return validMove;
    }
    ValidMoveTroopsAction moveTroops = this.getValidMoveTroopsMove(player);
    if (moveTroops.actionAvailable()) {
      validMove = moveTroops;
      return validMove;
    }
    validMove = null;
    return validMove;
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
   * @param move
   *          - move to check the validity of
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
   * @param move
   *          - move to check the validity of
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
   * @param move
   *          - move to check the validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateAttackMove(AttackAction move) {
    if (validMove == null
        || validMove.getMoveType() != MoveType.CHOOSE_ATTACK_DIE) {
      return false;
    }
    ValidAttackAction attack = (ValidAttackAction) validMove;
    return attack.validAttackMove(move);
  }

  /**
   * Checks the the DefendMoveis valid.
   *
   * @param move
   *          - move to check validity of
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
   * @param move
   *          - move to check validity of
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
   * @param move
   *          - move to check validity of
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
   * @param move
   *          - move to check validity of
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
}
