package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
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
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    assertNotNull(attack);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the player
   * id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullId() {
    new AttackAction(null, TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the
   * attacking territory is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullAttackTerr() {
    new AttackAction(null, TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the
   * defending territory is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullDefendTerr() {
    new AttackAction(null, TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
  }

  /**
   * Tests that getMoveType returns MoveType.CHOOSE_ATTACK_DIE.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());

    AttackAction attack = new AttackAction(player, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    assertTrue(attack.getMoveType() == MoveType.CHOOSE_ATTACK_DIE);
  }

  /**
   * Tests that getAttackFrom returns the correct Territory.
   */
  @Test
  public void testGetAttackFrom() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    assertTrue(attack.getAttackingTerritory() == TerritoryEnum.ICELAND);
  }

  /**
   * Tests that getAttackTo returns the correct Territory.
   */
  @Test
  public void testGetAttackTo() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    assertTrue(attack.getDefendingTerritory() == TerritoryEnum.GREENLAND);
  }

  /**
   * Tests that getDieRolled returns the correct number of Die.
   */
  @Test
  public void testGetDieRolled() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 2);
    assertNull(attack.getRoll());
  }

  /**
   * Tests getRoll returns a valid roll once the action has been executed.
   */
  @Test
  public void testGetRollAfterExecutingAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());

    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    attack.executeAction();
    List<Integer> roll = attack.getRoll();
    assertTrue(roll.size() == 3);
    int val = Integer.MAX_VALUE;
    for (int i = 0; i < 3; i++) {
      assertTrue(val >= roll.get(i));
      val = roll.get(i);
    }
  }

  /**
   * Tests getRoll returns null if the action has not been executed.
   */
  @Test
  public void testGetRollBeforeExecutingAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    assertNull(attack.getRoll());
  }

  /**
   * Tests executeAction returns true on the first call.
   */
  @Test
  public void testExecuteAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    assertTrue(attack.executeAction());
  }

  /**
   * Tests that executeAction returns false after the first call and that the
   * roll remains the same even after calling executeAction again.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    assertTrue(attack.executeAction());
    List<Integer> roll = attack.getRoll();
    assertFalse(attack.executeAction());
    List<Integer> otherRoll = attack.getRoll();
    assertTrue(roll.equals(otherRoll));
  }

  /**
   * Test isActionExecuted returns false if the action has not been executed.
   */
  @Test
  public void testActionExecutedFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    assertFalse(attack.isActionExecuted());
  }

  /**
   * Test isActionExecuted returns true if the action has been executed.
   */
  @Test
  public void testActionExecutedTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    AttackAction attack = new AttackAction(player, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    attack.executeAction();
    assertTrue(attack.isActionExecuted());
  }
}
