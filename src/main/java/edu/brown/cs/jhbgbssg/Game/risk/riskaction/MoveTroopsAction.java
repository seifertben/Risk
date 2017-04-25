package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents MoveTroopsMove.
 *
 * @author sarahgilmore
 *
 */
public class MoveTroopsAction implements Action {
  private RiskPlayer player;
  private TerritoryEnum fromTerritory;
  private TerritoryEnum toTerritory;
  private int numberTroops;
  private RiskBoard board;
  private boolean actionExecuted;

  /**
   * Constructor for a MoveTroopsMove.
   *
   * @param player - player
   * @param board - board
   * @param fromTerritory - from territory
   * @param toTerritory - to territory
   * @param numberTroops - number of troops
   * @throws IllegalArgumentException if null input
   */
  public MoveTroopsAction(RiskPlayer player, RiskBoard board,
      TerritoryEnum fromTerritory, TerritoryEnum toTerritory, int numberTroops)
      throws IllegalArgumentException {
    if (player == null || board == null || fromTerritory == null
        || toTerritory == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    this.fromTerritory = fromTerritory;
    this.toTerritory = toTerritory;
    this.numberTroops = numberTroops;
    this.board = board;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.MOVE_TROOPS;
  }

  @Override
  public RiskPlayer getMovePlayer() {
    return player;
  }

  /**
   * Returns from territory.
   *
   * @return territory
   */
  public TerritoryEnum getFromTerritory() {
    return fromTerritory;
  }

  /**
   * Returns to territory.
   *
   * @return territory
   */
  public TerritoryEnum getToTerrtiory() {
    return toTerritory;
  }

  /**
   * Returns the number of troops moved.
   *
   * @return number of troops
   */
  public int troopsMoved() {
    return numberTroops;
  }

  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      Territory terr = board.getTerritory(fromTerritory);
      Territory terr2 = board.getTerritory(toTerritory);
      assert (!terr.removeTroops(numberTroops));
      terr2.addTroops(numberTroops);
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
