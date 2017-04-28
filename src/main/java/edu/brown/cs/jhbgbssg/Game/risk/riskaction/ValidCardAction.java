package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 *
 * @author sarahgilmore
 *
 */
public class ValidCardAction implements ValidAction {
  private RiskPlayer player;
  private Multiset<Integer> cards;
  private boolean canTurnInCards = true;

  /**
   * Constructor for ValidCardMove. Takes in the player id, the set of cards
   * owned by the player and the territories owned.
   *
   * @param player - player
   */
  public ValidCardAction(RiskPlayer player) {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    cards = player.getCards();
    if (cards.size() == 0) {
      canTurnInCards = false;
    }
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.TURN_IN_CARD;
  }

  /**
   * Returns whether or not the player has any cards to turn in.
   *
   * @return true if move is admissible; false otherwise
   */
  public boolean canTurnInCards() {
    return canTurnInCards;
  }

  /**
   * Returns multiset of cards owned by the player.
   *
   * @return multiset of integers
   */
  public Multiset<Integer> getCards() {
    return cards;
  }

  /**
   * Determines if a given CardTurnInMove is validate based on the bounds
   * defined by a ValidCardMove object.
   *
   * @param move - move to check validity of
   * @return true if the move is valid; false otherwise
   */
  public boolean validateCardMove(CardTurnInAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    RiskPlayer currPlayer = move.getMovePlayer();
    List<Integer> card = move.getCards();
    if (!currPlayer.equals(player)) {
      return false;
    } else if (!cards.containsAll(card)) {
      return false;
    }
    return true;
  }

  @Override
  public boolean actionAvailable() {
    return canTurnInCards;
  }

  @Override
  public UUID getMovePlayer() {
    return player.getPlayerId();
  }
}
