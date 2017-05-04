package edu.brown.cs.jhbgbssg.Game.risk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import edu.brown.cs.jhbgbssg.Game.GameUpdate;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssg.Game.RiskWorld.continent.ContinentEnum;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.DefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupReinforceAction;

/**
 * JUnit tests for referee.
 *
 * @author sarahgilmore
 *
 */
public class RefereeTest {
  private static final int MAX_PLAYERS = 6;

  /**
   * Tests that the constructor of Referee returns a non-null object.
   */
  @Test
  public void testConstructor() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assertNotNull(ref);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the board is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBoard() {
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    new Referee(null, ImmutableSet.of(player1, player2));
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the board is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPlayerSet() {
    RiskBoard board = new RiskBoard();
    new Referee(board, null);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the number of
   * players is less than 2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorTooFewPlayers() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    new Referee(board, ImmutableSet.of(player1));
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the number of
   * players is more than 7.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorTooManyPlayers() {
    RiskBoard board = new RiskBoard();
    Set<RiskPlayer> players = new HashSet<>();
    for (int i = 0; i < MAX_PLAYERS + 1; i++) {
      RiskPlayer player = new RiskPlayer(UUID.randomUUID());
      players.add(player);
    }
    new Referee(board, players);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the starting
   * state of the board is illegal.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalStartingBoard() {
    RiskBoard board = new RiskBoard();
    board.getTerritory(TerritoryEnum.ALASKA)
        .changePlayer(new RiskPlayer(UUID.randomUUID()), 3);
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    new Referee(board, ImmutableSet.of(player1, player2));
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the starting
   * state of the board is illegal.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalStartingPlayerState() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    player1.conqueredTerritory(TerritoryEnum.GREAT_BRITIAN);
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    new Referee(board, ImmutableSet.of(player1, player2));
  }

  /**
   * Tests getPlayerOrder returns a non-null list with the correct number of
   * elements.
   */
  @Test
  public void testGetPlayerOrder() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assertNotNull(ref.getPlayerOrder());
    assertTrue(ref.getPlayerOrder().size() == 2);
  }

  /**
   * Tests isWinner throws an IllegalArgumentException if the player given is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIsWinnerNullPlayer() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assert (ref.isWinner(null));
  }

  /**
   * Tests isWinner throws an IllegalArgumentException if the player given is
   * not part of the game.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIsWinnerPlayerNotPartOfGame() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player3 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assert (ref.isWinner(player3));
  }

  /**
   * Tests isWinner returns true and the number of players in the order list is
   * 1 if the player has all of the territories.
   */
  @Test
  public void testIsWinnerTrue() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    for (TerritoryEnum id : TerritoryEnum.values()) {
      player1.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player1, 1);
    }
    assertTrue(ref.playerLost(player2));
    assertTrue(ref.isWinner(player1));
    assertTrue(ref.getPlayerOrder().size() == 1);
  }

