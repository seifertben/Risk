package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

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
    actionExecuted = false;
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

  /**
   * Returns if the action has been executed.
   */
  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  /**
   * Executes the reinforcement action.
   */
  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      player.decrementInitialReinforcements(1);
      board.getTerritory(territorySelected).addTroops(1);
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
