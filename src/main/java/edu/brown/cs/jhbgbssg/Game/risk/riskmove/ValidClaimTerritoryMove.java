package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Defines ValidClaimTerritoryMove.
 *
 * @author sarahgilmore
 *
 */
public class ValidClaimTerritoryMove implements ValidAction {
  private UUID playerId;
  private TerritoryEnum fromTerritory;
  private TerritoryEnum territoryToClaim;
  private int maxNumberTroops;

  /**
   * Constructor for ValidClaimTerritoryMove.
   *
   * @param player - player
   * @param board - game board
   * @param attack - attack move
   * @throws IllegalArgumentException if the input is null
   */
  public ValidClaimTerritoryMove(RiskPlayer player, RiskBoard board,
      AttackMove attack) throws IllegalArgumentException {
    if (player == null || board == null || attack == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    assert (board.getTerritory(attack.getAttackTo()).getNumberTroops() == 0);
    assert (player.hasTerritory(attack.getAttackFrom()));
    this.playerId = player.getPlayerId();
    this.fromTerritory = attack.getAttackFrom();
    this.territoryToClaim = attack.getAttackTo();
    Territory attacking = board.getTerritory(fromTerritory);
    maxNumberTroops = attacking.getNumberTroops() - 1;
    assert (maxNumberTroops >= 1);
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CLAIM_TERRITORY;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  /**
   * Get territory troops must move from.
   *
   * @return territory
   */
  public TerritoryEnum getFromTerritory() {
    return fromTerritory;
  }

  /**
   * Gets territory player can claim.
   *
   * @return territory
   */
  public TerritoryEnum getClaimedTerritory() {
    return territoryToClaim;
  }

  /**
   * Get max number of troops player can move.
   *
   * @return number of troops
   */
  public int getMaxNumberTroops() {
    return maxNumberTroops;
  }

  /**
   * Checks that a ClaimTerritoryMove is valid according to the bounds defined
   * by this object.
   *
   * @param move - move player wants to make
   * @return true if the move is valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  public boolean validClaimTerritory(ClaimTerritoryMove move)
      throws IllegalArgumentException {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    if (move.getTerritoryClaimed() != territoryToClaim) {
      return false;
    }
    if (move.getTerritoryFrom() != fromTerritory) {
      return false;
    }
    int troops = move.getNumberTroops();
    if (troops < 1 || troops > maxNumberTroops) {
      return false;
    }
    return true;
  }

  @Override
  public boolean actionAvailable() {
    return true;
  }
}
