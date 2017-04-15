package edu.brown.cs.jhbgbssg.RiskWorld;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SouthAmerica implements ContinentInterface {
  private static final int BONUS_VALUE = 2;
  private int playerId;
  private Set<TerritoryEnum> territories;

  public SouthAmerica() {
    playerId = -1;
    territories = new HashSet<>();
    territories.add(TerritoryEnum.PERU);
    territories.add(TerritoryEnum.VENEZUAELA);
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
  public void setOwner(int id) {
    playerId = id;

  }

  @Override
  public int getOwner() {
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
