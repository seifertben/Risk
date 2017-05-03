package edu.brown.cs.jhbgbssg.Game;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.riskaction.Action;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAction;
import edu.brown.cs.jhbgbssh.tuple.Pair;

/**
 * Represents a GameUpdate object for Risk. It stores the next valid action
 * object to send to the player whose turn it is and the previous action to send
 * to all players in the game. The update also stores a player who caused an
 * error, if a player won the game or if a player lost the game.
 *
 * @author sarahgilmore
 */
public class GameUpdate {
  private UUID lostGame;
  private UUID wonGame;
  private Action prevMove;
  private ValidAction availableMoves;
  private boolean error;
  private Pair<UUID, Integer> handout = null;
  private boolean cardsLeft = true;
  private UUID errorPlayer;

  /**
   * Constructor of GameUpdate.
   */
  public GameUpdate() {
  }

  public boolean setCardToHandOut(UUID playerId, int card,
      boolean isCardsLeft) {
    if (playerId == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (card <= 0 || card > 2) {
      throw new IllegalArgumentException("ERROR: illegal card value");
    } else if (handout != null) {
      return false;
    }
    handout = new Pair<>(playerId, card);
    this.cardsLeft = isCardsLeft;
    return true;
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

  /**
   * Sets the valid moves field and the previous move field.
   *
   * @param validMoves - valid moves
   * @param previousMove - previous move
   */
  public void setValidMoves(ValidAction validMoves, Action previousMove) {
    if (validMoves != null) {
      assert (validMoves.actionAvailable());
    }
    if (previousMove != null) {
      assert (previousMove.isActionExecuted());
    }
    this.availableMoves = validMoves;
    this.prevMove = previousMove;
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

  /**
   * Sets the wonGame field to the player id given.
   *
   * @param wonGame - id of the player who won the game
   * @throws IllegalArgumentException - if the input is null or if the field has
   *           already been set.
   */
  public boolean setWonGame(UUID wonGame) throws IllegalArgumentException {
    if (wonGame == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (this.wonGame != null) {
      return false;
    }
    this.wonGame = wonGame;
    return true;
  }

  public boolean setLostGame(UUID lostGame) {
    if (lostGame == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (this.lostGame != null) {
      return false;
    }
    this.lostGame = lostGame;
    return true;
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
    return this.error;
  }

  /**
   * Sets the error player field.
   *
   * @param player - player who caused an error
   * @throws IllegalArgumentException - null player
   */
  public boolean setError(UUID player) throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (errorPlayer != null) {
      return false;
    }
    this.error = true;
    this.errorPlayer = player;
    return true;
  }

  /**
   * Returns the id of the player who caused the error.
   *
   * @return player id
   */
  public UUID getErrorPlayer() {
    return this.errorPlayer;
  }
}
