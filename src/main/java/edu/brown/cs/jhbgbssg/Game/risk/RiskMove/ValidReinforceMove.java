package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.Collection;
import java.util.Map;
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

  public boolean validReinforceMove(ReinforceMove move) {
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    Map<TerritoryEnum, Integer> reinforced = move.getReinforcedTerritories();
    Set<TerritoryEnum> terrs = reinforced.keySet();
    if (!territories.containsAll(terrs)) {
      return false;
    }
    int added = 0;
    Collection<Integer> values = reinforced.values();
    for (int val : values) {
      if (val <= 0) {
        return false;
      }
      added += val;
    }
    return added == numberReinforce;
  }
}
