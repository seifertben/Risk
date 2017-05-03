package edu.brown.cs.jhbgbssg.Game.RiskWorld.continent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.continent.ContinentEnum;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.continent.SouthAmerica;

/**
 * JUnit tests for SouthAmerica.
 *
 * @author sarahgilmore
 *
 */
public class SouthAmericaTest {
  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    SouthAmerica cont = new SouthAmerica();
    assertNotNull(cont);
  }

  /**
   * Tests get territories returns the right territory ids.
   */
  @Test
  public void testGetTerritories() {
    SouthAmerica cont = new SouthAmerica();
    Set<TerritoryEnum> terrs = cont.getTerritories();
    assertTrue(terrs.size() == 4);
    assertTrue(terrs.contains(TerritoryEnum.VENEZUELA));
    assertTrue(terrs.contains(TerritoryEnum.PERU));
    assertTrue(terrs.contains(TerritoryEnum.BRAZIL));
    assertTrue(terrs.contains(TerritoryEnum.ARGENTINA));
  }

  /**
   * Tests that getBonusValue returns 5.
   */
  @Test
  public void testGetBonusValue() {
    SouthAmerica cont = new SouthAmerica();
    assertTrue(cont.getBonusValue() == 2);
  }

  /**
   * Tests getContinentId returns SOUTH_AMERICA.
   */
  @Test
  public void testGetContinentId() {
    SouthAmerica cont = new SouthAmerica();
    assertTrue(cont.getContinentId() == ContinentEnum.SOUTH_AMERICA);
  }

  /**
   * Test containsTerritory returns true only for Territories that are within
   * South America and false otherwise.
   */
  @Test
  public void testContainsTerritory() {
    SouthAmerica cont = new SouthAmerica();
    Set<TerritoryEnum> territories = new HashSet<>();
    territories.addAll(Arrays.asList(TerritoryEnum.VENEZUELA,
        TerritoryEnum.BRAZIL, TerritoryEnum.PERU, TerritoryEnum.ARGENTINA));
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
    SouthAmerica cont = new SouthAmerica();
    cont.containsTerritory(null);
  }
}
