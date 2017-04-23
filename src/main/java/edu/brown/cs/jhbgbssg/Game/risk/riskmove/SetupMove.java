package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class SetupMove implements Move {
  private UUID playerId;
  private TerritoryEnum territorySelected;

  public SetupMove(UUID playerId, TerritoryEnum selected)
      throws IllegalArgumentException {
    if (playerId == null || territorySelected == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.playerId = playerId;
    this.territorySelected = selected;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.SETUP;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public TerritoryEnum getSelectedTerritory() {
    return territorySelected;
  }
}
