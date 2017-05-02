package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.riskaction.Action;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAction;
import edu.brown.cs.jhbgbssh.tuple.Pair;

/**
 * GameUpdate.
 *
 * @author sarahgilmore
 */
public class GameUpdate {
  private UUID lostGame;
  private UUID wonGame;
  private Action prevMove;
  private ValidAction availableMoves;
  private boolean errors;
  private Pair<UUID, Integer> handout = null;
  private boolean cardsLeft = true;

  /**
   * Constructor of GameUpdate.
   */
  public GameUpdate() {
  }

  protected void setCardToHandOut(UUID playerId, int card,
      boolean isCardsLeft) {
    if (playerId == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (card <= 0 || card > 2) {
      throw new IllegalArgumentException("ERROR: illegal card value");
    }
    handout = new Pair<>(playerId, card);
    this.cardsLeft = isCardsLeft;
  }

  /**
   * Gets the id of the player and card value to hand out.
   *
   * @return pair of player id and card value
   */
  public Pair<UUID, Integer> getCardHandOut() {
    return handout;
  }

  /**
   * Returns true if there are cards left in the deck.
   *
   * @return true if cards are left; false otherwise
   */
  public boolean cardsLeft() {
    return cardsLeft;
  }

  /**
   * Returns the previous move executed.
   *
   * @return previous move
   */
  public Action getPrevMove() {
    return this.prevMove;
  }

  protected void setValidMoves(ValidAction validMoves, Action previousMove,
      boolean error) {
    this.availableMoves = validMoves;
    this.prevMove = previousMove;
    this.errors = error;
  }

  /**
   * Gets the move object representing the valid moves the current player can
   * make.
   *
   * @return valid move options
   */
  public ValidAction getValidMoves() {
    return this.availableMoves;
  }

  protected void setWonGame(UUID wonGame) throws IllegalArgumentException {
    if (this.wonGame != null) {
      throw new IllegalArgumentException("ERROR: game already won");
    } else if (wonGame == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.wonGame = wonGame;
  }

  protected void setLostGame(UUID lostGame) {
    if (lostGame == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (this.lostGame != null) {
      throw new IllegalArgumentException("ERROR: already set a loser");
    }
    this.lostGame = lostGame;
  }

  /**
   * Returns the UUID of the loser.
   *
   * @return id of the loser
   */
  public UUID getLoser() {
    return lostGame;
  }

  /**
   * Returns the UUID of the winner.
   *
   * @return id of the winner.
   */
  public UUID getGameWon() {
    return wonGame;
  }

  /**
   * Returns true if an error occurred.
   *
   * @return true if there was an error; false otherwise
   */
  public boolean getErrors() {
    return this.errors;
  }
}
