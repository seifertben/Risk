package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ChooseAttackMove implements Move {
  private UUID playerId;
  private Map<TerritoryEnum, Integer> chooseDie;
  private Multimap<TerritoryEnum, TerritoryEnum> whoToAttack;

  public ChooseAttackMove(UUID playerId, Map<TerritoryEnum, Integer> chooseDie,
      Multimap<TerritoryEnum, TerritoryEnum> whoToAttack) {
    this.playerId = playerId;
    this.chooseDie = chooseDie;
    this.whoToAttack = whoToAttack;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_ATTACK_DIE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public Map<TerritoryEnum, Integer> getAttackableTerritories() {
    return chooseDie;
  }

  public Collection<Entry<TerritoryEnum, TerritoryEnum>> whoToAttack() {
    return whoToAttack.entries();
  }
}