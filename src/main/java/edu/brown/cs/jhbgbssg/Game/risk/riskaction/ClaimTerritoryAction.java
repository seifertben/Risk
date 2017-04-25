package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Claim Territory Move.
 *
 * @author sarahgilmore
 *
 */
public class ClaimTerritoryAction implements Action {
  private RiskPlayer player;
  private RiskBoard board;
  private TerritoryEnum attackingTerritory;
  private TerritoryEnum claimingTerritory;
  private int troops;
  private boolean actionExecuted;

  /**
   * Constructor for ClaimTerritoryMove.
   *
   * @param player - player
   * @param board - board
   * @param attackingTerritory - territory attacking
   * @param claimingTerritory - territory claiming
   * @param troops - troops moving
   * @throws IllegalArgumentException if null input
   */
  public ClaimTerritoryAction(RiskPlayer player, RiskBoard board,
      TerritoryEnum attackingTerritory, TerritoryEnum claimingTerritory,
      int troops) throws IllegalArgumentException {
    if (player == null || attackingTerritory == null
        || claimingTerritory == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    this.attackingTerritory = attackingTerritory;
    this.claimingTerritory = claimingTerritory;
    this.troops = troops;

    this.actionExecuted = false;
    this.board = board;
  }

  /**
   * Returns the type of move.
   */
  @Override
  public MoveType getMoveType() {
    return MoveType.CLAIM_TERRITORY;
  }

  /**
   * Returns the player id making the move.
   */
  @Override
  public RiskPlayer getMovePlayer() {
    return player;
  }

  /**
   * Returns the id of the territory claimed.
   *
   * @return territory
   */
  public TerritoryEnum getClaimingTerritory() {
    return claimingTerritory;
  }

  /**
   * Returns the id of the attacking territory.
   *
   * @return territory
   */
  public TerritoryEnum getAttackingTerritory() {
    return attackingTerritory;
  }

  /**
   * Returns the number of troops moved.
   *
   * @return number of troops
   */
  public int getNumberTroops() {
    return troops;
  }

  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      Territory fromTerr = board.getTerritory(attackingTerritory);
      Territory claimedTerr = board.getTerritory(claimingTerritory);
      fromTerr.removeTroops(troops);
      claimedTerr.changePlayer(player, troops);
      player.conqueredTerritory(claimingTerritory);
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
