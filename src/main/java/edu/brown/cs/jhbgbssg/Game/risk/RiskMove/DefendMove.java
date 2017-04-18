package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class DefendMove implements Move {
  private UUID playerId;
  private TerritoryEnum defended;
  private TerritoryEnum attacking;
  private int defendDie;

  public DefendMove(UUID playerId, TerritoryEnum attacking,
      TerritoryEnum defended, int defendDie) {
    this.playerId = playerId;
    this.defended = defended;
    this.attacking = attacking;
    this.defendDie = defendDie;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_DEFEND_DIE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public int getDieRolled() {
    return defendDie;
  }

  public TerritoryEnum getDefendedTerritory() {
    return defended;
  }

  public TerritoryEnum getAttackingTerritory() {
    return attacking;
  }
}
