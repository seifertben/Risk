package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidReinforceAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentInterface;

/**
 *
 *
 * @author sarahgilmore
 *
 */
public class ValidReinforceMoveTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertNotNull(action);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the the player
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskBoard board = new RiskBoard();
    new ValidReinforceAction(null, board);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the the board
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    new ValidReinforceAction(player, null);
  }

  /**
   * Tests getMoveType returns REINFORCE.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.getMoveType() == MoveType.REINFORCE);
  }

  /**
   * Tests that getPlayer returns the correct player.
   */
  @Test
  public void testGetPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.getMovePlayer().equals(player.getPlayerId()));
  }

  /**
   *
   */
  @Test
  public void testActionAvailable() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.ONTARIO);
    player.conqueredTerritory(TerritoryEnum.ARGENTINA);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ONTARIO).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(player, 3);
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.actionAvailable());
    assertTrue(action.getTerritories().size() == 3);
    assertTrue(action.getTerritories().contains(TerritoryEnum.ALASKA));
    assertTrue(action.getTerritories().contains(TerritoryEnum.ONTARIO));
    assertTrue(action.getTerritories().contains(TerritoryEnum.ARGENTINA));
  }

  /**
   * Tests that the number of troops to reinforce includes the North America
   * continental bonus.
   */
  @Test
  public void testNorthAmericaContinentalBonus() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ContinentInterface cont = board.getContinent(ContinentEnum.NORTH_AMERICA);
    for (TerritoryEnum id : cont.getTerritories()) {
      player.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player, 2);
    }
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.actionAvailable());
    assertTrue(action.getTerritories().containsAll(player.getTerritories()));
    assertTrue(player.getTerritories().containsAll(action.getTerritories()));
    int size = player.getNumberTerritories();
    assertTrue(player.getTerritories().containsAll(
        board.getContinent(ContinentEnum.NORTH_AMERICA).getTerritories()));
    int troops = size / 3 + 5;
    assertTrue(troops == action.getNumberToReinforce());
  }

  /**
   * Tests that the number of troops to reinforce includes the South America
   * continental bonus.
   */
  @Test
  public void testSouthAmericaContinentalBonus() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ContinentInterface cont = board.getContinent(ContinentEnum.SOUTH_AMERICA);
    for (TerritoryEnum id : cont.getTerritories()) {
      player.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player, 2);
    }
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.actionAvailable());
    assertTrue(action.getTerritories().containsAll(player.getTerritories()));
    assertTrue(player.getTerritories().containsAll(action.getTerritories()));
    int size = player.getNumberTerritories();
    assertTrue(player.getTerritories().containsAll(
        board.getContinent(ContinentEnum.SOUTH_AMERICA).getTerritories()));
    int troops = size / 3 + 2;
    assertTrue(troops == action.getNumberToReinforce());
  }

  /**
   * Tests that the number of troops to reinforce includes the Africa
   * continental bonus.
   */
  @Test
  public void testAfricaContinentalBonus() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ContinentInterface cont = board.getContinent(ContinentEnum.AFRICA);
    for (TerritoryEnum id : cont.getTerritories()) {
      player.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player, 2);
    }
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.actionAvailable());
    assertTrue(action.getTerritories().containsAll(player.getTerritories()));
    assertTrue(player.getTerritories().containsAll(action.getTerritories()));
    int size = player.getNumberTerritories();
    assertTrue(player.getTerritories().containsAll(
        board.getContinent(ContinentEnum.AFRICA).getTerritories()));
    int troops = size / 3 + 3;
    assertTrue(troops == action.getNumberToReinforce());
  }

  /**
   * Tests that the number of troops to reinforce includes the Africa
   * continental bonus.
   */
  @Test
  public void testEuropeContinentalBonus() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ContinentInterface cont = board.getContinent(ContinentEnum.EUROPE);
    for (TerritoryEnum id : cont.getTerritories()) {
      player.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player, 2);
    }
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.actionAvailable());
    assertTrue(action.getTerritories().containsAll(player.getTerritories()));
    assertTrue(player.getTerritories().containsAll(action.getTerritories()));
    int size = player.getNumberTerritories();
    assertTrue(player.getTerritories().containsAll(
        board.getContinent(ContinentEnum.EUROPE).getTerritories()));
    int troops = size / 3 + 5;
    assertTrue(troops == action.getNumberToReinforce());
  }

  /**
   * Tests that the number of troops to reinforce includes the Africa
   * continental bonus.
   */
  @Test
  public void testAsiaContinentalBonus() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ContinentInterface cont = board.getContinent(ContinentEnum.ASIA);
    for (TerritoryEnum id : cont.getTerritories()) {
      player.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player, 2);
    }
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.actionAvailable());
    assertTrue(action.getTerritories().containsAll(player.getTerritories()));
    assertTrue(player.getTerritories().containsAll(action.getTerritories()));
    int size = player.getNumberTerritories();
    assertTrue(player.getTerritories()
        .containsAll(board.getContinent(ContinentEnum.ASIA).getTerritories()));
    int troops = size / 3 + 11;
    assertTrue(troops == action.getNumberToReinforce());
  }

  /**
   * Tests that the number of troops to reinforce includes the Africa
   * continental bonus.
   */
  @Test
  public void testAustraliaContinentalBonus() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ContinentInterface cont = board.getContinent(ContinentEnum.AUSTRALIA);
    for (TerritoryEnum id : cont.getTerritories()) {
      player.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player, 2);
    }
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertTrue(action.actionAvailable());
    assertTrue(action.getTerritories().containsAll(player.getTerritories()));
    assertTrue(player.getTerritories().containsAll(action.getTerritories()));
    int size = player.getNumberTerritories();
    assertTrue(player.getTerritories().containsAll(
        board.getContinent(ContinentEnum.AUSTRALIA).getTerritories()));
    int troops = size / 3 + 2;
    assertTrue(troops == action.getNumberToReinforce());
  }
}
