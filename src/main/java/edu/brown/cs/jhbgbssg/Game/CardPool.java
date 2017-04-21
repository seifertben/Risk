package edu.brown.cs.jhbgbssg.Game;

/**
 *
 * @author Ben
 *
 */
public interface CardPool {

  int handOutCard();

  void handInCard(int value);

  boolean isEmpty();

}
