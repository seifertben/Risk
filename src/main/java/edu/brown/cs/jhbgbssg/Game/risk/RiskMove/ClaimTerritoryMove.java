package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ClaimTerritoryMove implements Move {
  private UUID playerId;
  private TerritoryEnum terr;
  private int troops;

  public ClaimTerritoryMove(UUID playerId, TerritoryEnum terr, int troops) {
    this.playerId = playerId;
    this.terr = terr;
    this.troops = troops;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CLAIM_TERRITORY;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public TerritoryEnum getTerritoryClaimed() {
    return terr;
  }

  public int getNumberTroops() {
    return troops;
  }
}
