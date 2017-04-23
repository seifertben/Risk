package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 *
 * @author sarahgilmore
 *
 */
public class ValidDieDefendMove implements ValidAction {
  private UUID playerId;
  private int maxNumberDie;
  private TerritoryEnum toDefend;

  /**
   * Constructor for ValidDieDefendMove.
   *
   * @param player - player
   * @param board - board
   * @param toDefend - territory
   * @throws IllegalArgumentException if input is null
   */
  public ValidDieDefendMove(RiskPlayer player, RiskBoard board,
      TerritoryEnum toDefend) {
    if (player == null || board == null || toDefend == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    playerId = player.getPlayerId();
    this.toDefend = toDefend;
    Territory terr = board.getTerritory(toDefend);
    int troops = terr.getNumberTroops();
    assert (troops > 0);
    if (troops >= 2) {
      maxNumberDie = 2;
    } else {
      maxNumberDie = 1;
    }
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CHOOSE_DEFEND_DIE;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  /**
   * Returns the number of die the player can roll.
   *
   * @return number of die
   */
  public int getMaxNumberDie() {
    return maxNumberDie;
  }

  /**
   * Checks if the defend move given is within the bounds defined by this valid
   * defend object.
   *
   * @param move - move to validate
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if null input
   */
  public boolean validateDefendMove(DefendMove move)
      throws IllegalArgumentException {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    if (!toDefend.equals(move.getDefendedTerritory())) {
      return false;
    }
    int die = move.getDieRolled();
    if (die < 1 || die > maxNumberDie) {
      return false;
    }
    return true;
  }

  @Override
  public boolean actionAvailable() {
    return true;
  }
}
