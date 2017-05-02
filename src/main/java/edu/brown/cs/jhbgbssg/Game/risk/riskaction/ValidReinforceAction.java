package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentEnum;

/**
 * Represents a Valid Reinforce Move.
 *
 * @author sarahgilmore
 *
 */
public class ValidReinforceAction implements ValidAction {
  private RiskPlayer player;
  private Set<TerritoryEnum> territories;
  private int numberReinforce;
  private boolean actionAvailable;

  /**
   * Constructor for a ValidReinforceMove.
   *
   * @param player - player
   * @param cards - cards turned in
   * @throws IllegalArgumentException - thrown if the input is null or if the
   *           numberReinforce is negative
   */
  public ValidReinforceAction(RiskPlayer player, List<Integer> cards)
      throws IllegalArgumentException {
    if (player == null || cards == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    numberReinforce = player.getNumberTerritories() / 3;
    territories = player.getTerritories();
    Set<ContinentEnum> conts = player.getContinents();
    for (ContinentEnum cont : conts) {
      numberReinforce += ContinentEnum.getContinentalBonus(cont);
    }
    for (Integer card : cards) {
      numberReinforce += card;
    }
    numberReinforce = Math.max(3, numberReinforce);
    if (territories.size() == 0) {
      numberReinforce = 0;
      actionAvailable = false;
    } else {
      actionAvailable = true;
    }
  }

  @Override
  public MoveType getMoveType() {
    return MoveType.REINFORCE;
  }

  @Override
  public UUID getMovePlayer() {
    return player.getPlayerId();
  }

  /**
   * Returns the set of territories that can be reinforced.
   *
   * @return territories
   */
  public Set<TerritoryEnum> getTerritories() {
    return this.territories;
  }

  /**
   * Returns the number of territories that can be reinforced.
   *
   * @return number of territories
   */
  public int getNumberToReinforce() {
    return numberReinforce;
  }

  /**
   * Determines if the Reinforce move is valid.
   *
   * @param move - move to validate
   * @return true if valid; false otherwise
   */
  public boolean validReinforceMove(ReinforceAction move) {
    RiskPlayer currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(player)) {
      return false;
    }
    Map<TerritoryEnum, Integer> reinforced = move.getReinforcedTerritories();
    Set<TerritoryEnum> terrs = reinforced.keySet();
    if (!territories.containsAll(terrs)) {
      return false;
    }
    int added = 0;
    Collection<Integer> values = reinforced.values();
    for (int val : values) {
      if (val <= 0) {
        return false;
      }
      added += val;
    }
    return added == numberReinforce;
  }

  @Override
  public boolean actionAvailable() {
    return actionAvailable;
  }
}
