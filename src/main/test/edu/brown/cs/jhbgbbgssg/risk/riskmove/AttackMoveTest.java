package edu.brown.cs.jhbgbbgssg.risk.riskmove;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.AttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveType;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for AttackMove.
 *
 * @author sarahgilmore
 *
 */
public class AttackMoveTest {

  /**
   * Tests that the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    assertNotNull(attack);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the player
   * id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullId() {
    new AttackMove(null, TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the
   * attacking territory is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullAttackTerr() {
    new AttackMove(null, TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the
   * defending territory is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullDefendTerr() {
    new AttackMove(null, TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
  }

  /**
   * Tests that getMoveType returns MoveType.CHOOSE_ATTACK_DIE.
   */
  @Test
  public void testGetMoveType() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    assertTrue(attack.getMoveType() == MoveType.CHOOSE_ATTACK_DIE);
  }

  /**
   * Tests that getAttackFrom returns the correct Territory.
   */
  @Test
  public void testGetAttackFrom() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    assertTrue(attack.getAttackFrom() == TerritoryEnum.ICELAND);
  }

  /**
   * Tests that getAttackTo returns the correct Territory.
   */
  @Test
  public void testGetAttackTo() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    assertTrue(attack.getAttackTo() == TerritoryEnum.GREENLAND);
  }

  /**
   * Tests that getDieRolled returns the correct number of Die.
   */
  @Test
  public void testGetDieRolled() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 2);
    assertTrue(attack.getDieRolled() == 2);
  }

  /**
   * Tests that getAttackTo returns the correct Territory.
   */
  @Test
  public void testGetRollResults() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    List<Integer> roll = attack.getDieResults();
    assertTrue(roll.size() == 3);
    int val = Integer.MAX_VALUE;
    for (int i = 0; i < 3; i++) {
      assertTrue(val >= roll.get(i));
      val = roll.get(i);
    }
  }
}
