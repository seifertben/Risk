package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for SetupAction.
 *
 * @author sarahgilmore
 *
 */
public class SetupActionTest {

  /**
   * Tests that the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.CHINA);
    assertNotNull(action);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the player
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskBoard board = new RiskBoard();
    new SetupAction(null, board, TerritoryEnum.CHINA);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the board
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    new SetupAction(player, null, TerritoryEnum.CHINA);
  }

  /**
   * Tests that the constructor returns a non-null object.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    new SetupAction(player, board, null);
  }

  /**
   * Tests getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.CHINA);
    assertTrue(action.getMoveType() == MoveType.SETUP);
  }

  /**
   * Tests getSelectedTerritory returns the correct territory.
   */
  @Test
  public void testGetSelectedTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.CHINA);
    assertTrue(action.getSelectedTerritory() == TerritoryEnum.CHINA);
  }

  /**
   * Tests that executeAction returns true and executes the action.
   */
  @Test
  public void testExecuteAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.CHINA);
    assertNull(board.getTerritory(TerritoryEnum.CHINA).getOwner());
    assertTrue(board.getTerritory(TerritoryEnum.CHINA).getNumberTroops() == 0);
    assertFalse(player.hasTerritory(TerritoryEnum.CHINA));
    assertTrue(action.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.CHINA).getOwner().equals(player));
    assertTrue(board.getTerritory(TerritoryEnum.CHINA).getNumberTroops() == 1);
    assertTrue(player.hasTerritory(TerritoryEnum.CHINA));
  }

  /**
   * Tests that executeAction has returns false and has no effect after the
   * first call.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.CHINA);
    assertNull(board.getTerritory(TerritoryEnum.CHINA).getOwner());
    assertTrue(board.getTerritory(TerritoryEnum.CHINA).getNumberTroops() == 0);
    assertFalse(player.hasTerritory(TerritoryEnum.CHINA));
    assertTrue(action.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.CHINA).getOwner().equals(player));
    assertTrue(board.getTerritory(TerritoryEnum.CHINA).getNumberTroops() == 1);
    assertTrue(player.hasTerritory(TerritoryEnum.CHINA));
    assertFalse(action.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.CHINA).getOwner().equals(player));
    assertTrue(board.getTerritory(TerritoryEnum.CHINA).getNumberTroops() == 1);
    assertTrue(player.hasTerritory(TerritoryEnum.CHINA));
  }

  /**
   * Tests that isExecuteAction returns false if the action has not been
   * executed.
   */
  @Test
  public void testIsActionExecutedFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.CHINA);
    assertFalse(action.isActionExecuted());
  }

  /**
   * Tests that isExecuteAction returns true if the action has been executed.
   */
  @Test
  public void testIsActionExecutedTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.CHINA);
    assertFalse(action.isActionExecuted());
    assertTrue(action.executeAction());
    assertTrue(action.isActionExecuted());

  }
}
