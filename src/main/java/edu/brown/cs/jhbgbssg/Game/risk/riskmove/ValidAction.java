package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

/**
 *
 * @author sarahgilmore
 *
 */
public interface ValidAction extends Move {

  /**
   * Returns true if the there is any valid action available.
   *
   * @return true if the user can take any action; false otherwise.
   */
  boolean actionAvailable();
}
