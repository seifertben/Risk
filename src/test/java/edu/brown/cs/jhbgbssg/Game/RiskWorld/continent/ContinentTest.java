package edu.brown.cs.jhbgbssg.Game.RiskWorld.continent;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.continent.ContinentEnum;

public class ContinentTest {

  @Test
  public void testNorthAmericaGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.NORTH_AMERICA.getTerrs();
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
    Set<TerritoryEnum> terrs = ContinentEnum.SOUTH_AMERICA.getTerrs();
    assertTrue(terrs.size() == 4);
    assertTrue(terrs.contains(TerritoryEnum.VENEZUELA));
    assertTrue(terrs.contains(TerritoryEnum.PERU));
    assertTrue(terrs.contains(TerritoryEnum.BRAZIL));
    assertTrue(terrs.contains(TerritoryEnum.ARGENTINA));
  }

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

  @Test
  public void testEuropeGetTerritories() {
    Set<TerritoryEnum> terrs = ContinentEnum.EUROPE.getTerrs();
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
    Set<TerritoryEnum> terrs = ContinentEnum.ASIA.getTerrs();
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
    Set<TerritoryEnum> terrs = ContinentEnum.AUSTRALIA.getTerrs();
    assertTrue(terrs.size() == 4);
    assertTrue(terrs.contains(TerritoryEnum.INDONESIA));
    assertTrue(terrs.contains(TerritoryEnum.NEW_GUINEA));
    assertTrue(terrs.contains(TerritoryEnum.WESTERN_AUSTRALIA));
    assertTrue(terrs.contains(TerritoryEnum.EASTERN_AUSTRALIA));
  }

  @Test
  public void testContinentalBonus() {
    assertTrue(ContinentEnum.NORTH_AMERICA.getContinentalBonus() == 5);
    assertTrue(ContinentEnum.SOUTH_AMERICA.getContinentalBonus() == 2);
    assertTrue(ContinentEnum.AFRICA.getContinentalBonus() == 3);
    assertTrue(ContinentEnum.EUROPE.getContinentalBonus() == 5);
    assertTrue(ContinentEnum.ASIA.getContinentalBonus() == 11);
    assertTrue(ContinentEnum.AUSTRALIA.getContinentalBonus() == 2);
  }

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
