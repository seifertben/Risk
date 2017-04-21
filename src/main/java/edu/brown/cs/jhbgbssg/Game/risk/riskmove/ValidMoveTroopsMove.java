package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents a Valid MoveTroops move.
 *
 * @author sarahgilmore
 *
 */
public class ValidMoveTroopsMove implements ValidAction {
  private UUID playerId;
  private Multimap<TerritoryEnum, TerritoryEnum> whereToReach;
  private Map<TerritoryEnum, Integer> maxTroopsToMove;
  private boolean canMove;

  /**
   * Constructor for ValidMoveTroopsMove.
   *
   * @param player - player
   * @param board - board
   * @throws IllegalArgumentException if input is null
   */
  public ValidMoveTroopsMove(RiskPlayer player, RiskBoard board)
      throws IllegalArgumentException {
    if (player == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    playerId = player.getPlayerId();
    whereToReach = board.getMoveableTroops(player);
    maxTroopsToMove = new HashMap<>();
    for (TerritoryEnum id : whereToReach.keySet()) {
      maxTroopsToMove.put(id, board.getTerritory(id).getNumberTroops() - 1);
    }
    if (whereToReach.size() == 0 || maxTroopsToMove.size() == 0) {
      canMove = false;
    }
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
   * Returns the reachable territories.
   *
   * @return pairs of territories
   */
  public Collection<Entry<TerritoryEnum, TerritoryEnum>> getReachableTerritores() {
    return Collections.unmodifiableCollection(whereToReach.entries());
  }

  /**
   * Returns the max number of territories that can be moved from a territory.
   *
   * @return pair of territories and integers
   */
  public Set<Entry<TerritoryEnum, Integer>> maxTroopsToMove() {
    return Collections.unmodifiableSet(maxTroopsToMove.entrySet());
  }

  /**
   * Determines if a given MoveTroopsMove is valid.
   *
   * @param move - move to validate
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  public boolean validMoveTroopMove(MoveTroopsMove move)
      throws IllegalArgumentException {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
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

  @Override
  public boolean actionAvailable() {
    return canMove;
  }
}
