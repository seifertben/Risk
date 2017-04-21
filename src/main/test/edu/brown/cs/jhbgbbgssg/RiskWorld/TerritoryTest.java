package edu.brown.cs.jhbgbbgssg.RiskWorld;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentEnum;

public class TerritoryTest {

  @Test
  public void territoryConstructorTest() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    assertNotNull(territory);
  }

  @Test(expected = IllegalArgumentException.class)
  public void territoryConstructorNullTerr() {
    new Territory(null, ContinentEnum.NORTH_AMERICA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void territoryConstructorNullCont() {
    new Territory(TerritoryEnum.ALASKA, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void territoryConstructorNullInput() {
    new Territory(null, null);
  }

  @Test
  public void testInitialUnitSize() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    assertTrue(territory.getNumberTroops() == 0);
  }

  @Test
  public void testInitialPlayer() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    assertNull(territory.getOwner());
  }

  @Test
  public void testAddTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    territory.changePlayer(UUID.randomUUID(), 1);
    assertTrue(territory.getNumberTroops() == 1);
    territory.addTroops(3);
    assertTrue(territory.getNumberTroops() == 4);
  }

  @Test
  public void testRemoveTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    territory.changePlayer(UUID.randomUUID(), 3);
    assertTrue(territory.getNumberTroops() == 3);
    assertFalse(territory.removeTroops(2));
    assertTrue(territory.getNumberTroops() == 1);
  }

  @Test
  public void testRemoveAllTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    territory.changePlayer(UUID.randomUUID(), 3);
    assertTrue(territory.getNumberTroops() == 3);
    assertFalse(territory.removeTroops(2));
    assertTrue(territory.getNumberTroops() == 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAdddTroopsNoPlayer() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    territory.addTroops(3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePlayerZeroTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    territory.changePlayer(UUID.randomUUID(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePlayerNegativeTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    territory.changePlayer(UUID.randomUUID(), -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangePlayerNullId() {
    Territory territory = new Territory(TerritoryEnum.ALASKA,
        ContinentEnum.NORTH_AMERICA);
    territory.changePlayer(null, 3);
  }

}
