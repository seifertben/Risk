package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for DefendMove.
 *
 * @author sarahgilmore
 *
 */
public class DefendMoveTest {
  private static final int ITERATE = 100;

  /**
   * Tests constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    assertNotNull(defend);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the player is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    new DefendAction(null, board, attack, 2);

  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the board is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    new DefendAction(defender, null, attack, 2);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the
   * AttackAction is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullAttack() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    new DefendAction(defender, board, null, 2);
  }

  /**
   * Tests getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    assertTrue(defend.getMoveType() == MoveType.CHOOSE_DEFEND_DIE);
  }

  /**
   * Tests getDefendedTerritory returns the right territory.
   */
  @Test
  public void testGetDefendTerritory() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    assertTrue(defend.getDefendedTerritory() == TerritoryEnum.ALBERTA);
  }

  /**
   * Tests getAttackingTerritory returns the right territory.
   */
  @Test
  public void testGetAttackingTerritory() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    assertTrue(defend.getAttackingTerritory() == TerritoryEnum.ALASKA);
  }

  /**
   * Tests that the roll results are listed in descending order.
   */
  @Test
  public void testGetRoll() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    defend.executeAction();
    List<Integer> roll = defend.getRoll();
    assertTrue(roll.size() == 2);
    int val = Integer.MAX_VALUE;
    for (int i = 0; i < 2; i++) {
      assertTrue(val >= roll.get(i));
      val = roll.get(i);
    }
  }

  /**
   * Tests that the roll is null before executeAction is called.
   */
  @Test
  public void testGetRollBeforeExecution() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    assertNull(defend.getRoll());
  }

  /**
   * Tests that executing a defend action works properly.
   */
  @Test
  public void testExecuteAction() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 2);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    int attackOriginalTroops = board.getTerritory(TerritoryEnum.ALASKA)
        .getNumberTroops();
    int defendOriginalTroops = board.getTerritory(TerritoryEnum.ALBERTA)
        .getNumberTroops();
    assertTrue(defend.executeAction());
    List<Integer> defendRoll = defend.getRoll();
    List<Integer> attackRoll = attack.getRoll();
    int attackTroopsLost = 0;
    int defendTroopsLost = 0;
    int compare = Integer.compare(attackRoll.get(0), defendRoll.get(0));
    if (compare > 0) {
      defendTroopsLost++;
    } else {
      attackTroopsLost++;
    }
    compare = Integer.compare(attackRoll.get(1), defendRoll.get(1));
    if (compare > 0) {
      defendTroopsLost++;
    } else {
      attackTroopsLost++;
    }
    assertTrue(attackTroopsLost == defend.getTroopsAttackerLost());
    assertTrue(defendTroopsLost == defend.getTroopsDefenderLost());
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA)
        .getNumberTroops() == attackOriginalTroops - attackTroopsLost);
    assertTrue(board.getTerritory(TerritoryEnum.ALBERTA)
        .getNumberTroops() == defendOriginalTroops - defendTroopsLost);
    if (defendTroopsLost == 2) {
      assertTrue(defend.getDefenderLostTerritory());
      assertFalse(defender.hasTerritory(TerritoryEnum.ALBERTA));
      assertNull(board.getTerritory(TerritoryEnum.ALBERTA).getOwner());
    } else {
      assertFalse(defend.getDefenderLostTerritory());
      assertTrue(defender.hasTerritory(TerritoryEnum.ALBERTA));
      assertTrue(board.getTerritory(TerritoryEnum.ALBERTA).getOwner()
          .equals(defender));
    }
  }

  /**
   * Tests that for an if it is mathematically impossible for a defender to lose
   * a territory, the defender does not. This tests iterates a 100 times due to
   * the randomness inherent in the attack and defend actions.
   */
  @Test
  public void testExecuteActionDefenderCannotLoseTerritory() {
    for (int i = 0; i < ITERATE; i++) {
      RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
      RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
      RiskBoard board = new RiskBoard();
      attacker.conqueredTerritory(TerritoryEnum.ALASKA);
      attacker.conqueredTerritory(TerritoryEnum.ALASKA);
      board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
      defender.conqueredTerritory(TerritoryEnum.ALBERTA);
      defender.conqueredTerritory(TerritoryEnum.ALBERTA);
      board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
      AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
          TerritoryEnum.ALBERTA, 3);
      DefendAction defend = new DefendAction(defender, board, attack, 2);
      attack.executeAction();
      defend.executeAction();
      assertTrue(defender.hasTerritory(TerritoryEnum.ALBERTA));
      assertFalse(defend.getDefenderLostTerritory());
      assertTrue(
          board.getTerritory(TerritoryEnum.ALBERTA).getNumberTroops() > 0);
    }
  }

  /**
   * Tests that isActionExecuted returns false if the defend action has not been
   * executed yet.
   */
  @Test
  public void testIsActionExecutedFalse() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    assertFalse(defend.isActionExecuted());
  }

  /**
   * Tests that isActionExecuted returns true if the defend action has been
   * executed.
   */
  @Test
  public void testIsActionExecutedTrue() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    assertFalse(defend.isActionExecuted());
    defend.executeAction();
    assertTrue(defend.isActionExecuted());
  }

  /**
   * Tests that calling executeAction on after the first call causes it to
   * return false.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskPlayer attacker = new RiskPlayer(UUID.randomUUID());
    RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    attacker.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(attacker, 5);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    defender.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(defender, 3);
    AttackAction attack = new AttackAction(attacker, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 3);
    DefendAction defend = new DefendAction(defender, board, attack, 2);
    assertFalse(defend.isActionExecuted());
    assertTrue(defend.executeAction());
    assertTrue(defend.isActionExecuted());
    assertFalse(defend.executeAction());
    assertTrue(defend.isActionExecuted());
  }
}
