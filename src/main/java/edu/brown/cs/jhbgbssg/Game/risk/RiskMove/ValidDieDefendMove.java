package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

public class ValidDieDefendMove implements Move {
  private UUID playerId;
  private int maxNumberDie;

  public ValidDieDefendMove(UUID playerId, int maxNumberDie) {
    this.playerId = playerId;
    this.maxNumberDie = maxNumberDie;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_DEFEND_DIE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public int getMaxNumberDie() {
    return maxNumberDie;
  }
}
