package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents a SetupMove.
 *
 * @author sarahgilmore
 *
 */
public class SetupAction implements Action {
  private RiskPlayer player;
  private TerritoryEnum territorySelected;
  private boolean actionExecuted;
  private RiskBoard board;

  /**
   * Constructor.
   *
   * @param player - player
   * @param board - board
   * @param selected - territory to select
   * @throws IllegalArgumentException - if the input is null
   */
  public SetupAction(RiskPlayer player, RiskBoard board, TerritoryEnum selected)
      throws IllegalArgumentException {
    if (player == null || board == null || selected == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    this.board = board;
    this.territorySelected = selected;
    this.actionExecuted = false;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.SETUP;
  }

  @Override
  public RiskPlayer getMovePlayer() {
    return player;
  }

  /**
   * Returns the selected territory.
   *
   * @return territory id
   */
  public TerritoryEnum getSelectedTerritory() {
    return territorySelected;
  }

  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      player.conqueredTerritory(territorySelected);
      Territory terr = board.getTerritory(territorySelected);
      player.decrementInitialReinforcements(1);
      terr.changePlayer(player, 1);
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
