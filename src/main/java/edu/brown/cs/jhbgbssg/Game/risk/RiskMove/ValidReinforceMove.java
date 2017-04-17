package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidReinforceMove implements Move {
  private UUID playerId;
  private Set<TerritoryEnum> territories;
  private int numberReinforce;

  public ValidReinforceMove(UUID playerId, Set<TerritoryEnum> territories,
      int numberReinforce) {
    this.playerId = playerId;
    this.territories = territories;
    this.numberReinforce = numberReinforce;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.REINFORCE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public Set<TerritoryEnum> getTerritories() {
    return this.territories;
  }

  public int getNumberToReinforce() {
    return numberReinforce;
  }
}
