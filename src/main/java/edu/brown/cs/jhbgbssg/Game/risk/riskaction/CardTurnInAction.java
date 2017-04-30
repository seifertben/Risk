package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.Collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 * Represents a Card Turn in move.
 *
 * @author sarahgilmore
 *
 */

public class CardTurnInAction implements Action {
  private Multiset<Integer> cards;
  private RiskPlayer player;
  private boolean actionExecuted;

  /**
   * Constructor for CardTurnInAction. It takes in a collection of cards to turn
   * in and the player turning in the cards.
   *
   * @param cards - cards to turn in
   * @param player - player
   * @throws IllegalArgumentException if the input is null
   */
  public CardTurnInAction(Collection<Integer> cards, RiskPlayer player)
      throws IllegalArgumentException {
    if (cards == null || player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.cards = HashMultiset.create(cards);
    this.player = player;
    actionExecuted = false;
  }

  /**
   * Returns the multiset of cards to turn in.
   *
   * @return multiset of integer
   */
  public Multiset<Integer> getCards() {
    return cards;
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
