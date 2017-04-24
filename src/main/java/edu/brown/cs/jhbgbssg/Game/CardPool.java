package edu.brown.cs.jhbgbssg.Game;

/**
 * Represents a CardPool.
 *
 */
public interface CardPool {

  /**
   * Hands out a card at random from the pool.
   *
   * @return value of the card handed out
   */
  int handOutCard();

  /**
   * Hands in a card.
   *
   * @param value - card value handed in
   */
  void handInCard(int value);

  /**
   * Determines if the card pool is empty.
   *
   * @return true if the pool is empty; false otherwise
   */
  boolean isEmpty();

}
