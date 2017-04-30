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
 * JUnit tests for NorthAmerica.
 *
 * @author sarahgilmore
 *
 */
public class NorthAmericaTest {
  private static final int NUMBER_TERRITORIES = 9;

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    NorthAmerica cont = new NorthAmerica();
    assertNotNull(cont);
  }

  /**
   * Tests get territories returns the right territory ids.
   */
  @Test
  public void testGetTerritories() {
    NorthAmerica cont = new NorthAmerica();
    Set<TerritoryEnum> terrs = cont.getTerritories();
    assertTrue(terrs.size() == NUMBER_TERRITORIES);
    assertTrue(terrs.contains(TerritoryEnum.ALASKA));
    assertTrue(terrs.contains(TerritoryEnum.ALBERTA));
    assertTrue(terrs.contains(TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(terrs.contains(TerritoryEnum.ONTARIO));
    assertTrue(terrs.contains(TerritoryEnum.QUEBEC));
    assertTrue(terrs.contains(TerritoryEnum.WESTERN_US));
    assertTrue(terrs.contains(TerritoryEnum.EASTERN_US));
    assertTrue(terrs.contains(TerritoryEnum.CENTRAL_AMERICA));
    assertTrue(terrs.contains(TerritoryEnum.GREENLAND));

  }

  /**
   * Tests that getBonusValue returns 5.
   */
  @Test
  public void testGetBonusValue() {
    NorthAmerica cont = new NorthAmerica();
    assertTrue(cont.getBonusValue() == 5);
  }

  /**
   * Tests getContinentId returns NORTH_AMERICA.
   */
  @Test
  public void testGetContinentId() {
    NorthAmerica cont = new NorthAmerica();
    assertTrue(cont.getContinentId() == ContinentEnum.NORTH_AMERICA);
  }

  /**
   * Test containsTerritory returns true only for Territories that are within
   * North America and false otherwise.
   */
  @Test
  public void testContainsTerritory() {
    NorthAmerica cont = new NorthAmerica();
    Set<TerritoryEnum> territories = new HashSet<>();
    territories.addAll(
        Arrays.asList(TerritoryEnum.ALASKA, TerritoryEnum.NORTHWEST_TERRITORY,
            TerritoryEnum.ALBERTA, TerritoryEnum.ONTARIO, TerritoryEnum.QUEBEC,
            TerritoryEnum.WESTERN_US, TerritoryEnum.EASTERN_US,
            TerritoryEnum.CENTRAL_AMERICA, TerritoryEnum.GREENLAND));
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
    NorthAmerica cont = new NorthAmerica();
    cont.containsTerritory(null);
  }
}
