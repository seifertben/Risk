package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents the continent Australia.
 *
 * @author sarahgilmore
 *
 */
public class Australia implements ContinentInterface {
  private static final int BONUS_VALUE = 2;
  private UUID playerId;
  private Set<TerritoryEnum> territories;

  /**
   * Constructor for Australia.
   */
  public Australia() {
    playerId = null;
    territories = new HashSet<>();
    territories.add(TerritoryEnum.EASTERN_AUSTRALIA);
    territories.add(TerritoryEnum.WESTERN_AUSTRALIA);
    territories.add(TerritoryEnum.INDONESIA);
    territories.add(TerritoryEnum.NEW_GUINEA);
  }

  @Override
  public ContinentEnum getContinentId() {
    return ContinentEnum.AUSTRALIA;
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
