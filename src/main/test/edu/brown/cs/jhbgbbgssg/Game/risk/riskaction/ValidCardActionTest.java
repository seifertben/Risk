package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidCardAction;

/**
 * JUnit tests for ValidCardAction.
 *
 * @author sarahgilmore
 *
 */
public class ValidCardActionTest {

  /**
   * Tests that the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    ValidCardAction action = new ValidCardAction(player);
    assertNotNull(action);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the player
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    new ValidCardAction(null);
  }

  /**
   * Tests that the getMoveType returns TURN_IN_CARD.
   */
  @Test
  public void testGetMoveType() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    ValidCardAction action = new ValidCardAction(player);
    assertTrue(action.getMoveType() == MoveType.TURN_IN_CARD);
  }

  /**
   * Tests that the actionAvailable method returns true if the action is
   * available.
   */
  @Test
  public void testActionAvailableTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.addCard(1);
    ValidCardAction action = new ValidCardAction(player);
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that the actionAvailable method returns false if the action is not
   * available.
   */
  @Test
  public void testActionAvailableFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    ValidCardAction action = new ValidCardAction(player);
    assertFalse(action.actionAvailable());
  }

  /**
   * Tests that validateCardMove returns true if the given CardTurnInAction is
   * valid.
   */
  @Test
  public void testValidCardMove() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.addCard(1);
    ValidCardAction action = new ValidCardAction(player);
    CardTurnInAction card = new CardTurnInAction(Arrays.asList(2, 1), player);
    assertTrue(action.validateCardMove(card));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that validateCardMove returns false if the given CardTurnInAction is
   * false.
   */
  @Test
  public void testInValidCardMove() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.addCard(1);
    ValidCardAction action = new ValidCardAction(player);
    CardTurnInAction card = new CardTurnInAction(Arrays.asList(2, 2, 1),
        player);
    assertFalse(action.validateCardMove(card));
    assertTrue(action.actionAvailable());
  }

  /**
   * Tests that validateCardMove returns false if the given CardTurnInAction is
   * valid.
   */
  @Test
  public void testInValidCardMoveBadPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.addCard(1);
    player2.addCard(2);
    player2.addCard(1);
    ValidCardAction action = new ValidCardAction(player);
    CardTurnInAction card = new CardTurnInAction(player.getCards(), player2);
    assertFalse(action.validateCardMove(card));
    assertTrue(action.actionAvailable());
  }
}
