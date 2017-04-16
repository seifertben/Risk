package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
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

  private Referee referee;

  /**
   * Initializes the game state.
   *
   * @param numPlayers the number of players.
   * @param ids the player ids.
   */
  public RiskGame(int numPlayers, Set<UUID> ids) {
    gameBoard = new RiskBoard();
    turnState = new Turn();
    referee = new Referee();
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
    Territory territoryObject = gameBoard.getTerritory(territory);
    RiskPlayer player = idToPlayer.get(playerId);
    if (referee.checkValidPlace(playerId, turnState, territoryObject)) {
      player.conqueredTerritory(territory);
      territoryObject.changePlayer(playerId, 1);
    }
  }

  public void attack(UUID playerId, TerritoryEnum attackFrom,
      TerritoryEnum attackTo, int numAttackDice, int numDefendDice) {
    // Create a die for the attack/defense.
    Die die = new Die();
    if (referee.checkValidAttack(playerId, turnState)) {
      Territory terrFrom = gameBoard.getTerritory(attackFrom);
      Territory terrTo = gameBoard.getTerritory(attackTo);
      RiskPlayer attacker = idToPlayer.get(playerId);
      RiskPlayer defender = idToPlayer.get(terrTo.getTerritoryOwner());

      ArrayList<Integer> attackerRolls = new ArrayList<>();
      ArrayList<Integer> defenderRolls = new ArrayList<>();

      // Execute the dice rolls.
      for (int i = 0; i < numAttackDice; i++) {
        attackerRolls.add(die.roll());
      }

      for (int i = 0; i < numDefendDice; i++) {
        defenderRolls.add(die.roll());
      }

      // Compare the rolls.
      int attackerLoss = 0;
      int defenderLoss = 0;
      for (int i = 0; i < Math.min(numAttackDice, numDefendDice); i++) {
        if (attackerRolls.get(i) > defenderRolls.get(i)) {
          defenderLoss++;
        } else if (attackerRolls.get(i) == defenderRolls.get(i)) {
          attackerLoss++;
        } else if (defenderRolls.get(i) > attackerRolls.get(i)) {
          attackerLoss++;
        }
      }
    }
  }
}
