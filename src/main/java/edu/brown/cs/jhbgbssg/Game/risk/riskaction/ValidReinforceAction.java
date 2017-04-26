package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.RiskWorld.continent.ContinentInterface;

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

  /**
   * Constructor for a ValidReinforceMove.
   *
   * @param player - player
   * @param board - board
   * @throws IllegalArgumentException - thrown if the input is null or if the
   *           numberReinforce is negative
   */
  public ValidReinforceAction(RiskPlayer player, RiskBoard board)
      throws IllegalArgumentException {
    if (player == null || player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.player = player;
    numberReinforce = player.getNumberTerritories() / 3;
    territories = player.getTerritories();
    Collection<ContinentInterface> conts = board.getContinents();
    for (ContinentInterface cont : conts) {
      Set<TerritoryEnum> territoriesInCont = cont.getTerritories();
      System.out.println(territories);
      System.out.println(territoriesInCont);
      System.out.println(cont.getContinentId());
      if (territories.containsAll(territoriesInCont)) {
        numberReinforce += cont.getBonusValue();
      }
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
    return true;
  }
}
