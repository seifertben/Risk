package edu.brown.cs.jhbgbssg.Game.risk;

/**
 * Enum to represent the different types of messages sent to the frontend by the
 * backend or to the backend by the frontend.
 *
 * @author sarahgilmore
 *
 */
public enum RiskMessageType {

  CONNECT, REMOVE, START, JOIN, CREATE, CHANGE, DESTROY, WINNER, LOSER,
  HANDOUT_CARD, NO_CARDS_LEFT, PREVIOUS_ACTION, VALID_ACTIONS, ERROR, MOVE,
  MESSAGE;

}
