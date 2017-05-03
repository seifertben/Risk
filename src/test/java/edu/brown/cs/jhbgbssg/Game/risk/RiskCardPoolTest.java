package edu.brown.cs.jhbgbssg.Game.risk;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit tests for RiskCardPool.
 *
 * @author sarahgilmore
 *
 */
public class RiskCardPoolTest {
  private static final int NUMBER_CARDS = 42;
  private static final int NUMBER_ONE_STAR_CARDS = 30;
  private static final int NUMBER_TWO_STAR_CARDS = 12;

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskCardPool cardPool = new RiskCardPool();
    assertNotNull(cardPool);
  }

  /**
   * Tests there are 42 cards in the card pool.
   */
  @Test
  public void testNumberCards() {
    RiskCardPool cardPool = new RiskCardPool();
    for (int i = 0; i < NUMBER_CARDS; i++) {
      cardPool.handOutCard();
    }
    assertTrue(cardPool.isEmpty());
  }

  /**
   * Tests there are 30 one-star cards in the card pool.
   */
  @Test
  public void testNumberOfOneStarCards() {
    RiskCardPool cardPool = new RiskCardPool();
    int number = 0;
    for (int i = 0; i < NUMBER_CARDS; i++) {
      if (cardPool.handOutCard() == 1) {
        number++;
      }
    }
    assertTrue(cardPool.isEmpty());
    assertTrue(number == NUMBER_ONE_STAR_CARDS);
  }

  /**
   * Tests there are 12 two-star cards in the card pool.
   */
  @Test
  public void testNumberOfOneTwoCards() {
    RiskCardPool cardPool = new RiskCardPool();
    int number = 0;
    for (int i = 0; i < NUMBER_CARDS; i++) {
      if (cardPool.handOutCard() == 2) {
        number++;
      }
    }
    assertTrue(cardPool.isEmpty());
    assertTrue(number == NUMBER_TWO_STAR_CARDS);
  }

  /**
   * Tests card pool only hands out cards with values of 1 or 2.
   */
  @Test
  public void testCardsNumbers() {
    RiskCardPool cardPool = new RiskCardPool();
    for (int i = 0; i < NUMBER_CARDS; i++) {
      int card = cardPool.handOutCard();
      assertTrue(card == 1 || card == 2);
    }
    assertTrue(cardPool.isEmpty());
  }

  /**
   * Tests HandInCard throws an UnsupportedOperationException if called.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testHandInCard() {
    RiskCardPool cardPool = new RiskCardPool();
    cardPool.handInCard(1);
  }

  /**
   * Tests that handoutCard returns -1 once all the cards have been handed out.
   */
  @Test
  public void testHandoutCardOnceEmpty() {
    RiskCardPool cardPool = new RiskCardPool();
    for (int i = 0; i < NUMBER_CARDS; i++) {
      int card = cardPool.handOutCard();
      assertTrue(card == 1 || card == 2);
    }
    assertTrue(cardPool.isEmpty());
    assertTrue(cardPool.handOutCard() == -1);
  }
}
