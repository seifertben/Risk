package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidClaimTerritoryActionTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    assertNotNull(action);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the player
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    new ValidClaimTerritoryAction(null, board, attack);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the board given
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    new ValidClaimTerritoryAction(player, null, attack);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the attack
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullAttack() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    new ValidClaimTerritoryAction(player, board, null);
  }

  /**
   * Tests that getMoveType returns CLAIM_TERRITORY.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    assertTrue(action.getMoveType() == MoveType.CLAIM_TERRITORY);
  }

  /**
   * Tests that getMovePlayer returns the right player.
   */
  @Test
  public void testGetMovePlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    assertTrue(action.getMovePlayer().equals(player.getPlayerId()));
  }

  @Test
  public void testGetTerritoryToClaim() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    assertTrue(action.getClaimedTerritory() == TerritoryEnum.EASTERN_AUSTRALIA);
  }

  @Test
  public void testGetFromTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    assertTrue(action.getFromTerritory() == TerritoryEnum.WESTERN_AUSTRALIA);
  }

  @Test
  public void testGetMaxNumberTroops() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    assertTrue(action.getMaxNumberTroops() == 3);
  }

  /**
   * Tests that validClaimTerritoryMove returns true for a valid claim action.
   */
  @Test
  public void testValidClaimTerritoryAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 3);
    assertTrue(action.validClaimTerritory(claim));
    claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    assertTrue(action.validClaimTerritory(claim));
    claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 1);
    assertTrue(action.validClaimTerritory(claim));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that validClaimTerritoryMove returns true for a valid claim action.
   */
  @Test
  public void testInvalidPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(
        new RiskPlayer(UUID.randomUUID()), board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 3);
    assertFalse(action.validClaimTerritory(claim));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidFromTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.INDONESIA, TerritoryEnum.EASTERN_AUSTRALIA, 3);
    assertFalse(action.validClaimTerritory(claim));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidClaimTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.INDONESIA, 3);
    assertFalse(action.validClaimTerritory(claim));
    assertTrue(action.actionAvailable());
  }

  @Test
  public void testInvalidNumberofTroops() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 4);
    assertFalse(action.validClaimTerritory(claim));
    claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 5);
    assertFalse(action.validClaimTerritory(claim));
    claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 0);
    assertFalse(action.validClaimTerritory(claim));
    claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, -1);
    assertFalse(action.validClaimTerritory(claim));
    assertTrue(action.actionAvailable());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValidateClaimTerritoryMoveNullMove() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.WESTERN_AUSTRALIA);
    board.getTerritory(TerritoryEnum.WESTERN_AUSTRALIA).changePlayer(player, 4);
    AttackAction attack = new AttackAction(player,
        TerritoryEnum.WESTERN_AUSTRALIA, TerritoryEnum.EASTERN_AUSTRALIA, 2);
    ValidClaimTerritoryAction action = new ValidClaimTerritoryAction(player,
        board, attack);
    action.validClaimTerritory(null);
  }
}
