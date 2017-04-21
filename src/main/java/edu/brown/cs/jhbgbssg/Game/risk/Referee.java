package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.AttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.CardTurnInMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.DefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.Move;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ReinforceMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidAttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidCardMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidDieDefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidMoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidReinforceMove;
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
  private Move lastMove;
  private Move validMove = null;

  /**
   * Initializes the referee.
   *
   * @param board - game board
   */
  public Referee(RiskBoard board) {
    this.board = board;
  }

  // protected GameUpdate setRestrictions() {
  // GameUpdate toSend = new GameUpdate();
  // Move availableMoves;
  // UUID playerId = turn.getPlayerId();
  // switch (turn.getPhase()) {
  // case REINFORCE:
  // availableMoves = this.getValidReinforceMove();
  // toSend.setValidMoves(availableMoves);
  // break;
  // case TURN_IN_CARD:
  // availableMoves = this.getValidCardMove();
  // toSend.setValidMoves(availableMoves);
  // break;
  // case CHOOSE_ATTACK_DIE:
  // availableMoves = this.getValidAttackMove();
  // toSend.setValidMoves(availableMoves);
  // break;
  // case CHOOSE_DEFEND_DIE:
  // availableMoves = this.getValidDieDefendMove();
  // toSend.setValidMoves(availableMoves);
  // break;
  // case CLAIM_TERRITORY:
  // availableMoves = this.getValidClaimTerritoryMove();
  // toSend.setValidMoves(availableMoves);
  // break;
  // case MOVE_TROOPS:
  // availableMoves = this.getValidMoveTroopsMove();
  // toSend.setValidMoves(availableMoves);
  // break;
  // }
  // return toSend;
  // }

  protected Move getValidMove() {
    return validMove;
  }

  /**
   * Sets the valid moves for reinforce.
   *
   * @return ValidReinforceMove
   */
  protected ValidReinforceMove getValidReinforceMove(RiskPlayer player) {
    int reinforce = player.getNumberTerritories() / 3;
    Set<TerritoryEnum> territories = player.getTerritories();
    Collection<ContinentInterface> conts = board.getContinents();
    for (ContinentInterface cont : conts) {
      Set<TerritoryEnum> territoriesInCont = cont.getTerritories();
      if (territories.containsAll(territoriesInCont)) {
        reinforce += cont.getBonusValue();
      }
    }
    validMove = new ValidReinforceMove(player.getPlayerId(),
        player.getTerritories(), reinforce);
    return (ValidReinforceMove) validMove;
  }

  private ValidCardMove getValidCardMove(RiskPlayer player) {
    Multiset<Integer> cards = player.getCards();
    Set<TerritoryEnum> territories = player.getTerritories();
    return new ValidCardMove(player.getPlayerId(), cards, territories);
  }

  private ValidMoveTroopsMove getValidMoveTroopsMove(RiskPlayer player) {
    Multimap<TerritoryEnum, TerritoryEnum> canReach = board
        .getMoveableTroops(player);
    Map<TerritoryEnum, Integer> maxMove = new HashMap<>();
    for (TerritoryEnum id : canReach.keySet()) {
      maxMove.put(id, board.getTerritory(id).getNumberTroops() - 1);
    }
    validMove = new ValidMoveTroopsMove(player.getPlayerId(), canReach,
        maxMove);
    return (ValidMoveTroopsMove) validMove;
  }

  private ValidAttackMove getValidAttackMove(RiskPlayer player) {
    UUID playerId = player.getPlayerId();
    Map<TerritoryEnum, Integer> chooseDie = new HashMap<>();
    Multimap<TerritoryEnum, TerritoryEnum> whoToAttack = board
        .getPlayerAttackMap(player);

    Collection<Territory> territories = board.getTerritories();

    for (Territory terr : territories) {
      int numTroops = terr.getNumberTroops();
      int maxDice = Math.min(3, numTroops - 1);
      if (maxDice > 0) {
        chooseDie.put(terr.getTerritoryId(), maxDice);
      }
    }
    validMove = new ValidAttackMove(playerId, chooseDie, whoToAttack);
    return (ValidAttackMove) validMove;
  }

  private ValidDieDefendMove getValidDieDefendMove(RiskPlayer player) {
    UUID playerId = player.getPlayerId();
    Territory terr = board.getTerritory(((AttackMove) lastMove).getAttackTo());
    int troops = terr.getNumberTroops();
    int maxNumDefendDice = 0;
    if (troops >= 2) {
      maxNumDefendDice = 2;
    } else {
      maxNumDefendDice = 1;
    }
    validMove = new ValidDieDefendMove(playerId,
        ((AttackMove) lastMove).getAttackTo(), maxNumDefendDice);
    return (ValidDieDefendMove) validMove;
  }

  private ValidClaimTerritoryMove getValidClaimTerritoryMove(
      RiskPlayer player) {
    UUID playerId = player.getPlayerId();
    Territory attacking = board
        .getTerritory(((DefendMove) lastMove).getAttackingTerritory());
    int maxTroopMove = attacking.getNumberTroops() - 1;
    validMove = new ValidClaimTerritoryMove(playerId,
        ((DefendMove) lastMove).getAttackingTerritory(),
        ((DefendMove) lastMove).getDefendedTerritory(), maxTroopMove);
    return (ValidClaimTerritoryMove) validMove;
  }

  protected Move getValidMoveAfterReinforce(RiskPlayer player) {
    if (player.getCards().size() != 0) {
      return this.getValidCardMove(player);
    }
    ValidAttackMove move = this.getValidAttackMove(player);
    if (move.getAttackableTerritories().size() != 0) {
      return move;
    }
    ValidMoveTroopsMove troopMove = this.getValidMoveTroopsMove(player);
    if (troopMove.getReachableTerritores().size() != 0) {
      return troopMove;
    }
    return null;
  }

  protected Move getValidMoveAfterCardTurnIn(RiskPlayer player) {
    ValidAttackMove move = this.getValidAttackMove(player);
    if (move.getAttackableTerritories().size() != 0) {
      validMove = move;
      return validMove;
    }
    ValidMoveTroopsMove troopMove = this.getValidMoveTroopsMove(player);
    if (troopMove.getReachableTerritores().size() != 0) {
      validMove = troopMove;
      return validMove;
    }
    validMove = null;
    return null;
  }

  protected Move getValidMoveAfterAttack(RiskPlayer player) {
    ValidDieDefendMove move = this.getValidDieDefendMove(player);
    validMove = move;
    return validMove;
  }

  protected Move getValidMoveAfterDefend(RiskPlayer player, DefendMove move) {
    if (move.getDefenderLostTerritory()) {
      validMove = this.getValidClaimTerritoryMove(player);
      return validMove;
    }
    ValidAttackMove attack = this.getValidAttackMove(player);
    if (attack.getAttackableTerritories().size() != 0) {
      validMove = attack;
      return validMove;
    }
    ValidMoveTroopsMove moveTroops = this.getValidMoveTroopsMove(player);
    if (moveTroops.getReachableTerritores().size() != 0) {
      validMove = moveTroops;
      return validMove;
    }
    validMove = null;
    return null;
  }

  protected Move getValidMoveAfterClaimTerritory(RiskPlayer player) {
    ValidAttackMove attack = this.getValidAttackMove(player);
    if (attack.getAttackableTerritories().size() != 0) {
      validMove = attack;
      return validMove;
    }
    ValidMoveTroopsMove moveTroops = this.getValidMoveTroopsMove(player);
    if (moveTroops.getReachableTerritores().size() != 0) {
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
}
