package edu.brown.cs.jhbgbssg.Game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit tests for Die.
 *
 * @author sarahgilmore
 *
 */
public class DieTest {
  private static final int ROLL = 1000;

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    Die die = new Die();
    assertNotNull(die);
  }

  /**
   * Tests for a 1000 calls, roll always returns an integer between 1 and 6,
   * inclusive.
   */
  @Test
  public void testRoll() {
    Die die = new Die();
    for (int i = 0; i < ROLL; i++) {
      int roll = die.roll();
      assertTrue(roll >= 1 && roll <= 6);
    }
  }
}
