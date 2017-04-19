package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidMoveTroopsMove implements Move {
  private UUID playerId;
  private Multimap<TerritoryEnum, TerritoryEnum> whereToReach;
  private Map<TerritoryEnum, Integer> maxTroopsToMove;

  public ValidMoveTroopsMove(UUID playerId,
      Multimap<TerritoryEnum, TerritoryEnum> whereToReach,
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

  public Collection<Entry<TerritoryEnum, TerritoryEnum>> getReachableTerritores() {
    return whereToReach.entries();
  }

  public Set<Entry<TerritoryEnum, Integer>> maxTroopsToMove() {
    return maxTroopsToMove.entrySet();
  }

  public boolean validMoveTroopMove(MoveTroopsMove move) {
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    TerritoryEnum from = move.getFromTerritory();
    TerritoryEnum to = move.getToTerrtiory();
    int moved = move.troopsMoved();
    if (!whereToReach.containsEntry(from, to)) {
      return false;
    }
    int maxNumber = maxTroopsToMove.get(from);
    if (moved < 1 || moved > maxNumber) {
      return false;
    }
    return true;
  }
}
