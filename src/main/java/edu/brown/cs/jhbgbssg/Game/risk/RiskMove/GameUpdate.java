package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GameUpdate {
  private UUID playerId;
  private Set<UUID> newLoss;
  private Set<UUID> lostGame;
  private UUID wonGame;
  private Move currMove;
  private Move availableMoves;
  private Map<UUID, Integer> errors;

  public GameUpdate() {
    lostGame = new HashSet<>();
    availableMoves = null;
  }

  public void setCurrMove(Move currMove) {
    this.currMove = currMove;
  }

  public Move getCurrentMove() {
    return this.currMove;
  }

  public void setValidMoves(Move availableMoves) {
    this.playerId = availableMoves.getMovePlayer();
    this.availableMoves = availableMoves;
  }

  public Move getValidMoves() {
    return this.availableMoves;
  }

  public void setWonGame(UUID wonGame) {
    if (this.wonGame != null) {
      throw new IllegalArgumentException("ERROR: game already won");
    }
    assert (!lostGame.contains(wonGame));
    this.wonGame = wonGame;
  }

  public void setLostGame(UUID playerLostGame) {
    lostGame.add(playerLostGame);
    newLoss.add(playerLostGame);
  }

  public Set<UUID> getNewLosses() {
    Set<UUID> losses = new HashSet<>(newLoss);
    newLoss.clear();
    return losses;
  }

  public UUID getGameWon() {
    return wonGame;
  }

  public void setError(Map<UUID, Integer> errors) {
    this.errors = errors;
  }

  public Map<UUID, Integer> getErrors() {
    return this.errors;
  }

  public UUID getCurrentPlayer() {
    return this.playerId;
  }
}
