package edu.brown.cs.jhbgbssg.RiskWorld;

/**
 * Represents a Territory.
 *
 * @author sarahgilmore
 *
 */
public class Territory {
  private TerritoryEnum id;
  private int numberTroops;
  private int playerId = -1;

  /**
   * Constructs a territory and assigns it an id.
   *
   * @param id - id of territory
   * @throws IllegalArgumentException thrown if id is null
   */
  public Territory(TerritoryEnum id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("ERROR: null id");
    }
    this.id = id;
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

  public void changePlayer(int newPlayerId, int numTroops) {
    if (playerId != -1 && numberTroops != 0) {
      throw new IllegalArgumentException(
          "ERROR: cannot change playeres if number of troops is not zero");
    } else {
      playerId = newPlayerId;
      numberTroops = numTroops;
    }
  }

  public int getTerritoryOwner() {
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
}
