package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.brown.cs.jhbgbssg.Game.Die;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * Represents an Attack Action in Risk. It knows which player is attacking, the
 * territory attacking and territory being attacked as well as the number of
 * dice rolled.
 *
 * @author sarahgilmore
 *
 */
public class AttackAction implements Action {
  private RiskPlayer player;
  private TerritoryEnum attackingTerritory;
  private TerritoryEnum defendingTerritory;
  private int numberDiceRolled;
  private List<Integer> roll;
  private Die die = new Die();
  private boolean actionExecuted;

  /**
   * Constructor for an AttackMove. It takes in the player, the attacking
   * territory, the defending territory and the number of dice to roll.
   *
   * @param player - id of player attacking
   * @param attackingTerritory - territory attacking from
   * @param defendingTerritory - territory attacking
   * @param numberDiceRolled - number of die rolled
   * @throws IllegalArgumentException if the input is list
   */
  public AttackAction(RiskPlayer player, TerritoryEnum attackingTerritory,
      TerritoryEnum defendingTerritory, int numberDiceRolled) {
    if (player == null || attackingTerritory == null
        || defendingTerritory == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    this.attackingTerritory = attackingTerritory;
    this.defendingTerritory = defendingTerritory;
    this.numberDiceRolled = numberDiceRolled;
    this.roll = null;
    this.actionExecuted = false;
  }

  /**
   * Returns the MoveType of an Attack Action: CHOOSE_ATTACK_DIE.
   */
  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_ATTACK_DIE;
  }

  /**
   * Returns the RiskPlayer attacking.
   */
  @Override
  public RiskPlayer getMovePlayer() {
    return player;
  }

  /**
   * Returns the territory that is attacking.
   *
   * @return territory
   */
  public TerritoryEnum getAttackingTerritory() {
    return attackingTerritory;
  }

  /**
   * Returns the territory that is defending against the attack.
   *
   * @return territory
   */
  public TerritoryEnum getDefendingTerritory() {
    return defendingTerritory;
  }

  /**
   * Returns number of die rolled.
   *
   * @return number of die rolled
   */
  public int getNumberDiceRolled() {
    return numberDiceRolled;
  }

  /**
   * Returns an unmodifiable list of the die results.
   *
   * @return list of integers representing the die roll.
   */
  public List<Integer> getRoll() {
    if (roll != null) {
      return Collections.unmodifiableList(roll);
    }
    return null;
  }

  /**
   * Returns true if the action has been executed; false otherwise.
   */
  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  /**
   * Rolls the die. and sorts the die into descending order.
   */
  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      this.roll = new ArrayList<>();
      for (int i = 0; i < numberDiceRolled; i++) {
        roll.add(die.roll());
      }
      Collections.sort(roll);
      Collections.reverse(roll);
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
