package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

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
public class ValidReinforceActionTest {

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

  /**
   * Tests that for a random territory assignment, the action returns the
   * correct number of troops to reinforce and the correct territory set.
   */
  @Test
  public void testRandomTerritoryAssignment() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    Collection<TerritoryEnum> terrs = board.getTerritoryIds();
    ArrayList<TerritoryEnum> arr = new ArrayList<>(terrs);
    Collections.shuffle(arr);
    for (int i = 0; i < 19; i++) {
      player.conqueredTerritory(arr.get(i));
      board.getTerritory(arr.get(i)).changePlayer(player, 2);
    }
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    int numberToReinforce = 0;
    for (ContinentInterface cont : board.getContinents()) {
      if (player.getTerritories().containsAll(cont.getTerritories())) {
        numberToReinforce += cont.getBonusValue();
      }
    }
    numberToReinforce += 19 / 3;
    assertTrue(action.getNumberToReinforce() == numberToReinforce);
    assertTrue(player.getTerritories().containsAll(action.getTerritories()));
    assertTrue(action.getTerritories().containsAll(player.getTerritories()));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that if the player has no territories that can be reinforced,
   * actionAvailable returns false.
   */
  @Test
  public void testNoReinforcementActionAvailable() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    assertFalse(action.actionAvailable());
  }

  /**
   * 
   */
  @Test
  public void testValidReinforcementMove() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ContinentInterface cont = board.getContinent(ContinentEnum.ASIA);
    for (TerritoryEnum id : cont.getTerritories()) {
      player.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player, 2);
    }
    ValidReinforceAction action = new ValidReinforceAction(player, board);
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.CHINA, 5);
    map.put(TerritoryEnum.JAPAN, 5);
    map.put(TerritoryEnum.MONGOLIA, 2);
    // ReinforceAction reinforce = new ReinforceAction(player, board, player);
  }

  @Test
  public void test() {
    Gson gson = new Gson();
    List<List<Integer>> list = new ArrayList<>();
    list.add(Arrays.asList(1, 3));
    list.add(Arrays.asList(3, 4));
    JsonObject obj = new JsonObject();
    obj.addProperty("list", gson.toJson(list));

    List<List<Integer>> territories = gson.fromJson(
        obj.get("list").getAsString(), new TypeToken<List<List<Integer>>>() {
        }.getType());
    System.out.println(territories);
    Map<TerritoryEnum, Integer> toReinforce = new HashMap<>();
    for (List<Integer> el : territories) {
      if (el.size() == 2) {
        TerritoryEnum key = TerritoryEnum.values()[el.get(0)];
        toReinforce.put(key, el.get(1));
      }
    }
    System.out.println(toReinforce);
  }
}
