package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.brown.cs.jhbgbssg.Game.Die;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents a DefendMove.
 *
 * @author sarahgilmore
 *
 */
public class DefendAction implements Action {
  private RiskPlayer defender;
  private AttackAction attack;
  private RiskBoard board;
  private int defendDie;
  private List<Integer> defenderRoll;
  private Integer troopsDefendLost = null;
  private Integer troopsAttackLost = null;
  private boolean defenderLostTerritory = false;
  private boolean actionExecuted;
  private Die die = new Die();

  /**
   * Constructor for defend move.
   *
   * @param player - defending player
   * @param board - board
   * @param defendDie - number of die to roll
   * @param attack - attack move
   * @throws IllegalArgumentException - thrown if the input is null
   */
  public DefendAction(RiskPlayer player, RiskBoard board, AttackAction attack,
      int defendDie) throws IllegalArgumentException {
    if (player == null || attack == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.defender = player;
    this.attack = attack;
    this.defendDie = defendDie;
    this.board = board;
    this.defenderRoll = null;
    this.actionExecuted = false;

  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_DEFEND_DIE;
  }

  @Override
  public RiskPlayer getMovePlayer() {
    return defender;
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
    return attack.getDefendingTerritory();
  }

  /**
   * Gets attacking territory.
   *
   * @return territory
   */
  public TerritoryEnum getAttackingTerritory() {
    return attack.getAttackingTerritory();
  }

  /**
   * Get attacker's id.
   *
   * @return attacker id
   */
  public RiskPlayer getAttackerId() {
    return attack.getMovePlayer();
  }

  /**
   * Gets the results of the die roll.
   *
   * @return rolled die
   */
  public List<Integer> getRoll() {
    if (defenderRoll == null) {
      return null;
    }
    return Collections.unmodifiableList(defenderRoll);
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

  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      defenderRoll = new ArrayList<>();
      for (int i = 0; i < defendDie; i++) {
        defenderRoll.add(die.roll());
      }
      Collections.sort(defenderRoll);
      Collections.reverse(defenderRoll);
      this.attack();
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }

  /**
   * Runs the attack.
   *
   * @param defend
   * @return
   */
  private void attack() {
    if (!attack.isActionExecuted()) {
      attack.executeAction();
    }
    List<Integer> attackRolls = attack.getRoll();
    int compare = Math.min(attackRolls.size(), defenderRoll.size());
    int defendTroopsLost = 0;
    int attackTroopsLost = 0;
    for (int i = 0; i < compare; i++) {
      int result = Integer.compare(attackRolls.get(i), defenderRoll.get(i));
      if (result > 0) {
        defendTroopsLost++;
      } else {
        attackTroopsLost++;
      }
    }
    Territory attackTerr = board.getTerritory(attack.getAttackingTerritory());
    Territory defendTerr = board.getTerritory(attack.getDefendingTerritory());
    attackTerr.removeTroops(attackTroopsLost);
    defenderLostTerritory = defendTerr.removeTroops(defendTroopsLost);
    troopsDefendLost = Integer.valueOf(defendTroopsLost);
    troopsAttackLost = Integer.valueOf(attackTroopsLost);
    if (defenderLostTerritory) {
      defender.lostTerritory(attack.getDefendingTerritory());
    }
  }
}
