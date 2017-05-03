package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 * JUnit tests for MoveTroopsMove.
 *
 * @author sarahgilmore
 *
 */
public class MoveTroopsMoveTest {

  /**
   * Tests constructor returns non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertNotNull(move);
  }

  /**
   * Tests constructor throws an IllegalArgumentExcetpion if the player is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    new MoveTroopsAction(null, board, TerritoryEnum.GREENLAND,
        TerritoryEnum.ICELAND, 2);
  }

  /**
   * Tests constructor throws an IllegalArgumentExcetpion if the board is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    new MoveTroopsAction(player, null, TerritoryEnum.GREENLAND,
        TerritoryEnum.ICELAND, 2);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the from territory
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullFromTerr() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    new MoveTroopsAction(player, board, null, TerritoryEnum.ICELAND, 2);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the from territory
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullToTerr() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    new MoveTroopsAction(player, board, TerritoryEnum.GREENLAND, null, 2);
  }

  /**
   * Tests getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertTrue(move.getMoveType() == MoveType.MOVE_TROOPS);
  }

  /**
   * Tests getMoveType returns the from territory.
   */
  @Test
  public void testGetFromTerritory() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertTrue(move.getFromTerritory() == TerritoryEnum.GREENLAND);
  }

  /**
   * Tests getMoveType returns the from territory.
   */
  @Test
  public void testGetToTerritory() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertTrue(move.getToTerrtiory() == TerritoryEnum.ICELAND);
  }

  /**
   * Tests troopsMoved returns the correct number of troops.
   */
  @Test
  public void testGetTroopsMoved() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertTrue(move.troopsMoved() == 2);
  }

  /**
   * Tests that executeAction returns true and executes the action.
   */
  @Test
  public void testExecuteAction() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 3);
    assertTrue(
        board.getTerritory(TerritoryEnum.ICELAND).getNumberTroops() == 1);
    assertTrue(move.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 1);
    assertTrue(
        board.getTerritory(TerritoryEnum.ICELAND).getNumberTroops() == 3);
  }

  /**
   * Tests that executeAction has no effect after the first call.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 3);
    assertTrue(
        board.getTerritory(TerritoryEnum.ICELAND).getNumberTroops() == 1);
    assertTrue(move.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 1);
    assertTrue(
        board.getTerritory(TerritoryEnum.ICELAND).getNumberTroops() == 3);
    assertFalse(move.executeAction());
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 1);
    assertTrue(
        board.getTerritory(TerritoryEnum.ICELAND).getNumberTroops() == 3);
  }

  /**
   * Tests that isActionExecuted returns false if the action has not been
   * executed.
   */
  @Test
  public void testIsActionExecutedFalse() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertFalse(move.isActionExecuted());
  }

  /**
   * Tests that isActionExecuted returns true if the action has been executed.
   */
  @Test
  public void testIsActionExecutedTrue() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND, 2);
    assertFalse(move.isActionExecuted());
    move.executeAction();
    assertTrue(move.isActionExecuted());
  }
}
