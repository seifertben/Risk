package edu.brown.cs.jhbgbssg.RiskWorld;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentEnum;

/**
 * JUnit test for Territory.
 *
 * @author sarahgilmore
 *
 */
public class TerritoryTest {
  private static final int NEGATIVE_TROOPS = -3;

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void territoryConstructorTest() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    assertNotNull(territory);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if territory id is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void territoryConstructorNullTerr() {
    new Territory(null, ContinentEnum.NORTH_AMERICA);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if continent id is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void territoryConstructorNullCont() {
    new Territory(TerritoryEnum.ALASKA, null);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if territory id
   * and the continent id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void territoryConstructorNullInput() {
    new Territory(null, null);
  }

  /**
   * Tests that the initial number of troops is zero.
   */
  @Test
  public void testInitialUnitSize() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    assertTrue(territory.getNumberTroops() == 0);
  }

  /**
   * Tests that the owner of the territory is null initially.
   */
  @Test
  public void testInitialPlayer() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    assertNull(territory.getOwner());
  }

  /**
   * Tests addTroops adds the specified number of troops to the territory.
   */
  @Test
  public void testAddTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, 1);
    assertTrue(territory.getNumberTroops() == 1);
    territory.addTroops(3);
    assertTrue(territory.getNumberTroops() == 4);
  }

  /**
   * Tests removeTroops removes the specified number of troops to the territory
   * and returns true if all the troops have been removed.
   */
  @Test
  public void testRemoveTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, 3);
    assertTrue(territory.getNumberTroops() == 3);
    assertFalse(territory.removeTroops(2));
    assertTrue(territory.getNumberTroops() == 1);
  }

  /**
   * Tests that once all the troops have been removed, the territory's owner is
   * null.
   */
  @Test
  public void testRemoveAllTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, 3);
    assertTrue(territory.getNumberTroops() == 3);
    assertFalse(territory.removeTroops(2));
    assertTrue(territory.getNumberTroops() == 1);
    assertTrue(territory.removeTroops(1));
    assertNull(territory.getOwner());
  }

  /**
   * Tests that addTroops returns false and does not add any troops if the
   * territory has no owner.
   */
  @Test
  public void testAdddTroopsNoPlayer() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    assertFalse(territory.addTroops(3));
    assertTrue(territory.getNumberTroops() == 0);
  }

  /**
   * Tests that changePlayer throws an IllegalArgumentException if the number of
   * troops to add is zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangePlayerZeroTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, 0);
  }

  /**
   * Tests that changePlayer throws an IllegalArgumentException if the number of
   * troops to add is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangePlayerNegativeTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, NEGATIVE_TROOPS);
  }

  /**
   * Tests that changePlayer throws an IllegalArgumentException if the player
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangePlayerNullId() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    territory.changePlayer(null, 3);
  }

}
