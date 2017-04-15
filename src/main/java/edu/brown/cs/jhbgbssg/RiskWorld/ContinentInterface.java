package edu.brown.cs.jhbgbssg.RiskWorld;

import java.util.Set;

public interface ContinentInterface {

  ContinentEnum getContinentId();

  int getBonusValue();

  void setOwner(int id);

  int getOwner();

  boolean containsTerritory(TerritoryEnum terr);

  Set<TerritoryEnum> getTerritories();

}
