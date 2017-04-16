package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class RiskGuiRestriction {

  public RiskGuiRestriction(UUID playerId) {
    this.playerId = playerId;
  }

  public void setCanAttack(Map<TerritoryEnum, Integer> canAttackFrom,
      Multimap<TerritoryEnum, TerritoryEnum> canAttackTo) {
    if (canAttackFrom.size() != 0 && canAttackTo.size() != 0) {
      this.canAttackFrom = canAttackFrom;
      this.canAttackTo = canAttackTo;
      canAttack = true;
    }
  }

  public void setCanReinforce(Set<TerritoryEnum> reinforceTerritories,
      int reinforceNumber) {
    if (reinforceTerritories.size() != 0 && reinforceNumber > 0) {
      this.reinforceTerritories = reinforceTerritories;
      canReinforce = true;
      this.reinforceNumber = reinforceNumber;
    }
  }

  public boolean setMoveTroopsTo(
      Multimap<TerritoryEnum, TerritoryEnum> movement,
      Map<TerritoryEnum, Integer> numberTroopsCanMove) {
    if (movement.size() != 0 && numberTroopsCanMove.size() != 0) {
      canMove = true;
      this.movement = movement;
      this.numberTroopsCanMove = numberTroopsCanMove;
    }

  }

  public RiskGuiRestriction() {

  }

  public void setCanAttackFromTerritories(Set<TerritoryEnum> territories) {
    canAttackFrom = territories;
  }

  public void set

}
