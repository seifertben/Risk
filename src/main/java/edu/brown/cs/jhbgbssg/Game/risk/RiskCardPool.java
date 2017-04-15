package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.brown.cs.jhbgbssg.Game.CardPool;

/**
 *
 * @author sarahgilmore
 *
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
    Collections.shuffle(cards);
  }

  /**
   * Hands out a card if the pool is not empty.
   *
   * @return card value removed
   */
  public int handOutCard() {
    if (size == 0) {
      throw new UnsupportedOperationException("ERROR: no cards left");
    }
    size -= 1;
    return cards.remove(0);
  }

  /**
   * Returns a boolean indicating if all the cards have been handed out.
   *
   * @return true if the cardpool is empty; false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * UnsupportedOperation for RiskCardPool.
   */
  @Override
  public void handInCard() {
    throw new UnsupportedOperationException("ERROR: no reusing cards in Risk");

  }
}
