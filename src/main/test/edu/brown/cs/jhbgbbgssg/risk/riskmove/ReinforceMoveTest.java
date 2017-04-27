package edu.brown.cs.jhbgbbgssg.risk.riskmove;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ReinforceAction;
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
    player.
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(, map);
    assertNotNull(reinforce);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the player
   * id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullId() {
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    new ReinforceAction(null, map);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the map is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullMap() {
    new ReinforceAction(UUID.randomUUID(), null);
  }

  /**
   * Tests that getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(UUID.randomUUID(), map);
    assertTrue(reinforce.getMoveType() == MoveType.REINFORCE);
  }

  /**
   * Tests that getReinforcedTerritories returns the correct map.
   */
  @Test
  public void testGetReinforcedTerritories() {
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.SOUTHEAST_ASIA, 3);
    map.put(TerritoryEnum.ARGENTINA, 1);
    map.put(TerritoryEnum.INDONESIA, 2);
    ReinforceAction reinforce = new ReinforceAction(UUID.randomUUID(), map);
    Map<TerritoryEnum, Integer> reinforceMap = reinforce
        .getReinforcedTerritories();
    assertTrue(reinforceMap.size() == 3);
    assertTrue(reinforceMap.containsKey(TerritoryEnum.SOUTHEAST_ASIA));
    assertTrue(reinforceMap.get(TerritoryEnum.SOUTHEAST_ASIA) == 3);
    assertTrue(reinforceMap.containsKey(TerritoryEnum.ARGENTINA));
    assertTrue(reinforceMap.get(TerritoryEnum.ARGENTINA) == 1);
    assertTrue(reinforceMap.containsKey(TerritoryEnum.INDONESIA));
    assertTrue(reinforceMap.get(TerritoryEnum.INDONESIA) == 2);

  }
}
