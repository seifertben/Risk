package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidCardMove implements Move {
  private UUID playerId;
  private Multiset<Integer> cards;
  private Set<TerritoryEnum> terrs;

  public ValidCardMove(UUID playerId, Multiset<Integer> cards,
      Set<TerritoryEnum> terrs) {
    this.playerId = playerId;
    this.cards = cards;
    this.terrs = terrs;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.TURN_IN_CARD;
  }

  public Multiset<Integer> getCards() {
    return cards;
  }

  public Set<TerritoryEnum> getTerritories() {
    return terrs;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public boolean validateCardMove(CardTurnInMove move) {
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    int card = move.getCard();
    if (!cards.contains(card)) {
      return false;
    }
    Map<TerritoryEnum, Integer> reinforced = move.getTerritoriesReinforced();
    if (!terrs.containsAll(reinforced.keySet())) {
      return false;
    }
    Collection<Integer> values = reinforced.values();
    int added = 0;
    for (int val : values) {
      if (val <= 0) {
        return false;
      }
      added += val;
    }
    return added == card;
  }
}
