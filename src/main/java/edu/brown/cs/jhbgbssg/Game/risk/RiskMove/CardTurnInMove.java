package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import com.google.common.collect.Multiset;

public class CardTurnInMove implements Move {
  private UUID playerId;
  private Multiset<Integer> cards;

  public CardTurnInMove(UUID playerId, Multiset<Integer> cards) {
    this.playerId = playerId;
    this.cards = cards;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.TURN_IN_CARD;
  }

  public Multiset<Integer> getCards() {
    return cards;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

}
