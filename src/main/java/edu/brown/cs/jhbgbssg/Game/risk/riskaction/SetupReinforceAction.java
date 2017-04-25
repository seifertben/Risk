package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 *
 * @author sarahgilmore
 *
 */
public class SetupReinforceAction implements Action {
  private RiskPlayer player;
  private RiskBoard board;
  private TerritoryEnum territorySelected;
  private boolean actionExecuted;

  /**
   * Constructor.
   *
   * @param player - player
   * @param board - board
   * @param selected - selected
   * @throws IllegalArgumentException if the input is null
   */
  public SetupReinforceAction(RiskPlayer player, RiskBoard board,
      TerritoryEnum selected) throws IllegalArgumentException {
    if (player == null || board == null || selected == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    this.board = board;
    territorySelected = selected;
    actionExecuted = true;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.SETUP_REINFORCE;
  }

  @Override
  public RiskPlayer getMovePlayer() {
    return player;
  }

  /**
   * Gets the selected territory.
   *
   * @return territory
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
      player.decrementInitialReinforcements(1);
      board.addTroops(territorySelected, 1);
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }

}
