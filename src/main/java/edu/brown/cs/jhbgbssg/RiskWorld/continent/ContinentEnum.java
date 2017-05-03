package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Each continent has a unique id represented by an Enum.
 *
 * @author sarah gilmore
 *
 */
public enum ContinentEnum {

  NORTH_AMERICA, SOUTH_AMERICA, AFRICA, EUROPE, ASIA, AUSTRALIA;

  private static final int NORTH_AMERICA_BONUS = 5;
  private static final int SOUTH_AMERICA_BONUS = 2;
  private static final int AFRICA_BONUS = 3;
  private static final int EUROPE_BONUS = 5;
  private static final int ASIA_BONUS = 11;
  private static final int AUSTRALIA_BONUS = 2;

  /**
   * Returns the bonus value for the given continental bonus.
   *
   * @return bonus value for the given continent.
   */
  public int getContinentalBonus() {

    switch (this) {
      case NORTH_AMERICA:
        return NORTH_AMERICA_BONUS;
      case SOUTH_AMERICA:
        return SOUTH_AMERICA_BONUS;
      case AFRICA:
        return AFRICA_BONUS;
      case EUROPE:
        return EUROPE_BONUS;
      case ASIA:
        return ASIA_BONUS;
      default:
        return AUSTRALIA_BONUS;
    }
  }

  /**
   * Returns the set of Territories that are within the Continent.
   *
   * @return set of territories.
   */
  public Set<TerritoryEnum> getTerrs() {
    switch (this) {
      case AFRICA:
        return ImmutableSet.of(TerritoryEnum.CENTRAL_AFRICA,
            TerritoryEnum.NORTH_AFRICA, TerritoryEnum.EAST_AFRICA,
            TerritoryEnum.SOUTH_AFRICA, TerritoryEnum.EGYPT,
            TerritoryEnum.MADAGASCAR);
      case ASIA:
        return ImmutableSet.of(TerritoryEnum.AFGHANISTAN, TerritoryEnum.CHINA,
            TerritoryEnum.INDIA, TerritoryEnum.IRKUTSK, TerritoryEnum.JAPAN,
            TerritoryEnum.KAMACHATKA, TerritoryEnum.MIDDLE_EAST,
            TerritoryEnum.MONGOLIA, TerritoryEnum.SOUTHEAST_ASIA,
            TerritoryEnum.SIBERIA, TerritoryEnum.URAL, TerritoryEnum.YAKUTSK);
      case AUSTRALIA:
        return ImmutableSet.of(TerritoryEnum.EASTERN_AUSTRALIA,
            TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.INDONESIA,
            TerritoryEnum.NEW_GUINEA);
      case EUROPE:
        return ImmutableSet.of(TerritoryEnum.ICELAND,
            TerritoryEnum.GREAT_BRITIAN, TerritoryEnum.NORTHERN_EUROPE,
            TerritoryEnum.SCANDINAVIA, TerritoryEnum.SOUTHERN_EUROPE,
            TerritoryEnum.WESTERN_EUROPE, TerritoryEnum.RUSSIA);
      case NORTH_AMERICA:
        return ImmutableSet.of(TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA,
            TerritoryEnum.NORTHWEST_TERRITORY, TerritoryEnum.QUEBEC,
            TerritoryEnum.ONTARIO, TerritoryEnum.WESTERN_US,
            TerritoryEnum.EASTERN_US, TerritoryEnum.GREENLAND,
            TerritoryEnum.CENTRAL_AMERICA);
      case SOUTH_AMERICA:
        return ImmutableSet.of(TerritoryEnum.PERU, TerritoryEnum.VENEZUELA,
            TerritoryEnum.BRAZIL, TerritoryEnum.ARGENTINA);
      default:
        return new HashSet<>();
    }
  }

  @Override
  public String toString() {
    switch (this) {
      case AFRICA:
        return "Africa";
      case ASIA:
        return "Asia";
      case AUSTRALIA:
        return "Australia";
      case EUROPE:
        return "Europe";
      case NORTH_AMERICA:
        return "North America";
      case SOUTH_AMERICA:
        return "South America";
      default:
        return "";
    }
  }
}
