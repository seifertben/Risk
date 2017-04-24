package edu.brown.cs.jhbgbbgssg.risk.riskmove;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.AttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.DefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveType;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssh.tuple.Pair;

/**
 * JUnit tests for DefendMove.
 *
 * @author sarahgilmore
 *
 */
public class DefendMoveTest {

  /**
   * Tests constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendMove defend = new DefendMove(
        new Pair<>(UUID.randomUUID(), TerritoryEnum.ALBERTA), 2, attack);
    assertNotNull(defend);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the id,
   * territory pair is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPair() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    new DefendMove(null, 2, attack);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the attack
   * parameter is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullAttack() {
    new DefendMove(new Pair<>(UUID.randomUUID(), TerritoryEnum.ALBERTA), 2,
        null);
  }

  /**
   * Tests the getRoll returns the results of rolling the die in descending
   * order.
   */
  @Test
  public void testGetRoll() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 2);
    DefendMove defend = new DefendMove(
        new Pair<>(UUID.randomUUID(), TerritoryEnum.ALBERTA), 2, attack);
    List<Integer> roll = defend.getRoll();
    assertTrue(roll.size() == 2);
    int val = Integer.MAX_VALUE;
    for (int i = 0; i < 2; i++) {
      assertTrue(val >= roll.get(i));
      val = roll.get(i);
      assertTrue(roll.get(i) > 0 && roll.get(i) < 7);
    }
  }

  /**
   * Tests getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 2);
    DefendMove defend = new DefendMove(
        new Pair<>(UUID.randomUUID(), TerritoryEnum.ALBERTA), 2, attack);
    assertTrue(defend.getMoveType() == MoveType.CHOOSE_DEFEND_DIE);
  }

  /**
   * Tests getDefendedTerritory returns the right territory.
   */
  @Test
  public void testGetDefendTerritory() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 2);
    DefendMove defend = new DefendMove(
        new Pair<>(UUID.randomUUID(), TerritoryEnum.ALBERTA), 2, attack);
    assertTrue(defend.getDefendedTerritory() == TerritoryEnum.ALBERTA);
  }

  /**
   * Tests getAttackingTerritory returns the right territory.
   */
  @Test
  public void testGetAttackingTerritory() {
    AttackMove attack = new AttackMove(UUID.randomUUID(), TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 2);
    DefendMove defend = new DefendMove(
        new Pair<>(UUID.randomUUID(), TerritoryEnum.ALBERTA), 2, attack);
    assertTrue(defend.getAttackingTerritory() == TerritoryEnum.ALASKA);
  }
}
