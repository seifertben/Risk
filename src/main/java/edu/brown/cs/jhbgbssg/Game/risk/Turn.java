package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.UUID;

/**
 * Models a RiskPlayer turn.
 *
 * @author Ben
 *
 */
public class Turn {

  private TurnPhase phase = TurnPhase.BEGIN;

  private UUID playerId;
  private RiskPlayer player;

  /**
   * Indicates the phase of the turn.
   */
  public Turn() {

  }

  public void changePhase(TurnPhase newPhase) {
    phase = newPhase;
    // call referee
  }

  public TurnPhase getPhase() {
    return phase;
  }

  public void setTurnId(UUID id) {
    playerId = id;
    changePhase(TurnPhase.BEGIN);
  }

  public UUID getPlayerId() {
    return playerId;
  }

  public RiskPlayer getPlayer() {
    return player;
  }

}
