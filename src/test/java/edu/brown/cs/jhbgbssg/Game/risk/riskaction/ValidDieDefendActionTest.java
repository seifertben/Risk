package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;

/**
 * JUnit tests for ValidDieDefendAction.
 *
 * @author sarahgilmore
 *
 */
public class ValidDieDefendActionTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 2);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
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
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 2);
    new ValidDieDefendAction(null, board, TerritoryEnum.GREENLAND);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the board given
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 2);
    new ValidDieDefendAction(player, null, TerritoryEnum.GREENLAND);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the territory
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 2);
    new ValidDieDefendAction(player, board, null);
  }

  /**
   * Tests that getMoveType returns CHOOSE_DEFEND_DIE.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 2);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    assertTrue(action.getMoveType() == MoveType.CHOOSE_DEFEND_DIE);
  }

  /**
   * Tests getMovePlayer returns the correct player.
   */
  @Test
  public void testGetMovePlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 2);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    assertTrue(action.getMovePlayer().equals(player.getPlayerId()));
  }

  /**
   * Tests getMaxNumberDie returns the max number of die a player can roll.
   */
  @Test
  public void testGetNumberDie() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 2);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    assertTrue(action.getMaxNumberDie() == 2);
  }

  /**
   * Tests getMaxNumberDie returns the max number of die a player can roll.
   */
  @Test
  public void testGetNumberDieMoreThanTwoTroops() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    assertTrue(action.getMaxNumberDie() == 2);
  }

  /**
   * Tests getMaxNumberDie returns the max number of die a player can roll.
   */
  @Test
  public void testGetNumberDieOneTroop() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    assertTrue(action.getMaxNumberDie() == 1);
  }

  /**
   * Tests validateDefendMove returns true if a player chooses to roll 2 or 1
   * die if possible.
   */
  @Test
  public void testValidateDefendMoveCanRollTwoOrOneDie() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 5);
    player2.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player2, 6);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    AttackAction attack = new AttackAction(player2, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    DefendAction defend = new DefendAction(player, board, attack, 2);
    assertTrue(action.validateDefendMove(defend));
    defend = new DefendAction(player, board, attack, 1);
    assertTrue(action.validateDefendMove(defend));
  }

  /**
   * Tests validateDefendMove returns true only if the player chooses to roll 1
   * die.
   */
  @Test
  public void testValidateDefendMoveCanRollOneDie() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 1);
    player2.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player2, 6);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    AttackAction attack = new AttackAction(player2, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    DefendAction defend = new DefendAction(player, board, attack, 2);
    assertFalse(action.validateDefendMove(defend));
    defend = new DefendAction(player, board, attack, 1);
    assertTrue(action.validateDefendMove(defend));
  }

  /**
   * Tests validateDefendMove returns false if the player trying to defend is
   * not the player the ValidDieDefendAction is defined for.
   */
  @Test
  public void testInvalidPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    player2.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player2, 6);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    AttackAction attack = new AttackAction(player2, TerritoryEnum.ICELAND,
        TerritoryEnum.GREENLAND, 3);
    DefendAction defend = new DefendAction(player2, board, attack, 2);
    assertFalse(action.validateDefendMove(defend));
  }

  /**
   * Tests validateDefendMove returns false if the territory the player is
   * trying to defend is not the one the ValidDieDefendAction is defined for.
   */
  @Test
  public void testInvalidTerritory() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    board.getTerritory(TerritoryEnum.GREENLAND).changePlayer(player, 3);
    player2.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player2, 6);
    ValidDieDefendAction action = new ValidDieDefendAction(player, board,
        TerritoryEnum.GREENLAND);
    AttackAction attack = new AttackAction(player2, TerritoryEnum.ICELAND,
        TerritoryEnum.GREAT_BRITIAN, 3);
    DefendAction defend = new DefendAction(player2, board, attack, 2);
    assertFalse(action.validateDefendMove(defend));
  }

}
