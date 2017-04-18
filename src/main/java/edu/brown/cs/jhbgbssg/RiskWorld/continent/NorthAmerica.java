package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class NorthAmerica implements ContinentInterface {
  private static final int BONUS_VALUE = 5;
  private UUID playerId;
  private Set<TerritoryEnum> territories;

  public NorthAmerica() {
    playerId = null;
    territories = new HashSet<>();
    territories.add(TerritoryEnum.ALASKA);
    territories.add(TerritoryEnum.ALBERTA);
    territories.add(TerritoryEnum.NORTHWEST_TERRITORY);
    territories.add(TerritoryEnum.QUEBEC);
    territories.add(TerritoryEnum.ONTARIO);
    territories.add(TerritoryEnum.WESTERN_US);
    territories.add(TerritoryEnum.EASTERN_US);
    territories.add(TerritoryEnum.GREENLAND);
    territories.add(TerritoryEnum.CENTRAL_AMERICA);
  }

  @Override
  public ContinentEnum getContinentId() {
    return ContinentEnum.NORTH_AMERICA;
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
