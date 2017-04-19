package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 *
 * @author sarahgilmore
 *
 */
public class ValidCardMove implements Move {
  private UUID playerId;
  private Multiset<Integer> cards;
  private Set<TerritoryEnum> terrs;
  private boolean canTurnInCards;

  /**
   * Constructor for ValidCardMove. Takes in the player id, the set of cards
   * owned by the player and the territories owned.
   *
   * @param playerId - playerId
   * @param cards - set of cards
   * @param terrs - territories owned by the player
   */
  public ValidCardMove(UUID playerId, Multiset<Integer> cards,
      Set<TerritoryEnum> terrs) {
    if (playerId == null || cards == null || terrs == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }

    this.playerId = playerId;
    this.cards = cards;
    this.terrs = terrs;
    if (cards.size() == 0) {
      canTurnInCards = false;
    } else {
      canTurnInCards = true;
    }
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.TURN_IN_CARD;
  }

  /**
   * Returns whether or not the player has any cards to turn in.
   *
   * @return true if move is admissible; false otherwise
   */
  public boolean canTurnInCards() {
    return canTurnInCards;
  }

  /**
   * Returns multiset of cards owned by the player.
   *
   * @return multiset of integers
   */
  public Multiset<Integer> getCards() {
    return cards;
  }

  /**
   * Returns set of territories the player can reinforce by turning in the card.
   *
   * @return set of territory ids
   */
  public Set<TerritoryEnum> getTerritories() {
    return terrs;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  /**
   * Determines if a given CardTurnInMove is validate based on the bounds
   * defined by a ValidCardMove object.
   *
   * @param move - move to check validity of
   * @return true if the move is valid; false otherwise
   */
  public boolean validateCardMove(CardTurnInMove move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    int card = move.getCard();
    if (!cards.contains(card)) {
      return false;
    }
    Map<TerritoryEnum, Integer> reinforced = move.getTerritoriesReinforced();
    if (!terrs.containsAll(reinforced.keySet())) {
      return false;
    }
    Collection<Integer> values = reinforced.values();
    int added = 0;
    for (int val : values) {
      if (val <= 0) {
        return false;
      }
      added += val;
    }
    return added == card;
  }
}
