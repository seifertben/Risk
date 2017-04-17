package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidClaimTerritoryMove implements Move {
  private UUID playerId;
  private TerritoryEnum fromTerritory;
  private TerritoryEnum territoryToClaim;
  private int maxNumberTroops;

  public ValidClaimTerritoryMove(UUID playerId, TerritoryEnum fromTerritory,
      TerritoryEnum territoryToClaim, int maxNumberTroops) {
    this.playerId = playerId;
    this.fromTerritory = fromTerritory;
    this.territoryToClaim = territoryToClaim;
    this.maxNumberTroops = maxNumberTroops;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.CLAIM_TERRITORY;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
  }

  public TerritoryEnum getFromTerritory() {
    return fromTerritory;
  }

  public TerritoryEnum getClaimedTerritory() {
    return territoryToClaim;
  }

  public int getMaxNumberTroops() {
    return maxNumberTroops;
  }

  public boolean validClaimTerritory(ClaimTerritoryMove move) {
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
      return false;
    }
    if (move.getTerritoryClaimed() != territoryToClaim) {
      return false;
    }
    if (move.getTerritoryFrom() != fromTerritory) {
      return false;
    }
    int troops = move.getNumberTroops();
    if (troops < 1 || troops > maxNumberTroops) {
      return false;
    }
    return true;
  }
}
