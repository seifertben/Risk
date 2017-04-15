package edu.brown.cs.jhbgbbgssg.RiskWorld;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class TerritoryTest {

  @Test
  public void territoryConstructorTest() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    assertNotNull(territory);
  }

  @Test(expected = IllegalArgumentException.class)
  public void territoryConstructorNullInput() {
    new Territory(null);
  }

  @Test
  public void testInitialUnitSize() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    assertTrue(territory.getNumberTroops() == 0);
  }

  @Test
  public void testAddTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    territory.addTroops(3);
    assertTrue(territory.getNumberTroops() == 3);
  }

  @Test
  public void testRemoveTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    territory.addTroops(3);
    assertTrue(territory.getNumberTroops() == 3);
    assertFalse(territory.removeTroops(2));
    assertTrue(territory.getNumberTroops() == 1);
  }

  @Test
  public void testRemoveAllTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    territory.addTroops(3);
    assertTrue(territory.getNumberTroops() == 3);
    assertFalse(territory.removeTroops(2));
    assertTrue(territory.getNumberTroops() == 1);

  }

}
