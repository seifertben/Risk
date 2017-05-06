package edu.brown.cs.jhbgbssg.Game;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.riskaction.Action;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAction;
import edu.brown.cs.jhbgbssg.tuple.Pair;

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
  private Pair<UUID, MoveType> skipMove = null;

  /**
   * Constructor of GameUpdate.
   */
  public GameUpdate() {
  }

  /**
   * Sets the card value to hand out and the player to hand out the card.
   *
   * @param playerId - player to hand the card out to
   * @param card - card value to hand out
   * @param isCardsLeft - a boolean indicating if there are cards left in the
   *          deck.
   * @return a boolean indicating if the card field was set
   * @throws IllegalArgumentException if the playerId is null
   */
  public boolean setCardToHandOut(UUID playerId, int card, boolean isCardsLeft)
      throws IllegalArgumentException {
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
   * @throws IllegalArgumentException if the skip move is not null
   */
  public void setValidMoves(ValidAction validMoves, Action previousMove)
      throws IllegalArgumentException {
    if (validMoves != null) {
      assert (validMoves.actionAvailable());
    }
    if (previousMove != null) {
      assert (previousMove.isActionExecuted());
    }
    if (skipMove != null && previousMove != null) {
      throw new IllegalArgumentException(
          "ERROR: ski pmove must be null to set a previous move");
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
   * Sets the wonGame field to the player id given if the field has not been set
   * yet.
   *
   * @param winner - id of the player who won the game
   * @return boolean indicating if the winner field was set
   * @throws IllegalArgumentException - if the input is null
   */
  public boolean setWonGame(UUID winner) throws IllegalArgumentException {
    if (winner == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (this.wonGame != null) {
      return false;
    }
    this.wonGame = winner;
    return true;
  }

  /**
   * Sets the lostGame field to the player id given if the field has not been
   * set yet.
   *
   * @param loser - player that lost the game
   * @return boolean indicating if the field was set
   */
  public boolean setLostGame(UUID loser) {
    if (loser == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (this.lostGame != null) {
      return false;
    }
    this.lostGame = loser;
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
   * Sets the error player field if the field has not been set yet.
   *
   * @param player - player who caused an error
   * @return boolean indicating if the field was set
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

  /**
   * Sets the player who skipped and the the move type that was skipped.
   *
   * @param move - type of move skipped
   * @param skipPlayer - player who skipped
   * @throws IllegalArgumentException if the previous move is not null
   */
  public void setSkipMoveType(MoveType move, UUID skipPlayer)
      throws IllegalArgumentException {
    if (move == null || skipPlayer == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (prevMove != null) {
      throw new IllegalArgumentException(
          "ERROR: previous move must be null to set a skip move");
    }
    this.skipMove = new Pair<>(skipPlayer, move);
  }

  /**
   * Returns the skip pair.
   *
   * @return skipMove - player id who skipped and move type skipped
   */
  public Pair<UUID, MoveType> getSkipMove() {
    return skipMove;
  }
}
