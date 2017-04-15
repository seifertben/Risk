package edu.brown.cs.jhbgbgssg.Game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class BoardTest {

  /**
   * Tests constructor returns a non-null object.
   */
  @Test
  public void testBoardConstructor() {
    RiskBoard board = new RiskBoard();
    assertNotNull(board);
  }

  /**
   * Tests isNeighbor method for RiskBoard.
   *
   * WE SHOULD PROBABLY DO EXTENSIVE TESTING OF THIS method
   */
  @Test
  public void testIsNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ALASKA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(board.isNeighbor(TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ALASKA, TerritoryEnum.KAMACHATKA));
    assertFalse(board.isNeighbor(TerritoryEnum.ALASKA, TerritoryEnum.QUEBEC));
  }
}
