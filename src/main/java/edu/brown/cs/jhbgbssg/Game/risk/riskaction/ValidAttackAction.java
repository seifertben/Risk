package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * Represents a ValidAttackMove. Object is used to convey to the frontend what
 * attack moves are admissible and checks the attackmove returned by the gui.
 *
 * @author sarahgilmore
 *
 */
public class ValidAttackAction implements ValidAction {
  private RiskPlayer player;
  private Map<TerritoryEnum, Integer> chooseDie;
  private Multimap<TerritoryEnum, TerritoryEnum> whoToAttack;
  private boolean canAttack;

  /**
   * Constructor for ValidAttackMove.
   *
   * @param player - player
   * @param board - game board territory
   */
  public ValidAttackAction(RiskPlayer player, RiskBoard board) {
    if (player == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    chooseDie = new HashMap<>();
    whoToAttack = board.getPlayerAttackMap(player);
    for (TerritoryEnum terrId : whoToAttack.keySet()) {
      int numberTroops = board.getTerritory(terrId).getNumberTroops();
      int maxDice = Math.min(3, numberTroops - 1);
      chooseDie.put(terrId, maxDice);
    }
    if (chooseDie.size() == 0) {
      canAttack = false;
    } else {
      canAttack = true;
    }
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_ATTACK_DIE;
  }

  @Override
  public UUID getMovePlayer() {
    return player.getPlayerId();
  }

  /**
   * Returns true if the player can even attack based on the information given
   * to the constructor.
   *
   * @return true if the player can make the move; false otherwise
   */
  public boolean canAttack() {
    return canAttack;
  }

  /**
   * Returns a map of the Territory id's to the number of die that can be rolled
   * when attacking with that territory.
   *
   * @return map of territoryEnum to integers.
   */
  public Map<TerritoryEnum, Integer> getTerrioryMaxDie() {
    return chooseDie;
  }

  /**
   * Returns a collection of TerritoryEnum-TerritoryEnum entries representing
   * from which territory to another one can the player attack.
   *
   * @return collection of TerritoryEnum-TerritoryEnum entries
   */
  public Multimap<TerritoryEnum, TerritoryEnum> getTerritoriesCanAttack() {
    return whoToAttack;
  }

  /**
   * Returns true or false if the AttackAction is valid according to the bounds
   * defined by a ValidAttackMove object.
   *
   * @param action - action user is trying to execute
   * @return true if the move is valid; false otherwise
   */
  public boolean isValidAttackAction(AttackAction action) {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null move");
    }
    RiskPlayer currPlayer = action.getMovePlayer();
    TerritoryEnum attackFrom = action.getAttackingTerritory();
    TerritoryEnum attackTo = action.getDefendingTerritory();
    int die = action.getNumberDiceRolled();
    if (!currPlayer.equals(player)) {
      return false;
    } else if (!whoToAttack.containsEntry(attackFrom, attackTo)) {
      return false;
    }
    assert (chooseDie.containsKey(attackFrom));
    int allowedDie = chooseDie.get(attackFrom);
    if (die < 1 || die > allowedDie) { // valid number of die
      return false;
    }
    return true;
  }

  /**
   * Returns a boolean indicating if any attack move is valid.
   */
  @Override
  public boolean actionAvailable() {
    return canAttack;
  }

}
