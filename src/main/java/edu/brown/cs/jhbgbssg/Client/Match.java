package edu.brown.cs.jhbgbssg.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Match {

  private final UUID id;
  private List<UUID> players;
  private boolean started = false;

  public Match(UUID uid) {
    players = Collections.synchronizedList(new ArrayList<>());
    id = uid;
  }

  public String getId() {
    return id.toString();
  }

  public void addPlayer(UUID playerId) {
    if (players.size() < 6 && !players.contains(playerId)) {
      players.add(playerId);
    }
  }

  public void removePlayer(UUID playerId) {
    if (players.contains(playerId)) {
      players.remove(playerId);
    }
  }

  public int playerNum() {
    return players.size();
  }

  public List<UUID> getPlayers() {
    return new ArrayList<UUID>(players);
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