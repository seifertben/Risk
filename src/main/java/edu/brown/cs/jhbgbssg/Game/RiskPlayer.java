package edu.brown.cs.jhbgbssg.Game;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class RiskPlayer implements Player {
  private int playerId;
  private Set<TerritoryEnum> territories;
  private Multiset<Integer> cards;

  public RiskPlayer(int playerId) {
    this.playerId = playerId;
    territories = new HashSet<>();
  }

  @Override
  public int getPlayerId() {
    return playerId;
  }

  public boolean hasCard(int cardValue) {
    return cards.contains(cardValue);
  }

  public boolean removeCard(int cardValue) {
    return cards.remove(cardValue);
  }

  public boolean addCard(int cardValue) {
    return cards.add(cardValue);
  }

  public boolean hasTerritory(TerritoryEnum territory) {
    return territories.contains(territory);
  }

  public boolean conqueredTerritory(TerritoryEnum conqueredTerritory) {
    return territories.add(conqueredTerritory);
  }

  public boolean lostTerritory(TerritoryEnum lostTerritory) {
    return territories.remove(lostTerritory);
  }
}
