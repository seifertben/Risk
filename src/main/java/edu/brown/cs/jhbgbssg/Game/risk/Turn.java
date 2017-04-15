package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.UUID;

/**
 * Models a RiskPlayer turn.
 *
 * @author Ben
 *
 */
public class Turn {

  private boolean reinforcementPhase;

  private boolean attackingPhase;

  private boolean movementPhase;

  private boolean beginningPhase;

  private UUID playerId;

  /**
   * Indicates the phase of the turn.
   */
  public Turn() {

  }

  public void setReinforcement() {
    reinforcementPhase = true;
    attackingPhase = false;
    movementPhase = false;
    beginningPhase = false;
  }

  public void setAttacking() {
    attackingPhase = true;
    reinforcementPhase = false;
    movementPhase = false;
    beginningPhase = false;
  }

  public void setMovement() {
    movementPhase = true;
    reinforcementPhase = false;
    attackingPhase = false;
    beginningPhase = false;
  }

  public void setBeginning() {
    beginningPhase = true;
    movementPhase = false;
    reinforcementPhase = false;
    attackingPhase = false;
  }

  public void setTurnId(UUID id) {
    playerId = id;
  }

  public boolean getReinforcement() {
    return reinforcementPhase;
  }

  public boolean getAttacking() {
    return attackingPhase;
  }

  public boolean getMovement() {
    return movementPhase;
  }

  public UUID getPlayerId() {
    return playerId;
  }

  public boolean getBeginning() {
    return beginningPhase;
  }
}
