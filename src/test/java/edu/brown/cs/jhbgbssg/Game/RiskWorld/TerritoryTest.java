package edu.brown.cs.jhbgbssg.Game.RiskWorld;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.riskworld.ContinentEnum;
import edu.brown.cs.jhbgbssg.Game.riskworld.Territory;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * JUnit tests for Territory.
 *
 * @author sarahgilmore
 *
 */
public class TerritoryTest {
  private static final int NEGATIVE_TROOPS = -3;

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void territoryConstructorTest() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    assertNotNull(territory);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if territory id is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void territoryConstructorNullTerr() {
    new Territory(null);
  }

  /**
   * Tests that the initial number of troops is zero.
   */
  @Test
  public void testInitialUnitSize() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    assertTrue(territory.getNumberTroops() == 0);
  }

  /**
   * Tests that the owner of the territory is null initially.
   */
  @Test
  public void testInitialPlayer() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    assertNull(territory.getOwner());
  }

  /**
   * Tests addTroops adds the specified number of troops to the territory.
   */
  @Test
  public void testAddTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, 1);
    assertTrue(territory.getNumberTroops() == 1);
    territory.addTroops(3);
    assertTrue(territory.getNumberTroops() == 4);
  }

  /**
   * Tests removeTroops removes the specified number of troops to the territory
   * and returns true if all the troops have been removed.
   */
  @Test
  public void testRemoveTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, 3);
    assertTrue(territory.getNumberTroops() == 3);
    assertFalse(territory.removeTroops(2));
    assertTrue(territory.getNumberTroops() == 1);
  }

  /**
   * Tests that once all the troops have been removed, the territory's owner is
   * null.
   */
  @Test
  public void testRemoveAllTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, 3);
    assertTrue(territory.getNumberTroops() == 3);
    assertFalse(territory.removeTroops(2));
    assertTrue(territory.getNumberTroops() == 1);
    assertTrue(territory.removeTroops(1));
    assertNull(territory.getOwner());
  }

  /**
   * Tests that removing more troops than there are on the territory causes the
   * number of troops to drop only down to 0 and the owner field of the
   * territory is null.
   */
  @Test
  public void testRemoveMoreTroopsThanInTerritory() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, 1);
    assertTrue(territory.getNumberTroops() == 1);
    assertTrue(territory.removeTroops(3));
    assertTrue(territory.getNumberTroops() == 0);
    assertNull(territory.getOwner());
  }

  /**
   * Tests that addTroops returns false and does not add any troops if the
   * territory has no owner.
   */
  @Test
  public void testAdddTroopsNoPlayer() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    assertFalse(territory.addTroops(3));
    assertTrue(territory.getNumberTroops() == 0);
  }

  /**
   * Tests that changePlayer throws an IllegalArgumentException if the number of
   * troops to add is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangePlayerNegativeTroops() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    territory.changePlayer(player, NEGATIVE_TROOPS);
  }

  /**
   * Tests that changePlayer throws an IllegalArgumentException if the player
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangePlayerNullId() {
    Territory territory = new Territory(TerritoryEnum.ALASKA);
    territory.changePlayer(null, 3);
  }

  /**
   * Tests for Territory's in North America, the Continent is set to
   * NORTH_AMERICA.
   */
  @Test
  public void testNorthAmericaTerritoriesContinent() {
    Territory alaska = new Territory(TerritoryEnum.ALASKA);
    assertTrue(alaska.getContinent() == ContinentEnum.NORTH_AMERICA);
    Territory alberta = new Territory(TerritoryEnum.ALBERTA);
    assertTrue(alberta.getContinent() == ContinentEnum.NORTH_AMERICA);
    Territory nw = new Territory(TerritoryEnum.NORTHWEST_TERRITORY);
    assertTrue(nw.getContinent() == ContinentEnum.NORTH_AMERICA);
    Territory ontario = new Territory(TerritoryEnum.ONTARIO);
    assertTrue(ontario.getContinent().equals(ContinentEnum.NORTH_AMERICA));
    Territory quebec = new Territory(TerritoryEnum.QUEBEC);
    assertTrue(quebec.getContinent() == ContinentEnum.NORTH_AMERICA);
    Territory greenland = new Territory(TerritoryEnum.GREENLAND);
    assertTrue(greenland.getContinent() == ContinentEnum.NORTH_AMERICA);
    Territory westUS = new Territory(TerritoryEnum.WESTERN_US);
    assertTrue(westUS.getContinent() == ContinentEnum.NORTH_AMERICA);
    Territory eastUS = new Territory(TerritoryEnum.EASTERN_US);
    assertTrue(eastUS.getContinent() == ContinentEnum.NORTH_AMERICA);
    Territory centralAmerica = new Territory(TerritoryEnum.CENTRAL_AMERICA);
    assertTrue(centralAmerica.getContinent() == ContinentEnum.NORTH_AMERICA);
  }

  /**
   * Tests for Territory's in South America, the Continent is set to
   * SOUTH_AMERICA.
   */
  @Test
  public void testSouthAmericaTerritoriesContinent() {
    Territory venezuela = new Territory(TerritoryEnum.VENEZUELA);
    assertTrue(venezuela.getContinent() == ContinentEnum.SOUTH_AMERICA);
    Territory peru = new Territory(TerritoryEnum.PERU);
    assertTrue(peru.getContinent() == ContinentEnum.SOUTH_AMERICA);
    Territory brazil = new Territory(TerritoryEnum.BRAZIL);
    assertTrue(brazil.getContinent() == ContinentEnum.SOUTH_AMERICA);
    Territory argentina = new Territory(TerritoryEnum.ARGENTINA);
    assertTrue(argentina.getContinent() == ContinentEnum.SOUTH_AMERICA);
  }

  /**
   * Tests for Territory's in Africa, the Continent is set to Africa.
   */
  @Test
  public void testAfricaTerritoriesContinent() {
    Territory nAfrica = new Territory(TerritoryEnum.NORTH_AFRICA);
    assertTrue(nAfrica.getContinent() == ContinentEnum.AFRICA);
    Territory egypt = new Territory(TerritoryEnum.EGYPT);
    assertTrue(egypt.getContinent() == ContinentEnum.AFRICA);
    Territory eastAfrica = new Territory(TerritoryEnum.EAST_AFRICA);
    assertTrue(eastAfrica.getContinent() == ContinentEnum.AFRICA);
    Territory centralAfrica = new Territory(TerritoryEnum.CENTRAL_AFRICA);
    assertTrue(centralAfrica.getContinent() == ContinentEnum.AFRICA);
    Territory southAfrica = new Territory(TerritoryEnum.CENTRAL_AFRICA);
    assertTrue(southAfrica.getContinent() == ContinentEnum.AFRICA);
    Territory madagascar = new Territory(TerritoryEnum.MADAGASCAR);
    assertTrue(madagascar.getContinent() == ContinentEnum.AFRICA);
  }

  /**
   * Tests for Territory's in Europe, the Continent is set to EUROPE.
   */
  @Test
  public void testEuropeTerritoriesContinent() {
    Territory iceland = new Territory(TerritoryEnum.ICELAND);
    assertTrue(iceland.getContinent() == ContinentEnum.EUROPE);
    Territory greatBritian = new Territory(TerritoryEnum.GREAT_BRITIAN);
    assertTrue(greatBritian.getContinent() == ContinentEnum.EUROPE);
    Territory scandinavia = new Territory(TerritoryEnum.SCANDINAVIA);
    assertTrue(scandinavia.getContinent() == ContinentEnum.EUROPE);
    Territory wEurope = new Territory(TerritoryEnum.WESTERN_EUROPE);
    assertTrue(wEurope.getContinent() == ContinentEnum.EUROPE);
    Territory nEurope = new Territory(TerritoryEnum.NORTHERN_EUROPE);
    assertTrue(nEurope.getContinent() == ContinentEnum.EUROPE);
    Territory sEurope = new Territory(TerritoryEnum.SOUTHERN_EUROPE);
    assertTrue(sEurope.getContinent() == ContinentEnum.EUROPE);
    Territory russia = new Territory(TerritoryEnum.RUSSIA);
    assertTrue(russia.getContinent() == ContinentEnum.EUROPE);
  }

  /**
   * Tests for Territory's in Asia, the Continent is set to ASIA.
   */
  @Test
  public void testAsiaTerritoriesContinent() {
    Territory middleEast = new Territory(TerritoryEnum.MIDDLE_EAST);
    assertTrue(middleEast.getContinent() == ContinentEnum.ASIA);
    Territory afganistan = new Territory(TerritoryEnum.AFGHANISTAN);
    assertTrue(afganistan.getContinent() == ContinentEnum.ASIA);
    Territory ural = new Territory(TerritoryEnum.URAL);
    assertTrue(ural.getContinent() == ContinentEnum.ASIA);
    Territory siberia = new Territory(TerritoryEnum.SIBERIA);
    assertTrue(siberia.getContinent() == ContinentEnum.ASIA);
    Territory china = new Territory(TerritoryEnum.CHINA);
    assertTrue(china.getContinent() == ContinentEnum.ASIA);
    Territory india = new Territory(TerritoryEnum.INDIA);
    assertTrue(india.getContinent() == ContinentEnum.ASIA);
    Territory mongolia = new Territory(TerritoryEnum.MONGOLIA);
    assertTrue(mongolia.getContinent() == ContinentEnum.ASIA);
    Territory irkutsk = new Territory(TerritoryEnum.IRKUTSK);
    assertTrue(irkutsk.getContinent() == ContinentEnum.ASIA);
    Territory yakutsk = new Territory(TerritoryEnum.YAKUTSK);
    assertTrue(yakutsk.getContinent() == ContinentEnum.ASIA);
    Territory kamchatka = new Territory(TerritoryEnum.KAMACHATKA);
    assertTrue(kamchatka.getContinent() == ContinentEnum.ASIA);
    Territory japan = new Territory(TerritoryEnum.JAPAN);
    assertTrue(japan.getContinent() == ContinentEnum.ASIA);
    Territory southeastAsia = new Territory(TerritoryEnum.SOUTHEAST_ASIA);
    assertTrue(southeastAsia.getContinent() == ContinentEnum.ASIA);
  }

  /**
   * Tests for Territory's in Asia, the Continent is set to ASIA.
   */
  @Test
  public void testAustraliaTerritoriesContinent() {
    Territory indonesia = new Territory(TerritoryEnum.INDONESIA);
    assertTrue(indonesia.getContinent() == ContinentEnum.AUSTRALIA);
    Territory newGuinea = new Territory(TerritoryEnum.NEW_GUINEA);
    assertTrue(newGuinea.getContinent() == ContinentEnum.AUSTRALIA);
    Territory eAustralia = new Territory(TerritoryEnum.EASTERN_AUSTRALIA);
    assertTrue(eAustralia.getContinent() == ContinentEnum.AUSTRALIA);
    Territory wAustralia = new Territory(TerritoryEnum.WESTERN_AUSTRALIA);
    assertTrue(wAustralia.getContinent() == ContinentEnum.AUSTRALIA);
  }

}
