package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.UUID;

import org.junit.Test;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAttackAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidAttackActionTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidAttackAction action = new ValidAttackAction(player, board);
    assertNotNull(action);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the player
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskBoard board = new RiskBoard();
    new ValidAttackAction(null, board);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the board given
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBOard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    new ValidAttackAction(player, null);
  }

  /**
   * Tests getMoveType returns CHOOSE_ATTACK_DIE.
   */
  @Test
  public void testGetMoveType() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidAttackAction action = new ValidAttackAction(player, board);
    assertTrue(action.getMoveType() == MoveType.CHOOSE_ATTACK_DIE);
  }

  /**
   * Tests getPlayer returns the right player.
   */
  @Test
  public void testGetPlayer() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidAttackAction action = new ValidAttackAction(player, board);
    assertTrue(action.getMovePlayer().equals(player.getPlayerId()));
  }

  @Test
  public void testGetAttackableTerritories() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 2);
    ValidAttackAction action = new ValidAttackAction(player, board);
    Multimap attackMap = action.whoToAttack();
    assertTrue(attackMap.keySet().size() == 2);
    assertTrue(attackMap.keySet().contains(TerritoryEnum.WESTERN_US));
    assertTrue(attackMap.keySet().contains(TerritoryEnum.EASTERN_US));
    Collection<TerritoryEnum> westernUSMap = attackMap
        .get(TerritoryEnum.WESTERN_US);
    assertTrue(action.actionAvailable());
  }

}
