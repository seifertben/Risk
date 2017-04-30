package edu.brown.cs.jhbgbssg.Game.risk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for RiskPlayer.
 *
 * @author sarahgilmore
 *
 */
public class RiskPlayerTest {

  /**
   * Tests that the RiskPlayer Constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    assertNotNull(player);
  }

  /**
   * Tests initial state of player.
   */
  @Test
  public void testInitialState() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    assertTrue(player.getCards().size() == 0);
    assertTrue(player.getNumberTerritories() == 0);
    assertTrue(player.getTerritories().size() == 0);
  }

  /**
   * Tests adding a Territory to the player set.
   */
  @Test
  public void testAddTerritory() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    assertTrue(player.conqueredTerritory(TerritoryEnum.ALASKA));
    assertTrue(player.hasTerritory(TerritoryEnum.ALASKA));
    assertFalse(player.conqueredTerritory(TerritoryEnum.ALASKA));
  }

  /**
   * Tests adding a null territory throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTerritoryNullInput() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    player.conqueredTerritory(null);
  }

  /**
   * Tests removing a territory from a player's territory set.
   */
  @Test
  public void testLostTerritory() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    player.conqueredTerritory(TerritoryEnum.ALASKA);
    assertTrue(player.lostTerritory(TerritoryEnum.ALASKA));
    assertTrue(player.getNumberTerritories() == 0);
    assertFalse(player.lostTerritory(TerritoryEnum.ALASKA));
  }

  /**
   * Testing removing a null territory throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLostTerritoryNullInput() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    player.lostTerritory(null);
  }

  /**
   * Tests adding a card.
   */
  @Test
  public void testAddCard() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    assertTrue(player.addCard(1));
    assertTrue(player.getCards().size() == 1);
    assertTrue(player.hasCard(1));
  }

  /**
   * Tests adding multiple cards of the same type.
   */
  @Test
  public void testAddMultipleOfSameCard() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    assertTrue(player.addCard(1));
    assertTrue(player.addCard(1));
    assertTrue(player.getCards().size() == 2);
    assertTrue(player.hasCard(1));
  }

  /**
   * Tests adding multiple cards to the card set.
   */
  @Test
  public void testAddMultipleCards() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    assertTrue(player.addCard(1));
    assertTrue(player.addCard(2));
    assertTrue(player.getCards().size() == 2);
    assertTrue(player.hasCard(1));
    assertTrue(player.hasCard(2));
  }

  /**
   * Tests removing a card.
   */
  @Test
  public void testRemoveCard() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    player.addCard(1);
    assertTrue(player.removeCard(1));
    assertTrue(player.getCards().size() == 0);
    assertFalse(player.removeCard(2));
  }

  /**
   * Tests removing one card but another card with the same value remains.
   */
  @Test
  public void testRemoveOneInstanceOfACard() {
    UUID id = UUID.randomUUID();
    RiskPlayer player = new RiskPlayer(id);
    player.addCard(1);
    player.addCard(1);
    assertTrue(player.removeCard(1));
    assertTrue(player.hasCard(1));
    assertTrue(player.removeCard(1));
    assertFalse(player.hasCard(1));
    assertTrue(player.getCards().size() == 0);
  }
}
