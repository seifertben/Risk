package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;

import edu.brown.cs.jhbgbssg.RiskWorld.ContinentEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.Africa;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.Asia;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.Australia;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentInterface;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.Europe;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.NorthAmerica;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.SouthAmerica;

public class RiskBoard {
  private Graph<TerritoryEnum> board;
  private Map<TerritoryEnum, Territory> territoryMap;
  private Map<ContinentEnum, ContinentInterface> continentMap;

  public RiskBoard() {
    this.buildBoard();
    this.setUpContinents();
    this.setUpTerritories();
  }

  private void buildBoard() {
    MutableGraph<TerritoryEnum> builder = GraphBuilder.undirected().build();
    builder.putEdge(TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA);
    builder.putEdge(TerritoryEnum.ALASKA, TerritoryEnum.NORTHWEST_TERRITORY);
    builder.putEdge(TerritoryEnum.ALBERTA, TerritoryEnum.WESTERN_US);
    builder.putEdge(TerritoryEnum.ALBERTA, TerritoryEnum.NORTHWEST_TERRITORY);
    builder.putEdge(TerritoryEnum.NORTHWEST_TERRITORY, TerritoryEnum.ONTARIO);
    builder.putEdge(TerritoryEnum.NORTHWEST_TERRITORY, TerritoryEnum.GREENLAND);
    builder.putEdge(TerritoryEnum.WESTERN_US, TerritoryEnum.ONTARIO);
    builder.putEdge(TerritoryEnum.WESTERN_US, TerritoryEnum.EASTERN_US);
    builder.putEdge(TerritoryEnum.WESTERN_US, TerritoryEnum.CENTRAL_AMERICA);
    builder.putEdge(TerritoryEnum.ONTARIO, TerritoryEnum.EASTERN_US);
    builder.putEdge(TerritoryEnum.ONTARIO, TerritoryEnum.QUEBEC);
    builder.putEdge(TerritoryEnum.ONTARIO, TerritoryEnum.GREENLAND);
    builder.putEdge(TerritoryEnum.QUEBEC, TerritoryEnum.GREENLAND);
    builder.putEdge(TerritoryEnum.CENTRAL_AMERICA, TerritoryEnum.VENEZUAELA);
    builder.putEdge(TerritoryEnum.VENEZUAELA, TerritoryEnum.PERU);
    builder.putEdge(TerritoryEnum.VENEZUAELA, TerritoryEnum.BRAZIL);
    builder.putEdge(TerritoryEnum.PERU, TerritoryEnum.BRAZIL);
    builder.putEdge(TerritoryEnum.PERU, TerritoryEnum.ARGENTINIA);
    builder.putEdge(TerritoryEnum.BRAZIL, TerritoryEnum.NORTH_AFRICA);
    builder.putEdge(TerritoryEnum.NORTH_AFRICA, TerritoryEnum.EGYPT);
    builder.putEdge(TerritoryEnum.NORTH_AFRICA, TerritoryEnum.EAST_AFRICA);
    builder.putEdge(TerritoryEnum.NORTH_AFRICA, TerritoryEnum.CENTRAL_AFRICA);
    builder.putEdge(TerritoryEnum.NORTH_AFRICA, TerritoryEnum.WESTERN_EUROPE);
    builder.putEdge(TerritoryEnum.NORTH_AFRICA, TerritoryEnum.SOUTHERN_EUROPE);
    builder.putEdge(TerritoryEnum.EGYPT, TerritoryEnum.SOUTHERN_EUROPE);
    builder.putEdge(TerritoryEnum.EGYPT, TerritoryEnum.EAST_AFRICA);
    builder.putEdge(TerritoryEnum.EGYPT, TerritoryEnum.MIDDLE_EAST);
    builder.putEdge(TerritoryEnum.EAST_AFRICA, TerritoryEnum.MIDDLE_EAST);
    builder.putEdge(TerritoryEnum.EAST_AFRICA, TerritoryEnum.CENTRAL_AFRICA);
    builder.putEdge(TerritoryEnum.EAST_AFRICA, TerritoryEnum.SOUTH_AFRICA);
    builder.putEdge(TerritoryEnum.EAST_AFRICA, TerritoryEnum.MADAGASCAR);
    builder.putEdge(TerritoryEnum.CENTRAL_AFRICA, TerritoryEnum.SOUTH_AFRICA);
    builder.putEdge(TerritoryEnum.SOUTH_AFRICA, TerritoryEnum.MADAGASCAR);
    builder.putEdge(TerritoryEnum.GREENLAND, TerritoryEnum.ICELAND);
    builder.putEdge(TerritoryEnum.ICELAND, TerritoryEnum.GREAT_BRITIAN);
    builder.putEdge(TerritoryEnum.ICELAND, TerritoryEnum.SCANDINAVIA);
    builder.putEdge(TerritoryEnum.GREAT_BRITIAN, TerritoryEnum.WESTERN_EUROPE);
    builder.putEdge(TerritoryEnum.GREAT_BRITIAN, TerritoryEnum.NORTHERN_EUROPE);
    builder.putEdge(TerritoryEnum.GREAT_BRITIAN, TerritoryEnum.SCANDINAVIA);
    builder.putEdge(TerritoryEnum.NORTHERN_EUROPE,
        TerritoryEnum.WESTERN_EUROPE);
    builder.putEdge(TerritoryEnum.NORTHERN_EUROPE, TerritoryEnum.SCANDINAVIA);
    builder.putEdge(TerritoryEnum.NORTHERN_EUROPE,
        TerritoryEnum.SOUTHERN_EUROPE);
    builder.putEdge(TerritoryEnum.NORTHERN_EUROPE, TerritoryEnum.RUSSIA);
    builder.putEdge(TerritoryEnum.SOUTHERN_EUROPE, TerritoryEnum.RUSSIA);
    builder.putEdge(TerritoryEnum.SOUTHERN_EUROPE, TerritoryEnum.MIDDLE_EAST);
    builder.putEdge(TerritoryEnum.RUSSIA, TerritoryEnum.SCANDINAVIA);
    builder.putEdge(TerritoryEnum.RUSSIA, TerritoryEnum.MIDDLE_EAST);
    builder.putEdge(TerritoryEnum.RUSSIA, TerritoryEnum.AFGHANISTAN);
    builder.putEdge(TerritoryEnum.RUSSIA, TerritoryEnum.URAL);
    builder.putEdge(TerritoryEnum.MIDDLE_EAST, TerritoryEnum.AFGHANISTAN);
    builder.putEdge(TerritoryEnum.MIDDLE_EAST, TerritoryEnum.INDIA);
    builder.putEdge(TerritoryEnum.AFGHANISTAN, TerritoryEnum.URAL);
    builder.putEdge(TerritoryEnum.AFGHANISTAN, TerritoryEnum.CHINA);
    builder.putEdge(TerritoryEnum.AFGHANISTAN, TerritoryEnum.INDIA);
    builder.putEdge(TerritoryEnum.URAL, TerritoryEnum.SIBERIA);
    builder.putEdge(TerritoryEnum.URAL, TerritoryEnum.CHINA);
    builder.putEdge(TerritoryEnum.SIBERIA, TerritoryEnum.YAKUTSK);
    builder.putEdge(TerritoryEnum.SIBERIA, TerritoryEnum.IRKUTSK);
    builder.putEdge(TerritoryEnum.SIBERIA, TerritoryEnum.MONGOLIA);
    builder.putEdge(TerritoryEnum.SIBERIA, TerritoryEnum.CHINA);
    builder.putEdge(TerritoryEnum.CHINA, TerritoryEnum.INDIA);
    builder.putEdge(TerritoryEnum.CHINA, TerritoryEnum.SIAM);
    builder.putEdge(TerritoryEnum.CHINA, TerritoryEnum.MONGOLIA);
    builder.putEdge(TerritoryEnum.MONGOLIA, TerritoryEnum.IRKUTSK);
    builder.putEdge(TerritoryEnum.MONGOLIA, TerritoryEnum.KAMACHATKA);
    builder.putEdge(TerritoryEnum.MONGOLIA, TerritoryEnum.JAPAN);
    builder.putEdge(TerritoryEnum.IRKUTSK, TerritoryEnum.YAKUTSK);
    builder.putEdge(TerritoryEnum.IRKUTSK, TerritoryEnum.KAMACHATKA);
    builder.putEdge(TerritoryEnum.KAMACHATKA, TerritoryEnum.ALASKA);
    builder.putEdge(TerritoryEnum.SIAM, TerritoryEnum.INDIA);
    builder.putEdge(TerritoryEnum.SIAM, TerritoryEnum.INDONESIA);
    builder.putEdge(TerritoryEnum.INDONESIA, TerritoryEnum.NEW_GUINEA);
    builder.putEdge(TerritoryEnum.NEW_GUINEA, TerritoryEnum.WESTERN_AUSTRALIA);
    builder.putEdge(TerritoryEnum.NEW_GUINEA, TerritoryEnum.EASTERN_AUSTRALIA);
    builder.putEdge(TerritoryEnum.WESTERN_AUSTRALIA,
        TerritoryEnum.EASTERN_AUSTRALIA);
    board = ImmutableGraph.copyOf(builder);
  }

