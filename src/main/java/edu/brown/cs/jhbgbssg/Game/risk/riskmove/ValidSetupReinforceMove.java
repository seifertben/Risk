package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

public class ValidSetupReinforceMove implements ValidAction {

  private UUID playerId;
  int maxReinforce;

  public ValidSetupReinforceMove(RiskPlayer player, UUID playerId) {
    maxReinforce = player.getInitialReinforcements();
  }

  public boolean validSetupReinforceMove(SetupReinforceMove move) {
    if (move.getToReinforce() > maxReinforce) {
      return false;
    }
    return true;
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
