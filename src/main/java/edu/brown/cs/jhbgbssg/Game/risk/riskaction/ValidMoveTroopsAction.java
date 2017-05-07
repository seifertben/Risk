package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * Represents a Valid MoveTroops move.
 *
 * @author sarahgilmore
 *
 */
public class ValidMoveTroopsAction implements ValidAction {
  private RiskPlayer player;
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
  public ValidMoveTroopsAction(RiskPlayer player, RiskBoard board)
      throws IllegalArgumentException {
    if (player == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    whereToReach = board.getMoveableTroops(player);
    maxTroopsToMove = new HashMap<>();
    for (TerritoryEnum id : whereToReach.keySet()) {
      maxTroopsToMove.put(id, board.getTerritory(id).getNumberTroops() - 1);
    }
    if (whereToReach.size() == 0 || maxTroopsToMove.size() == 0) {
      canMove = false;
    } else {
      canMove = true;
    }
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.MOVE_TROOPS;
  }

  @Override
  public UUID getMovePlayer() {
    return player.getPlayerId();
  }

  /**
   * Returns the reachable territories.
   *
   * @return pairs of territories
   */
  public Multimap<TerritoryEnum, TerritoryEnum> getReachableTerritores() {
    return whereToReach;
  }

  /**
   * Returns the max number of territories that can be moved from a territory.
   *
   * @return pair of territories and integers
   */
  public Map<TerritoryEnum, Integer> maxTroopsToMove() {
    return Collections.unmodifiableMap(maxTroopsToMove);
  }

  /**
   * Determines if a given MoveTroopsMove is valid.
   *
   * @param move - move to validate
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  public boolean validMoveTroopMove(MoveTroopsAction move)
      throws IllegalArgumentException {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    RiskPlayer currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(player)) {
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
