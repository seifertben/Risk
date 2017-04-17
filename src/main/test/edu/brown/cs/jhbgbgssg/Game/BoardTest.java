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

  /**
   * Tests neighbors for Alaska.
   */
  @Test
  public void testAlaskaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ALASKA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(board.isNeighbor(TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ALASKA, TerritoryEnum.KAMACHATKA));
  }

  /**
   * Tests neighbors for Northwest_Territory.
   */
  @Test
  public void testNorthwestTerritoryNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHWEST_TERRITORY,
        TerritoryEnum.ALASKA));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHWEST_TERRITORY,
        TerritoryEnum.ALBERTA));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHWEST_TERRITORY,
        TerritoryEnum.ONTARIO));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHWEST_TERRITORY,
        TerritoryEnum.GREENLAND));
  }

  /**
   * Tests neighbors for Alberta Territory.
   */
  @Test
  public void testAlbertaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ALBERTA, TerritoryEnum.ALASKA));
    assertTrue(board.isNeighbor(TerritoryEnum.ALBERTA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(board.isNeighbor(TerritoryEnum.ALBERTA, TerritoryEnum.ONTARIO));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ALBERTA, TerritoryEnum.WESTERN_US));
  }

  /**
   *
   */
  @Test
  public void testOntarioNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ONTARIO,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(board.isNeighbor(TerritoryEnum.ONTARIO, TerritoryEnum.QUEBEC));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ONTARIO, TerritoryEnum.WESTERN_US));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ONTARIO, TerritoryEnum.EASTERN_US));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ONTARIO, TerritoryEnum.GREENLAND));
  }

  /**
   *
   */
  @Test
  public void testQuebecNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.QUEBEC, TerritoryEnum.ONTARIO));
    assertTrue(board.isNeighbor(TerritoryEnum.QUEBEC, TerritoryEnum.GREENLAND));
    assertTrue(
        board.isNeighbor(TerritoryEnum.QUEBEC, TerritoryEnum.EASTERN_US));
  }

  /**
   *
   */
  @Test
  public void testEasternUSNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.EASTERN_US, TerritoryEnum.ONTARIO));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EASTERN_US, TerritoryEnum.QUEBEC));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EASTERN_US, TerritoryEnum.WESTERN_US));
    assertTrue(board.isNeighbor(TerritoryEnum.EASTERN_US,
        TerritoryEnum.CENTRAL_AMERICA));
  }

  /**
   *
   */
  @Test
  public void testWesternUSNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.WESTERN_US, TerritoryEnum.ONTARIO));
    assertTrue(
        board.isNeighbor(TerritoryEnum.WESTERN_US, TerritoryEnum.ALBERTA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.WESTERN_US, TerritoryEnum.EASTERN_US));
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_US,
        TerritoryEnum.CENTRAL_AMERICA));
  }

  /**
   *
   */
  @Test
  public void testCentralAmericaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AMERICA,
        TerritoryEnum.EASTERN_US));
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AMERICA,
        TerritoryEnum.WESTERN_US));
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AMERICA,
        TerritoryEnum.VENEZUELA));
  }

  /**
   *
   */
  @Test
  public void testVenezuelaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.VENEZUELA,
        TerritoryEnum.CENTRAL_AMERICA));
    assertTrue(board.isNeighbor(TerritoryEnum.VENEZUELA, TerritoryEnum.PERU));
    assertTrue(board.isNeighbor(TerritoryEnum.VENEZUELA, TerritoryEnum.BRAZIL));
  }

  public void testPeruNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.PERU, TerritoryEnum.VENEZUELA));
    assertTrue(board.isNeighbor(TerritoryEnum.PERU, TerritoryEnum.BRAZIL));
    assertTrue(
        board.isNeighbor(TerritoryEnum.VENEZUELA, TerritoryEnum.ARGENTINIA));
  }

  public void testArgentiniaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ARGENTINIA, TerritoryEnum.PERU));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ARGENTINIA, TerritoryEnum.BRAZIL));
  }
}