  private void setUpTerritories() {
    territoryMap = new HashMap<>();
    for (TerritoryEnum id : TerritoryEnum.values()) {
      for (ContinentInterface cont : continentMap.values()) {
        if (cont.containsTerritory(id)) {
          Territory territory = new Territory(id, cont.getContinentId());
          territoryMap.put(id, territory);
          break;
        }
      }
    }
  }

  private void setUpContinents() {
    ContinentInterface australia = new Australia();
    ContinentInterface southAmerica = new SouthAmerica();
    ContinentInterface northAmerica = new NorthAmerica();
    ContinentInterface africa = new Africa();
    ContinentInterface asia = new Asia();
    ContinentInterface europe = new Europe();
    continentMap = new HashMap<>();
    continentMap.put(ContinentEnum.AFRICA, africa);
    continentMap.put(ContinentEnum.ASIA, asia);
    continentMap.put(ContinentEnum.AUSTRALIA, australia);
    continentMap.put(ContinentEnum.EUROPE, europe);
    continentMap.put(ContinentEnum.NORTH_AMERICA, northAmerica);
    continentMap.put(ContinentEnum.SOUTH_AMERICA, southAmerica);

  }

  public boolean isNeighbor(TerritoryEnum terr1, TerritoryEnum terr2) {
    return board.adjacentNodes(terr1).contains(terr2);
  }

