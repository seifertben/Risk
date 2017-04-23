package edu.brown.cs.jhbgbssg.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Match {

  private final UUID id;
  private List<UUID> players;
  private List<String> names;
  private boolean started = false;
  private final String matchName;
  private final Integer lobbySize;

  public Match(UUID uid, Integer max, String title) {
    players = Collections.synchronizedList(new ArrayList<>());
    names = Collections.synchronizedList(new ArrayList<>());
    id = uid;
    lobbySize = max;
    matchName = title;
  }

  public String getId() {
    return id.toString();
  }

  public Integer lobbySize() {
    return lobbySize;
  }

  public String matchName() {
    return matchName;
  }

  public int playerNum() {
    return players.size();
  }

  public void addPlayer(UUID playerId, String name) {
    if (players.size() < lobbySize && !players.contains(playerId)) {
      players.add(playerId);
      names.add(name);
    }
  }

  public void removePlayer(UUID playerId) {
    if (players.contains(playerId)) {
      players.remove(playerId);
    }
  }

  public List<UUID> getPlayers() {
    return new ArrayList<UUID>(players);
  }

  public String getPlayerName(Integer index) {
    return names.get(index);
  }

  public boolean started() {
    return started;
  }

  public void start() {
    started = true;
  }

  @Override
  public boolean equals(Object obj) {
    Match objMatch = (Match) obj;
    return id.toString().equals(objMatch.getId());
  }
}