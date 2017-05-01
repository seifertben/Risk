package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

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
public class ValidClaimTerritoryAction implements ValidAction {
  private RiskPlayer player;
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
  public ValidClaimTerritoryAction(RiskPlayer player, RiskBoard board,
      AttackAction attack) throws IllegalArgumentException {
    if (player == null || board == null || attack == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    assert (board.getTerritory(attack.getDefendingTerritory())
        .getNumberTroops() == 0);
    assert (player.hasTerritory(attack.getAttackingTerritory()));
    assert (player.equals(attack.getMovePlayer()));
    this.player = player;
    this.fromTerritory = attack.getAttackingTerritory();
    this.territoryToClaim = attack.getDefendingTerritory();
    Territory attacking = board.getTerritory(fromTerritory);
    System.out.println(attacking.getNumberTroops());
    maxNumberTroops = attacking.getNumberTroops() - 1;
    assert (maxNumberTroops >= 1);
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CLAIM_TERRITORY;
  }

  @Override
  public UUID getMovePlayer() {
    return player.getPlayerId();
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
  public boolean validClaimTerritory(ClaimTerritoryAction move)
      throws IllegalArgumentException {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    RiskPlayer currPlayer = move.getMovePlayer();
    int troops = move.getNumberTroops();
    if (!currPlayer.equals(player)) {
      return false;
    } else if (move.getClaimingTerritory() != territoryToClaim) {
      return false;
    } else if (move.getAttackingTerritory() != fromTerritory) {
      return false;
    } else if (troops < 1 || troops > maxNumberTroops) {
      return false;
    }
    return true;
  }

  @Override
  public boolean actionAvailable() {
    return true;
  }
}
