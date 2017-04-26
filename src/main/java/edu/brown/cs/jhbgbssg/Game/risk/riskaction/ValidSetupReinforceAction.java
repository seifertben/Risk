package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 *
 * @author sarahgilmore
 *
 */
public class ValidSetupReinforceAction implements ValidAction {

  private RiskPlayer player;
  private Set<TerritoryEnum> territories;
  private int numberLeftToPlace;
  private boolean actionAvailable = true;

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
    }

  }

  /**
   * Validates a move.
   *
   * @param move - move
   * @return true or false.
   */
  public boolean validSetupReinforceMove(SetupReinforceAction move) {
    move.getSelectedTerritory();
    if (!actionAvailable
        || !territories.contains(move.getSelectedTerritory())) {
      return false;
    } else if (!move.getMovePlayer().equals(player.getPlayerId())) {
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
    return false;
  }

  public Set<TerritoryEnum> getTerritories() {
    return territories;
  }

  public int getTroopsLeftToPlace() {
    return numberLeftToPlace;
  }

}
