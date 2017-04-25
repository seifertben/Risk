package edu.brown.cs.jhbgbssg.RiskWorld;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentEnum;

/**
 * Represents a Territory.
 *
 * @author sarahgilmore
 *
 */
public class Territory {
  private TerritoryEnum id;
  private int numberTroops;
  private RiskPlayer player = null;
  private ContinentEnum contId;

  /**
   * Constructs a territory and assigns it an id.
   *
   * @param id - id of territory
   * @param contId - id of the continent territory belongs to
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
   * Returns whether or not the territory is occupied.
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
    } else if (player == null) {
      throw new IllegalArgumentException(
          "ERROR: cannot add troops to a territory with no player");
    }
    numberTroops += troopsToAdd;
  }

  /**
   * Changes the player that controls the territory. It only executes if there
   * are no troops on the territory and if no player currently owns the
   * territory.
   *
   * @param newPlayer - player to control the territory
   * @param numTroops - number of troops to put on the territory
   * @throws IllegalArgumentException - if the player id is null or if the a new
   *           player cannot be set as the territory's owner in its current
   *           state
   */
  public void changePlayer(RiskPlayer newPlayer, int numTroops)
      throws IllegalArgumentException {
    if (player != null && numberTroops != 0) {
      throw new IllegalArgumentException(
          "ERROR: cannot change playeres if number of troops is not zero");
    } else if (newPlayer == null) {
      throw new IllegalArgumentException("ERROR: cann to have a null id");
    } else if (numTroops <= 0) {
      throw new IllegalArgumentException(
          "ERROR: troop number must be positive");
    }
    player = newPlayer;
    numberTroops = numTroops;
  }

  /**
   * Gets the player that owns the territory.
   *
   * @return id of the player
   */
  public RiskPlayer getTerritoryOwner() {
    return player;
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

  /**
   * Gets the id of the continent this territory belongs to.
   *
   * @return continent id
   */
  public ContinentEnum getContinent() {
    return this.contId;
  }

  /**
   * Gets the player that owns the territory.
   *
   * @return id of the player
   */
  public RiskPlayer getOwner() {
    return player;
  }
}
