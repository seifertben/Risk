package edu.brown.cs.jhbgbssg.Game.RiskWorld.continent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for Europe.
 *
 * @author sarahgilmore
 *
 */
public class EuropeTest {
  private static final int NUMBER_TERRITORIES = 7;

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    Europe cont = new Europe();
    assertNotNull(cont);
  }

  /**
   * Tests get territories returns the right territory ids.
   */
  @Test
  public void testGetTerritories() {
    Europe cont = new Europe();
    Set<TerritoryEnum> terrs = cont.getTerritories();
    System.out.println(terrs);
    assertTrue(terrs.size() == NUMBER_TERRITORIES);
    assertTrue(terrs.contains(TerritoryEnum.ICELAND));
    assertTrue(terrs.contains(TerritoryEnum.GREAT_BRITIAN));
    assertTrue(terrs.contains(TerritoryEnum.SCANDINAVIA));
    assertTrue(terrs.contains(TerritoryEnum.WESTERN_EUROPE));
    assertTrue(terrs.contains(TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(terrs.contains(TerritoryEnum.RUSSIA));
    assertTrue(terrs.contains(TerritoryEnum.NORTHERN_EUROPE));

  }

  /**
   * Tests that getBonusValue returns 5.
   */
  @Test
  public void testGetBonusValue() {
    Europe cont = new Europe();
    assertTrue(cont.getBonusValue() == 5);
  }

  /**
   * Tests getContinentId returns EUROPE.
   */
  @Test
  public void testGetContinentId() {
    Europe cont = new Europe();
    assertTrue(cont.getContinentId() == ContinentEnum.EUROPE);
  }

  /**
   * Test containsTerritory returns true only for Territories that are within
   * North America and false otherwise.
   */
  @Test
  public void testContainsTerritory() {
    Europe cont = new Europe();
    Set<TerritoryEnum> territories = new HashSet<>();
    territories.addAll(Arrays.asList(TerritoryEnum.ICELAND,
        TerritoryEnum.NORTHERN_EUROPE, TerritoryEnum.SCANDINAVIA,
        TerritoryEnum.GREAT_BRITIAN, TerritoryEnum.RUSSIA,
        TerritoryEnum.SOUTHERN_EUROPE, TerritoryEnum.WESTERN_EUROPE));
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
    Europe cont = new Europe();
    cont.containsTerritory(null);
  }
}
