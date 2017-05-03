package edu.brown.cs.jhbgbssg.RiskWorld;

import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentEnum;

/**
 * Each territory is given a unique id represented by a specific TerritoryEnum.
 *
 * @author sarahgilmore
 *
 */
public enum TerritoryEnum {

  ALASKA, ONTARIO, CENTRAL_AMERICA, EASTERN_US, WESTERN_US, GREENLAND,
  NORTHWEST_TERRITORY, ALBERTA, QUEBEC, VENEZUELA, PERU, BRAZIL, ARGENTINA,
  NORTH_AFRICA, EGYPT, EAST_AFRICA, CENTRAL_AFRICA, SOUTH_AFRICA, MADAGASCAR,
  ICELAND, GREAT_BRITIAN, WESTERN_EUROPE, NORTHERN_EUROPE, SCANDINAVIA,
  SOUTHERN_EUROPE, RUSSIA, AFGHANISTAN, CHINA, INDIA, IRKUTSK, JAPAN,
  KAMACHATKA, MIDDLE_EAST, MONGOLIA, SOUTHEAST_ASIA, SIBERIA, URAL, YAKUTSK,
  EASTERN_AUSTRALIA, INDONESIA, NEW_GUINEA, WESTERN_AUSTRALIA;

  /**
   * Returns the Continent that the territory is a part of.
   *
   * @return the continent the territory belongs to.
   */
  public ContinentEnum getContinent() {
    for (ContinentEnum cont : ContinentEnum.values()) {
      if (cont.getTerrs().contains(this)) {
        return cont;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    switch (this) {
      case AFGHANISTAN:
        return "Afghanistan";
      case ALASKA:
        return "Alaska";
      case ALBERTA:
        return "Alberta";
      case ARGENTINA:
        return "Argentina";
      case BRAZIL:
        return "Brazil";
      case CENTRAL_AFRICA:
        return "Cental Africa";
      case CENTRAL_AMERICA:
        return "Central America";
      case CHINA:
        return "China";
      case EASTERN_AUSTRALIA:
        return "Eastern Australia";
      case EASTERN_US:
        return "Eastern United States";
      case EAST_AFRICA:
        return "East Africa";
      case EGYPT:
        return "Egypt";
      case GREAT_BRITIAN:
        return "Great Britian";
      case GREENLAND:
        return "Greenland";
      case ICELAND:
        return "Iceland";
      case INDIA:
        return "India";
      case INDONESIA:
        return "Indonesia";
      case IRKUTSK:
        return "Irkutsk";
      case JAPAN:
        return "Japan";
      case KAMACHATKA:
        return "Kamachatka";
      case MADAGASCAR:
        return "Madagascar";
      case MIDDLE_EAST:
        return "Middle East";
      case MONGOLIA:
        return "Mongolia";
      case NEW_GUINEA:
        return "New Guinea";
      case NORTHERN_EUROPE:
        return "Northern Europe";
      case NORTHWEST_TERRITORY:
        return "Northwest Territory";
      case NORTH_AFRICA:
        return "North Africa";
      case ONTARIO:
        return "Ontario";
      case PERU:
        return "Peru";
      case QUEBEC:
        return "Quebec";
      case RUSSIA:
        return "Russia";
      case SCANDINAVIA:
        return "Scandinavia";
      case SIBERIA:
        return "Siberia";
      case SOUTHEAST_ASIA:
        return "Southeast Asia";
      case SOUTHERN_EUROPE:
        return "Southern Europe";
      case SOUTH_AFRICA:
        return "South Africa";
      case URAL:
        return "Ural";
      case VENEZUELA:
        return "Venezuela";
      case WESTERN_AUSTRALIA:
        return "Western Australia";
      case WESTERN_EUROPE:
        return "Western Europe";
      case WESTERN_US:
        return "Western United States";
      case YAKUTSK:
        return "Yakutsk";
      default:
        return "";
    }
  }

}