  /**
   * Tests isWinner returns false and the number of players in the order list is
   * not 1 if the player does not all of the territories.
   */
  @Test
  public void testIsWinnerFalse() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));

    for (TerritoryEnum id : ContinentEnum.AFRICA.getTerrs()) {
      player1.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player1, 1);
    }
    assertFalse(ref.isWinner(player1));
    assertTrue(ref.getPlayerOrder().size() == 2);
  }

  /**
   * Tests playerLost throws an IllegalArgumentException if the player given is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testplayerLostNullPlayer() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assert (ref.playerLost(null));
  }

  /**
   * Tests playerLost throws an IllegalArgumentException if the player given is
   * not part of the game.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testplayerLostPlayerNotPartOfGame() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player3 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assert (ref.playerLost(player3));
  }

  /**
   * Tests that playerLost returns true if the player has no territories.
   */
  @Test
  public void testPlayerLostTrue() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assertTrue(ref.playerLost(player1));
    assertTrue(ref.getPlayerOrder().size() == 1);
  }

  /**
   * Tests that playerLost returns false if the player has territories.
   */
  @Test
  public void testPlayerLostFalse() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    for (TerritoryEnum id : ContinentEnum.AFRICA.getTerrs()) {
      player1.conqueredTerritory(id);
      board.getTerritory(id).changePlayer(player1, 2);
    }
    assertFalse(ref.playerLost(player1));
    assertTrue(ref.getPlayerOrder().size() == 2);
  }

  /**
   * Tests validateSetup throws an IllegalArgumentException if the input is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateSetupActionNullInput() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.validateSetupMove(null);
  }

  /**
   * Tests validateReinforceSetup throws an IllegalArgumentException if the
   * input is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateSetupReinforceActionNullInput() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.validateSetupReinforceMove(null);
  }

  /**
   * Tests validateCardTurnIn throws an IllegalArgumentException if the input is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateCardTurnInNullInput() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.validateCardTurnIn(null);
  }

  /**
   * Tests validateReinforce throws an IllegalArgumentException if the input is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateReinforceNullInput() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.validateReinforce(null);
  }

  /**
   * Tests validateAttack throws an IllegalArgumentException if the input is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateAttackNullInput() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.validateAttackMove(null);
  }

  /**
   * Tests validateAttack throws an IllegalArgumentException if the input is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateDefendNullInput() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.validateDefendMove(null);
  }

  /**
   * Tests validateClaimTerritory throws an IllegalArgumentException if the
   * input is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateClaimActionNullInput() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.validateClaimTerritory(null);
  }

  /**
   * Tests validateMoveTroopsMove throws an IllegalArgumentException if the
   * input is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testValidateMoveTroopsActionNullInput() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.validateMoveTroopsMove(null);
  }

  /**
   * Tests startGame returns a GameUpdate with a validSetupAction on the first
   * call.
   */
  @Test
  public void testStartGame() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assertFalse(ref.gameStarted());
    GameUpdate update = ref.startGame();
    assertNotNull(update);
    assertTrue(update.getValidMoves().getMoveType() == MoveType.SETUP);
    assertTrue(update.getValidMoves().getMovePlayer()
        .equals(ref.getPlayerOrder().get(0)));
    ValidSetupAction action = (ValidSetupAction) update.getValidMoves();
    List<TerritoryEnum> terrs = new ArrayList<>();
    for (TerritoryEnum terr : TerritoryEnum.values()) {
      terrs.add(terr);
    }
    assertTrue(action.getTerritories().containsAll(terrs));
    assertTrue(terrs.containsAll(action.getTerritories()));
    assertTrue(ref.gameStarted());
  }

  /**
   * Tests the startGame returns null after the first call.
   */
  @Test
  public void testStartGameReturnsNull() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    assertNotNull(ref.startGame());
    assertNull(ref.startGame());
  }

  /**
   * Tests validateSetupMove returns true if the given action is valid and false
   * for any other type of action is trying to be validated.
   */
  @Test
  public void testValidateSetupAction() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.startGame();
    SetupAction action =
        new SetupAction(ref.getCurrentPlayer(), board, TerritoryEnum.ALASKA);
    assertTrue(ref.validateSetupMove(action));
    SetupReinforceAction setupReinforce = new SetupReinforceAction(
        ref.getCurrentPlayer(), board, TerritoryEnum.ALASKA);
    assertFalse(ref.validateSetupReinforceMove(setupReinforce));
    Map<TerritoryEnum, Integer> map = new HashMap<>();
    map.put(TerritoryEnum.ALASKA, 3);
    ReinforceAction reinforce =
        new ReinforceAction(ref.getCurrentPlayer(), board, map);
    assertFalse(ref.validateReinforce(reinforce));
    CardTurnInAction card =
        new CardTurnInAction(Arrays.asList(1), ref.getCurrentPlayer());
    assertFalse(ref.validateCardTurnIn(card));
    AttackAction attack = new AttackAction(ref.getCurrentPlayer(),
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 2);
    assertFalse(ref.validateAttackMove(attack));
    if (player1.equals(ref.getCurrentPlayer())) {
      DefendAction defend = new DefendAction(player2, board, attack, 2);
      assertFalse(ref.validateDefendMove(defend));
    } else {
      DefendAction defend = new DefendAction(player1, board, attack, 2);
      assertFalse(ref.validateDefendMove(defend));
    }
    ClaimTerritoryAction claim =
        new ClaimTerritoryAction(ref.getCurrentPlayer(), board,
            TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 2);
    assertFalse(ref.validateClaimTerritory(claim));
    MoveTroopsAction move = new MoveTroopsAction(ref.getCurrentPlayer(), board,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
    assertFalse(ref.validateMoveTroopsMove(move));
  }

  /**
   * Tests the switching between the Setup and SetupReinforce phases.
   */
  @Test
  public void testswitchPhaseToSetupReinforce() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.startGame();
    RiskPlayer currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupAction validAction = (ValidSetupAction) ref.getValidMove();
      SetupAction action = new SetupAction(currPlayer, board,
          validAction.getTerritories().get(0));
      assertTrue(ref.validateSetupMove(action));
      assertTrue(action.executeAction());
      ref.switchPlayer(action);
      assertFalse(currPlayer.equals(ref.getCurrentPlayer()));
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP);
    for (Territory terr : board.getTerritories()) {
      assertTrue(terr.occuppied() && terr.getNumberTroops() == 1);
    }
    currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupReinforceAction validAction =
          (ValidSetupReinforceAction) ref.getValidMove();
      List<TerritoryEnum> terrs = new ArrayList<>(validAction.getTerritories());
      SetupReinforceAction action =
          new SetupReinforceAction(currPlayer, board, terrs.get(0));
      assertTrue(ref.validateSetupReinforceMove(action));
      assertTrue(action.executeAction());
      ref.switchPlayer(action);
      assertFalse(currPlayer.equals(ref.getCurrentPlayer()));
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP_REINFORCE);
    assertTrue(player1.getInitialReinforcements() == 0);
    assertTrue(player2.getInitialReinforcements() == 0);
  }

  /**
   * Tests that after setup and setup reinforce, the next valid action is of
   * type REINFORCE, not CARD_TURN_IN.
   */
  @Test
  public void testValidActionAfterSetupReinforceIsReinforce() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.startGame();
    RiskPlayer currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupAction validAction = (ValidSetupAction) ref.getValidMove();
      SetupAction action = new SetupAction(currPlayer, board,
          validAction.getTerritories().get(0));
      action.executeAction();
      ref.switchPlayer(action);
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP);
    currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupReinforceAction validAction =
          (ValidSetupReinforceAction) ref.getValidMove();
      List<TerritoryEnum> terrs = new ArrayList<>(validAction.getTerritories());
      SetupReinforceAction action =
          new SetupReinforceAction(currPlayer, board, terrs.get(0));
      ref.validateSetupReinforceMove(action);
      action.executeAction();
      ref.switchPlayer(action);
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP_REINFORCE);
    assertTrue(ref.getValidMove().getMoveType() == MoveType.REINFORCE);
  }

  /**
   * Tests skipping is invalid during the setup.
   */
  @Test
  public void testSkipInvalidSetup() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.startGame();
    RiskPlayer currPlayer = ref.getCurrentPlayer();
    assertFalse(ref.validSkipMove(currPlayer));
    assertTrue(ref.getValidMove().getMoveType() == MoveType.SETUP);
  }

  /**
   * Tests skipping is invalid during setup reinforce.
   */
  @Test
  public void testSkipInvalidSetupReinforce() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.startGame();
    RiskPlayer currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupAction validAction = (ValidSetupAction) ref.getValidMove();
      SetupAction action = new SetupAction(currPlayer, board,
          validAction.getTerritories().get(0));
      action.executeAction();
      ref.switchPlayer(action);
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP);
    currPlayer = ref.getCurrentPlayer();
    assertTrue(ref.getValidMove().getMoveType() == MoveType.SETUP_REINFORCE);
    assertFalse(ref.validSkipMove(currPlayer));
  }

  /**
   * Tests skipping a reinforce action is invalid.
   */
  @Test
  public void testSkipInvalidReinforce() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.startGame();
    RiskPlayer currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupAction validAction = (ValidSetupAction) ref.getValidMove();
      SetupAction action = new SetupAction(currPlayer, board,
          validAction.getTerritories().get(0));
      action.executeAction();
      ref.switchPlayer(action);
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP);
    currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupReinforceAction validAction =
          (ValidSetupReinforceAction) ref.getValidMove();
      List<TerritoryEnum> terrs = new ArrayList<>(validAction.getTerritories());
      SetupReinforceAction action =
          new SetupReinforceAction(currPlayer, board, terrs.get(0));
      ref.validateSetupReinforceMove(action);
      action.executeAction();
      ref.switchPlayer(action);
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP_REINFORCE);
    assertTrue(ref.getValidMove().getMoveType() == MoveType.REINFORCE);
    assertFalse(ref.validSkipMove(currPlayer));
  }

  /**
   * Tests that skipping an attack is valid.
   */
  @Test
  public void testSkipValidAttack() {
    RiskBoard board = new RiskBoard();
    RiskPlayer player1 = new RiskPlayer(UUID.randomUUID());
    RiskPlayer player2 = new RiskPlayer(UUID.randomUUID());
    Referee ref = new Referee(board, ImmutableSet.of(player1, player2));
    ref.startGame();
    RiskPlayer currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupAction validAction = (ValidSetupAction) ref.getValidMove();
      SetupAction action = new SetupAction(currPlayer, board,
          validAction.getTerritories().get(0));
      action.executeAction();
      ref.switchPlayer(action);
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP);
    currPlayer = ref.getCurrentPlayer();
    do {
      ValidSetupReinforceAction validAction =
          (ValidSetupReinforceAction) ref.getValidMove();
      List<TerritoryEnum> terrs = new ArrayList<>(validAction.getTerritories());
      SetupReinforceAction action =
          new SetupReinforceAction(currPlayer, board, terrs.get(0));
      ref.validateSetupReinforceMove(action);
      action.executeAction();
      ref.switchPlayer(action);
      currPlayer = ref.getCurrentPlayer();
    } while (ref.getValidMove().getMoveType() == MoveType.SETUP_REINFORCE);
    ValidReinforceAction action = (ValidReinforceAction) ref.getValidMove();
    int total = action.getNumberToReinforce();
    ReinforceAction reinforce;
    Set<TerritoryEnum> terrs = action.getTerritories();
    for (TerritoryEnum terr : terrs) {
      if (!terrs.containsAll(board.getNeighbors(terr))) {
        Map<TerritoryEnum, Integer> map = new HashMap<>();
        map.put(terr, total);
        reinforce = new ReinforceAction(currPlayer, board, map);
        assertTrue(ref.validateReinforce(reinforce));
        assertTrue(reinforce.executeAction());
        ValidAction next = ref.getValidMoveAfterReinforce();
        assertNotNull(next);
        assertTrue(next.getMoveType() == MoveType.CHOOSE_ATTACK_DIE);
        assertTrue(ref.validSkipMove(currPlayer));
        break;
      }
    }
  }
}
