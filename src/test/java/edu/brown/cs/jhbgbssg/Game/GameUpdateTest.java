package edu.brown.cs.jhbgbssg.Game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidCardAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupAction;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

/**
 * JUnit tests for GameUpdate.
 *
 * @author sarahgilmore
 *
 */
public class GameUpdateTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    GameUpdate update = new GameUpdate();
    assertNotNull(update);
  }

  /**
   * Tests setHandoutCard sets the card player id and the card value to the
   * right values.
   */
  @Test
  public void testSetHandoutCard() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    assertTrue(update.setCardToHandOut(id, 1, true));
    assertTrue(update.cardsLeft());
    assertTrue(update.getCardHandOut().getFirstElement().equals(id));
    assertTrue(update.getCardHandOut().getSecondElement() == 1);
  }

  /**
   * Tests setHandoutCard throws an IllegalArgumentException if the player id is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetHandoutCardNullPlayer() {
    GameUpdate update = new GameUpdate();
    update.setCardToHandOut(null, 1, true);
  }

  /**
   * Tests that the card value and player id to give the card to and the value
   * can only be set once.
   */
  @Test
  public void testSetHandoutCardAlreadySet() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    assertTrue(update.setCardToHandOut(id, 1, true));
    UUID player2 = UUID.randomUUID();
    assertFalse(update.setCardToHandOut(player2, 2, false));
    assertTrue(update.cardsLeft());
    assertTrue(update.getCardHandOut().getFirstElement().equals(id));
    assertTrue(update.getCardHandOut().getSecondElement() == 1);
  }

  /**
   * Tests the card and player values are null if no card has been handed out.
   */
  @Test
  public void testNoCard() {
    GameUpdate update = new GameUpdate();
    assertNull(update.getCardHandOut());
  }

  /**
   * Tests setError sets the error boolean to true and the error player id to
   * the given id.
   */
  @Test
  public void testSetError() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    assertTrue(update.setError(id));
    assertTrue(update.getErrorPlayer().equals(id));
    assertTrue(update.getErrors());
  }

  /**
   * Tests setError only sets the error player at most once and returns false if
   * it is called again.
   */
  @Test
  public void testSetErrorAlreadySet() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    UUID player2 = UUID.randomUUID();
    assertTrue(update.setError(id));
    assertTrue(update.getErrorPlayer().equals(id));
    assertTrue(update.getErrors());
    assertFalse(update.setError(player2));
    assertTrue(update.getErrorPlayer().equals(id));
    assertTrue(update.getErrors());
  }

  /**
   * Tests setError throws an IllegalArgumentException if the player id given is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetErrorNullId() {
    GameUpdate update = new GameUpdate();
    update.setError(null);
  }

  /**
   * Tests that the error boolean is false and the error player is null if no
   * errors have been set.
   */
  @Test
  public void testNoError() {
    GameUpdate update = new GameUpdate();
    assertFalse(update.getErrors());
    assertNull(update.getErrorPlayer());
  }

  /**
   * Tests that setValidMoves can accept a null previous move.
   */
  @Test
  public void testSetValidMovesNullPreviousMove() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    ValidSetupAction action = new ValidSetupAction(player, board);
    GameUpdate update = new GameUpdate();
    update.setValidMoves(action, null);
    assertTrue(update.getValidMoves().equals(action));
    assertNull(update.getPrevMove());
  }

  /**
   * Tests that setValidMoves can accept a null valid next move.
   */
  @Test
  public void testSetValidMoves() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 4);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ICELAND, TerritoryEnum.GREENLAND, 2);
    claim.executeAction();
    GameUpdate update = new GameUpdate();
    update.setValidMoves(null, claim);
    assertTrue(update.getPrevMove().equals(claim));
    assertNull(update.getValidMoves());
  }

  /**
   * Tests setValidMoves throws an AssertionError if the ValidAction object
   * given has not actions available.
   */
  @Test(expected = AssertionError.class)
  public void testSetValidMovesMoveNotAvailable() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidCardAction action = new ValidCardAction(player);
    assertFalse(action.actionAvailable());
    GameUpdate update = new GameUpdate();
    update.setValidMoves(action, null);
  }

  /**
   * Tests that setValidMoves throws an AssertionError if the Previous Move has
   * not been executed yet.
   */
  @Test(expected = AssertionError.class)
  public void testSetValidMovesPreviousMoveNotExecuted() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    player.conqueredTerritory(TerritoryEnum.ICELAND);
    board.getTerritory(TerritoryEnum.ICELAND).changePlayer(player, 4);
    ClaimTerritoryAction claim = new ClaimTerritoryAction(player, board,
        TerritoryEnum.ICELAND, TerritoryEnum.GREENLAND, 2);
    GameUpdate update = new GameUpdate();
    update.setValidMoves(null, claim);
  }

  /**
   * Tests setWonGame returns true if the wonGame field is set.
   */
  @Test
  public void testSetWonGame() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    assertTrue(update.setWonGame(id));
    assertTrue(update.getGameWon().equals(id));
  }

  /**
   * Tests setWonGame returns false if the field has already been set and tests
   * that the field is not overriden.
   */
  @Test
  public void testSetWonGameAlreadySet() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    assertTrue(update.setWonGame(id));
    assertTrue(update.getGameWon().equals(id));
    UUID id2 = UUID.randomUUID();
    assertFalse(update.setWonGame(id2));
    assertTrue(update.getGameWon().equals(id));
  }

  /**
   * Tests setWonGame throws an IllegalArgumentException if the player id given
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetWonGameNullId() {
    GameUpdate update = new GameUpdate();
    update.setWonGame(null);
  }

  /**
   * Tests setLostGame returns true if the wonGame field is set.
   */
  @Test
  public void testSetLostGame() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    assertTrue(update.setLostGame(id));
    assertTrue(update.getLoser().equals(id));
  }

  /**
   * Tests setWonLost returns false if the field has already been set and tests
   * that the field is not overriden.
   */
  @Test
  public void testSetLostGameAlreadySet() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    assertTrue(update.setLostGame(id));
    assertTrue(update.getLoser().equals(id));
    UUID id2 = UUID.randomUUID();
    assertFalse(update.setLostGame(id2));
    assertTrue(update.getLoser().equals(id));
  }

  /**
   * Tests setWonLostthrows an IllegalArgumentException if the player id given
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetLostGameNullId() {
    GameUpdate update = new GameUpdate();
    update.setLostGame(null);
  }

  /**
   * Tests setSkipMove returns the correct MoveType and player id.
   */
  @Test
  public void testSetSkipMove() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    update.setSkipMoveType(MoveType.TURN_IN_CARD, id);
    assertTrue(
        update.getSkipMove().getSecondElement() == MoveType.TURN_IN_CARD);
    assertTrue(update.getSkipMove().getFirstElement().equals(id));
  }

  /**
   * Tests setSkipMoveType throws an IllegalArgumentException if the MoveType
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetSkipMoveNullMoveType() {
    GameUpdate update = new GameUpdate();
    UUID id = UUID.randomUUID();
    update.setSkipMoveType(null, id);
  }

  /**
   * Tests setSkipMoveType throws an IllegalArgumentException if the player id
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetSkipMoveNullPlayerId() {
    GameUpdate update = new GameUpdate();
    update.setSkipMoveType(MoveType.CHOOSE_ATTACK_DIE, null);
  }

  /**
   * Tests that setSkipMove throws an IllegalArgumentException if the previous
   * move field has already been set.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetSkipMovePrevNotNull() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.ALASKA);
    action.executeAction();
    GameUpdate update = new GameUpdate();
    update.setValidMoves(null, action);
    update.setSkipMoveType(MoveType.SETUP_REINFORCE, player.getPlayerId());
  }

  /**
   * Tests that setValidMoves throws an IllegalArgumentException if the skip
   * field has already been set.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPreviousMoveSkipNotNull() {
    GameUpdate update = new GameUpdate();
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    update.setSkipMoveType(MoveType.SETUP_REINFORCE, player.getPlayerId());
    RiskBoard board = new RiskBoard();
    SetupAction action = new SetupAction(player, board, TerritoryEnum.ALASKA);
    action.executeAction();
    update.setValidMoves(null, action);

  }
}
