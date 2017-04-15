package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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

  /**
   * Initializes the game state.
   *
   * @param numPlayers
   *          the number of players.
   * @param ids
   *          the player ids.
   */
  public RiskGame(int numPlayers, Set<Integer> ids) {
    gameBoard = new RiskBoard();
    turnState = new Turn();
    // Create the RiskPlayers.
    for (Integer i : ids) {
      RiskPlayer player = new RiskPlayer(i);
      players.add(player);
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
  public void selectCountry() {

  }

}
