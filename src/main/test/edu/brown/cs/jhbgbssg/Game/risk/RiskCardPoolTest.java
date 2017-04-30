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
   * Tests the number of cards in the CardPool.
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
   * Tests number of one star cards in the CardPool.
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
   * Tests number of two star cards in the CardPool.
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
   * Tests cardpool only hands out cards with values of 1 or 2.
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
}
