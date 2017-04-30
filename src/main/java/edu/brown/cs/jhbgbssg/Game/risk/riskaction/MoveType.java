package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

/**
 * MoveType represent the different type of moves a player can make and each
 * move is assigned a specific MoveType as an id.
 *
 * @author sarahgilmore
 *
 */
public enum MoveType {
  SETUP, SETUP_REINFORCE, TURN_IN_CARD, REINFORCE, CHOOSE_ATTACK_DIE,
  CHOOSE_DEFEND_DIE, CLAIM_TERRITORY, MOVE_TROOPS, SKIP;
}
