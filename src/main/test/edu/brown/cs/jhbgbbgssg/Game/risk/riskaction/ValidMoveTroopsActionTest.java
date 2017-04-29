package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidMoveTroopsAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidMoveTroopsActionTest {

  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    assertNotNull(action);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstrutorNullPlayer() {
    RiskBoard board = new RiskBoard();
    new ValidMoveTroopsAction(null, board);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstrutorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    new ValidMoveTroopsAction(player, null);
  }

  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    assertTrue(action.getMoveType() == MoveType.MOVE_TROOPS);
  }

  @Test
  public void testGetMovePlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    assertTrue(action.getMovePlayer().equals(player.getPlayerId()));
  }

  @Test
  public void testGetMoveableTroops() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 2);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    Multimap<TerritoryEnum, TerritoryEnum> map = action
        .getReachableTerritores();
    assertTrue(map.keySet().size() == 5);
    assertTrue(map.get(TerritoryEnum.WESTERN_US).size() == 2);
    assertTrue(map.containsEntry(TerritoryEnum.WESTERN_US,
        TerritoryEnum.CENTRAL_AMERICA));
    assertTrue(
        map.containsEntry(TerritoryEnum.WESTERN_US, TerritoryEnum.VENEZUELA));
    assertTrue(map.get(TerritoryEnum.CENTRAL_AMERICA).size() == 2);
    assertTrue(map.containsEntry(TerritoryEnum.CENTRAL_AMERICA,
        TerritoryEnum.WESTERN_US));
    assertTrue(map.containsEntry(TerritoryEnum.CENTRAL_AMERICA,
        TerritoryEnum.VENEZUELA));
    assertTrue(map.get(TerritoryEnum.VENEZUELA).size() == 2);
    assertTrue(
        map.containsEntry(TerritoryEnum.VENEZUELA, TerritoryEnum.WESTERN_US));
    assertTrue(map.containsEntry(TerritoryEnum.VENEZUELA,
        TerritoryEnum.CENTRAL_AMERICA));
    assertTrue(map.get(TerritoryEnum.GREENLAND).size() == 1);
    assertTrue(
        map.containsEntry(TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND));
    assertTrue(map.get(TerritoryEnum.ICELAND).size() == 1);
    assertTrue(
        map.containsEntry(TerritoryEnum.ICELAND, TerritoryEnum.GREENLAND));
  }

  @Test
  public void testGetNumberTroopsCanMove() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 4);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 7);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    Map<TerritoryEnum, Integer> map = action.maxTroopsToMove();
    assertTrue(map.size() == 5);
    assertTrue(map.get(TerritoryEnum.WESTERN_US) == 3);
    assertTrue(map.get(TerritoryEnum.CENTRAL_AMERICA) == 4);
    assertTrue(map.get(TerritoryEnum.VENEZUELA) == 2);
    assertTrue(map.get(TerritoryEnum.ICELAND) == 1);
    assertTrue(map.get(TerritoryEnum.GREENLAND) == 6);
  }

  @Test
  public void testNoEnoughTroopsToMove() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    assertTrue(action.getReachableTerritores().size() == 0);
    assertTrue(action.maxTroopsToMove().size() == 0);
    assertFalse(action.actionAvailable());
  }

  @Test
  public void testNoReachableTerritories() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 5);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    assertTrue(action.getReachableTerritores().size() == 0);
    assertTrue(action.maxTroopsToMove().size() == 0);
    assertFalse(action.actionAvailable());
  }

  @Test
  public void testValidMoveTroopsMove() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 4);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 7);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.WESTERN_US, TerritoryEnum.VENEZUELA, 3);
    assertTrue(action.validMoveTroopMove(move));
    move = new MoveTroopsAction(player, board, TerritoryEnum.WESTERN_US,
        TerritoryEnum.VENEZUELA, 2);
    assertTrue(action.validMoveTroopMove(move));
    move = new MoveTroopsAction(player, board, TerritoryEnum.WESTERN_US,
        TerritoryEnum.VENEZUELA, 1);
    assertTrue(action.validMoveTroopMove(move));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 4);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 7);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    MoveTroopsAction move = new MoveTroopsAction(
        new RiskPlayer(UUID.randomUUID()), board, TerritoryEnum.WESTERN_US,
        TerritoryEnum.VENEZUELA, 2);
    assertFalse(action.validMoveTroopMove(move));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidFromTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 4);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 7);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.EASTERN_US, TerritoryEnum.WESTERN_US, 1);
    assertFalse(action.validMoveTroopMove(move));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidToTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 4);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 7);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.WESTERN_US, TerritoryEnum.ICELAND, 2);
    assertFalse(action.validMoveTroopMove(move));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidNumberTroops() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 4);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 7);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.WESTERN_US, TerritoryEnum.CENTRAL_AMERICA, 4);
    assertFalse(action.validMoveTroopMove(move));
    move = new MoveTroopsAction(player, board, TerritoryEnum.WESTERN_US,
        TerritoryEnum.CENTRAL_AMERICA, 0);
    assertFalse(action.validMoveTroopMove(move));
    move = new MoveTroopsAction(player, board, TerritoryEnum.WESTERN_US,
        TerritoryEnum.CENTRAL_AMERICA, -1);
    assertFalse(action.validMoveTroopMove(move));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testActionNotAvailable() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.VENEZUELA);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.VENEZUELA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    ValidMoveTroopsAction action = new ValidMoveTroopsAction(player, board);
    MoveTroopsAction move = new MoveTroopsAction(player, board,
        TerritoryEnum.WESTERN_US, TerritoryEnum.CENTRAL_AMERICA, 1);
    assertFalse(action.validMoveTroopMove(move));
    assertFalse(action.actionAvailable());
  }
}
