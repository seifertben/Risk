package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

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

  /**
   * Returns the MoveType: MOVE_TROOPS.
   */
  @Override
  public MoveType getMoveType() {
    return MoveType.MOVE_TROOPS;
  }

  /**
   * Returns the player moving troops.
   */
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

  /**
   * Returns a boolean indicating if the move troops action has been executed.
   */
  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  /**
   * Executes the move troops action.
   */
  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      Territory fromTerr = board.getTerritory(fromTerritory);
      Territory toTerr = board.getTerritory(toTerritory);
      System.out.println("troops before " + fromTerr.getNumberTroops());
      fromTerr.removeTroops(numberTroops);
      toTerr.addTroops(numberTroops);
      System.out.println("troops after " + fromTerr.getNumberTroops());
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
