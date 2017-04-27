package edu.brown.cs.jhbgbbgssg.risk.riskmove;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for ClaimTerritoryMove.
 *
 * @author sarahgilmore
 *
 */
public class ClaimTerritoryMoveTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 1);
    assertNotNull(claim);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    new ClaimTerritoryAction(null, board, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 1);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if territory the
   * user attacked with is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    new ClaimTerritoryAction(player, null, TerritoryEnum.ALASKA,
        TerritoryEnum.ALBERTA, 1);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the territory
   * the player is claiming is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullClaimFromTerr() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    new ClaimTerritoryAction(player, board, null, TerritoryEnum.ALBERTA, 1);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the
   * territory to claim is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullClaimTerr() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    new ClaimTerritoryAction(player, board, TerritoryEnum.ALASKA, null, 1);
  }

  /**
   * Tests getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 1);
    assertTrue(claim.getMoveType() == MoveType.CLAIM_TERRITORY);
  }

  /**
   * Tests getTerritoryFrom returns the right Territory.
   */
  @Test
  public void testGetAttackTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 1);
    assertTrue(claim.getAttackingTerritory() == TerritoryEnum.ALASKA);
  }

  /**
   * Tests getTerritoryClaimed returns the correct Territory.
   */
  @Test
  public void testGetTerritoryClaimed() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 1);

    assertTrue(claim.getClaimingTerritory() == TerritoryEnum.ALBERTA);
  }

  /**
   * Tests getNumberTroops returns the right number of troops.
   */
  @Test
  public void testGetNumberTroops() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 5);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertTrue(claim.getNumberTroops() == 4);
  }

  /**
   * Tests executeAction executes the claim territory action.
   */
  @Test
  public void testExecuteAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 5);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertFalse(player.hasTerritory(TerritoryEnum.ALBERTA));
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 5);
    assertTrue(
        board.getTerritory(TerritoryEnum.ALBERTA).getNumberTroops() == 0);
    assertNull(board.getTerritory(TerritoryEnum.ALBERTA).getOwner());
    assertTrue(claim.executeAction());
    assertTrue(player.hasTerritory(TerritoryEnum.ALBERTA));
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 1);
    assertTrue(
        board.getTerritory(TerritoryEnum.ALBERTA).getNumberTroops() == 4);
    assertTrue(
        board.getTerritory(TerritoryEnum.ALBERTA).getOwner().equals(player));

  }

  /**
   * Tests that calling executeAction twice has no effect.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 5);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertFalse(player.hasTerritory(TerritoryEnum.ALBERTA));
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 5);
    assertTrue(
        board.getTerritory(TerritoryEnum.ALBERTA).getNumberTroops() == 0);
    assertNull(board.getTerritory(TerritoryEnum.ALBERTA).getOwner());
    assertTrue(claim.executeAction());
    assertTrue(player.hasTerritory(TerritoryEnum.ALBERTA));
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 1);
    assertTrue(
        board.getTerritory(TerritoryEnum.ALBERTA).getNumberTroops() == 4);
    assertTrue(
        board.getTerritory(TerritoryEnum.ALBERTA).getOwner().equals(player));
    assertFalse(claim.executeAction());
    assertTrue(player.hasTerritory(TerritoryEnum.ALBERTA));
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 1);
    assertTrue(
        board.getTerritory(TerritoryEnum.ALBERTA).getNumberTroops() == 4);
    assertTrue(
        board.getTerritory(TerritoryEnum.ALBERTA).getOwner().equals(player));
  }

  /**
   * Tests that isActionExecuted returns false if the action has not been
   * executed.
   */
  @Test
  public void testIsActionExecutedFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 5);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertFalse(claim.isActionExecuted());
  }

  /**
   * Tests that isActionExecuted returns true if the action has been executed.
   */
  @Test
  public void testIsActionExecutedTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 5);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    claim.executeAction();
    assertTrue(claim.isActionExecuted());
  }
}
