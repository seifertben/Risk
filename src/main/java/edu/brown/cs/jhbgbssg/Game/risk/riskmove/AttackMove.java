package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.Die;
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
  private Die die = new Die();

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
    dieRolledResult = new ArrayList<>();
    for (int i = 0; i < dieRolled; i++) {
      dieRolledResult.add(die.roll());
    }
    Collections.sort(dieRolledResult);
    Collections.reverse(dieRolledResult);
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
   * Returns an unmodifiable list of the die results.
   *
   * @return list of integers
   */
  public List<Integer> getDieResults() {
    return Collections.unmodifiableList(dieRolledResult);
  }
}
