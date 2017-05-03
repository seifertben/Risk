package edu.brown.cs.jhbgbssg.Game.RiskWorld.continent;

import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;

/**
 * Interface taht all continents in Risk must implement.
 *
 * @author sarahgilmore
 *
 */
public interface ContinentInterface {

  /**
   * Returns the continents id.
   *
   * @return continent id
   */
  ContinentEnum getContinentId();

  /**
   * Returns the bonus value of the continent.
   *
   * @return bonus value
   */
  int getBonusValue();

  /**
   * Sets the current owner of the continent.
   *
   * @param id - player id
   */
  void setOwner(UUID id);

  /**
   * Gets the owner of the continent.
   *
   * @return id of the owner
   */
  UUID getOwner();

  /**
   * Returns whether or not the given territory is contained by this continent.
   *
   * @param terr - territory id
   * @return true if the continent contains the territory; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  boolean containsTerritory(TerritoryEnum terr) throws IllegalArgumentException;

  /**
   * Returns an unmodifiable set of territories contained by this. continent.
   *
   * @return set of territories.
   */
  Set<TerritoryEnum> getTerritories();

}
