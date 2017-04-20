package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 *
 * @author sarahgilmore
 *
 */
public class ValidDieDefendMove implements Move {
  private UUID playerId;
  private int maxNumberDie;

  /**
   * Constructor for ValidDieDefendMove.
   *
   * @param playerId - player
   * @param territory - territory defending
   * @param maxNumberDie - number of die
   * @throws IllegalArgumentException if input is null or maxNumberDie is less
   *           than 1 or greater than 2
   */
  public ValidDieDefendMove(UUID playerId, TerritoryEnum territory,
      int maxNumberDie) throws IllegalArgumentException {
    if (playerId == null || territory == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (maxNumberDie < 1 || maxNumberDie > 3) {
      throw new IllegalArgumentException("ERROR: illegal number of die");
    }
    this.playerId = playerId;
    this.maxNumberDie = maxNumberDie;
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
    int die = move.getDieRolled();
    if (1 > die || die > maxNumberDie) {
      return false;
    }
    return true;
  }
}
