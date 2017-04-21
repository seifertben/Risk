package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

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
public class ValidReinforceMove implements Move {
  private UUID playerId;
  private Set<TerritoryEnum> territories;
  private int numberReinforce;

  /**
   * Constructor for a ValidReinforceMove.
   *
   * @param playerId - player
   * @param territories - territories player owns
   * @param numberReinforce - number of troops the player must reinforce with
   * @throws IllegalArgumentException - thrown if the input is null or if the
   *           numberReinforce is negative
   */
  public ValidReinforceMove(UUID playerId, Set<TerritoryEnum> territories,
      int numberReinforce) throws IllegalArgumentException {
    if (territories == null || playerId == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (numberReinforce < 0) {
      throw new IllegalArgumentException("ERROR: cannot have negative numbers");
    }
    this.playerId = playerId;
    this.territories = territories;
    this.numberReinforce = numberReinforce;
  }

  private void setUp(RiskPlayer player, RiskBoard board) {
    playerId = player.getPlayerId();
    numberReinforce = player.getNumberTerritories() / 3;
    territories = player.getTerritories();
    Collection<ContinentInterface> conts = board.getContinents();
    for (ContinentInterface cont : conts) {
      Set<TerritoryEnum> territoriesInCont = cont.getTerritories();
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
    return playerId;
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
  public boolean validReinforceMove(ReinforceMove move) {
    UUID currPlayer = move.getMovePlayer();
    if (!currPlayer.equals(playerId)) {
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
}
