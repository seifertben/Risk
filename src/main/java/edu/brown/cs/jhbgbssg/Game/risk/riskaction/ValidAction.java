package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.UUID;

/**
 *
 * @author sarahgilmore
 *
 */
public interface ValidAction {

  /**
   * Returns true if the there is any valid action available.
   *
   * @return true if the user can take any action; false otherwise.
   */
  boolean actionAvailable();

  /**
   * Gets the MoveType.
   *
   * @return MoveType
   */
  MoveType getMoveType();

  /**
   * Gets the player id.
   *
   * @return id
   */
  UUID getMovePlayer();
}
