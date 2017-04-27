package edu.brown.cs.jhbgbbgssg.risk.riskmove;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for CardTurnInAction.
 *
 * @author sarahgilmore
 *
 */
public class CardTurnInMoveTest {

  /**
   * Tests constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(1);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    CardTurnInAction cardMove = new CardTurnInAction(player, board, 1, map);
    assertNotNull(cardMove);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the player is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(1);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    CardTurnInAction cardMove = new CardTurnInAction(null, board, 1, map);
    assertNotNull(cardMove);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the board is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(1);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    CardTurnInAction cardMove = new CardTurnInAction(player, null, 1, map);
    assertNotNull(cardMove);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the map is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullMap() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(1);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    CardTurnInAction cardMove = new CardTurnInAction(player, board, 1, null);
    assertNotNull(cardMove);
  }

  /**
   * Tests getCard returns the card value.
   */
  @Test
  public void testGetCard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 2);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 2);
    CardTurnInAction cardMove = new CardTurnInAction(player, board, 2, map);
    assertTrue(cardMove.getCard() == 2);
  }

  /**
   * Tests getReinforcedTerritories returns the correct map.
   */
  @Test
  public void testGetReinforcedTerritories() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    map.put(TerritoryEnum.GREENLAND, 1);
    CardTurnInAction cardMove = new CardTurnInAction(player, board, 2, map);
    Map<TerritoryEnum, Integer> reinforced = cardMove
        .getTerritoriesReinforced();
    assertTrue(reinforced.size() == 2);
    assertTrue(reinforced.containsKey(TerritoryEnum.ALASKA));
    assertTrue(reinforced.containsKey(TerritoryEnum.GREENLAND));
    assertTrue(reinforced.get(TerritoryEnum.ALASKA) == 1);
    assertTrue(reinforced.get(TerritoryEnum.GREENLAND) == 1);
  }

  /**
   * Tests executeAction returns true on the first call.
   */
  @Test
  public void testExecuteActionTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    map.put(TerritoryEnum.GREENLAND, 1);
    CardTurnInAction cardMove = new CardTurnInAction(player, board, 2, map);
    assertTrue(cardMove.executeAction());
  }

  /**
   * Tests executeAction returns false after the first call.
   */
  @Test
  public void testExecuteActionFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    map.put(TerritoryEnum.GREENLAND, 1);
    CardTurnInAction cardMove = new CardTurnInAction(player, board, 2, map);
    assertTrue(cardMove.executeAction());
    assertFalse(cardMove.executeAction());
  }

  /**
   * Tests that executeAction actually turns in the card and reinforces the
   * proper territories.
   */
  @Test
  public void testExecuteAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    map.put(TerritoryEnum.GREENLAND, 1);
    CardTurnInAction cardMove = new CardTurnInAction(player, board, 2, map);
    assertTrue(player.hasCard(2));
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 1);
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 1);
    cardMove.executeAction();
    assertFalse(player.hasCard(2));
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 2);
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 2);
  }

  /**
   * Tests that calling executeAction twice has no effect after the first call.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 1);
    map.put(TerritoryEnum.GREENLAND, 1);
    CardTurnInAction cardMove = new CardTurnInAction(player, board, 2, map);
    assertTrue(player.hasCard(2));
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 1);
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 1);
    cardMove.executeAction();
    assertFalse(player.hasCard(2));
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 2);
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 2);
    assertFalse(cardMove.executeAction());
    assertFalse(player.hasCard(2));
    assertTrue(
        board.getTerritory(TerritoryEnum.GREENLAND).getNumberTroops() == 2);
    assertTrue(board.getTerritory(TerritoryEnum.ALASKA).getNumberTroops() == 2);
  }
}
