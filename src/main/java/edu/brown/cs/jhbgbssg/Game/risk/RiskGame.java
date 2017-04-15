package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Stores the state of the game.
 *
 * @author Ben
 *
 */
public class RiskGame {

  private RiskBoard gameBoard;

  private Turn turnState;

  private List<RiskPlayer> players;

  private HashMap<UUID, RiskPlayer> idToPlayer;

  /**
   * Initializes the game state.
   *
   * @param numPlayers
   *          the number of players.
   * @param ids
   *          the player ids.
   */
  public RiskGame(int numPlayers, Set<UUID> ids) {
    gameBoard = new RiskBoard();
    turnState = new Turn();
    // Create the RiskPlayers.
    for (UUID i : ids) {
      RiskPlayer player = new RiskPlayer(i);
      players.add(player);
      idToPlayer.put(i, player);
    }
    // Shuffle the players to see who goes first.
    Collections.shuffle(players);
    // Assign the turnState to the first player.
    turnState.setTurnId(players.get(0).getPlayerId());
    turnState.setBeginning();
  }

  /**
   * At the beginning of the game, players choose countries.
   */
  public void selectTerritory(UUID playerId, TerritoryEnum territory) {
    RiskPlayer player = idToPlayer.get(playerId);
    player.conqueredTerritory(territory);
    Territory territoryObject = gameBoard.getTerritory(territory);
    territoryObject.changePlayer(playerId, 1);
  }

  public void attack(UUID playerId, TerritoryEnum attackFrom,
      Territory attackTo) {
    RiskPlayer attacker = idToPlayer.get(playerId);
  }

}
