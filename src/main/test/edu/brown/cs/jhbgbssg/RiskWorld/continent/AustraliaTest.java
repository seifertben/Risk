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
 * JUnit tests for Australia.
 *
 * @author sarahgilmore
 *
 */
public class AustraliaTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    Australia cont = new Australia();
    assertNotNull(cont);
  }

  /**
   * Tests get territories returns the right territory ids.
   */
  @Test
  public void testGetTerritories() {
    Australia cont = new Australia();
    Set<TerritoryEnum> terrs = cont.getTerritories();
    assertTrue(terrs.size() == 4);
    assertTrue(terrs.contains(TerritoryEnum.INDONESIA));
    assertTrue(terrs.contains(TerritoryEnum.NEW_GUINEA));
    assertTrue(terrs.contains(TerritoryEnum.WESTERN_AUSTRALIA));
    assertTrue(terrs.contains(TerritoryEnum.EASTERN_AUSTRALIA));
  }

  /**
   * Tests that getBonusValue returns 2.
   */
  @Test
  public void testGetBonusValue() {
    Australia cont = new Australia();
    assertTrue(cont.getBonusValue() == 2);
  }

  /**
   * Tests getContinentId returns AUSTRALIA.
   */
  @Test
  public void testGetContinentId() {
    Australia cont = new Australia();
    assertTrue(cont.getContinentId() == ContinentEnum.AUSTRALIA);
  }

  /**
   * Test containsTerritory returns true only for Territories that are within
   * Africa and false otherwise.
   */
  @Test
  public void testContainsTerritory() {
    Australia cont = new Australia();
    Set<TerritoryEnum> territories = new HashSet<>();
    territories
        .addAll(Arrays.asList(TerritoryEnum.INDONESIA, TerritoryEnum.NEW_GUINEA,
            TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA));
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
    Australia cont = new Australia();
    cont.containsTerritory(null);
  }
}
