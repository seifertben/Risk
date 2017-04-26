package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
public class ValidSetupAction implements ValidAction {
  private RiskPlayer player;
  private List<TerritoryEnum> selectableTerritories;
  private boolean moveAvailable = true;

  /**
   * Constructor.
   *
   * @param player
   *          - player
   * @param board
   *          - board
   * @throws IllegalArgumentException
   *           if the input is null
   */
  public ValidSetupAction(RiskPlayer player, RiskBoard board)
      throws IllegalArgumentException {
    if (player == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    selectableTerritories = new ArrayList<>();
    Collection<Territory> territories = board.getTerritories();
    for (Territory terr : territories) {
      if (!terr.occuppied()) {
        TerritoryEnum terrEnum = terr.getTerritoryId();
        selectableTerritories.add(terrEnum);
      }
    }
    if (selectableTerritories.isEmpty()) {
      moveAvailable = false;
    }
  }

  /**
   * Determines of the setup move is valid.
   *
   * @param move
   *          - move to validate
   * @return true if it is valid; false otherwise
   * @throws IllegalArgumentException
   *           if the input is null
   */
  public boolean validSetupMove(SetupAction move)
      throws IllegalArgumentException {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (!moveAvailable) {
      return false;
    }
    RiskPlayer currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(player)) {
      return false;
    }
    TerritoryEnum selectedTerritory = move.getSelectedTerritory();
    if (!selectableTerritories.contains(selectedTerritory)) {
      return false;
    }
    return true;
  }

  /**
   * Returns the move type.
   */
  @Override
  public MoveType getMoveType() {
    return MoveType.SETUP;
  }

  /**
   * Returns the player who owns the turn.
   */
  @Override
  public UUID getMovePlayer() {
    return player.getPlayerId();
  }

  /**
   * Returns if the move can be made.
   */
  @Override
  public boolean actionAvailable() {
    return moveAvailable;
  }

  /**
   * Returns the selectable territories.
   *
   * @return list of selectable territories
   */
  public List<TerritoryEnum> getTerritories() {
    return Collections.unmodifiableList(selectableTerritories);
  }
}
