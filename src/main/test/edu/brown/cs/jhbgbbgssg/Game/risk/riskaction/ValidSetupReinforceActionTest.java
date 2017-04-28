package edu.brown.cs.jhbgbbgssg.Game.risk.riskaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupReinforceAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for ValidSetupReinforceAction.
 *
 * @author sarahgilmore
 *
 */
public class ValidSetupReinforceActionTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    this.initializeRandomGame(player1, player2, board);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    assertNotNull(action);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException if the player
   * given is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructerNullPlayer() {
    new ValidSetupReinforceAction(null);
  }

  /**
   * Tests that the actionAvailable field is true with the given input in which
   * the player has territories and that the player has 19 more troops to place.
   */
  @Test
  public void testValidFirstReinforce() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    this.initializeRandomGame(player1, player2, board);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    assertTrue(action.actionAvailable());
    assertTrue(action.getTroopsLeftToPlace() == 19);
    assertTrue(player1.getTerritories().containsAll(action.getTerritories()));
    assertTrue(action.getTerritories().containsAll(player1.getTerritories()));
  }

  @Test
  public void testInvalidFirstReinforceNoTerritories() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    player1.setIntialReinforcement(2);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    assertFalse(action.actionAvailable());
    assertTrue(action.getTroopsLeftToPlace() == 40);
    assertTrue(action.getTerritories().size() == 0);
    assertTrue(player1.getTerritories().size() == 0);
  }

  @Test
  public void testInvalidFirstReinforceNoTroops() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    player1.conqueredTerritory(TerritoryEnum.YAKUTSK);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    assertFalse(action.actionAvailable());
    assertTrue(action.getTroopsLeftToPlace() == 0);
    assertTrue(action.getTerritories().size() == 1);
    assertTrue(player1.getTerritories().size() == 1);
    assertTrue(player1.getTerritories().containsAll(action.getTerritories()));
    assertTrue(action.getTerritories().containsAll(player1.getTerritories()));
  }

  @Test
  public void testValidSetupReinforceAction() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    this.initializeRandomGame(player1, player2, board);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    TerritoryEnum terr = (TerritoryEnum) player1.getTerritories().toArray()[0];
    SetupReinforceAction reinforce = new SetupReinforceAction(player1, board,
        terr);
    assertTrue(action.validSetupReinforceMove(reinforce));
  }

  @Test
  public void testInvalidTerritorySetupReinforceAction() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    this.initializeRandomGame(player1, player2, board);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    TerritoryEnum terr = (TerritoryEnum) player2.getTerritories().toArray()[0];
    SetupReinforceAction reinforce = new SetupReinforceAction(player1, board,
        terr);
    assertFalse(action.validSetupReinforceMove(reinforce));
  }

  @Test
  public void testInvalidPlayerSetupReinforceAction() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    this.initializeRandomGame(player1, player2, board);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    TerritoryEnum terr = (TerritoryEnum) player1.getTerritories().toArray()[0];
    SetupReinforceAction reinforce = new SetupReinforceAction(player2, board,
        terr);
    assertFalse(action.validSetupReinforceMove(reinforce));
  }

  @Test
  public void testValidSetupReinforceActionNotAvailableNoTerrs() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    player1.setIntialReinforcement(2);
    RiskBoard board = new RiskBoard();
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    assertFalse(action.actionAvailable());
    SetupReinforceAction reinforce = new SetupReinforceAction(player1, board,
        TerritoryEnum.ALASKA);
    assertFalse(action.validSetupReinforceMove(reinforce));
    assertFalse(action.actionAvailable());
  }

  @Test
  public void testValidSetupReinforceActionNotAvailableNoTroops() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    player1.conqueredTerritory(TerritoryEnum.YAKUTSK);
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.YAKUTSK).changePlayer(player1, 2);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    assertFalse(action.actionAvailable());
    SetupReinforceAction reinforce = new SetupReinforceAction(player1, board,
        TerritoryEnum.YAKUTSK);
    assertFalse(action.validSetupReinforceMove(reinforce));
  }

  @Test
  public void testValidSetupReinforceActionAllTerritories() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskBoard board = new RiskBoard();
    this.initializeRandomGame(player1, player2, board);
    ValidSetupReinforceAction action = new ValidSetupReinforceAction(player1);
    for (TerritoryEnum terr : player1.getTerritories()) {
      SetupReinforceAction reinforce = new SetupReinforceAction(player1, board,
          terr);
      assertTrue(action.validSetupReinforceMove(reinforce));
    }
  }

  /**
   *
   * @param player1
   * @param player2
   * @param board
   */
  private void initializeRandomGame(RiskPlayer player1, RiskPlayer player2,
      RiskBoard board) {
    Set<TerritoryEnum> terrs = board.getTerritoryIds();
    ArrayList<TerritoryEnum> arr = new ArrayList<>(terrs);
    Collections.shuffle(arr);
    player1.setIntialReinforcement(2);
    player2.setIntialReinforcement(2);
    for (int i = 0; i < arr.size(); i++) {
      if (i % 2 == 0) {
        player1.conqueredTerritory(arr.get(i));
        board.getTerritory(arr.get(i)).changePlayer(player1, 1);
        player1.decrementInitialReinforcements(1);
      } else {
        player2.conqueredTerritory(arr.get(i));
        board.getTerritory(arr.get(i)).changePlayer(player2, 1);
        player2.decrementInitialReinforcements(1);
      }
    }
  }
}
