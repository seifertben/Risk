package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.ContinentEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class SouthAmerica implements ContinentInterface {
  private static final int BONUS_VALUE = 2;
  private UUID playerId;
  private Set<TerritoryEnum> territories;

  public SouthAmerica() {
    playerId = null;
    territories = new HashSet<>();
    territories.add(TerritoryEnum.PERU);
    territories.add(TerritoryEnum.VENEZUELA);
    territories.add(TerritoryEnum.BRAZIL);
    territories.add(TerritoryEnum.ARGENTINIA);
  }

  @Override
  public ContinentEnum getContinentId() {
    return ContinentEnum.SOUTH_AMERICA;
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
  public boolean containsTerritory(TerritoryEnum terr) {
    return territories.contains(terr);
  }

  @Override
  public Set<TerritoryEnum> getTerritories() {
    return Collections.unmodifiableSet(territories);
  }
}
