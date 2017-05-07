package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * This class defines all of the valid setup reinforce actions a player can make
 * at the beginning of the game when all of the territories have been selected
 * and each player has leftover troops to place.
 *
 * @author sarahgilmore
 *
 */
public class ValidSetupReinforceAction implements ValidAction {

  private RiskPlayer player;
  private Set<TerritoryEnum> territories;
  private int numberLeftToPlace;
  private boolean actionAvailable;

  /**
   * Constructor.
   *
   * @param player - player
   * @throws IllegalArgumentException if the input is null
   */
  public ValidSetupReinforceAction(RiskPlayer player)
      throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    territories = player.getTerritories();
    this.numberLeftToPlace = player.getInitialReinforcements();
    if (territories.size() == 0 || numberLeftToPlace <= 0) {
      actionAvailable = false;
    } else {
      actionAvailable = true;
    }
  }

  /**
   * Validates a move.
   *
   * @param move - move
   * @return true or false.
   */
  public boolean validSetupReinforceMove(SetupReinforceAction move) {
    if (!actionAvailable
        || !territories.contains(move.getSelectedTerritory())) {
      return false;
    } else if (!move.getMovePlayer().equals(player)) {
      return false;
    }
    return true;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.SETUP_REINFORCE;
  }

  @Override
  public UUID getMovePlayer() {
    return player.getPlayerId();
  }

  @Override
  public boolean actionAvailable() {
    return actionAvailable;
  }

  /**
   * Returns an unmodifiable set of territories that can be reinforced.
   *
   * @return set of territories
   */
  public Set<TerritoryEnum> getTerritories() {
    return Collections.unmodifiableSet(territories);
  }

  /**
   * Returns the number of troops to place.
   *
   * @return number of troops
   */
  public int getTroopsLeftToPlace() {
    return numberLeftToPlace;
  }

}
