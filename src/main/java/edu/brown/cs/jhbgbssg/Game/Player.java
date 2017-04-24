package edu.brown.cs.jhbgbssg.Game;

import java.util.UUID;

/**
 * Represents a player.
 *
 * @author sarahgilmore
 *
 */
public interface Player {

  /**
   * Every player has a unique UUID.
   *
   * @return player id
   */
  UUID getPlayerId();
}
