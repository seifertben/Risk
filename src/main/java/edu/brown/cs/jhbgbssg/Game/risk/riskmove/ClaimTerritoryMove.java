package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Claim Territory Move.
 *
 * @author sarahgilmore
 *
 */
public class ClaimTerritoryMove implements Move {
  private UUID playerId;
  private TerritoryEnum terr;
  private TerritoryEnum from;
  private int troops;

  /**
   * Constructor for ClaimTerritoryMove.
   *
   * @param playerId - player id
   * @param from - territory attacking
   * @param terr - territory claiming
   * @param troops - troops moving
   * @throws IllegalArgumentException if null input
   */
  public ClaimTerritoryMove(UUID playerId, TerritoryEnum from,
      TerritoryEnum terr, int troops) throws IllegalArgumentException {
    if (playerId == null || from == null || terr == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.playerId = playerId;
    this.terr = terr;
    this.troops = troops;
    this.from = from;
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
  public UUID getMovePlayer() {
    return playerId;
  }

  /**
   * Returns the id of the territory claimed.
   *
   * @return territory
   */
  public TerritoryEnum getTerritoryClaimed() {
    return terr;
  }

  /**
   * Returns the id of the attacking territory.
   *
   * @return territory
   */
  public TerritoryEnum getTerritoryFrom() {
    return from;
  }

  /**
   * Returns the number of troops moved.
   *
   * @return number of troops
   */
  public int getNumberTroops() {
    return troops;
  }
}
