package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public interface ContinentInterface {

  ContinentEnum getContinentId();

  int getBonusValue();

  void setOwner(UUID id);

  UUID getOwner();

  boolean containsTerritory(TerritoryEnum terr);

  Set<TerritoryEnum> getTerritories();

}
