package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 *
 * @author sarahgilmore
 *
 */
public class ValidDieDefendAction implements ValidAction {
  private RiskPlayer player;
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
  public ValidDieDefendAction(RiskPlayer player, RiskBoard board,
      TerritoryEnum toDefend) {
    if (player == null || board == null || toDefend == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    this.toDefend = toDefend;
    Territory terr = board.getTerritory(toDefend);
    assert (terr.getOwner().equals(player));
    assert (player.hasTerritory(toDefend));
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
    return player.getPlayerId();
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
   * Returns the defending territory.
   *
   * @return territory to defend
   */
  public TerritoryEnum getDefendTerritory() {
    return toDefend;
  }

  /**
   * Checks if the defend move given is within the bounds defined by this valid
   * defend object.
   *
   * @param move - move to validate
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if null input
   */
  public boolean validateDefendMove(DefendAction move)
      throws IllegalArgumentException {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    RiskPlayer currPlayer = move.getMovePlayer();
    int die = move.getDieRolled();
    if (!currPlayer.equals(player)) {
      return false;
    } else if (!toDefend.equals(move.getDefendedTerritory())) {
      return false;
    } else if (die < 1 || die > maxNumberDie) {
      return false;
    } else if (!move.getDefendedTerritory().equals(toDefend)) {
      return false;
    }
    return true;
  }

  @Override
  public boolean actionAvailable() {
    return true;
  }
}
