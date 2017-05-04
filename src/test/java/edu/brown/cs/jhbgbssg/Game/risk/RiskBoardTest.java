package edu.brown.cs.jhbgbssg.Game.risk;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for RiskBoard.
 *
 * @author sarahgilmore
 *
 */
public class RiskBoardTest {
  private static final int NUMBER_TERRITORIES = 42;

  /**
   * Tests constructor returns a non-null object.
   */
  @Test
  public void testBoardConstructor() {
    RiskBoard board = new RiskBoard();
    assertNotNull(board);
  }

  /**
   * Tests neighbors for Alaska.
   */
  @Test
  public void testAlaskaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ALASKA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(board.isNeighbor(TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ALASKA, TerritoryEnum.KAMACHATKA));
  }

  /**
   * Tests neighbors for Northwest Territory.
   */
  @Test
  public void testNorthwestTerritoryNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHWEST_TERRITORY,
        TerritoryEnum.ALASKA));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHWEST_TERRITORY,
        TerritoryEnum.ALBERTA));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHWEST_TERRITORY,
        TerritoryEnum.ONTARIO));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHWEST_TERRITORY,
        TerritoryEnum.GREENLAND));
  }

  /**
   * Tests neighbors for Alberta Territory.
   */
  @Test
  public void testAlbertaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ALBERTA, TerritoryEnum.ALASKA));
    assertTrue(board.isNeighbor(TerritoryEnum.ALBERTA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(board.isNeighbor(TerritoryEnum.ALBERTA, TerritoryEnum.ONTARIO));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ALBERTA, TerritoryEnum.WESTERN_US));
  }

  /**
   * Tests neighbors for Ontario.
   */
  @Test
  public void testOntarioNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ONTARIO,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(board.isNeighbor(TerritoryEnum.ONTARIO, TerritoryEnum.QUEBEC));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ONTARIO, TerritoryEnum.WESTERN_US));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ONTARIO, TerritoryEnum.EASTERN_US));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ONTARIO, TerritoryEnum.GREENLAND));
  }

  /**
   * Tests neighbors for quebec.
   */
  @Test
  public void testQuebecNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.QUEBEC, TerritoryEnum.ONTARIO));
    assertTrue(board.isNeighbor(TerritoryEnum.QUEBEC, TerritoryEnum.GREENLAND));
    assertTrue(
        board.isNeighbor(TerritoryEnum.QUEBEC, TerritoryEnum.EASTERN_US));
  }

  /**
   * Tests neighbors for Eastern United States.
   */
  @Test
  public void testEasternUSNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.EASTERN_US, TerritoryEnum.ONTARIO));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EASTERN_US, TerritoryEnum.QUEBEC));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EASTERN_US, TerritoryEnum.WESTERN_US));
    assertTrue(board.isNeighbor(TerritoryEnum.EASTERN_US,
        TerritoryEnum.CENTRAL_AMERICA));
  }

  /**
   * Tests neighbors for Western United States.
   */
  @Test
  public void testWesternUSNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.WESTERN_US, TerritoryEnum.ONTARIO));
    assertTrue(
        board.isNeighbor(TerritoryEnum.WESTERN_US, TerritoryEnum.ALBERTA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.WESTERN_US, TerritoryEnum.EASTERN_US));
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_US,
        TerritoryEnum.CENTRAL_AMERICA));
  }

  /**
   * Tests neighbors for Greenland.
   */
  @Test
  public void testGreenlandNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND));
    assertTrue(board.isNeighbor(TerritoryEnum.GREENLAND, TerritoryEnum.QUEBEC));
    assertTrue(
        board.isNeighbor(TerritoryEnum.GREENLAND, TerritoryEnum.ONTARIO));
    assertTrue(board.isNeighbor(TerritoryEnum.GREENLAND,
        TerritoryEnum.NORTHWEST_TERRITORY));
  }

  /**
   * Tests neighbors for Central America.
   */
  @Test
  public void testCentralAmericaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AMERICA,
        TerritoryEnum.EASTERN_US));
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AMERICA,
        TerritoryEnum.WESTERN_US));
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AMERICA,
        TerritoryEnum.VENEZUELA));
  }

  /**
   * Tests neighbors for Venezuela.
   */
  @Test
  public void testVenezuelaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.VENEZUELA,
        TerritoryEnum.CENTRAL_AMERICA));
    assertTrue(board.isNeighbor(TerritoryEnum.VENEZUELA, TerritoryEnum.PERU));
    assertTrue(board.isNeighbor(TerritoryEnum.VENEZUELA, TerritoryEnum.BRAZIL));
  }

  /**
   * Tests neighbors for Peru.
   */
  @Test
  public void testPeruNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.PERU, TerritoryEnum.VENEZUELA));
    assertTrue(board.isNeighbor(TerritoryEnum.PERU, TerritoryEnum.BRAZIL));
    assertTrue(board.isNeighbor(TerritoryEnum.PERU, TerritoryEnum.ARGENTINA));
  }

  /**
   * Tests neighbors for Argentina.
   */
  @Test
  public void testArgentinaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.ARGENTINA, TerritoryEnum.PERU));
    assertTrue(board.isNeighbor(TerritoryEnum.ARGENTINA, TerritoryEnum.BRAZIL));
  }

  /**
   * Tests neighbors for Brazil.
   */
  @Test
  public void testBrazilNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.BRAZIL, TerritoryEnum.VENEZUELA));
    assertTrue(board.isNeighbor(TerritoryEnum.BRAZIL, TerritoryEnum.PERU));
    assertTrue(board.isNeighbor(TerritoryEnum.BRAZIL, TerritoryEnum.ARGENTINA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.BRAZIL, TerritoryEnum.NORTH_AFRICA));
  }

  /**
   * Tests neighbors for North Africa.
   */
  @Test
  public void testNorthAfricaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.NORTH_AFRICA, TerritoryEnum.EGYPT));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTH_AFRICA,
        TerritoryEnum.CENTRAL_AFRICA));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTH_AFRICA,
        TerritoryEnum.EAST_AFRICA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.NORTH_AFRICA, TerritoryEnum.BRAZIL));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTH_AFRICA,
        TerritoryEnum.WESTERN_EUROPE));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTH_AFRICA,
        TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(
        board.isNeighbor(TerritoryEnum.NORTH_AFRICA, TerritoryEnum.BRAZIL));
  }

  /**
   * Tests neighbors for Egypt.
   */
  @Test
  public void testEgyptNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.EGYPT, TerritoryEnum.NORTH_AFRICA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EGYPT, TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EGYPT, TerritoryEnum.MIDDLE_EAST));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EGYPT, TerritoryEnum.EAST_AFRICA));
  }

  /**
   * Tests neighbors for East Africa.
   */
  @Test
  public void testEastAfricaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.EAST_AFRICA, TerritoryEnum.EGYPT));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EAST_AFRICA, TerritoryEnum.MIDDLE_EAST));
    assertTrue(board.isNeighbor(TerritoryEnum.EAST_AFRICA,
        TerritoryEnum.NORTH_AFRICA));
    assertTrue(board.isNeighbor(TerritoryEnum.EAST_AFRICA,
        TerritoryEnum.CENTRAL_AFRICA));
    assertTrue(board.isNeighbor(TerritoryEnum.EAST_AFRICA,
        TerritoryEnum.SOUTH_AFRICA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.EAST_AFRICA, TerritoryEnum.MADAGASCAR));
  }

  /**
   * Tests neighbors for Central Africa.
   */
  @Test
  public void testCentalAfricaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AFRICA,
        TerritoryEnum.EAST_AFRICA));
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AFRICA,
        TerritoryEnum.NORTH_AFRICA));
    assertTrue(board.isNeighbor(TerritoryEnum.CENTRAL_AFRICA,
        TerritoryEnum.SOUTH_AFRICA));
  }

  /**
   * Tests neighbors for South Africa.
   */
  @Test
  public void testSouthAfricaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.SOUTH_AFRICA,
        TerritoryEnum.EAST_AFRICA));
    assertTrue(board.isNeighbor(TerritoryEnum.SOUTH_AFRICA,
        TerritoryEnum.CENTRAL_AFRICA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.SOUTH_AFRICA, TerritoryEnum.MADAGASCAR));
  }

  /**
   * Tests neighbors for Madagascar.
   */
  @Test
  public void testMadagascarNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.MADAGASCAR, TerritoryEnum.EAST_AFRICA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.MADAGASCAR, TerritoryEnum.SOUTH_AFRICA));
  }

  /**
   * Tests neighbors for Iceland.
   */
  public void testIcelandNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.ICELAND, TerritoryEnum.GREENLAND));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ICELAND, TerritoryEnum.GREAT_BRITIAN));
    assertTrue(
        board.isNeighbor(TerritoryEnum.ICELAND, TerritoryEnum.SCANDINAVIA));
  }

  /**
   * Tests neighbors for Great Britain.
   */
  @Test
  public void testGreatBritianNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.GREAT_BRITIAN, TerritoryEnum.ICELAND));
    assertTrue(board.isNeighbor(TerritoryEnum.GREAT_BRITIAN,
        TerritoryEnum.NORTHERN_EUROPE));
    assertTrue(board.isNeighbor(TerritoryEnum.GREAT_BRITIAN,
        TerritoryEnum.WESTERN_EUROPE));
    assertTrue(board.isNeighbor(TerritoryEnum.GREAT_BRITIAN,
        TerritoryEnum.SCANDINAVIA));
  }

  /**
   * Tests neighbors for Western Europe.
   */
  @Test
  public void testWesternEuropeNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_EUROPE,
        TerritoryEnum.NORTHERN_EUROPE));
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_EUROPE,
        TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_EUROPE,
        TerritoryEnum.NORTH_AFRICA));
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_EUROPE,
        TerritoryEnum.NORTH_AFRICA));
  }

  /**
   * Tests neighbors for Southern Europe.
   */
  @Test
  public void testSouthernEuropeNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.SOUTHERN_EUROPE,
        TerritoryEnum.WESTERN_EUROPE));
    assertTrue(board.isNeighbor(TerritoryEnum.SOUTHERN_EUROPE,
        TerritoryEnum.NORTHERN_EUROPE));
    assertTrue(
        board.isNeighbor(TerritoryEnum.SOUTHERN_EUROPE, TerritoryEnum.RUSSIA));
    assertTrue(board.isNeighbor(TerritoryEnum.SOUTHERN_EUROPE,
        TerritoryEnum.NORTH_AFRICA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.SOUTHERN_EUROPE, TerritoryEnum.EGYPT));
    assertTrue(board.isNeighbor(TerritoryEnum.SOUTHERN_EUROPE,
        TerritoryEnum.MIDDLE_EAST));
  }

  /**
   * Tests neighbors for Northern Europe.
   */
  @Test
  public void testNorthernEuropeNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHERN_EUROPE,
        TerritoryEnum.WESTERN_EUROPE));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHERN_EUROPE,
        TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(
        board.isNeighbor(TerritoryEnum.NORTHERN_EUROPE, TerritoryEnum.RUSSIA));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHERN_EUROPE,
        TerritoryEnum.GREAT_BRITIAN));
    assertTrue(board.isNeighbor(TerritoryEnum.NORTHERN_EUROPE,
        TerritoryEnum.SCANDINAVIA));
  }

  /**
   * Tests neighbors for Scandinavia.
   */
  @Test
  public void testScandinaviaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.SCANDINAVIA,
        TerritoryEnum.NORTHERN_EUROPE));
    assertTrue(board.isNeighbor(TerritoryEnum.SCANDINAVIA,
        TerritoryEnum.GREAT_BRITIAN));
    assertTrue(
        board.isNeighbor(TerritoryEnum.SCANDINAVIA, TerritoryEnum.RUSSIA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.SCANDINAVIA, TerritoryEnum.ICELAND));
  }

  /**
   * Tests neighbors for Russia.
   */
  @Test
  public void testRussiaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.RUSSIA, TerritoryEnum.SCANDINAVIA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.RUSSIA, TerritoryEnum.NORTHERN_EUROPE));
    assertTrue(
        board.isNeighbor(TerritoryEnum.RUSSIA, TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(
        board.isNeighbor(TerritoryEnum.RUSSIA, TerritoryEnum.MIDDLE_EAST));
    assertTrue(
        board.isNeighbor(TerritoryEnum.RUSSIA, TerritoryEnum.AFGHANISTAN));
    assertTrue(board.isNeighbor(TerritoryEnum.RUSSIA, TerritoryEnum.URAL));
  }

  /**
   * Tests neighbors for Middle East.
   */
  @Test
  public void testMiddleEast() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.MIDDLE_EAST,
        TerritoryEnum.SOUTHERN_EUROPE));
    assertTrue(
        board.isNeighbor(TerritoryEnum.MIDDLE_EAST, TerritoryEnum.EGYPT));
    assertTrue(
        board.isNeighbor(TerritoryEnum.MIDDLE_EAST, TerritoryEnum.EAST_AFRICA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.MIDDLE_EAST, TerritoryEnum.RUSSIA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.MIDDLE_EAST, TerritoryEnum.AFGHANISTAN));
    assertTrue(
        board.isNeighbor(TerritoryEnum.MIDDLE_EAST, TerritoryEnum.INDIA));
  }

  /**
   * Tests neighbors for Afghanistan.
   */
  @Test
  public void testAfghanistanNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.AFGHANISTAN, TerritoryEnum.MIDDLE_EAST));
    assertTrue(
        board.isNeighbor(TerritoryEnum.AFGHANISTAN, TerritoryEnum.RUSSIA));
    assertTrue(board.isNeighbor(TerritoryEnum.AFGHANISTAN, TerritoryEnum.URAL));
    assertTrue(
        board.isNeighbor(TerritoryEnum.AFGHANISTAN, TerritoryEnum.INDIA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.AFGHANISTAN, TerritoryEnum.CHINA));
  }

  /**
   * Tests neighbors for Ural.
   */
  @Test
  public void testUralNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.URAL, TerritoryEnum.RUSSIA));
    assertTrue(board.isNeighbor(TerritoryEnum.URAL, TerritoryEnum.AFGHANISTAN));
    assertTrue(board.isNeighbor(TerritoryEnum.URAL, TerritoryEnum.CHINA));
    assertTrue(board.isNeighbor(TerritoryEnum.URAL, TerritoryEnum.SIBERIA));
  }

  /**
   * Tests neighbors for India.
   */
  @Test
  public void testIndiaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.INDIA, TerritoryEnum.MIDDLE_EAST));
    assertTrue(
        board.isNeighbor(TerritoryEnum.INDIA, TerritoryEnum.AFGHANISTAN));
    assertTrue(board.isNeighbor(TerritoryEnum.INDIA, TerritoryEnum.CHINA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.INDIA, TerritoryEnum.SOUTHEAST_ASIA));
  }

  /**
   * Tests neighbors for China.
   */
  @Test
  public void testChinaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.CHINA, TerritoryEnum.INDIA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.CHINA, TerritoryEnum.SOUTHEAST_ASIA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.CHINA, TerritoryEnum.AFGHANISTAN));
    assertTrue(board.isNeighbor(TerritoryEnum.CHINA, TerritoryEnum.MONGOLIA));
    assertTrue(board.isNeighbor(TerritoryEnum.CHINA, TerritoryEnum.URAL));
    assertTrue(board.isNeighbor(TerritoryEnum.CHINA, TerritoryEnum.SIBERIA));
  }

  /**
   * Tests neighbors for Siberia.
   */
  @Test
  public void testSiberiaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.SIBERIA, TerritoryEnum.URAL));
    assertTrue(board.isNeighbor(TerritoryEnum.SIBERIA, TerritoryEnum.CHINA));
    assertTrue(board.isNeighbor(TerritoryEnum.SIBERIA, TerritoryEnum.IRKUTSK));
    assertTrue(board.isNeighbor(TerritoryEnum.SIBERIA, TerritoryEnum.MONGOLIA));
    assertTrue(board.isNeighbor(TerritoryEnum.SIBERIA, TerritoryEnum.YAKUTSK));
  }

  /**
   * Tests neighbors for Yakutsk.
   */
  @Test
  public void testYakutskNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.YAKUTSK, TerritoryEnum.SIBERIA));
    assertTrue(board.isNeighbor(TerritoryEnum.YAKUTSK, TerritoryEnum.IRKUTSK));
    assertTrue(
        board.isNeighbor(TerritoryEnum.YAKUTSK, TerritoryEnum.KAMACHATKA));
  }

  /**
   * Tests neighbors for Irkusk.
   */
  @Test
  public void testIrkuskNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.IRKUTSK, TerritoryEnum.SIBERIA));
    assertTrue(board.isNeighbor(TerritoryEnum.IRKUTSK, TerritoryEnum.YAKUTSK));
    assertTrue(
        board.isNeighbor(TerritoryEnum.IRKUTSK, TerritoryEnum.KAMACHATKA));
    assertTrue(board.isNeighbor(TerritoryEnum.IRKUTSK, TerritoryEnum.MONGOLIA));
  }

  /**
   * Tests neighbors for Mongolia.
   */
  @Test
  public void testMongoliaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.MONGOLIA, TerritoryEnum.SIBERIA));
    assertTrue(board.isNeighbor(TerritoryEnum.MONGOLIA, TerritoryEnum.IRKUTSK));
    assertTrue(
        board.isNeighbor(TerritoryEnum.MONGOLIA, TerritoryEnum.KAMACHATKA));
    assertTrue(board.isNeighbor(TerritoryEnum.MONGOLIA, TerritoryEnum.JAPAN));
    assertTrue(board.isNeighbor(TerritoryEnum.MONGOLIA, TerritoryEnum.CHINA));

  }

  /**
   * Tests neighbors for Kamatchka.
   */
  @Test
  public void testKamatchkaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.KAMACHATKA, TerritoryEnum.YAKUTSK));
    assertTrue(
        board.isNeighbor(TerritoryEnum.KAMACHATKA, TerritoryEnum.IRKUTSK));
    assertTrue(
        board.isNeighbor(TerritoryEnum.KAMACHATKA, TerritoryEnum.MONGOLIA));
    assertTrue(board.isNeighbor(TerritoryEnum.KAMACHATKA, TerritoryEnum.JAPAN));
    assertTrue(
        board.isNeighbor(TerritoryEnum.KAMACHATKA, TerritoryEnum.ALASKA));
  }

  /**
   * Tests neighbors for Japan.
   */
  @Test
  public void testJapanNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.JAPAN, TerritoryEnum.KAMACHATKA));
    assertTrue(board.isNeighbor(TerritoryEnum.JAPAN, TerritoryEnum.MONGOLIA));
  }

  /**
   * Tests neighbors for SouthEast Asia.
   */
  @Test
  public void testSouthEastAsiaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.SOUTHEAST_ASIA, TerritoryEnum.INDIA));
    assertTrue(
        board.isNeighbor(TerritoryEnum.SOUTHEAST_ASIA, TerritoryEnum.CHINA));
    assertTrue(board.isNeighbor(TerritoryEnum.SOUTHEAST_ASIA,
        TerritoryEnum.INDONESIA));
  }

  /**
   * Tests neighbors for Indonesia.
   */
  @Test
  public void testIndonesiaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.INDONESIA, TerritoryEnum.NEW_GUINEA));
    assertTrue(board.isNeighbor(TerritoryEnum.INDONESIA,
        TerritoryEnum.SOUTHEAST_ASIA));
  }

  /**
   * Tests neighbors for New Guinea.
   */
  @Test
  public void testNewGuineaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(
        board.isNeighbor(TerritoryEnum.NEW_GUINEA, TerritoryEnum.INDONESIA));
    assertTrue(board.isNeighbor(TerritoryEnum.NEW_GUINEA,
        TerritoryEnum.EASTERN_AUSTRALIA));
    assertTrue(board.isNeighbor(TerritoryEnum.NEW_GUINEA,
        TerritoryEnum.WESTERN_AUSTRALIA));

  }

  /**
   * Tests neighbors for Western Australia.
   */
  @Test
  public void testWesternAustraliaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_AUSTRALIA,
        TerritoryEnum.NEW_GUINEA));
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_AUSTRALIA,
        TerritoryEnum.EASTERN_AUSTRALIA));
    assertTrue(board.isNeighbor(TerritoryEnum.WESTERN_AUSTRALIA,
        TerritoryEnum.INDONESIA));
  }

  /**
   * Tests neighbors for Eastern Australia.
   */
  @Test
  public void testEasternAustraliaNeighbors() {
    RiskBoard board = new RiskBoard();
    assertTrue(board.isNeighbor(TerritoryEnum.EASTERN_AUSTRALIA,
        TerritoryEnum.NEW_GUINEA));
    assertTrue(board.isNeighbor(TerritoryEnum.EASTERN_AUSTRALIA,
        TerritoryEnum.WESTERN_AUSTRALIA));
  }

  /**
   * Tests that getTerritories returns a collection of unique Territories and
   * that it returns all the possible territories on the board.
   *
   */
  @Test
  public void testGetTerritories() {
    RiskBoard board = new RiskBoard();
    Collection<Territory> terrs = board.getTerritories();
    assertTrue(terrs.size() == NUMBER_TERRITORIES);
    Set<TerritoryEnum> ids = new HashSet<>();
    for (TerritoryEnum id : TerritoryEnum.values()) {
      ids.add(id);
    }
    for (Territory terr : terrs) {
      assertTrue(ids.contains(terr.getTerritoryId()));
      ids.remove(terr.getTerritoryId());
    }
    assertTrue(ids.size() == 0);
  }

  /**
   * Tests that getTerritorIds returns all the territory ids.
   */
  @Test
  public void testGetTerritoriesIds() {
    RiskBoard board = new RiskBoard();
    Collection<TerritoryEnum> ids = board.getTerritoryIds();
    Set<TerritoryEnum> terrIds = new HashSet<>();
    for (TerritoryEnum id : TerritoryEnum.values()) {
      terrIds.add(id);
    }
    assertTrue(terrIds.containsAll(ids));
    assertTrue(ids.containsAll(terrIds));
  }
  //
  // /**
  // * Tests that getContinents return the right Continents.
  // */
  // @Test
  // public void testGetContinents() {
  // RiskBoard board = new RiskBoard();
  // Collection<ContinentInterface> conts = board.getContinents();
  // assertTrue(conts.size() == 6);
  // Set<ContinentEnum> contIds = new HashSet<>();
  // for (ContinentEnum id : ContinentEnum.values()) {
  // contIds.add(id);
  // }
  // for (ContinentInterface cont : conts) {
  // assertTrue(contIds.contains(cont.getContinentId()));
  // contIds.remove(cont.getContinentId());
  // }
  // assertTrue(contIds.size() == 0);
  // }

  /**
   * Tests that getTerritory returns the right Territory.
   */
  @Test
  public void testGetTerritory() {
    RiskBoard board = new RiskBoard();
    for (TerritoryEnum id : TerritoryEnum.values()) {
      Territory terr = board.getTerritory(id);
      assertTrue(terr.getTerritoryId() == id);
    }
  }

  /**
   * Tests that getTerritory throws an IllegalArgumentException if the territory
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetTerritoryNullInput() {
    RiskBoard board = new RiskBoard();
    board.getTerritory(null);
  }

  // /**
  // * Test that getContinent returns the right continent.
  // */
  // @Test
  // public void testGetContinent() {
  // RiskBoard board = new RiskBoard();
  // for (ContinentEnum id : ContinentEnum.values()) {
  // ContinentInterface cont = board.getContinent(id);
  // assertTrue(cont.getContinentId() == id);
  // }
  // }
  //
  // /**
  // * Tests that getContinent throws an IllegalArgumentException if the
  // continent
  // * given is null.
  // */
  // @Test(expected = IllegalArgumentException.class)
  // public void testGetContinentNullInput() {
  // RiskBoard board = new RiskBoard();
  // board.getContinent(null);
  // }

  /**
   * Tests getMoveableTroops throws an IllegalArgumentException if the player
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetMoveableTroopsNullPlayer() {
    RiskBoard board = new RiskBoard();
    board.getMoveableTroops(null);
  }

  /**
   * Tests getMoveableTroops returns an empty map if the player cannot move
   * troops anywhere.
   */
  @Test
  public void testGetMoveableTroopsCannotMove() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.NORTHWEST_TERRITORY);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.NORTHWEST_TERRITORY).changePlayer(player,
        1);
    Multimap<TerritoryEnum, TerritoryEnum> map =
        board.getMoveableTroops(player);
    assertTrue(map.size() == 0);
  }

  /**
   * Tests getMoveableTroops returns an empty map if the player can move troops.
   */
  @Test
  public void testGetMoveableTroopsCanMove() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.NORTHWEST_TERRITORY);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.NORTHWEST_TERRITORY).changePlayer(player,
        1);
    Multimap<TerritoryEnum, TerritoryEnum> map =
        board.getMoveableTroops(player);
    assertTrue(map.containsKey(TerritoryEnum.ALBERTA));
    assertTrue(map.containsEntry(TerritoryEnum.ALBERTA, TerritoryEnum.ALASKA));
    assertTrue(map.containsEntry(TerritoryEnum.ALBERTA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(map.keySet().size() == 1);
    assertTrue(map.get(TerritoryEnum.ALBERTA).size() == 2);
  }

  /**
   * Tests getMoveableTroops.
   */
  @Test
  public void testGetMoveableTroopsCanDisconnectedTerritorySet() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.NORTHWEST_TERRITORY);
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    player.conqueredTerritory(TerritoryEnum.GREAT_BRITIAN);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.NORTHWEST_TERRITORY).changePlayer(player,
        1);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.GREAT_BRITIAN).changePlayer(player, 1);
    Multimap<TerritoryEnum, TerritoryEnum> map =
        board.getMoveableTroops(player);
    assertTrue(map.containsKey(TerritoryEnum.ALBERTA));
    assertTrue(map.containsEntry(TerritoryEnum.ALBERTA, TerritoryEnum.ALASKA));
    assertTrue(map.containsEntry(TerritoryEnum.ALBERTA,
        TerritoryEnum.NORTHWEST_TERRITORY));

    assertTrue(map.containsEntry(TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA));
    assertTrue(map.containsEntry(TerritoryEnum.ALASKA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(
        map.containsEntry(TerritoryEnum.ICELAND, TerritoryEnum.GREAT_BRITIAN));
    assertTrue(map.keySet().size() == 3);
    assertTrue(map.get(TerritoryEnum.ALBERTA).size() == 2);
    assertTrue(map.get(TerritoryEnum.ALASKA).size() == 2);
    assertTrue(map.get(TerritoryEnum.ICELAND).size() == 1);

  }

  /**
   * Tests that getMoveableTroops returns the correct map when one territory can
   * move troops to another territory that is not a direct neighbor.
   */
  @Test
  public void testGetMoveableTroopsNotDirectNeighbors() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.NORTHWEST_TERRITORY);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.ALASKA).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.NORTHWEST_TERRITORY).changePlayer(player,
        1);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    Multimap<TerritoryEnum, TerritoryEnum> map =
        board.getMoveableTroops(player);
    assertTrue(map.containsEntry(TerritoryEnum.ALBERTA, TerritoryEnum.ALASKA));
    assertTrue(map.containsEntry(TerritoryEnum.ALBERTA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(
        map.containsEntry(TerritoryEnum.ALBERTA, TerritoryEnum.GREENLAND));
    assertTrue(map.containsEntry(TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA));
    assertTrue(map.containsEntry(TerritoryEnum.ALASKA,
        TerritoryEnum.NORTHWEST_TERRITORY));
    assertTrue(
        map.containsEntry(TerritoryEnum.ALASKA, TerritoryEnum.GREENLAND));
    assertTrue(map.keySet().size() == 2);
    assertTrue(map.get(TerritoryEnum.ALBERTA).size() == 3);
    assertTrue(map.get(TerritoryEnum.ALASKA).size() == 3);
  }
}
