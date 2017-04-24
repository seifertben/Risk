package edu.brown.cs.jhbgbssg.Game.risk;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.AttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.CardTurnInMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.DefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.Move;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ReinforceMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.SetupMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidAttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidCardMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidDieDefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidMoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidReinforceMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidSetupMove;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Controls the rules of the game.
 *
 * @author Ben
 *
 */
public class Referee {
  private RiskBoard board;
  private ValidAction validMove = null;

  /**
   * Initializes the referee.
   *
   * @param board - game board
   */
  public Referee(RiskBoard board) {
    this.board = board;
  }

  protected ValidAction getValidMove() {
    return validMove;
  }

  /**
   * Sets the valid moves for reinforce.
   *
   * @return ValidReinforceMove
   */
  protected ValidReinforceMove getValidReinforceMove(RiskPlayer player) {

    validMove = new ValidReinforceMove(player, board);
    return (ValidReinforceMove) validMove;
  }

  private ValidCardMove getValidCardMove(RiskPlayer player) {
    return new ValidCardMove(player);
  }

  private ValidMoveTroopsMove getValidMoveTroopsMove(RiskPlayer player) {
    validMove = new ValidMoveTroopsMove(player, board);
    return (ValidMoveTroopsMove) validMove;
  }

  private ValidAttackMove getValidAttackMove(RiskPlayer player) {
    validMove = new ValidAttackMove(player, board);
    return (ValidAttackMove) validMove;
  }

  private ValidDieDefendMove getValidDieDefendMove(RiskPlayer player,
      TerritoryEnum toDefend) {
    validMove = new ValidDieDefendMove(player, board, toDefend);
    return (ValidDieDefendMove) validMove;
  }

  private ValidClaimTerritoryMove getValidClaimTerritoryMove(RiskPlayer player,
      AttackMove attack) {
    validMove = new ValidClaimTerritoryMove(player, board, attack);
    return (ValidClaimTerritoryMove) validMove;
  }

  protected ValidAction getValidMoveAfterReinforce(RiskPlayer player) {
    if (player.getCards().size() != 0) {
      validMove = this.getValidCardMove(player);
      return validMove;
    }
    ValidAttackMove move = this.getValidAttackMove(player);
    if (move.actionAvailable()) {
      validMove = move;
      return move;
    }
    ValidMoveTroopsMove troopMove = this.getValidMoveTroopsMove(player);
    if (troopMove.getReachableTerritores().size() != 0) {
      validMove = troopMove;
      return troopMove;
    }
    validMove = null;
    return null;
  }

  protected ValidAction getValidMoveAfterCardTurnIn(RiskPlayer player) {
    ValidAttackMove move = this.getValidAttackMove(player);
    if (move.actionAvailable()) {
      validMove = move;
      return validMove;
    }
    ValidMoveTroopsMove troopMove = this.getValidMoveTroopsMove(player);
    if (troopMove.actionAvailable()) {
      validMove = troopMove;
      return validMove;
    }
    validMove = null;
    return null;
  }

  protected ValidAction getValidMoveAfterAttack(RiskPlayer player,
      TerritoryEnum defend) {
    ValidDieDefendMove move = this.getValidDieDefendMove(player, defend);
    validMove = move;
    return validMove;
  }

  protected ValidAction getValidMoveAfterDefend(RiskPlayer player,
      DefendMove defend, AttackMove attack) {
    if (defend.getDefenderLostTerritory()) {
      validMove = this.getValidClaimTerritoryMove(player, attack);
      return validMove;
    }
    ValidAttackMove validAttack = this.getValidAttackMove(player);
    if (validAttack.actionAvailable()) {
      validMove = validAttack;
      return validMove;
    }
    ValidMoveTroopsMove moveTroops = this.getValidMoveTroopsMove(player);
    if (moveTroops.actionAvailable()) {
      validMove = moveTroops;
      return validMove;
    }
    validMove = null;
    return null;
  }

  protected ValidAction getValidMoveAfterClaimTerritory(RiskPlayer player) {
    ValidAttackMove attack = this.getValidAttackMove(player);
    if (attack.actionAvailable()) {
      validMove = attack;
      return validMove;
    }
    ValidMoveTroopsMove moveTroops = this.getValidMoveTroopsMove(player);
    if (moveTroops.actionAvailable()) {
      validMove = moveTroops;
      return validMove;
    }
    validMove = null;
    return validMove;
  }

  protected Move getValidDefendMoveAfterTroopMove(RiskPlayer player,
      DefendMove move) {
    return null;
  }

  /**
   * Checks that the ReinforceMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateReinforce(ReinforceMove move) {
    if (validMove == null || validMove.getMoveType() != MoveType.REINFORCE) {
      return false;
    }
    ValidReinforceMove reinforce = (ValidReinforceMove) validMove;
    return reinforce.validReinforceMove(move);
  }

  /**
   * Checks that the CardTurnInMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateCardTurnIn(CardTurnInMove move) {
    if (validMove == null || validMove.getMoveType() != MoveType.TURN_IN_CARD) {
      return false;
    }
    ValidCardMove card = (ValidCardMove) validMove;
    return card.validateCardMove(move);
  }

  /**
   * Checks that the AttackMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateAttackMove(AttackMove move) {
    if (validMove == null
        || validMove.getMoveType() != MoveType.CHOOSE_ATTACK_DIE) {
      return false;
    }
    ValidAttackMove attack = (ValidAttackMove) validMove;
    return attack.validAttackMove(move);
  }

  /**
   * Checks the the DefendMoveis valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateDefendMove(DefendMove move) {
    if (validMove == null
        || validMove.getMoveType() != MoveType.CHOOSE_DEFEND_DIE) {
      return false;
    }
    ValidDieDefendMove defend = (ValidDieDefendMove) validMove;
    return defend.validateDefendMove(move);
  }

  /**
   * Checks that the ClaimTerritoryMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateClaimTerritory(ClaimTerritoryMove move) {
    if (validMove == null
        || validMove.getMoveType() != MoveType.CLAIM_TERRITORY) {
      return false;
    }
    ValidClaimTerritoryMove claim = (ValidClaimTerritoryMove) validMove;
    return claim.validClaimTerritory(move);
  }

  /**
   * Checks that the MoveTroopsMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateMoveTroopsMove(MoveTroopsMove move) {
    if (validMove == null || validMove.getMoveType() != MoveType.MOVE_TROOPS) {
      return false;
    }
    ValidMoveTroopsMove moveTroops = (ValidMoveTroopsMove) validMove;
    return moveTroops.validMoveTroopMove(move);
  }

  /**
   * Checks that the SetupMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   */
  protected boolean validateSetupMove(SetupMove move) {
    if (validMove == null || validMove.getMoveType() != MoveType.SETUP) {
      return false;
    }
    ValidSetupMove setupMove = (ValidSetupMove) validMove;
    return setupMove.validSetupMove(move);
  }
}
