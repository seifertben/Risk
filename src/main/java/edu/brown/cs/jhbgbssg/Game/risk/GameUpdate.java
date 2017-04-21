package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.Move;
import edu.brown.cs.jhbgbssh.tuple.Pair;

/**
 * GameUpdate.
 *
 * @author sarahgilmore
 */
public class GameUpdate {
  private UUID currPlayer;
  private UUID lostGame;
  private UUID wonGame;
  private Move prevMove;
  private Move availableMoves;
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
  public Move getPrevMove() {
    return this.prevMove;
  }

  protected void setValidMoves(Move validMoves, Move previousMove,
      boolean error) {
    this.currPlayer = validMoves.getMovePlayer();
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
  public Move getValidMoves() {
    return this.availableMoves;
  }

  protected void setWonGame(UUID wonGame) {
    if (this.wonGame != null) {
      throw new IllegalArgumentException("ERROR: game already won");
    }
    this.wonGame = wonGame;
  }

  protected void setLostGame(UUID lostGame) {
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

  /**
   * Returns the id of the current player in control of the game.
   *
   * @return unique UUID
   */
  public UUID getCurrentPlayer() {
    return this.currPlayer;
  }
}
