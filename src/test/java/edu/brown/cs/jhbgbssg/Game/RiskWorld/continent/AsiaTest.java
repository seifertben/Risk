package edu.brown.cs.jhbgbssg.Game.RiskWorld.continent;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.continent.Asia;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.continent.ContinentEnum;

/**
 * JUnit tests for Asia.
 *
 * @author sarahgilmore
 *
 */
public class AsiaTest {
  private static final int NUMBER_TERRITORIES = 12;
  private static final int BONUS = 11;

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    Asia cont = new Asia();
    assertNotNull(cont);
  }

  /**
   * Tests get territories returns the right territory ids.
   */
  @Test
  public void testGetTerritories() {
    Asia cont = new Asia();
    Set<TerritoryEnum> terrs = cont.getTerritories();
    assertTrue(terrs.size() == NUMBER_TERRITORIES);
    assertTrue(terrs.contains(TerritoryEnum.MIDDLE_EAST));
    assertTrue(terrs.contains(TerritoryEnum.AFGHANISTAN));
    assertTrue(terrs.contains(TerritoryEnum.INDIA));
    assertTrue(terrs.contains(TerritoryEnum.CHINA));
    assertTrue(terrs.contains(TerritoryEnum.MONGOLIA));
    assertTrue(terrs.contains(TerritoryEnum.IRKUTSK));
    assertTrue(terrs.contains(TerritoryEnum.JAPAN));
    assertTrue(terrs.contains(TerritoryEnum.URAL));
    assertTrue(terrs.contains(TerritoryEnum.KAMACHATKA));
    assertTrue(terrs.contains(TerritoryEnum.SOUTHEAST_ASIA));
    assertTrue(terrs.contains(TerritoryEnum.SIBERIA));
    assertTrue(terrs.contains(TerritoryEnum.YAKUTSK));

  }

  /**
   * Tests that getBonusValue returns 11.
   */
  @Test
  public void testGetBonusValue() {
    Asia cont = new Asia();
    assertTrue(cont.getBonusValue() == BONUS);
  }

  /**
   * Tests getContinentId returns ASIA.
   */
  @Test
  public void testGetContinentId() {
    Asia cont = new Asia();
    assertTrue(cont.getContinentId() == ContinentEnum.ASIA);
  }

  /**
   * Test containsTerritory returns true only for Territories that are within
   * North America and false otherwise.
   */
  @Test
  public void testContainsTerritory() {
    Asia cont = new Asia();
    Set<TerritoryEnum> territories = new HashSet<>();
    territories.addAll(Arrays.asList(TerritoryEnum.MIDDLE_EAST,
        TerritoryEnum.AFGHANISTAN, TerritoryEnum.CHINA, TerritoryEnum.INDIA,
        TerritoryEnum.IRKUTSK, TerritoryEnum.JAPAN, TerritoryEnum.KAMACHATKA,
        TerritoryEnum.MONGOLIA, TerritoryEnum.SOUTHEAST_ASIA,
        TerritoryEnum.SIBERIA, TerritoryEnum.URAL, TerritoryEnum.YAKUTSK));
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
    Asia cont = new Asia();
    cont.containsTerritory(null);
  }
}
