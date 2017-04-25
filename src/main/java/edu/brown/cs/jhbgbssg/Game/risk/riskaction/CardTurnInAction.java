package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents a Card Turn in move.
 *
 * @author sarahgilmore
 *
 */
public class CardTurnInAction implements Action {
  private RiskPlayer player;
  private Integer card;
  private RiskBoard board;
  private Map<TerritoryEnum, Integer> cardTerrMap;
  private boolean actionExecuted;

  /**
   * Constructor for CardTurnInMove.
   *
   * @param player - player
   * @param board - board
   * @param card - card value
   * @param territoriesReinforced - territory to integer map
   * @throws IllegalArgumentException if the input is null
   */
  public CardTurnInAction(RiskPlayer player, RiskBoard board, int card,
      Map<TerritoryEnum, Integer> territoriesReinforced)
      throws IllegalArgumentException {
    if (player == null || territoriesReinforced == null || board == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    this.board = board;
    this.card = card;
    this.cardTerrMap = new HashMap<>(territoriesReinforced);
    this.actionExecuted = false;
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.TURN_IN_CARD;
  }

  @Override
  public RiskPlayer getMovePlayer() {
    return player;
  }

  /**
   * Returns the card value.
   *
   * @return card value
   */
  public int getCard() {
    return card;
  }

  /**
   * Returns a map indicating relating territories to the number of troops
   * reinforced.
   *
   * @return unmodifiable map
   */
  public Map<TerritoryEnum, Integer> getTerritoriesReinforced() {
    return Collections.unmodifiableMap(cardTerrMap);
  }

  @Override
  public boolean isActionExecuted() {
    return actionExecuted;
  }

  @Override
  public boolean executeAction() {
    if (!actionExecuted) {
      player.removeCard(card);
      for (Entry<TerritoryEnum, Integer> entry : cardTerrMap.entrySet()) {
        Territory terr = board.getTerritory(entry.getKey());
        terr.addTroops(entry.getValue());
      }
      actionExecuted = true;
      return actionExecuted;
    }
    return false;
  }
}
