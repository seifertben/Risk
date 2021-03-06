package edu.brown.cs.jhbgbssg.Game.riskworld;

import com.google.common.base.Objects;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 * This class represents a territory. A territory knows which player currently
 * controls it, the number of troops on it and its continent. Each Territory is
 * identified by a unique TerritoryEnum id.
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
   * Constructs a territory and assigns it an id. The continent is assigned
   * internally by calling the getContinent() method of TerritoryEnum on hte id
   * given.
   *
   * @param id - id of territory
   * @throws IllegalArgumentException thrown if id is null
   */
  public Territory(TerritoryEnum id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("ERROR: null id");
    }
    this.id = id;
    this.contId = id.getContinent();
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
   * Adds troops to territory if a player owns the territory. If no player owns
   * the territory and the number of troops is zero, false is returned and no
   * troops are added.
   *
   * @param troopsToAdd - number of troops to add
   * @return boolean - true if the troops were added; false otherwise
   * @throws IllegalArgumentException if the input is non-positive.
   */
  public boolean addTroops(int troopsToAdd) throws IllegalArgumentException {
    if (troopsToAdd <= 0) {
      throw new IllegalArgumentException("ERROR: invalid troop number");
    } else if (player == null) {
      return false;
    }
    numberTroops += troopsToAdd;
    return true;
  }

  /**
   * Changes the player that controls the territory and sets the number of
   * troops to the given amount. It only executes if there are no troops on the
   * territory and if no player currently owns the territory.
   *
   * @param newPlayer - player to control the territory
   * @param numTroops - number of troops to put on the territory
   * @throws IllegalArgumentException - if the player id is null or if the a new
   *           player cannot be set as the territory's owner in its current
   *           state
   */
  public void changePlayer(RiskPlayer newPlayer, int numTroops)
      throws IllegalArgumentException {
    if (player != null || numberTroops != 0) {
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
    if (troopsToRemove < 0) {
      throw new IllegalArgumentException("ERROR: invalid troop number");
    }
    if (numberTroops <= troopsToRemove) {
      numberTroops = 0;
      player = null;
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
   * @return player who owns the territory
   */
  public RiskPlayer getOwner() {
    return player;
  }

  /**
   * Equals method for Territory.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Territory)) {
      return false;
    }
    Territory otherTerritory = (Territory) o;
    if (otherTerritory.id != this.id) {
      return false;
    } else if (!otherTerritory.player.equals(this.player)) {
      return false;
    } else if (otherTerritory.numberTroops != this.numberTroops) {
      return false;
    }

    // if reach this point, must be true
    assert (otherTerritory.contId == this.contId);
    return true;
  }

  /**
   * Hashes based on its id, the player that owns it and the number of troops.
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(this.id, this.player, this.numberTroops);
  }
}
