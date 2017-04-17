package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class AttackMove implements Move {
  private UUID playerId;
  private TerritoryEnum attackFrom;
  private TerritoryEnum attackTo;
  private int dieRolled;
  private Multiset<Integer> dieRolledResult;
  private int troopsLost;
  private boolean won;
  private boolean attackDone = false;

  public AttackMove(UUID playerId, TerritoryEnum attackFrom,
      TerritoryEnum attackTo, int dieRolled) {
    this.playerId = playerId;
    this.attackFrom = attackFrom;
    this.attackTo = attackTo;
    this.dieRolled = dieRolled;
    this.troopsLost = 0;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_ATTACK_DIE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public TerritoryEnum getAttackFrom() {
    return attackFrom;
  }

  public TerritoryEnum getAttackTo() {
    return attackTo;
  }

  public int getDieRolled() {
    return dieRolled;
  }

  public int getTroopsLost() {
    return troopsLost;
  }

  public void setDieResult(Multiset<Integer> result) {
    this.dieRolledResult = result;
  }

  public void setTroopsLost(int lost) {
    troopsLost = lost;
    attackDone = true;
  }

  public void setWonTerritory() {
    won = true;
  }

  public boolean getWonTerritory() {
    return won;
  }

  public boolean attackDone() {
    return attackDone;
  }

  public Multiset<Integer> getDieResults() {
    return dieRolledResult;
  }
}
