package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.Move;
import edu.brown.cs.jhbgbssh.tuple.Pair;

public class GameUpdate {
  private UUID currPlayer;
  private UUID lostGame;
  private UUID wonGame;
  private Move prevMove;
  private Move availableMoves;
  private boolean errors;
  private Pair<UUID, Integer> handout = null;
  private boolean cardsLeft = true;

  public GameUpdate() {
  }

  protected void setCardToHandOut(UUID playerId, int card, boolean cardsLeft) {
    handout = new Pair<>(playerId, card);
    this.cardsLeft = cardsLeft;
  }

  public Pair<UUID, Integer> getCardHandOut() {
    return handout;
  }

  public boolean cardsLeft() {
    return cardsLeft;
  }

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

  public UUID getLoser() {
    return lostGame;
  }

  public UUID getGameWon() {
    return wonGame;
  }

  public boolean getErrors() {
    return this.errors;
  }

  public UUID getCurrentPlayer() {
    return this.currPlayer;
  }
}
