package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.ContinentEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class Africa implements ContinentInterface {
  private static final int BONUS_VALUE = 3;
  private UUID playerId;
  private Set<TerritoryEnum> territories;

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
  public boolean containsTerritory(TerritoryEnum terr) {

    return false;
  }

  @Override
  public Set<TerritoryEnum> getTerritories() {
    // TODO Auto-generated method stub
    return null;
  }

}
