package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ClaimTerritoryMove implements Move {
  private UUID playerId;
  private TerritoryEnum terr;
  private TerritoryEnum from;
  private int troops;

  public ClaimTerritoryMove(UUID playerId, TerritoryEnum from,
      TerritoryEnum terr, int troops) {
    this.playerId = playerId;
    this.terr = terr;
    this.troops = troops;
    this.from = from;
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

  public TerritoryEnum getTerritoryFrom() {
    return from;
  }

  public int getNumberTroops() {
    return troops;
  }
}
