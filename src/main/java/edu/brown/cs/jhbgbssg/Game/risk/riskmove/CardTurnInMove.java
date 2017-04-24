package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * Represents a Card Turn in move.
 *
 * @author sarahgilmore
 *
 */
public class CardTurnInMove implements Move {
  private UUID playerId;
  private Integer card;
  private Map<TerritoryEnum, Integer> terr;

  /**
   * Constructor for CardTurnInMove.
   *
   * @param playerId - player
   * @param card - card value
   * @param terr - territory to integer map
   * @throws IllegalArgumentException if the input is null
   */
  public CardTurnInMove(UUID playerId, int card,
      Map<TerritoryEnum, Integer> terr) throws IllegalArgumentException {
    if (playerId == null || terr == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.playerId = playerId;
    this.card = card;
    this.terr = new HashMap<>(terr);
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.TURN_IN_CARD;
  }

  @Override
  public UUID getMovePlayer() {
    return playerId;
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
    return Collections.unmodifiableMap(terr);
  }
}
