package edu.brown.cs.jhbgbssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for ValidSetupAction.
 *
 * @author sarahgilmore
 *
 */
public class ValidSetupActionTest {

  /**
   * Tests that the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidSetupAction action = new ValidSetupAction(player, board);
    assertNotNull(action);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the board
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    new ValidSetupAction(player, null);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the player
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskBoard board = new RiskBoard();
    new ValidSetupAction(null, board);
  }

  /**
   * Tests that if no player has selected a territory, the set of territories
   * available contains all of them.
   */
  @Test
  public void testNoTerritoriesSelected() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidSetupAction action = new ValidSetupAction(player, board);
    assertTrue(action.getTerritories().containsAll(board.getTerritoryIds()));
    assertTrue(board.getTerritoryIds().containsAll(action.getTerritories()));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that if all the territories have been selected, no territories are
   * available for selection and the actionAvailable field is false.
   */
  @Test
  public void testAllTerritoriesSelected() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    for (TerritoryEnum id : TerritoryEnum.values()) {
      player.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player, 1);
    }
    ValidSetupAction action = new ValidSetupAction(player, board);
    assertTrue(action.getTerritories().size() == 0);
    assertFalse(action.actionAvailable());
  }

  /**
   * Tests that validSetupMove returns true if given a SetupAction that is
   * within the bounds of an setup action defined by the ValidSetupAction.
   */
  @Test
  public void testValidSetupAction() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidSetupAction action = new ValidSetupAction(player, board);
    SetupAction setup = new SetupAction(player, board, TerritoryEnum.BRAZIL);
    assertTrue(action.validSetupMove(setup));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that the validSetupMove returns false if the player given in the
   * SetupAction is the wrong player.
   */
  @Test
  public void testInvalidSetupActionWrongPlayer() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    ValidSetupAction action = new ValidSetupAction(player, board);
    SetupAction setup = new SetupAction(player2, board, TerritoryEnum.BRAZIL);
    assertFalse(action.validSetupMove(setup));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that the validSetupMove returns false if the Territory given is not
   * in the available set of territories.
   */
  @Test
  public void testInvalidSetupActionBadTerritory() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.conqueredTerritory(TerritoryEnum.CHINA);
    board.getTerritory(TerritoryEnum.CHINA).changePlayer(player, 2);
    ValidSetupAction action = new ValidSetupAction(player, board);
    assertFalse(action.getTerritories().contains(TerritoryEnum.CHINA));
    SetupAction setup = new SetupAction(player, board, TerritoryEnum.CHINA);
    assertFalse(action.validSetupMove(setup));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that validSetupAction throws an IllegalArgumentException if the input
   * action is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidSetupActionNullAction() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidSetupAction action = new ValidSetupAction(player, board);
    action.validSetupMove(null);
  }
}
