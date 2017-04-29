package edu.brown.cs.jhbgbssg.Game.risk;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskCardPool;

/**
 * JUnit tests for RiskCardPool.
 *
 * @author sarahgilmore
 *
 */
public class RiskCardPoolTest {

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
    for (int i = 0; i < 42; i++) {
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
    for (int i = 0; i < 42; i++) {
      if (cardPool.handOutCard() == 1) {
        number++;
      }
    }
    assertTrue(cardPool.isEmpty());
    assertTrue(number == 30);
  }

  /**
   * Tests number of two star cards in the CardPool.
   */
  @Test
  public void testNumberOfOneTwoCards() {
    RiskCardPool cardPool = new RiskCardPool();
    int number = 0;
    for (int i = 0; i < 42; i++) {
      if (cardPool.handOutCard() == 2) {
        number++;
      }
    }
    assertTrue(cardPool.isEmpty());
    assertTrue(number == 12);
  }

  /**
   * Tests cardpool only hands out cards with values of 1 or 2.
   */
  @Test
  public void testCardsNumbers() {
    RiskCardPool cardPool = new RiskCardPool();
    for (int i = 0; i < 42; i++) {
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
