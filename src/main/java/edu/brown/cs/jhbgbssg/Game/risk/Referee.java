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
  private Turn turn;
  private Move lastMove;
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

  protected GameUpdate setRestrictions() {
    GameUpdate toSend = new GameUpdate();
    Move availableMoves;
    UUID playerId = turn.getPlayerId();
    switch (turn.getPhase()) {
      case REINFORCE:
        availableMoves = this.getValidReinforceMove();
        toSend.setValidMoves(availableMoves);
        break;
      case TURN_IN_CARD:
        availableMoves = this.getValidCardMove();
        toSend.setValidMoves(availableMoves);
        break;
      case CHOOSE_ATTACK_DIE:
        availableMoves = this.getValidAttackMove();
        toSend.setValidMoves(availableMoves);
        break;
      case CHOOSE_DEFEND_DIE:
        availableMoves = this.getValidDieDefendMove();
        toSend.setValidMoves(availableMoves);
        break;
      case CLAIM_TERRITORY:
        availableMoves = this.getValidClaimTerritoryMove();
        toSend.setValidMoves(availableMoves);
        break;
      case MOVE_TROOPS:
        availableMoves = this.getValidMoveTroopsMove();
        toSend.setValidMoves(availableMoves);
        break;
    }
    return toSend;
  }

  /**
   * Sets the valid moves for reinforce.
   *
   * @return ValidReinforceMove
   */
  private ValidReinforceMove getValidReinforceMove() {
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
    ValidReinforceMove valid = new ValidReinforceMove(player.getPlayerId(),
        player.getTerritories(), reinforce);
    return valid;
  }

  private ValidCardMove getValidCardMove() {
    RiskPlayer player = turn.getPlayer();
    Multiset<Integer> cards = player.getCards();
    Set<TerritoryEnum> territories = player.getTerritories();
    return new ValidCardMove(player.getPlayerId(), cards, territories);
  }

  private ValidMoveTroopsMove getValidMoveTroopsMove() {
    RiskPlayer player = turn.getPlayer();
    Multimap<TerritoryEnum, TerritoryEnum> canReach = board
        .getMoveableTroops(player);
    Map<TerritoryEnum, Integer> maxMove = new HashMap<>();
    for (TerritoryEnum id : canReach.keySet()) {
      maxMove.put(id, board.getTerritory(id).getNumberTroops() - 1);
    }
    return new ValidMoveTroopsMove(player.getPlayerId(), canReach, maxMove);
  }

  private ValidAttackMove getValidAttackMove() {
    UUID playerId = turn.getPlayerId();
    RiskPlayer player = turn.getPlayer();

    Map<TerritoryEnum, Integer> chooseDie = new HashMap<>();
    Multimap<TerritoryEnum, TerritoryEnum> whoToAttack = board
        .getPlayerAttackMap(player);

    Collection<Territory> territories = board.getTerritories();

    for (Territory terr : territories) {
      int numTroops = terr.getNumberTroops();
      int maxDice = Math.min(3, numTroops - 1);
      chooseDie.put(terr.getTerritoryId(), maxDice);
    }
    ValidAttackMove move = new ValidAttackMove(playerId, chooseDie,
        whoToAttack);
    return move;
  }

  private ValidDieDefendMove getValidDieDefendMove() {
    UUID playerId = turn.getPlayerId();
    Territory terr = board.getTerritory(((AttackMove) lastMove).getAttackTo());
    int troops = terr.getNumberTroops();
    int maxNumDefendDice = 0;
    if (troops >= 2) {
      maxNumDefendDice = 2;
    } else {
      maxNumDefendDice = 1;
    }
    ValidDieDefendMove move = new ValidDieDefendMove(playerId,
        ((AttackMove) lastMove).getAttackTo(), maxNumDefendDice);
    return move;
  }

  private ValidClaimTerritoryMove getValidClaimTerritoryMove() {
    UUID playerId = turn.getPlayerId();
    Territory attacking = board
        .getTerritory(((DefendMove) lastMove).getAttackingTerritory());
    int maxTroopMove = attacking.getNumberTroops() - 1;
    ValidClaimTerritoryMove move = new ValidClaimTerritoryMove(playerId,
        ((DefendMove) lastMove).getAttackingTerritory(),
        ((DefendMove) lastMove).getDefendedTerritory(), maxTroopMove);
    return move;
  }

  protected boolean validateReinforce(ReinforceMove move) {
    // check can do so
    if (validReinforce == null) {
      return false;
    }
    return validReinforce.validReinforceMove(move);
  }

  protected boolean validateCardTurnIn(CardTurnInMove move) {
    if (validCard == null) {
      return false;
    }
    return validCard.validateCardMove(move);
  }

  protected boolean validateAttackMove(AttackMove move) {
    if (validAttack == null) {
      return false;
    }
    return validAttack.validAttackMove(move);
  }

  protected boolean validateDefendMove(DefendMove move) {
    // TODO : Fill in
    return false;
  }

  protected boolean validateClaimTerritory(ClaimTerritoryMove move) {
    if (validClaim == null) {
      return false;
    }
    return validClaim.validClaimTerritory(move);
  }

  protected boolean validateMoveTroopsMove(MoveTroopsMove move) {
    if (validMove == null) {
      return false;
    }
    return validMove.validMoveTroopMove(move);
  }

  private boolean validateEndMove(EndMove move) {
    // TODO : fill in
    return false;
  }
}
