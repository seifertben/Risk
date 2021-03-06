package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.Player;
import edu.brown.cs.jhbgbssg.Game.riskworld.ContinentEnum;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * Represents a RiskPlayer. A RiskPlayer knows the cards it owns, the
 * territories it controls and the continents it controls.
 *
 * @author sarahgilmore
 *
 */
public class RiskPlayer implements Player {
  private UUID playerId;
  private Set<TerritoryEnum> territories;
  private Set<ContinentEnum> conts;
  private Multiset<Integer> cards;
  private int remainingSetupReinforcements;
  private static final int INITIAL_NUMBER_TROOPS = 40;

  /**
   * Constructor for a RiskPlayer. Takes in the player's unique player id
   *
   * @param playerId - player Id.
   * @throws IllegalArgumentException - thrown if the id is null
   */
  public RiskPlayer(UUID playerId) throws IllegalArgumentException {
    if (playerId == null) {
      throw new IllegalArgumentException("ERROR: cannot have null id");
    }
    this.playerId = playerId;
    territories = new HashSet<>();
    cards = HashMultiset.create();
    conts = new HashSet<>();
  }

  /**
   * Returns the player's unique id.
   */
  @Override
  public UUID getPlayerId() {
    return playerId;
  }

  /**
   * Returns the cards the RiskPlayer owns.
   *
   * @return multiset of integers representing cards
   */
  public Multiset<Integer> getCards() {
    return cards;
  }

  /**
   * Determines if the player owns the given card.
   *
   * @param cardValue - card value
   * @return boolean indcating if the player owns the card
   */
  public boolean hasCard(int cardValue) {
    return cards.contains(cardValue);
  }

  /**
   * Removes a card from the player's card set.
   *
   * @param cardValue - card value to remove
   * @return boolean indicating if the card was removed.
   */
  public boolean removeCard(int cardValue) {
    return cards.remove(cardValue);
  }

  /**
   * Adds card to player set. If the cardValue is not 1 or 2, it is not added to
   * the player's card set and false is returned.
   *
   * @param cardValue - value of the card
   * @return indicating the card was added
   */
  public boolean addCard(int cardValue) {
    if (cardValue == 1 || cardValue == 2) {
      return cards.add(cardValue);
    }
    return false;
  }

  /**
   * Determines if the player owns this territory.
   *
   * @param territory - territory id
   * @return boolean indicating if the player owns it
   */
  public boolean hasTerritory(TerritoryEnum territory) {
    return territories.contains(territory);
  }

  /**
   * Adds territory to the owned territory set.
   *
   * @param conqueredTerritory - territory to add
   * @return boolean indicating if the territory was added and not a duplicate
   * @throws IllegalArgumentException - thrown if the territory is null
   */
  public boolean conqueredTerritory(TerritoryEnum conqueredTerritory)
      throws IllegalArgumentException {
    if (conqueredTerritory == null) {
      throw new IllegalArgumentException("ERROR: null territory");
    }
    boolean gained = territories.add(conqueredTerritory);
    if (gained) {
      ContinentEnum cont = conqueredTerritory.getContinent();
      if (territories.containsAll(cont.getTerrs())) {
        conts.add(cont);
      }
    }
    return gained;
  }

  /**
   * Removes a territory from the players owned territory set.
   *
   * @param lostTerritory - territory lost
   * @return boolean indicating if the territory was removed
   * @throws IllegalArgumentException - thrown if the territory is null
   */
  public boolean lostTerritory(TerritoryEnum lostTerritory)
      throws IllegalArgumentException {
    if (lostTerritory == null) {
      throw new IllegalArgumentException("ERROR: null territory");
    }
    ContinentEnum cont = lostTerritory.getContinent();
    if (conts.contains(cont)) {
      conts.remove(cont);
    }
    return territories.remove(lostTerritory);
  }

  /**
   * Returns an unmodifiable set of the territories owned by the player.
   *
   * @return set of territories
   */
  public Set<TerritoryEnum> getTerritories() {
    return Collections.unmodifiableSet(territories);
  }

  /**
   * Returns an unmodifiable collection of continents the player controls.
   *
   * @return set of continents
   */
  public Set<ContinentEnum> getContinents() {
    return Collections.unmodifiableSet(conts);
  }

  /**
   * Returns the number of territories owned by the player.
   *
   * @return number of owned territories
   */
  public int getNumberTerritories() {
    return territories.size();
  }

  /**
   * Determines if the risk player owns any territories.
   *
   * @return boolean indicating if player owns territories
   */
  public boolean hasTerritories() {
    return territories.size() != 0;
  }

  /**
   * Sets the initial number of reinforcements.
   *
   * @param numPlayers - number of players in the game
   */
  public void setIntialReinforcement(int numPlayers) {
    remainingSetupReinforcements = INITIAL_NUMBER_TROOPS - 5 * (numPlayers - 2);
  }

  /**
   * Decrements the initial number of reinforcements by the given number.
   *
   * @param numToDecrement - number to decrement by
   * @throws IllegalArgumentException if the input is not positive.
   */
  public void decrementInitialReinforcements(int numToDecrement) {
    if (numToDecrement <= 0) {
      throw new IllegalArgumentException("ERROR: bad input");
    }
    remainingSetupReinforcements -= numToDecrement;
  }

  /**
   * Returns the number of reinforcements.
   *
   * @return number of initial reinforcements
   */
  public int getInitialReinforcements() {
    return remainingSetupReinforcements;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof RiskPlayer)) {
      return false;
    }
    RiskPlayer otherPlayer = (RiskPlayer) o;
    if (otherPlayer.playerId.equals(playerId)) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(playerId);
  }
}
