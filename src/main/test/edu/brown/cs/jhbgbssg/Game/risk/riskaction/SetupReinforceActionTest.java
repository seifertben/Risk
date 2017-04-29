package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for SetupReinforceAction.
 *
 * @author sarahgilmore
 *
 */
public class SetupReinforceActionTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.MONGOLIA);
    assertNotNull(action);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the player is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    new SetupReinforceAction(null, board, TerritoryEnum.MONGOLIA);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the board is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    new SetupReinforceAction(player, null, TerritoryEnum.MONGOLIA);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the selected
   * territory is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    new SetupReinforceAction(player, board, null);
  }

  /**
   * Tests that getMoveType returns SETUP_REINFORCE.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.MONGOLIA);
    assertTrue(action.getMoveType() == MoveType.SETUP_REINFORCE);
  }

  /**
   * Tests that getSelectedTerritory returns the territory selected.
   */
  @Test
  public void testGetSelectedTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.MONGOLIA);
    assertTrue(action.getSelectedTerritory() == TerritoryEnum.MONGOLIA);
  }

  /**
   * Tests that executeAction executes the setupReinforce action.
   */
  @Test
  public void testExecuteAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.MONGOLIA);
    assertTrue(
        board.getTerritory(TerritoryEnum.MONGOLIA).getNumberTroops() == 1);
    assertTrue(action.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.MONGOLIA).getNumberTroops() == 2);
  }

  /**
   * Tests that calling executeAction twice has no effect.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.MONGOLIA);
    assertTrue(
        board.getTerritory(TerritoryEnum.MONGOLIA).getNumberTroops() == 1);
    assertTrue(action.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.MONGOLIA).getNumberTroops() == 2);
    assertFalse(action.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.MONGOLIA).getNumberTroops() == 2);
  }

  /**
   * Tests isActionExecuted returns false if the action has not been executed
   * yet.
   */
  @Test
  public void testisActionExecutedFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.MONGOLIA);
    assertFalse(action.isActionExecuted());
  }

  /**
   * Tests isActionExecuted returns true if the action has been executed.
   *
   */
  @Test
  public void testisActionExecutedTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.MONGOLIA);
    board.getTerritory(TerritoryEnum.MONGOLIA).changePlayer(player, 1);
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.MONGOLIA);
    assertFalse(action.isActionExecuted());
    assertTrue(action.executeAction());
    assertTrue(action.isActionExecuted());
  }

}
