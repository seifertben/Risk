package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Controls the rules of the game.
 *
 * @author Ben
 *
 */
public class Referee {

  /**
   * Initializes the referee.
   */
  public Referee() {

  }

  public static boolean checkPlayerTurn(Turn turn, UUID playerID) {
    return turn.getPlayerId().equals(playerID);
  }

  public static boolean checkAttackPhase(Turn turn) {
    return turn.getAttacking();
  }

  public static boolean checkAdjacentTerritory(RiskBoard board,
      TerritoryEnum terr1, TerritoryEnum terr2) {
    return board.isNeighbor(terr1, terr2);
  }

  public static boolean checkTerritoryOwner(RiskPlayer player,
      TerritoryEnum terr) {
    return player.hasTerritory(terr);
  }

  public static boolean checkTerritoryAttack(Territory terr) {
    return terr.getNumberTroops() > 1;
  }

  public static boolean checkNumberDieAttack(Territory terr, int numberDie) {
    return terr.getNumberTroops() > numberDie;
  }

  public static boolean checkNumberDieDefend(Territory terr, int numberDie) {
    return terr.getNumberTroops() >= numberDie;
  }

  public static boolean checkMovementNumber(Territory terr, int numberMove) {
    return terr.getNumberTroops() > numberMove;
  }

  /**
   * Validates if a player may choose a territory at the beginning.
   *
   * @param turn
   * @param territory
   * @return
   */
  public boolean checkValidPlace(UUID playerId, Turn turn,
      Territory territory) {
    if (turn.getBeginning() && turn.getPlayerId() == playerId
        && !territory.occuppied()) {
      return true;
    }
    return false;
  }
}
