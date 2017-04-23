package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents a ValidAttackMove. Object is used to convey to the frontend what
 * attack moves are admissible and checks the attackmove returned by the gui.
 *
 * @author sarahgilmore
 *
 */
public class ValidAttackMove implements ValidAction {
  private UUID playerId;
  private Map<TerritoryEnum, Integer> chooseDie;
  private Multimap<TerritoryEnum, TerritoryEnum> whoToAttack;
  private boolean canAttack;

  /**
   * Constructor for ValidAttackMove.
   *
   * @param player - player
   * @param board - game board territory
   */
  public ValidAttackMove(RiskPlayer player, RiskBoard board) {
    if (player == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    playerId = player.getPlayerId();
    chooseDie = new HashMap<>();
    whoToAttack = board.getPlayerAttackMap(player);
    Collection<Territory> territories = board.getTerritories();
    for (Territory terr : territories) {
      int numTroops = terr.getNumberTroops();
      int maxDice = Math.min(3, numTroops - 1);
      if (maxDice > 0) {
        chooseDie.put(terr.getTerritoryId(), maxDice);
      }
    }
    if (chooseDie.size() == 0) {
      canAttack = false;
    }
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
  public Map<TerritoryEnum, Integer> getAttackableTerritories() {
    return chooseDie;
  }

  /**
   * Returns a collection of TerritoryEnum-TerritoryEnum entries representing
   * from which territory to another one can the player attack.
   *
   * @return collection of TerritoryEnum-TerritoryEnum entries
   */
  public Collection<Entry<TerritoryEnum, TerritoryEnum>> whoToAttack() {
    return whoToAttack.entries();
  }

  /**
   * Returns true or false if the AttackMove is valid according to the bounds
   * defined by a ValidAttackMove object.
   *
   * @param move - move user is trying to execute
   * @return true if the move is valid; false otherwise
   */
  public boolean validAttackMove(AttackMove move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null move");
    }
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) { // right player trying to attack
      return false;
    }
    TerritoryEnum attackFrom = move.getAttackFrom();
    TerritoryEnum attackTo = move.getAttackTo();
    int die = move.getDieRolled();
    if (!whoToAttack.containsEntry(attackFrom, attackTo)) { // valid territories
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