  public Territory getTerritory(TerritoryEnum terrId) {
    return territoryMap.get(terrId);
  }

  public Set<Territory> getNeighbors(TerritoryEnum terr) {
    Set<Territory> neighbors = new HashSet<>();
    Set<TerritoryEnum> ids = board.adjacentNodes(terr);
    for (TerritoryEnum id : ids) {
      neighbors.add(territoryMap.get(id));
    }
    return neighbors;
  }

  public Collection<Territory> getTerritories() {
    return territoryMap.values();
  }

  public Collection<ContinentInterface> getContinents() {
    return continentMap.values();
  }

  public ContinentInterface getContinent(ContinentEnum contId) {
    return continentMap.get(contId);
  }

  public Set<TerritoryEnum> getAttackableTerritories(
      Set<TerritoryEnum> territories) {
    Set<TerritoryEnum> attackableTerritories = new HashSet<>();
    for (TerritoryEnum terrId : territories) {
      Territory terr = territoryMap.get(terrId);
      if (terr.getNumberTroops() > 1) {
        attackableTerritories.add(terrId);
      }
    }
    return attackableTerritories;
  }

  public Multimap<TerritoryEnum, TerritoryEnum> getPlayerAttackMap(
      RiskPlayer player) {
    Set<TerritoryEnum> territories = player.getTerritories();
    Multimap<TerritoryEnum, TerritoryEnum> attackMap = HashMultimap.create();
    for (TerritoryEnum terrId : territories) {
      Territory terr = territoryMap.get(terrId);
      if (terr.getNumberTroops() > 1) {
        Set<TerritoryEnum> otherIds = board.adjacentNodes(terrId);
        for (TerritoryEnum otherId : otherIds) {
          if (!territories.contains(otherId)) {
            attackMap.put(terrId, otherId);
          }
        }
      }
    }
    return attackMap;
  }

  public Set<TerritoryEnum> getTerritoryIds() {
    return Collections.unmodifiableSet(territoryMap.keySet());
  }
}
