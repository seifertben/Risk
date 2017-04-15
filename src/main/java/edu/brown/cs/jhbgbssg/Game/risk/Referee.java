package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.Territory;

/**
 * Controls the rules of the game.
 *
 * @author Ben
 *
 */
public class Referee {

  /**
   * Initializes the referee.
   */
  public Referee() {

  }

  /**
   * Validates if a player may choose a territory at the beginning.
   *
   * @param turn
   * @param territory
   * @return
   */
  public boolean checkValidPlace(UUID playerId, Turn turn,
      Territory territory) {
    if (turn.getBeginning() && turn.getPlayerId() == playerId
        && !territory.occuppied()) {
      return true;
    }
    return false;
  }
}
