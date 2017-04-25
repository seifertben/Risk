package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;

import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.Africa;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.Asia;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.Australia;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentInterface;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.Europe;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.NorthAmerica;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.SouthAmerica;

public class RiskBoard {
  private Graph<TerritoryEnum> board;
  private Map<TerritoryEnum, Territory> territoryMap;
  private Map<ContinentEnum, ContinentInterface> continentMap;
  private Map<UUID, RiskPlayer> playerMap;

  /**
   * Constructor for RiskBoard.
   */
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
    builder.putEdge(TerritoryEnum.ALBERTA, TerritoryEnum.ONTARIO);
    builder.putEdge(TerritoryEnum.NORTHWEST_TERRITORY, TerritoryEnum.ONTARIO);
    builder.putEdge(TerritoryEnum.NORTHWEST_TERRITORY, TerritoryEnum.GREENLAND);
    builder.putEdge(TerritoryEnum.WESTERN_US, TerritoryEnum.ONTARIO);
    builder.putEdge(TerritoryEnum.WESTERN_US, TerritoryEnum.EASTERN_US);
    builder.putEdge(TerritoryEnum.WESTERN_US, TerritoryEnum.CENTRAL_AMERICA);
    builder.putEdge(TerritoryEnum.ONTARIO, TerritoryEnum.EASTERN_US);
    builder.putEdge(TerritoryEnum.ONTARIO, TerritoryEnum.QUEBEC);
    builder.putEdge(TerritoryEnum.ONTARIO, TerritoryEnum.GREENLAND);
    builder.putEdge(TerritoryEnum.QUEBEC, TerritoryEnum.GREENLAND);
    builder.putEdge(TerritoryEnum.QUEBEC, TerritoryEnum.EASTERN_US);
    builder.putEdge(TerritoryEnum.CENTRAL_AMERICA, TerritoryEnum.VENEZUELA);
    builder.putEdge(TerritoryEnum.CENTRAL_AMERICA, TerritoryEnum.EASTERN_US);
    builder.putEdge(TerritoryEnum.VENEZUELA, TerritoryEnum.PERU);
    builder.putEdge(TerritoryEnum.VENEZUELA, TerritoryEnum.BRAZIL);
    builder.putEdge(TerritoryEnum.PERU, TerritoryEnum.BRAZIL);
    builder.putEdge(TerritoryEnum.PERU, TerritoryEnum.ARGENTINA);
    builder.putEdge(TerritoryEnum.BRAZIL, TerritoryEnum.NORTH_AFRICA);
    builder.putEdge(TerritoryEnum.BRAZIL, TerritoryEnum.ARGENTINA);
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
    builder.putEdge(TerritoryEnum.SOUTHERN_EUROPE,
        TerritoryEnum.WESTERN_EUROPE);
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
    builder.putEdge(TerritoryEnum.CHINA, TerritoryEnum.SOUTHEAST_ASIA);
    builder.putEdge(TerritoryEnum.CHINA, TerritoryEnum.MONGOLIA);
    builder.putEdge(TerritoryEnum.MONGOLIA, TerritoryEnum.IRKUTSK);
    builder.putEdge(TerritoryEnum.MONGOLIA, TerritoryEnum.KAMACHATKA);
    builder.putEdge(TerritoryEnum.MONGOLIA, TerritoryEnum.JAPAN);
    builder.putEdge(TerritoryEnum.IRKUTSK, TerritoryEnum.YAKUTSK);
    builder.putEdge(TerritoryEnum.IRKUTSK, TerritoryEnum.KAMACHATKA);
    builder.putEdge(TerritoryEnum.KAMACHATKA, TerritoryEnum.ALASKA);
    builder.putEdge(TerritoryEnum.KAMACHATKA, TerritoryEnum.YAKUTSK);
    builder.putEdge(TerritoryEnum.KAMACHATKA, TerritoryEnum.JAPAN);
    builder.putEdge(TerritoryEnum.SOUTHEAST_ASIA, TerritoryEnum.INDIA);
    builder.putEdge(TerritoryEnum.SOUTHEAST_ASIA, TerritoryEnum.INDONESIA);
    builder.putEdge(TerritoryEnum.INDONESIA, TerritoryEnum.NEW_GUINEA);
    builder.putEdge(TerritoryEnum.INDONESIA, TerritoryEnum.WESTERN_AUSTRALIA);
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

  /**
   * Gets a map portraying which territories a player can attack from and which
   * territories a player can attack from a given territory.
   *
   * @param player - player to construct attack map for
   * @return multimap indicating where a player can attack
   */
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

  /**
   * Returns true if the two territories are neighbors; false otherwise.
   *
   * @param terr1 - territory
   * @param terr2 - territory
   * @return true if they are neighbors; false otherwise
   */
  public boolean isNeighbor(TerritoryEnum terr1, TerritoryEnum terr2) {
    return board.adjacentNodes(terr1).contains(terr2);
  }

  /**
   * Gets the territory mapped two by a given id.
   *
   * @param terrId - territory id
   * @return territory
   */
  public Territory getTerritory(TerritoryEnum terrId) {
    return territoryMap.get(terrId);
  }

  /**
   * Gets the neighbors of this territory.
   *
   * @param terr - territory id.
   * @return set of neighboring territories.
   */
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

  public Multimap<TerritoryEnum, TerritoryEnum> getMoveableTroops(
      RiskPlayer player) {
    Set<TerritoryEnum> terrs = player.getTerritories();
    UUID playerId = player.getPlayerId();
    Multimap<TerritoryEnum, TerritoryEnum> canReach = HashMultimap.create();
    for (TerritoryEnum id : terrs) {
      Territory terr = territoryMap.get(id);
      if (terr != null && terr.getNumberTroops() > 1) {
        Deque<TerritoryEnum> visit = new ArrayDeque<>(board.adjacentNodes(id));
        while (!visit.isEmpty()) {
          TerritoryEnum pop = visit.pop();
          Territory neighbor = territoryMap.get(pop);
          if (neighbor.getOwner().equals(playerId)) {
            visit.addAll(board.adjacentNodes(pop));
            canReach.put(id, pop);
          }
        }
      }
    }
    return canReach;
  }

  public Set<TerritoryEnum> getTerritoryIds() {
    return Collections.unmodifiableSet(territoryMap.keySet());
  }
}
