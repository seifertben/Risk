package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;

public class ValidSetupReinforceMove implements ValidAction {

  private UUID playerId;

  public ValidSetupReinforceMove(UUID playerId, RiskBoard board) {

  }

  public boolean validSetupReinforceMove(SetupReinforceMove move) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.SETUP_REINFORCE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  @Override
  public boolean actionAvailable() {
    return false;
  }

}
