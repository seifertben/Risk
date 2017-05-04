package edu.brown.cs.jhbgbssg.Game.RiskWorld.continent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;

/**
 * Represents the continent Europe.
 *
 * @author sarahgilmore
 *
 */
public class Europe implements ContinentInterface {
  private static final int BONUS_VALUE = 5;
  private UUID playerId;
  private Set<TerritoryEnum> territories;

  /**
   * Constructor for Europe.
   */
  public Europe() {
    playerId = null;
    territories = new HashSet<>();
    territories.add(TerritoryEnum.ICELAND);
    territories.add(TerritoryEnum.GREAT_BRITIAN);
    territories.add(TerritoryEnum.NORTHERN_EUROPE);
    territories.add(TerritoryEnum.SCANDINAVIA);
    territories.add(TerritoryEnum.SOUTHERN_EUROPE);
    territories.add(TerritoryEnum.WESTERN_EUROPE);
    territories.add(TerritoryEnum.RUSSIA);
  }

  @Override
  public ContinentEnum getContinentId() {
    return ContinentEnum.EUROPE;
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
      throw new IllegalArgumentException("ERROR: null");
    }
    return territories.contains(terr);
  }

  @Override
  public Set<TerritoryEnum> getTerritories() {
    return Collections.unmodifiableSet(territories);
  }

}
