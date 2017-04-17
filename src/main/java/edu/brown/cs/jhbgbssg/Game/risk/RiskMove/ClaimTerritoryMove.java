package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ClaimTerritoryMove implements Move {
  private UUID playerId;
  private TerritoryEnum fromTerritory;
  private TerritoryEnum territoryToClaim;
  private int maxNumberTroops;

  public ClaimTerritoryMove(UUID playerId, TerritoryEnum fromTerritory,
      TerritoryEnum territoryToClaim, int maxNumberTroops) {
    this.playerId = playerId;
    this.fromTerritory = fromTerritory;
    this.territoryToClaim = territoryToClaim;
    this.maxNumberTroops = maxNumberTroops;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CLAIM_TERRITORY;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public TerritoryEnum getFromTerritory() {
    return fromTerritory;
  }

  public TerritoryEnum getClaimedTerritory() {
    return territoryToClaim;
  }

  public int getMaxNumberTroops() {
    return maxNumberTroops;
  }
}
