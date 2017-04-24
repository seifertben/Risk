package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.Die;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssh.tuple.Pair;

/**
 * Represents a DefendMove.
 *
 * @author sarahgilmore
 *
 */
public class DefendMove implements Move {
  private UUID playerId;
  private TerritoryEnum defended;
  private int defendDie;
  private List<Integer> rolled;
  private Integer troopsDefendLost = null;
  private Integer troopsAttackLost = null;
  private boolean defenderLostTerritory = false;
  private UUID attackerId;
  private TerritoryEnum attackerTerr;
  private Die die = new Die();

  /**
   * Constructor for defend move.
   *
   * @param defender - id and territory defended
   * @param defendDie - number of die to roll
   * @param attacker - attackMove
   * @throws IllegalArgumentException - thrown if the input is null
   */
  public DefendMove(Pair<UUID, TerritoryEnum> defender,
      Pair<UUID, TerritoryEnum> attacker, int defendDie)
      throws IllegalArgumentException {
    if (defender == null || attacker == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.playerId = defender.getFirstElement();
    this.defended = defender.getSecondElement();
    this.attackerId = attacker.getFirstElement();
    this.attackerTerr = attacker.getSecondElement();
    this.defendDie = defendDie;
    rolled = new ArrayList<>();
    for (int i = 0; i < defendDie; i++) {
      rolled.add(die.roll());
    }
    Collections.sort(rolled);
    Collections.reverse(rolled);
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_DEFEND_DIE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  /**
   * Gets number of die rolled.
   *
   * @return number of die rolled
   */
  public int getDieRolled() {
    return defendDie;
  }

  /**
   * Gets the territory defended.
   *
   * @return territory
   */
  public TerritoryEnum getDefendedTerritory() {
    return defended;
  }

  /**
   * Gets attacking territory.
   *
   * @return territory
   */
  public TerritoryEnum getAttackingTerritory() {
    return attackerTerr;
  }

  public UUID getAttackerId() {
    return attackerId;
  }

  /**
   * Gets the results of the die roll.
   *
   * @return rolled die
   */
  public List<Integer> getRoll() {
    return Collections.unmodifiableList(this.rolled);
  }

  /**
   * Sets the number of troops lost by the defender and if the defender lost the
   * territory.
   *
   * @param troops - number of troops lost
   * @param lostTerritory - whether or not the defender lost the territory
   */
  public void setDefendTroopsLost(int troops, boolean lostTerritory) {
    if (troopsDefendLost >= 0) {
      throw new IllegalArgumentException("ERROR: invalid value");
    } else if (troopsDefendLost != null) {
      throw new IllegalArgumentException("ERROR: already set");
    }
    troopsDefendLost = new Integer(troops);
    defenderLostTerritory = lostTerritory;
  }

  /**
   * Gets the number of troops lost by the defender.
   *
   * @return number of troops lost
   */
  public int getTroopsDefenderLost() {
    return troopsDefendLost;
  }

  /**
   * Gets whether or not the territory was lost.
   *
   * @return true if the defender lost the teritory; false otherwise
   */
  public boolean getDefenderLostTerritory() {
    return defenderLostTerritory;
  }

  /**
   * Sets the number of troops lost by the attacker.
   *
   * @param troops - troops lost by the attacker
   */
  public void setAttackTroopsLost(int troops) {
    if (troopsAttackLost != null) {
      throw new IllegalArgumentException("ERROR: already set");
    } else if (troops < 0) {
      throw new IllegalArgumentException("ERROR: invalid value");
    }
    troopsAttackLost = new Integer(troops);
  }

  /**
   * Gets the number of troops by the attacker.
   *
   * @return - number of troops lost
   */
  public int getTroopsAttackerLost() {
    return troopsAttackLost;
  }
}
