package edu.brown.cs.jhbgbssg.Game.RiskWorld;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.riskworld.ContinentEnum;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * JUnit tests for TerritoryEnum.
 *
 * @author sarahgilmore
 *
 */
public class TerritoryEnumTest {

  /**
   * Tests getContinent for Territories within North America return
   * NORTH_AMERICA.
   */
  @Test
  public void testGetContinentNorthAmerica() {
    assertTrue(
        TerritoryEnum.ALASKA.getContinent() == ContinentEnum.NORTH_AMERICA);
    assertTrue(
        TerritoryEnum.ALBERTA.getContinent() == ContinentEnum.NORTH_AMERICA);
    assertTrue(TerritoryEnum.NORTHWEST_TERRITORY
        .getContinent() == ContinentEnum.NORTH_AMERICA);
    assertTrue(
        TerritoryEnum.ONTARIO.getContinent() == ContinentEnum.NORTH_AMERICA);
    assertTrue(
        TerritoryEnum.QUEBEC.getContinent() == ContinentEnum.NORTH_AMERICA);
    assertTrue(
        TerritoryEnum.WESTERN_US.getContinent() == ContinentEnum.NORTH_AMERICA);
    assertTrue(
        TerritoryEnum.EASTERN_US.getContinent() == ContinentEnum.NORTH_AMERICA);
    assertTrue(TerritoryEnum.CENTRAL_AMERICA
        .getContinent() == ContinentEnum.NORTH_AMERICA);
    assertTrue(
        TerritoryEnum.GREENLAND.getContinent() == ContinentEnum.NORTH_AMERICA);
  }

  /**
   * Tests getContinent for Territories within South America return
   * SOUTH_AMERICA.
   */
  @Test
  public void testGetContinentSouthAmerica() {
    assertTrue(
        TerritoryEnum.VENEZUELA.getContinent() == ContinentEnum.SOUTH_AMERICA);
    assertTrue(
        TerritoryEnum.PERU.getContinent() == ContinentEnum.SOUTH_AMERICA);
    assertTrue(
        TerritoryEnum.BRAZIL.getContinent() == ContinentEnum.SOUTH_AMERICA);
    assertTrue(
        TerritoryEnum.ARGENTINA.getContinent() == ContinentEnum.SOUTH_AMERICA);
  }

  /**
   * Tests getContinent for Territories within Africa return AFRICA.
   */
  @Test
  public void testGetContinentAfrica() {
    assertTrue(TerritoryEnum.EGYPT.getContinent() == ContinentEnum.AFRICA);
    assertTrue(
        TerritoryEnum.EAST_AFRICA.getContinent() == ContinentEnum.AFRICA);
    assertTrue(
        TerritoryEnum.CENTRAL_AFRICA.getContinent() == ContinentEnum.AFRICA);
    assertTrue(
        TerritoryEnum.SOUTH_AFRICA.getContinent() == ContinentEnum.AFRICA);
    assertTrue(TerritoryEnum.MADAGASCAR.getContinent() == ContinentEnum.AFRICA);
    assertTrue(
        TerritoryEnum.NORTH_AFRICA.getContinent() == ContinentEnum.AFRICA);
  }

  /**
   * Tests getContinent for Territories within Europe return EUROPE.
   */
  @Test
  public void testGetContinentEurope() {
    assertTrue(TerritoryEnum.ICELAND.getContinent() == ContinentEnum.EUROPE);
    assertTrue(
        TerritoryEnum.GREAT_BRITIAN.getContinent() == ContinentEnum.EUROPE);
    assertTrue(
        TerritoryEnum.SCANDINAVIA.getContinent() == ContinentEnum.EUROPE);
    assertTrue(TerritoryEnum.ICELAND.getContinent() == ContinentEnum.EUROPE);
    assertTrue(
        TerritoryEnum.WESTERN_EUROPE.getContinent() == ContinentEnum.EUROPE);
    assertTrue(
        TerritoryEnum.SOUTHERN_EUROPE.getContinent() == ContinentEnum.EUROPE);
    assertTrue(TerritoryEnum.RUSSIA.getContinent() == ContinentEnum.EUROPE);
    assertTrue(
        TerritoryEnum.NORTHERN_EUROPE.getContinent() == ContinentEnum.EUROPE);
  }

  /**
   * Tests getContinent for Territories within Asia return ASIA.
   */
  @Test
  public void testGetContinentAsia() {
    assertTrue(TerritoryEnum.MIDDLE_EAST.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.AFGHANISTAN.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.INDIA.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.CHINA.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.MONGOLIA.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.IRKUTSK.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.JAPAN.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.URAL.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.KAMACHATKA.getContinent() == ContinentEnum.ASIA);
    assertTrue(
        TerritoryEnum.SOUTHEAST_ASIA.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.SIBERIA.getContinent() == ContinentEnum.ASIA);
    assertTrue(TerritoryEnum.YAKUTSK.getContinent() == ContinentEnum.ASIA);
  }

  /**
   * Tests getContinent for Territories within Australia return AUSTRALIA.
   */
  @Test
  public void testGetContinentAustralia() {
    assertTrue(
        TerritoryEnum.INDONESIA.getContinent() == ContinentEnum.AUSTRALIA);
    assertTrue(
        TerritoryEnum.NEW_GUINEA.getContinent() == ContinentEnum.AUSTRALIA);
    assertTrue(TerritoryEnum.WESTERN_AUSTRALIA
        .getContinent() == ContinentEnum.AUSTRALIA);
    assertTrue(TerritoryEnum.EASTERN_AUSTRALIA
        .getContinent() == ContinentEnum.AUSTRALIA);
  }

}
