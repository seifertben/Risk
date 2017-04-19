package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.Map;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Defines a ReinforceMove.
 *
 * @author sarahgilmore
 *
 */
public class ReinforceMove implements Move {
  private UUID playerId;
  private Map<TerritoryEnum, Integer> reinforcedTerritories;

  /**
   * Constructor for reinforce move.
   *
   * @param playerId - id of player making the move
   * @param reinforcedTerritories - territories to reinforce
   * @throws IllegalArgumentException if the input is null
   */
  public ReinforceMove(UUID playerId,
      Map<TerritoryEnum, Integer> reinforcedTerritories)
      throws IllegalArgumentException {
    if (playerId == null || reinforcedTerritories == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.playerId = playerId;
    this.reinforcedTerritories = reinforcedTerritories;
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
  public UUID getMovePlayer() {
    return playerId;
  }

  /**
   * Returns the territories reinforced.
   *
   * @return map of territories to integers
   */
  public Map<TerritoryEnum, Integer> getReinforcedTerritories() {
    return reinforcedTerritories;
  }
}
