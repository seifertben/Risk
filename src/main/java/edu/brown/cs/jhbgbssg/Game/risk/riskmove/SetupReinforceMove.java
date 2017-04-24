package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class SetupReinforceMove implements Move {
  private UUID playerId;
  private TerritoryEnum territorySelected;
  private int toReinforce;

  public SetupReinforceMove(UUID playerId, TerritoryEnum selected,
      int toReinforce) {
    this.toReinforce = toReinforce;
    this.playerId = playerId;
    territorySelected = selected;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.SETUP_REINFORCE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

}
