package edu.brown.cs.jhbgbssg.Game;

import java.util.Random;

/**
 * Class that simulates a die roll.
 *
 * @author Ben
 *
 */
public class Die {

  private static final int SIDES = 6;

  /**
   * The Risk Die.
   */
  public Die() {

  }

  /**
   * Rolls the die.
   *
   * @return the die roll.
   */
  public int roll() {
    Random generate = new Random();
    return generate.nextInt(SIDES) + 1;
  }
}
