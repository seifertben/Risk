package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.google.common.collect.Multimap;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
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
    Multimap<TerritoryEnum, TerritoryEnum> attackMap = action.whoToAttack();
    assertTrue(attackMap.keySet().size() == 2);
    assertTrue(attackMap.keySet().contains(TerritoryEnum.WESTERN_US));
    assertTrue(attackMap.keySet().contains(TerritoryEnum.EASTERN_US));
    Collection<TerritoryEnum> westernUSMap = attackMap
        .get(TerritoryEnum.WESTERN_US);
    assertTrue(westernUSMap.size() == 3);
    assertTrue(westernUSMap.contains(TerritoryEnum.CENTRAL_AMERICA));
    assertTrue(westernUSMap.contains(TerritoryEnum.ALBERTA));
    assertTrue(westernUSMap.contains(TerritoryEnum.ONTARIO));
    Collection<TerritoryEnum> easternUSMap = attackMap
        .get(TerritoryEnum.EASTERN_US);
    assertTrue(easternUSMap.size() == 3);
    assertTrue(easternUSMap.contains(TerritoryEnum.CENTRAL_AMERICA));
    assertTrue(easternUSMap.contains(TerritoryEnum.QUEBEC));
    assertTrue(easternUSMap.contains(TerritoryEnum.ONTARIO));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that the maximum number of die that can be rolled is correct for each
   * territory that can attack.
   */
  @Test
  public void testGetNumberDieCanRoll() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 3);
    ValidAttackAction action = new ValidAttackAction(player, board);
    Map<TerritoryEnum, Integer> dieMap = action.getAttackableTerritories();
    assertTrue(dieMap.containsKey(TerritoryEnum.WESTERN_US));
    assertTrue(dieMap.containsKey(TerritoryEnum.EASTERN_US));
    assertTrue(dieMap.size() == 2);
    assertTrue(dieMap.get(TerritoryEnum.WESTERN_US) == 3);
    assertTrue(dieMap.get(TerritoryEnum.EASTERN_US) == 2);
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testNotEnoughTroops() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 1);
    ValidAttackAction action = new ValidAttackAction(player, board);
    Map<TerritoryEnum, Integer> dieMap = action.getAttackableTerritories();
    assertTrue(dieMap.size() == 0);
    assertTrue(action.whoToAttack().size() == 0);
    assertFalse(action.actionAvailable());
  }

  /**
   * Tests that if the player has no territories with enough troops neighboring
   * territories they do not own, the actionAvailable method if false and the
   * fields are empty.
   */
  @Test
  public void testNoNeighboringTerritoriesToAttack() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    player.conqueredTerritory(TerritoryEnum.CENTRAL_AMERICA);
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.ONTARIO);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 4);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.CENTRAL_AMERICA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 1);
    board.getTerritory(TerritoryEnum.ONTARIO).changePlayer(player, 1);
    ValidAttackAction action = new ValidAttackAction(player, board);
    Map<TerritoryEnum, Integer> dieMap = action.getAttackableTerritories();
    assertTrue(dieMap.size() == 0);
    assertTrue(action.whoToAttack().size() == 0);
    assertFalse(action.actionAvailable());
  }

  @Test
  public void testValidateAttackMoveRollMaxDie() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 3);
    ValidAttackAction action = new ValidAttackAction(player, board);
    AttackAction attack = new AttackAction(player, TerritoryEnum.WESTERN_US,
        TerritoryEnum.ALBERTA, 3);
    assertTrue(action.validAttackMove(attack));
    attack = new AttackAction(player, TerritoryEnum.WESTERN_US,
        TerritoryEnum.ALBERTA, 2);
    assertTrue(action.validAttackMove(attack));
    attack = new AttackAction(player, TerritoryEnum.WESTERN_US,
        TerritoryEnum.ALBERTA, 1);
    assertTrue(action.validAttackMove(attack));
    assertTrue(action.actionAvailable());

  }

  @Test
  public void testValidateAttackMoveRollTwoDie() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 3);
    ValidAttackAction action = new ValidAttackAction(player, board);
    AttackAction attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, 3);
    assertFalse(action.validAttackMove(attack));
    attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, 2);
    assertTrue(action.validAttackMove(attack));
    attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, 1);
    assertTrue(action.validAttackMove(attack));
    assertTrue(action.actionAvailable());

  }

  @Test
  public void testValidateAttackMoveRollOneDie() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 2);
    ValidAttackAction action = new ValidAttackAction(player, board);
    AttackAction attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, 3);
    assertFalse(action.validAttackMove(attack));
    attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, 2);
    assertFalse(action.validAttackMove(attack));
    attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, 1);
    assertTrue(action.validAttackMove(attack));
    assertTrue(action.actionAvailable());

  }

  @Test
  public void testInvalidPlayer() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    player2.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player2, 2);
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 2);
    ValidAttackAction action = new ValidAttackAction(player, board);
    AttackAction attack = new AttackAction(player2, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, 1);
    assertFalse(action.validAttackMove(attack));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidDieRoll() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    player2.conqueredTerritory(TerritoryEnum.ALBERTA);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player2, 2);
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 2);
    ValidAttackAction action = new ValidAttackAction(player, board);
    AttackAction attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, 0);
    assertFalse(action.validAttackMove(attack));
    attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.QUEBEC, -1);
    assertFalse(action.validAttackMove(attack));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testAttackingTerritory() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 2);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 1);
    ValidAttackAction action = new ValidAttackAction(player, board);
    AttackAction attack = new AttackAction(player, TerritoryEnum.ALBERTA,
        TerritoryEnum.ONTARIO, 1);
    assertFalse(action.validAttackMove(attack));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidDefendingTerritory() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 1);
    ValidAttackAction action = new ValidAttackAction(player, board);
    AttackAction attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.WESTERN_US, 1);
    assertFalse(action.validAttackMove(attack));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testCannotReachTerritory() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 1);
    ValidAttackAction action = new ValidAttackAction(player, board);
    AttackAction attack = new AttackAction(player, TerritoryEnum.EASTERN_US,
        TerritoryEnum.GREENLAND, 1);
    assertFalse(action.validAttackMove(attack));
    assertTrue(action.actionAvailable());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidateAttackMoveNullMove() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.ALBERTA);
    player.conqueredTerritory(TerritoryEnum.WESTERN_US);
    player.conqueredTerritory(TerritoryEnum.EASTERN_US);
    board.getTerritory(TerritoryEnum.WESTERN_US).changePlayer(player, 5);
    board.getTerritory(TerritoryEnum.EASTERN_US).changePlayer(player, 3);
    board.getTerritory(TerritoryEnum.ALBERTA).changePlayer(player, 1);
    ValidAttackAction action = new ValidAttackAction(player, board);
    action.validAttackMove(null);
  }
}
