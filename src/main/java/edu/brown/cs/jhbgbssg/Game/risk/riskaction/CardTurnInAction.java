package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 * Represents a Card Turn in move.
 *
 * @author sarahgilmore
 *
 */

public class CardTurnInAction implements Action {
  private List<Integer> cards;
  private RiskPlayer player;
  private boolean actionExecuted;

  public CardTurnInAction(Collection<Integer> cards, RiskPlayer player) {
    if (cards == null || player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.cards = new ArrayList<>(cards);
    this.player = player;
    actionExecuted = false;
  }

  public List<Integer> getCards() {
    return Collections.unmodifiableList(cards);
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.TURN_IN_CARD;
  }

  @Override
  public RiskPlayer getMovePlayer() {
    return player;
  }

  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      for (Integer card : cards) {
        player.removeCard(card);
      }
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
