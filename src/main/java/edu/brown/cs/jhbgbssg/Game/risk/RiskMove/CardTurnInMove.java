package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.Map;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class CardTurnInMove implements Move {
  private UUID playerId;
  private Integer card;
  private Map<TerritoryEnum, Integer> terr;

  public CardTurnInMove(UUID playerId, int card,
      Map<TerritoryEnum, Integer> terr) {
    this.playerId = playerId;
    this.card = card;
    this.terr = terr;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.TURN_IN_CARD;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public int getCard() {
    return card;
  }

  public Map<TerritoryEnum, Integer> getTerritoriesReinforced() {
    return terr;
  }
}
