package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Map;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.Move;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssh.tuple.Pair;

public class GameUpdate {
  private UUID playerId;
  private UUID lostGame;
  private UUID wonGame;
  private Move prevMove;
  private Move availableMoves;
  private Map<UUID, Integer> errors;
  private Pair<UUID, Integer> handout = null;
  private boolean cardsLeft = true;
  private Pair<UUID, TerritoryEnum> lostTerritory;
  private Pair<UUID, TerritoryEnum> gainedTerritory;
  private boolean didLoseTerritory = false;

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

  protected void setPrevMove(Move currMove) {
    this.prevMove = currMove;
  }

  public Move getPrevMove() {
    return this.prevMove;
  }

  protected void setValidMoves(Move availableMoves) {
    this.playerId = availableMoves.getMovePlayer();
    this.availableMoves = availableMoves;
  }

  public Move getValidMoves() {
    return this.availableMoves;
  }

  // protected void setLostTerritory(UUID player, TerritoryEnum terr,
  // boolean didLoseGame) {
  // lostTerritory = new Pair<>(player, terr);
  // didLoseTerritory = true;
  // if (didLoseGame) {
  // lostGame = player;
  // }
  // }
  //
  // protected void setGainedTerritory(UUID player, TerritoryEnum terr,
  // boolean didWinGame) {
  // gainedTerritory = new Pair<>(player, terr);
  // if (didWinGame) {
  // wonGame = player;
  // }
  // }

  public Pair<UUID, TerritoryEnum> getLostTerritory() {
    return lostTerritory;
  }

  public Pair<UUID, TerritoryEnum> getGainedTerritory() {
    return gainedTerritory;
  }

  public boolean didLoseTerritory() {
    return didLoseTerritory;
  }

  protected void setWonGame(UUID wonGame) {
    if (this.wonGame != null) {
      throw new IllegalArgumentException("ERROR: game already won");
    }
    this.wonGame = wonGame;
  }

  public UUID getLoser() {
    return lostGame;
  }

  public UUID getGameWon() {
    return wonGame;
  }

  protected void setError(Map<UUID, Integer> errors) {
    this.errors = errors;
  }

  public Map<UUID, Integer> getErrors() {
    return this.errors;
  }

  public UUID getCurrentPlayer() {
    return this.playerId;
  }
}
