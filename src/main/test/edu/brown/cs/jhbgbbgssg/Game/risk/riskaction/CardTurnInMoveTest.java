package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for CardTurnInAction.
 *
 * @author sarahgilmore
 *
 */
public class CardTurnInMoveTest {

  /**
   * Tests constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(1);
    CardTurnInAction cardMove = new CardTurnInAction(player.getCards(), player);
    assertNotNull(cardMove);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the player is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayer() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(1);
    player.addCard(2);
    CardTurnInAction cardMove = new CardTurnInAction(player.getCards(), null);
    assertNotNull(cardMove);
  }

  /**
   * Tests getCard returns the card value.
   */
  @Test
  public void testGetCard() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.addCard(1);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    CardTurnInAction cardMove = new CardTurnInAction(Arrays.asList(2), player);
    assertTrue(cardMove.getCards().size() == 1);
    assertTrue(cardMove.getCards().get(0) == 2);
  }

  /**
   * Tests executeAction returns true on the first call.
   */
  @Test
  public void testExecuteActionTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    CardTurnInAction cardMove = new CardTurnInAction(player.getCards(), player);
    assertTrue(cardMove.executeAction());
  }

  /**
   * Tests executeAction returns false after the first call.
   */
  @Test
  public void testExecuteActionFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    CardTurnInAction cardMove = new CardTurnInAction(player.getCards(), player);
    assertTrue(cardMove.executeAction());
    assertFalse(cardMove.executeAction());
  }

  /**
   * Tests that executeAction actually turns in the card and reinforces the
   * proper territories.
   */
  @Test
  public void testExecuteAction() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    CardTurnInAction cardMove = new CardTurnInAction(player.getCards(), player);
    assertTrue(player.hasCard(2));
    assertTrue(cardMove.executeAction());
    assertFalse(player.hasCard(2));
  }

  /**
   * Tests that calling executeAction twice has no effect after the first call.
   */
  @Test
  public void testExecuteActionTwice() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    CardTurnInAction cardMove = new CardTurnInAction(player.getCards(), player);
    assertTrue(player.hasCard(2));
    assertTrue(cardMove.executeAction());
    assertFalse(player.hasCard(2));
    assertFalse(cardMove.executeAction());
    assertFalse(player.hasCard(2));
  }

  /**
   * Tests isActionExecuted returns false if the action has not been executed
   * yet.
   */
  @Test
  public void testIsActionExecutedFalse() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    player.conqueredTerritory(TerritoryEnum.GREENLAND);
    CardTurnInAction cardMove = new CardTurnInAction(Arrays.asList(2), player);
    assertFalse(cardMove.isActionExecuted());
  }

  /**
   * Tests isActionExecuted returns false if the action has been executed yet.
   */
  @Test
  public void testIsActionExecutedTrue() {
    RiskPlayer player = new RiskPlayer(UUID.randomUUID());
    player.addCard(2);
    player.addCard(2);
    CardTurnInAction cardMove = new CardTurnInAction(player.getCards(), player);
    cardMove.executeAction();
    assertTrue(cardMove.isActionExecuted());
  }
}
