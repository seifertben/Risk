package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidMoveTroopsMove implements Move {
  private UUID playerId;
  private Multimap<TerritoryEnum, Territory> whereToReach;
  private Map<TerritoryEnum, Integer> maxTroopsToMove;

  public ValidMoveTroopsMove(UUID playerId,
      Multimap<TerritoryEnum, Territory> whereToReach,
      Map<TerritoryEnum, Integer> maxTroopsToMove) {
    this.playerId = playerId;
    this.whereToReach = whereToReach;
    this.maxTroopsToMove = maxTroopsToMove;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.MOVE_TROOPS;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public Collection<Entry<TerritoryEnum, Territory>> getReachableTerritores() {
    return whereToReach.entries();
  }

  public Set<Entry<TerritoryEnum, Integer>> maxTroopsToMove() {
    return maxTroopsToMove.entrySet();
  }
}
