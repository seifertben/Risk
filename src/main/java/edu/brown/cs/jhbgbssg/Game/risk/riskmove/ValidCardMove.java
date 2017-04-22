package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 *
 * @author sarahgilmore
 *
 */
public class ValidCardMove implements ValidAction {
  private UUID playerId;
  private Multiset<Integer> cards;
  private Set<TerritoryEnum> terrs;
  private boolean canTurnInCards = true;

  /**
   * Constructor for ValidCardMove. Takes in the player id, the set of cards
   * owned by the player and the territories owned.
   *
   * @param player - player
   */
  public ValidCardMove(RiskPlayer player) {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.playerId = player.getPlayerId();
    playerId = player.getPlayerId();
    cards = player.getCards();
    terrs = player.getTerritories();
    if (cards.size() == 0) {
      canTurnInCards = false;
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

  @Override
  public boolean actionAvailable() {
    return canTurnInCards;
  }
}
