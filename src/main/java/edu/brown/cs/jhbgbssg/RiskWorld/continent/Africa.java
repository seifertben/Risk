package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents the continent Africa.
 *
 * @author sarahgilmore
 *
 */
public class Africa implements ContinentInterface {
  private static final int BONUS_VALUE = 3;
  private UUID playerId;
  private Set<TerritoryEnum> territories;

  /**
   * Constructor for Africa.
   */
  public Africa() {
    playerId = null;
    territories = new HashSet<>();
    territories.add(TerritoryEnum.CENTRAL_AFRICA);
    territories.add(TerritoryEnum.NORTH_AFRICA);
    territories.add(TerritoryEnum.EAST_AFRICA);
    territories.add(TerritoryEnum.SOUTH_AFRICA);
    territories.add(TerritoryEnum.EGYPT);
    territories.add(TerritoryEnum.MADAGASCAR);
  }

  @Override
  public ContinentEnum getContinentId() {
    return ContinentEnum.AFRICA;
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
