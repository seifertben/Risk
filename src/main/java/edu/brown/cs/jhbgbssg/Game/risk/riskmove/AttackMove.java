package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents an attack.
 *
 * @author sarahgilmore
 *
 */
public class AttackMove implements Move {
  private UUID playerId;
  private TerritoryEnum attackFrom;
  private TerritoryEnum attackTo;
  private int dieRolled;
  private List<Integer> dieRolledResult;

  /**
   * Constructor for an AttackMove.
   *
   * @param playerId - id of player attacking
   * @param attackFrom - territory attacking from
   * @param attackTo - territory attacking
   * @param dieRolled - number of die rolled
   * @throws IllegalArgumentException if the input is list
   */
  public AttackMove(UUID playerId, TerritoryEnum attackFrom,
      TerritoryEnum attackTo, int dieRolled) {
    if (playerId == null || attackFrom == null || attackTo == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.playerId = playerId;
    this.attackFrom = attackFrom;
    this.attackTo = attackTo;
    this.dieRolled = dieRolled;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_ATTACK_DIE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  /**
   * Returns territory attacking from.
   *
   * @return territory id being attacked
   */
  public TerritoryEnum getAttackFrom() {
    return attackFrom;
  }

  /**
   * Returns territory attacking.
   *
   * @return territory attacking
   */
  public TerritoryEnum getAttackTo() {
    return attackTo;
  }

  /**
   * Returns number of die rolled.
   *
   * @return number of die rolled
   */
  public int getDieRolled() {
    return dieRolled;
  }

  /**
   * Sets the result of rolling the die.
   *
   * @param result - result of rolling die
   * @throws IllegalArgumentException - if the list is null or if the die has
   *           already been rolled
   */
  public void setDieResult(List<Integer> result)
      throws IllegalArgumentException {
    if (result == null) {
      throw new IllegalArgumentException("ERROR: null list");
    } else if (result.size() != dieRolled) {
      throw new IllegalArgumentException("ERROR: wrong number of die rolled");
    } else if (dieRolledResult != null) {
      throw new IllegalArgumentException("ERROR: cannot roll die twice");
    }
    for (int i = 0; i < dieRolled; i++) {
      if (result.get(i) < 1 || result.get(i) > 6) {
        throw new IllegalArgumentException("ERROR: bad die values");
      }
    }
    this.dieRolledResult = new ArrayList<>(result);
  }

  /**
   * Returns an unmodifiable list of the die results.
   *
   * @return list of integers
   */
  public List<Integer> getDieResults() {
    return Collections.unmodifiableList(dieRolledResult);
  }
}
