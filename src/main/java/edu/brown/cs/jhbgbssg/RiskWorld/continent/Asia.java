package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents the continent Asia.
 *
 * @author sarahgilmore
 *
 */
public class Asia implements ContinentInterface {
  private static final int BONUS_VALUE = 11;
  private UUID playerId;
  private Set<TerritoryEnum> territories;

  /**
   * Constructor for Asia.
   */
  public Asia() {
    playerId = null;
    territories = new HashSet<>();
    territories.add(TerritoryEnum.AFGHANISTAN);
    territories.add(TerritoryEnum.CHINA);
    territories.add(TerritoryEnum.INDIA);
    territories.add(TerritoryEnum.IRKUTSK);
    territories.add(TerritoryEnum.JAPAN);
    territories.add(TerritoryEnum.KAMACHATKA);
    territories.add(TerritoryEnum.MIDDLE_EAST);
    territories.add(TerritoryEnum.MONGOLIA);
    territories.add(TerritoryEnum.SOUTHEAST_ASIA);
    territories.add(TerritoryEnum.SIBERIA);
    territories.add(TerritoryEnum.URAL);
    territories.add(TerritoryEnum.YAKUTSK);
  }

  @Override
  public ContinentEnum getContinentId() {
    return ContinentEnum.ASIA;
  }

  @Override
  public int getBonusValue() {
    return BONUS_VALUE;
  }

  @Override
  public void setOwner(UUID id) {
    playerId = id;

  }

  @Override
  public UUID getOwner() {
    return playerId;
  }

  @Override
  public boolean containsTerritory(TerritoryEnum terr)
      throws IllegalArgumentException {
    if (terr == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    return territories.contains(terr);
  }

  @Override
  public Set<TerritoryEnum> getTerritories() {
    return Collections.unmodifiableSet(territories);
  }

}
