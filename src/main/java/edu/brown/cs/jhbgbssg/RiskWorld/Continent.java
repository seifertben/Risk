package edu.brown.cs.jhbgbssg.RiskWorld;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Continent {
  private int bonusValue;
  private Set<TerritoryEnum> territories;
  private ContinentEnum id;

  private Continent(ContinentBuilder builder) {
    if (builder == null) {
      throw new IllegalArgumentException("ERROR: null builder");
    }
    this.bonusValue = builder.bonusValue;
    this.territories = new HashSet<>(builder.territories);
    this.id = builder.id;
  }

  public boolean hasTerritory(TerritoryEnum territory) {
    return territories.contains(territory);
  }

  public Set<TerritoryEnum> getTerritories() {
    return Collections.unmodifiableSet(territories);
  }

  public ContinentEnum getContinetId() {
    return id;
  }

  public int getBonusValue() {
    return bonusValue;
  }

  public static class ContinentBuilder {
    private Set<TerritoryEnum> territories;
    private int bonusValue;
    private ContinentEnum id;

    public ContinentBuilder() {
      territories = new HashSet<>();
      bonusValue = 0;
    }

    public ContinentBuilder territory(TerritoryEnum territory)
        throws IllegalArgumentException {
      if (territory == null) {
        throw new IllegalArgumentException("ERROR: null territory");
      }
      territories.add(territory);
      return this;
    }

    public ContinentBuilder bonusValue(int value) {
      if (value <= 0) {
        throw new IllegalArgumentException("ERROR: illegal bonus value");
      }
      bonusValue = value;
      return this;
    }

    public ContinentBuilder id(ContinentEnum id) {
      if (id == null) {
        throw new IllegalArgumentException("ERROR: null id");
      }
      this.id = id;
      return this;
    }

    public Continent build() {
      return new Continent(this);
    }
  }
}
