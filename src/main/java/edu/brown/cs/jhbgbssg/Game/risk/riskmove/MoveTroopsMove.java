package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents MoveTroopsMove.
 *
 * @author sarahgilmore
 *
 */
public class MoveTroopsMove implements Move {
  private UUID playerId;
  private TerritoryEnum from;
  private TerritoryEnum to;
  private int numberTroops;

  /**
   * Constructor for a MoveTroopsMove.
   *
   * @param playerId - player id
   * @param from - from territory
   * @param to - to territory
   * @param numberTroops - number of troops
   * @throws IllegalArgumentException if null input
   */
  public MoveTroopsMove(UUID playerId, TerritoryEnum from, TerritoryEnum to,
      int numberTroops) throws IllegalArgumentException {
    if (playerId == null || from == null || to == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.playerId = playerId;
    this.from = from;
    this.to = to;
    this.numberTroops = numberTroops;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.MOVE_TROOPS;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  /**
   * Returns from territory.
   *
   * @return territory
   */
  public TerritoryEnum getFromTerritory() {
    return from;
  }

  /**
   * Returns to territory.
   *
   * @return territory
   */
  public TerritoryEnum getToTerrtiory() {
    return to;
  }

  /**
   * Returns the number of troops moved.
   *
   * @return number of troops
   */
  public int troopsMoved() {
    return numberTroops;
  }
}
