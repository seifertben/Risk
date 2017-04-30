package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for Africa.
 *
 * @author sarahgilmore
 *
 */
public class AfricaTest {
  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    Africa cont = new Africa();
    assertNotNull(cont);
  }

  /**
   * Tests get territories returns the right territory ids.
   */
  @Test
  public void testGetTerritories() {
    Africa cont = new Africa();
    Set<TerritoryEnum> terrs = cont.getTerritories();
    assertTrue(terrs.size() == 6);
    assertTrue(terrs.contains(TerritoryEnum.EGYPT));
    assertTrue(terrs.contains(TerritoryEnum.EAST_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.CENTRAL_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.SOUTH_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.MADAGASCAR));
    assertTrue(terrs.contains(TerritoryEnum.NORTH_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.NORTH_AFRICA));

  }

  /**
   * Tests that getBonusValue returns 5.
   */
  @Test
  public void testGetBonusValue() {
    Africa cont = new Africa();
    assertTrue(cont.getBonusValue() == 3);
  }

  /**
   * Tests getContinentId returns AFRICA.
   */
  @Test
  public void testGetContinentId() {
    Africa cont = new Africa();
    assertTrue(cont.getContinentId() == ContinentEnum.AFRICA);
  }

  /**
   * Test containsTerritory returns true only for Territories that are within
   * South America and false otherwise.
   */
  @Test
  public void testContainsTerritory() {
    Africa cont = new Africa();
    Set<TerritoryEnum> territories = new HashSet<>();
    territories
        .addAll(Arrays.asList(TerritoryEnum.EGYPT, TerritoryEnum.NORTH_AFRICA,
            TerritoryEnum.EAST_AFRICA, TerritoryEnum.SOUTH_AFRICA,
            TerritoryEnum.CENTRAL_AFRICA, TerritoryEnum.MADAGASCAR));
    TerritoryEnum[] terrs = TerritoryEnum.values();
    for (int i = 0; i < terrs.length; i++) {
      if (territories.contains(terrs[i])) {
        assertTrue(cont.containsTerritory(terrs[i]));
      } else {
        assertFalse(cont.containsTerritory(terrs[i]));
      }
    }
  }

  /**
   * Tests that containsTerritory throws an IllegalArgumentException if the
   * territory given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testContainsTerritoryNullTerritory() {
    Africa cont = new Africa();
    cont.containsTerritory(null);
  }
}
