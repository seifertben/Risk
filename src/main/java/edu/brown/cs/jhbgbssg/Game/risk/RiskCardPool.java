package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.brown.cs.jhbgbssg.Game.CardPool;

/**
 * This class represents the card pool in risk. It has 30 one-star cards and 12
 * two-star cards. It implements the CardPool interface. RiskCardPool can hand
 * out cards, but it cannot accept cards back.
 *
 * @author sarahgilmore
 */
public class RiskCardPool implements CardPool {
  private List<Integer> cards;
  private static final int NUMBER_ONE_STARS = 30;
  private static final int NUMBER_TWO_STARS = 12;
  private int size;

  /**
   * Constructor for RiskCardPool.
   */
  public RiskCardPool() {
    cards = new ArrayList<>();
    for (int i = 0; i < NUMBER_ONE_STARS; i++) {
      cards.add(1);
    }
    for (int i = 0; i < NUMBER_TWO_STARS; i++) {
      cards.add(2);
    }
    size = cards.size();
    Collections.shuffle(cards); // shuffles the cards
  }

  /**
   * Hands out a card if the pool is not empty. If the card pool is empty, the
   * method returns -1 instead of a valid card value.
   *
   * @return card value removed
   */
  public int handOutCard() {
    if (size == 0) {
      return -1;
    }
    size -= 1;
    return cards.remove(0);
  }

  /**
   * Returns a boolean indicating whether or not the number of cards left in the
   * pool is zero.
   *
   * @return true if the card pool is empty; false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * UnsupportedOperation for RiskCardPool. Cards cannot be accepted back in
   * Risk.
   */
  @Override
  public void handInCard(int card) {
    throw new UnsupportedOperationException("ERROR: no reusing cards in Risk");

  }
}
