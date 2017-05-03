package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.Map;
import java.util.Map.Entry;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 * Defines a ReinforceMove.
 *
 * @author sarahgilmore
 *
 */
public class ReinforceAction implements Action {
  private RiskPlayer player;
  private RiskBoard board;
  private boolean actionExecuted;
  private Map<TerritoryEnum, Integer> reinforcedTerritories;

  /**
   * Constructor for reinforce move.
   *
   * @param player - player making the move
   * @param board - board
   * @param reinforcedTerritories - territories to reinforce
   * @throws IllegalArgumentException if the input is null
   */
  public ReinforceAction(RiskPlayer player, RiskBoard board,
      Map<TerritoryEnum, Integer> reinforcedTerritories)
      throws IllegalArgumentException {
    if (player == null || reinforcedTerritories == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    this.board = board;
    this.reinforcedTerritories = reinforcedTerritories;
    this.actionExecuted = false;
  }

  /**
   * Returns the move type.
   */
  @Override
  public MoveType getMoveType() {
    return MoveType.REINFORCE;
  }

  /**
   * Returns the playerId executing the move.
   */
  @Override
  public RiskPlayer getMovePlayer() {
    return player;
  }

  /**
   * Returns the territories reinforced.
   *
   * @return map of territories to integers
   */
  public Map<TerritoryEnum, Integer> getReinforcedTerritories() {
    return reinforcedTerritories;
  }

  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      for (Entry<TerritoryEnum, Integer> entry : reinforcedTerritories
          .entrySet()) {
        Territory terr = board.getTerritory(entry.getKey());
        terr.addTroops(entry.getValue());
      }
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
