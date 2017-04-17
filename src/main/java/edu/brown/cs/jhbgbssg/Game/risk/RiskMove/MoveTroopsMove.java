package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class MoveTroopsMove implements Move {
  private UUID playerId;
  private TerritoryEnum from;
  private TerritoryEnum to;
  private int numberTroops;

  public MoveTroopsMove(UUID playerId, TerritoryEnum from, TerritoryEnum to,
      int numberTroops) {
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

  public TerritoryEnum getFromTerritory() {
    return from;
  }

  public TerritoryEnum getToTerrtiory() {
    return to;
  }

  public int troopsMoved() {
    return numberTroops;
  }
}
