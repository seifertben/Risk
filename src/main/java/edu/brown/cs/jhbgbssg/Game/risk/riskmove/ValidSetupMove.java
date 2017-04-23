package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidSetupMove implements ValidAction {
  private UUID playerId;
  private RiskBoard board;
  private List<TerritoryEnum> selectableTerritories;
  private boolean moveAvailable = true;

  public ValidSetupMove(RiskPlayer player, RiskBoard board) {
    playerId = player.getPlayerId();
    this.board = board;
    selectableTerritories = this.getSelectableTerritories();
  }

  public boolean validSetupMove(SetupMove move)
      throws IllegalArgumentException {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    TerritoryEnum selectedTerritory = move.getSelectedTerritory();
    Territory terr = board.getTerritory(selectedTerritory);
    if (terr.occuppied()) {
      return false;
    }
    return true;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.SETUP;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  @Override
  public boolean actionAvailable() {
    return moveAvailable;
  }

  private List<TerritoryEnum> getSelectableTerritories() {
    List<TerritoryEnum> list = new ArrayList<>();
    Collection<Territory> territories = board.getTerritories();
    for (Territory terr : territories) {
      if (!terr.occuppied()) {
        TerritoryEnum terrEnum = terr.getTerritoryId();
        list.add(terrEnum);
      }
    }
    if (list.isEmpty()) {
      moveAvailable = false;
    }
    return list;
  }

}
