package edu.brown.cs.jhbgbssg.RiskWorld;

import java.util.UUID;

/**
 * Represents a Territory.
 *
 * @author sarahgilmore
 *
 */
public class Territory {
  private TerritoryEnum id;
  private int numberTroops;
  private UUID playerId = null;
  private ContinentEnum contId;

  /**
   * Constructs a territory and assigns it an id.
   *
   * @param id - id of territory
   * @throws IllegalArgumentException thrown if id is null
   */
  public Territory(TerritoryEnum id, ContinentEnum contId)
      throws IllegalArgumentException {
    if (id == null || contId == null) {
      throw new IllegalArgumentException("ERROR: null id");
    }
    this.id = id;
    this.contId = contId;
    this.numberTroops = 0;
  }

  /**
   * Returns the number of troops in the territory.
   *
   * @return number of troops
   */
  public int getNumberTroops() {
    return numberTroops;
  }

  /**
   * Returns whether or not the territory is occuppied.
   *
   * @return true if occupied; false otherwise.
   */
  public boolean occuppied() {
    return numberTroops > 0;
  }

  /**
   * Adds troops to territory.
   *
   * @param troopsToAdd - number of troops to add
   * @throws IllegalArgumentException if the input is non-positive.
   */
  public void addTroops(int troopsToAdd) throws IllegalArgumentException {
    if (troopsToAdd <= 0) {
      throw new IllegalArgumentException("ERROR: invalid troop number");
    }
    numberTroops += troopsToAdd;
  }

  public void changePlayer(UUID newPlayerId, int numTroops) {
    if (playerId != null && numberTroops != 0) {
      throw new IllegalArgumentException(
          "ERROR: cannot change playeres if number of troops is not zero");
    } else {
      playerId = newPlayerId;
      numberTroops = numTroops;
    }
  }

  public UUID getTerritoryOwner() {
    return playerId;
  }

  /**
   * Returns the territory id.
   *
   * @return territory id
   */
  public TerritoryEnum getTerritoryId() {
    return id;
  }

  /**
   * Removes troops and returns a boolean indicating if the number of troops
   * left is zero.
   *
   * @param troopsToRemove - number of troops to remove
   * @return boolean if number of troops left is zero
   * @throws IllegalArgumentException if the input is non-positive.
   */
  public boolean removeTroops(int troopsToRemove)
      throws IllegalArgumentException {
    if (troopsToRemove <= 0) {
      throw new IllegalArgumentException("ERROR: invalid troop number");
    }
    if (numberTroops <= troopsToRemove) {
      numberTroops = 0;
      return true;
    }
    numberTroops = numberTroops - troopsToRemove;
    return false;
  }

  public ContinentEnum getContinent() {
    return this.contId;
  }
}
