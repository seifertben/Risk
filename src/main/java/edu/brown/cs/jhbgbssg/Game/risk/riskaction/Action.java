package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 *
 * @author sarahgilmore
 *
 */
public interface Action {

  /**
   * Gets the move type.
   *
   * @return move type
   */
  MoveType getMoveType();

  /**
   * Gets the id of the player undertaking the move.
   *
   * @return player id
   */
  RiskPlayer getMovePlayer();

  /**
   * Returns true if the action has been executed; false otherwise.
   *
   * @return true if action has been executed; false other
   */
  boolean isActionExecuted();

  /**
   * Executes the action and returns true if it was executed; false otherwise.
   * An action can be executed at most once.
   *
   * @return the action was executed; false otherwise
   */
  boolean executeAction();
}
