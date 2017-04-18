package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskMove.MoveType;

/**
 * Models a RiskPlayer turn.
 *
 * @author Ben
 *
 */
public class Turn {

  private MoveType phase = MoveType.REINFORCE;

  private UUID playerId;
  private RiskPlayer player;

  /**
   * Indicates the phase of the turn.
   */
  public Turn() {

  }

  public void changePhase(MoveType newPhase) {
    phase = newPhase;
    // call referee
  }

  public MoveType getPhase() {
    return phase;
  }

  public void setTurnId(UUID id) {
    playerId = id;
    changePhase(MoveType.REINFORCE);
  }

  public UUID getPlayerId() {
    return playerId;
  }

  public RiskPlayer getPlayer() {
    return player;
  }

}
