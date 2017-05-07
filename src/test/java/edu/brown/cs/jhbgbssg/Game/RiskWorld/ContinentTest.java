package edu.brown.cs.jhbgbssg.Game.RiskWorld;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.riskworld.ContinentEnum;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * JUnit tests for ContinentEnum.
 *
 * @author sarahgilmore
 *
 */
public class ContinentTest {
  private static final int NAMERICA_TERRS = 9;
  private static final int ASIA_TERRS = 12;
  private static final int EUROPE_TERRS = 7;
  private static final int ASIA_BONUS = 11;

  /**
   * Tests getTerritories returns the correct TerritoryEnum for NORTH_AMERICA.
   */
  @Test
  public void testNorthAmericaGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.NORTH_AMERICA.getTerrs();
    assertTrue(terrs.size() == NAMERICA_TERRS);
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
   * Tests getTerritories returns the correct TerritoryEnum for SOUTH_AMERICA.
   */
  @Test
  public void testSouthAmericaGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.SOUTH_AMERICA.getTerrs();
    assertTrue(terrs.size() == 4);
    assertTrue(terrs.contains(TerritoryEnum.VENEZUELA));
    assertTrue(terrs.contains(TerritoryEnum.PERU));
    assertTrue(terrs.contains(TerritoryEnum.BRAZIL));
    assertTrue(terrs.contains(TerritoryEnum.ARGENTINA));
  }

  /**
   * Tests getTerritories returns the correct TerritoryEnum for AFRICA.
   */
  @Test
  public void testAfricaGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.AFRICA.getTerrs();
    assertTrue(terrs.size() == 6);
    assertTrue(terrs.contains(TerritoryEnum.EGYPT));
    assertTrue(terrs.contains(TerritoryEnum.EAST_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.CENTRAL_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.SOUTH_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.MADAGASCAR));
    assertTrue(terrs.contains(TerritoryEnum.NORTH_AFRICA));
  }

  /**
   * Tests getTerritories returns the correct TerritoryEnum for EURPOPE.
   */
  @Test
  public void testEuropeGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.EUROPE.getTerrs();
    assertTrue(terrs.size() == EUROPE_TERRS);
    assertTrue(terrs.contains(TerritoryEnum.ICELAND));
    assertTrue(terrs.contains(TerritoryEnum.GREAT_BRITIAN));
    assertTrue(terrs.contains(TerritoryEnum.SCANDINAVIA));
    assertTrue(terrs.contains(TerritoryEnum.WESTERN_EUROPE));
    assertTrue(terrs.contains(TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(terrs.contains(TerritoryEnum.RUSSIA));
    assertTrue(terrs.contains(TerritoryEnum.NORTHERN_EUROPE));
  }

  /**
   * Tests getTerritories returns the correct TerritoryEnum for ASIA.
   */
  @Test
  public void testAsiaGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.ASIA.getTerrs();
    assertTrue(terrs.size() == ASIA_TERRS);
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
   * Tests getTerritories returns the correct TerritoryEnum for AUSTRALIA.
   */
  @Test
  public void testAustraliaGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.AUSTRALIA.getTerrs();
    assertTrue(terrs.size() == 4);
    assertTrue(terrs.contains(TerritoryEnum.INDONESIA));
    assertTrue(terrs.contains(TerritoryEnum.NEW_GUINEA));
    assertTrue(terrs.contains(TerritoryEnum.WESTERN_AUSTRALIA));
    assertTrue(terrs.contains(TerritoryEnum.EASTERN_AUSTRALIA));
  }

  /**
   * Tests that for every continent, the getContinentalBonus returns the correct
   * value.
   */
  @Test
  public void testContinentalBonus() {
    assertTrue(ContinentEnum.NORTH_AMERICA.getContinentalBonus() == 5);
    assertTrue(ContinentEnum.SOUTH_AMERICA.getContinentalBonus() == 2);
    assertTrue(ContinentEnum.AFRICA.getContinentalBonus() == 3);
    assertTrue(ContinentEnum.EUROPE.getContinentalBonus() == 5);
    assertTrue(ContinentEnum.ASIA.getContinentalBonus() == ASIA_BONUS);
    assertTrue(ContinentEnum.AUSTRALIA.getContinentalBonus() == 2);
  }
}
