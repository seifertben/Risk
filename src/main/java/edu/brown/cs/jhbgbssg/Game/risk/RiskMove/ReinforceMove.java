package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.Map;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ReinforceMove implements Move {
  private UUID playerId;
  private Map<TerritoryEnum, Integer> reinforcedTerritories;

  public ReinforceMove(UUID playerId,
      Map<TerritoryEnum, Integer> reinforcedTerritories) {

  }

  @Override
  public MoveType getMoveType() {
    return MoveType.REINFORCE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public Map<TerritoryEnum, Integer> getReinforcedTerritories() {
    return reinforcedTerritories;
  }
}
