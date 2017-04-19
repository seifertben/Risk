package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents a ValidAttackMove. Object is used to convey to the frontend what
 * attack moves are admissible and checks the attackmove returned by the gui.
 *
 * @author sarahgilmore
 *
 */
public class ValidAttackMove implements Move {
  private UUID playerId;
  private Map<TerritoryEnum, Integer> chooseDie;
  private Multimap<TerritoryEnum, TerritoryEnum> whoToAttack;
  private boolean canAttack;

  /**
   * Constructor for ValidAttackMove.
   *
   * @param playerId - id of the player who can attack
   * @param chooseDie - maximum number of die a player can roll for each
   *          territory
   * @param whoToAttack - which territories and from where the player can attack
   */
  public ValidAttackMove(UUID playerId, Map<TerritoryEnum, Integer> chooseDie,
      Multimap<TerritoryEnum, TerritoryEnum> whoToAttack) {
    if (playerId == null || chooseDie == null || whoToAttack == null) {
      throw new IllegalArgumentException(
          "ERROR: null input to ValidAttackMove");
    }
    this.playerId = playerId;
    this.chooseDie = chooseDie;
    this.whoToAttack = whoToAttack;
    if (whoToAttack.size() == 0) {
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
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    TerritoryEnum attackFrom = move.getAttackFrom();
    TerritoryEnum attackTo = move.getAttackTo();
    int die = move.getDieRolled();
    if (!whoToAttack.containsEntry(attackFrom, attackTo)) {
      return false;
    }
    assert (chooseDie.containsKey(attackFrom));
    int allowedDie = chooseDie.get(attackFrom);
    if (die > 1 || die > allowedDie) {
      return false;
    }
    return true;
  }

}
