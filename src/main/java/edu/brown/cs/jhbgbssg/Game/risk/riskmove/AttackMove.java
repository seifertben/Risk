package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.List;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

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
   * @return
   */
  public TerritoryEnum getAttackFrom() {
    return attackFrom;
  }

  /**
   * Returns territory attacking.
   *
   * @return
   */
  public TerritoryEnum getAttackTo() {
    return attackTo;
  }

  /**
   * Returns number of die rolled.
   *
   * @return
   */
  public int getDieRolled() {
    return dieRolled;
  }

  /**
   * Sets the result of rolling the die.
   *
   * @param result - result of rolling die
   * @throws IllegalArgumentException - if the list is null
   */
  public void setDieResult(List<Integer> result)
      throws IllegalArgumentException {
    if (result == null) {
      throw new IllegalArgumentException("ERROR: null list");
    } else if (result.size() != dieRolled) {
      throw new IllegalArgumentException("ERROR: wrong number of die rolled");
    }
    this.dieRolledResult = result;
  }

  public List<Integer> getDieResults() {
    return dieRolledResult;
  }
}
