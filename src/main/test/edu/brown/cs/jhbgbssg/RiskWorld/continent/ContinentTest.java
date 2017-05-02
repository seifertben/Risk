package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ContinentTest {

  @Test(expected = IllegalArgumentException.class)
  public void testGetTerritoriesNullCont() {
    ContinentEnum.getTerritories(null);
  }

  @Test
  public void testNorthAmericaGetTerritories() {
    Set<TerritoryEnum> terrs =
        ContinentEnum.getTerritories(ContinentEnum.NORTH_AMERICA);
    assertTrue(terrs.size() == 9);
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

  @Test
  public void testSouthAmericaGetTerritories() {
    Set<TerritoryEnum> terrs =
        ContinentEnum.getTerritories(ContinentEnum.SOUTH_AMERICA);
    assertTrue(terrs.size() == 4);
    assertTrue(terrs.contains(TerritoryEnum.VENEZUELA));
    assertTrue(terrs.contains(TerritoryEnum.PERU));
    assertTrue(terrs.contains(TerritoryEnum.BRAZIL));
    assertTrue(terrs.contains(TerritoryEnum.ARGENTINA));
  }

  @Test
  public void testAfricaGetTerritories() {
    Set<TerritoryEnum> terrs =
        ContinentEnum.getTerritories(ContinentEnum.AFRICA);
    assertTrue(terrs.size() == 6);
    assertTrue(terrs.contains(TerritoryEnum.EGYPT));
    assertTrue(terrs.contains(TerritoryEnum.EAST_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.CENTRAL_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.SOUTH_AFRICA));
    assertTrue(terrs.contains(TerritoryEnum.MADAGASCAR));
    assertTrue(terrs.contains(TerritoryEnum.NORTH_AFRICA));
  }

  @Test
  public void testEuropeGetTerritories() {
    Set<TerritoryEnum> terrs =
        ContinentEnum.getTerritories(ContinentEnum.EUROPE);
    assertTrue(terrs.size() == 7);
    assertTrue(terrs.contains(TerritoryEnum.ICELAND));
    assertTrue(terrs.contains(TerritoryEnum.GREAT_BRITIAN));
    assertTrue(terrs.contains(TerritoryEnum.SCANDINAVIA));
    assertTrue(terrs.contains(TerritoryEnum.WESTERN_EUROPE));
    assertTrue(terrs.contains(TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(terrs.contains(TerritoryEnum.RUSSIA));
    assertTrue(terrs.contains(TerritoryEnum.NORTHERN_EUROPE));
  }

  @Test
  public void testAsiaGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.getTerritories(ContinentEnum.ASIA);
    assertTrue(terrs.size() == 12);
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

  @Test
  public void testAustraliaGetTerritories() {
    Set<TerritoryEnum> terrs =
        ContinentEnum.getTerritories(ContinentEnum.AUSTRALIA);
    assertTrue(terrs.size() == 4);
    assertTrue(terrs.contains(TerritoryEnum.INDONESIA));
    assertTrue(terrs.contains(TerritoryEnum.NEW_GUINEA));
    assertTrue(terrs.contains(TerritoryEnum.WESTERN_AUSTRALIA));
    assertTrue(terrs.contains(TerritoryEnum.EASTERN_AUSTRALIA));
  }

  @Test
  public void testContinentalBonus() {
    assertTrue(
        ContinentEnum.getContinentalBonus(ContinentEnum.NORTH_AMERICA) == 5);
    assertTrue(
        ContinentEnum.getContinentalBonus(ContinentEnum.SOUTH_AMERICA) == 2);
    assertTrue(ContinentEnum.getContinentalBonus(ContinentEnum.AFRICA) == 3);
    assertTrue(ContinentEnum.getContinentalBonus(ContinentEnum.EUROPE) == 5);
    assertTrue(ContinentEnum.getContinentalBonus(ContinentEnum.ASIA) == 11);
    assertTrue(ContinentEnum.getContinentalBonus(ContinentEnum.AUSTRALIA) == 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetContinentalBonusNullCont() {
    ContinentEnum.getContinentalBonus(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetContinentNullTerritory() {
    ContinentEnum.getContinent(null);
  }

  @Test
  public void testGetContinentNorthAmerica() {
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.ALASKA) == ContinentEnum.NORTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.ALBERTA) == ContinentEnum.NORTH_AMERICA);
    assertTrue(ContinentEnum.getContinent(
        TerritoryEnum.NORTHWEST_TERRITORY) == ContinentEnum.NORTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.ONTARIO) == ContinentEnum.NORTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.QUEBEC) == ContinentEnum.NORTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.WESTERN_US) == ContinentEnum.NORTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.EASTERN_US) == ContinentEnum.NORTH_AMERICA);
    assertTrue(ContinentEnum.getContinent(
        TerritoryEnum.CENTRAL_AMERICA) == ContinentEnum.NORTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.GREENLAND) == ContinentEnum.NORTH_AMERICA);
  }

  @Test
  public void testGetContinentSouthAmerica() {
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.VENEZUELA) == ContinentEnum.SOUTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.PERU) == ContinentEnum.SOUTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.BRAZIL) == ContinentEnum.SOUTH_AMERICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.ARGENTINA) == ContinentEnum.SOUTH_AMERICA);
  }

  @Test
  public void testGetContinentAfrica() {
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.EGYPT) == ContinentEnum.AFRICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.EAST_AFRICA) == ContinentEnum.AFRICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.CENTRAL_AFRICA) == ContinentEnum.AFRICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.SOUTH_AFRICA) == ContinentEnum.AFRICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.MADAGASCAR) == ContinentEnum.AFRICA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.NORTH_AFRICA) == ContinentEnum.AFRICA);
  }

  @Test
  public void testGetContinentEurope() {
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.ICELAND) == ContinentEnum.EUROPE);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.GREAT_BRITIAN) == ContinentEnum.EUROPE);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.SCANDINAVIA) == ContinentEnum.EUROPE);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.ICELAND) == ContinentEnum.EUROPE);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.WESTERN_EUROPE) == ContinentEnum.EUROPE);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.SOUTHERN_EUROPE) == ContinentEnum.EUROPE);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.RUSSIA) == ContinentEnum.EUROPE);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.NORTHERN_EUROPE) == ContinentEnum.EUROPE);
  }

  @Test
  public void testGetContinentAsia() {
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.MIDDLE_EAST) == ContinentEnum.ASIA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.AFGHANISTAN) == ContinentEnum.ASIA);
    assertTrue(
        ContinentEnum.getContinent(TerritoryEnum.INDIA) == ContinentEnum.ASIA);
    assertTrue(
        ContinentEnum.getContinent(TerritoryEnum.CHINA) == ContinentEnum.ASIA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.MONGOLIA) == ContinentEnum.ASIA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.IRKUTSK) == ContinentEnum.ASIA);
    assertTrue(
        ContinentEnum.getContinent(TerritoryEnum.JAPAN) == ContinentEnum.ASIA);
    assertTrue(
        ContinentEnum.getContinent(TerritoryEnum.URAL) == ContinentEnum.ASIA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.KAMACHATKA) == ContinentEnum.ASIA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.SOUTHEAST_ASIA) == ContinentEnum.ASIA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.SIBERIA) == ContinentEnum.ASIA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.YAKUTSK) == ContinentEnum.ASIA);
  }

  @Test
  public void testGetContinentAustralia() {
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.INDONESIA) == ContinentEnum.AUSTRALIA);
    assertTrue(ContinentEnum
        .getContinent(TerritoryEnum.NEW_GUINEA) == ContinentEnum.AUSTRALIA);
    assertTrue(ContinentEnum.getContinent(
        TerritoryEnum.WESTERN_AUSTRALIA) == ContinentEnum.AUSTRALIA);
    assertTrue(ContinentEnum.getContinent(
        TerritoryEnum.EASTERN_AUSTRALIA) == ContinentEnum.AUSTRALIA);
  }
}
