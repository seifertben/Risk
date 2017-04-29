package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for ReinforceMove.
 *
 * @author sarahgilmore
 *
 */
public class ReinforceMoveTest {

  /**
   * Tests that the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(player, board, map);
    assertNotNull(reinforce);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the player
   * id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    new ReinforceAction(null, board, map);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the board
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    new ReinforceAction(player, null, map);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the map is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullMap() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    new ReinforceAction(player, board, null);
  }

  /**
   * Tests that getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(player, board, map);
    assertTrue(reinforce.getMoveType() == MoveType.REINFORCE);
  }

  /**
   * Tests that getReinforcedTerritories returns the correct map.
   */
  @Test
  public void testGetReinforcedTerritories() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(player, board, map);
    Map<TerritoryEnum, Integer> reinforceMap = reinforce
        .getReinforcedTerritories();
    assertTrue(reinforceMap.size() == 3);
    assertTrue(reinforceMap.containsKey(TerritoryEnum.SOUTHEAST_ASIA));
    assertTrue(reinforceMap.containsKey(TerritoryEnum.ARGENTINA));
    assertTrue(reinforceMap.containsKey(TerritoryEnum.INDONESIA));
    assertTrue(reinforceMap.get(TerritoryEnum.SOUTHEAST_ASIA) == 3);
    assertTrue(reinforceMap.get(TerritoryEnum.ARGENTINA) == 1);
    assertTrue(reinforceMap.get(TerritoryEnum.INDONESIA) == 2);
  }

  /**
   * Tests that getPlayer returns the correct player.
   */
  @Test
  public void testGetPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(player, board, map);
    assertTrue(reinforce.getMovePlayer().equals(player));
  }

  /**
   * Tests that executeAction returns true on the first call and that it
   * reinforces the territories.
   */
  @Test
  public void testExecuteAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(player, board, map);
    assertTrue(board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA)
        .getNumberTroops() == 3);
    assertTrue(
        board.getTerritory(TerritoryEnum.ARGENTINA).getNumberTroops() == 2);
    assertTrue(
        board.getTerritory(TerritoryEnum.INDONESIA).getNumberTroops() == 6);
    assertTrue(reinforce.executeAction());
    assertTrue(board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA)
        .getNumberTroops() == 6);
    assertTrue(
        board.getTerritory(TerritoryEnum.ARGENTINA).getNumberTroops() == 3);
    assertTrue(
        board.getTerritory(TerritoryEnum.INDONESIA).getNumberTroops() == 8);
  }

  /**
   * Tests that calling executeAction twice has no effect.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(player, board, map);
    assertTrue(board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA)
        .getNumberTroops() == 3);
    assertTrue(
        board.getTerritory(TerritoryEnum.ARGENTINA).getNumberTroops() == 2);
    assertTrue(
        board.getTerritory(TerritoryEnum.INDONESIA).getNumberTroops() == 6);
    assertTrue(reinforce.executeAction());
    assertTrue(board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA)
        .getNumberTroops() == 6);
    assertTrue(
        board.getTerritory(TerritoryEnum.ARGENTINA).getNumberTroops() == 3);
    assertTrue(
        board.getTerritory(TerritoryEnum.INDONESIA).getNumberTroops() == 8);
    assertFalse(reinforce.executeAction());
    assertTrue(board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA)
        .getNumberTroops() == 6);
    assertTrue(
        board.getTerritory(TerritoryEnum.ARGENTINA).getNumberTroops() == 3);
    assertTrue(
        board.getTerritory(TerritoryEnum.INDONESIA).getNumberTroops() == 8);
  }

  /**
   * Tests that calling executeAction returns true.
   */
  @Test
  public void testExecuteActionTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(player, board, map);
    assertTrue(reinforce.executeAction());
  }

  /**
   * Tests that after the first call executeAction returns false.
   */
  @Test
  public void testExecuteActionFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.SOUTHEAST_ASIA);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    player.conqueredTerritory(TerritoryEnum.INDONESIA);
    board.getTerritory(TerritoryEnum.SOUTHEAST_ASIA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.INDONESIA).changePlayer(player, 6);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(player, board, map);
    assertTrue(reinforce.executeAction());
    assertFalse(reinforce.executeAction());
  }
}
