package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidDieDefendMove implements Move {
  private UUID playerId;
  private int maxNumberDie;

  public ValidDieDefendMove(UUID playerId, TerritoryEnum territory,
      int maxNumberDie) {
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

  public boolean validateDefendMove(DefendMove move) {
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    int die = move.getDieRolled();
    if (1 > die || die > maxNumberDie) {
      return false;
    }
    return true;
  }
}
