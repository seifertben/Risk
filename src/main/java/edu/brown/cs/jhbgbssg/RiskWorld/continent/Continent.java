package edu.brown.cs.jhbgbssg.RiskWorld.continent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 *
 * @author sarahgilmore
 *
 */
public final class Continent {
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

    // sets the builder fields back to initial state
    builder.bonusValue = -1;
    builder.territories = null;
    builder.id = null;
  }

  /**
   * Checks if this Continent contains a territory.
   *
   * @param territory - id of territory to check
   * @return true if so; false otherwise
   */
  public boolean hasTerritory(TerritoryEnum territory) {
    return territories.contains(territory);
  }

  /**
   * Gets an unmodifiable set of Territory ids contained by this map.
   *
   * @return set of territory ids
   */
  public Set<TerritoryEnum> getTerritories() {
    return Collections.unmodifiableSet(territories);
  }

  /**
   * Gets the id of this Continent.
   *
   * @return Continent id
   */
  public ContinentEnum getContinetId() {
    return id;
  }

  /**
   * Gets the bonus value of this Continent.
   *
   * @return bonus value
   */
  public int getBonusValue() {
    return bonusValue;
  }

  /**
   * Builder for Continent.
   *
   * @author sarahgilmore
   *
   */
  public static class ContinentBuilder {
    private Set<TerritoryEnum> territories;
    private int bonusValue;
    private ContinentEnum id;

    /**
     * Constructor for ContinentBuider.
     */
    public ContinentBuilder() {
      territories = new HashSet<>();
      bonusValue = -1;
      id = null;
    }

    /**
     * Adds a TerritoryId to the set of territories.
     *
     * @param territory - id of territory to add
     * @return this ContinentBuidler
     * @throws IllegalArgumentException - thrown if the value given is null
     */
    public ContinentBuilder territory(TerritoryEnum territory)
        throws IllegalArgumentException {
      if (territory == null) {
        throw new IllegalArgumentException("ERROR: null territory");
      }
      territories.add(territory);
      return this;
    }

    /**
     * Sets the Continent's bonus value.
     *
     * @param value - bonus value
     * @return this ContinentBuidler
     */
    public ContinentBuilder bonusValue(int value) {
      if (value <= 0) {
        throw new IllegalArgumentException("ERROR: illegal bonus value");
      }
      bonusValue = value;
      return this;
    }

    /**
     * Sets the id of the Continent.
     *
     *
     * @param contId - Continent's unique id
     * @return this ContinentBuidler llegalArgumentException - thrown if the
     *         value given is null
     */
    public ContinentBuilder id(ContinentEnum contId) {
      if (id == null) {
        throw new IllegalArgumentException("ERROR: null id");
      }
      this.id = contId;
      return this;
    }

    /**
     * Builds a Continent.
     *
     * @return a new Continent with the specified values
     */
    public Continent build() {
      return new Continent(this);
    }
  }
}
