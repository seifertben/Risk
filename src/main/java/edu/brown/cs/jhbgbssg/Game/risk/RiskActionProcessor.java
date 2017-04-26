package edu.brown.cs.jhbgbssg.Game.risk;

import edu.brown.cs.jhbgbssg.Game.risk.riskaction.Action;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.DefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAction;

/**
 * Stores the state of the game.
 *
 * @author Ben
 *
 */
public class RiskActionProcessor {

  private Referee referee;
  private int cardToHandOut = -1;

  /**
   * Constructor for RiskActionProcessor. It takes in a referee it uses to
   * validate the actions before executing them.
   *
   * @param referee - referee for the Risk Game
   * @throws IllegalArgumentException if the input is null
   */
  public RiskActionProcessor(Referee referee) throws IllegalArgumentException {
    if (referee == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.referee = referee;
  }

  /**
   * Processes a set up action and returns a game update indicating what
   * happened.
   *
   * @param action - set up action to execute
   * @return GameUpdate
   * @throws IllegalArgumentException - if the input is null
   */
  public GameUpdate processSetupAction(SetupAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) {
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateSetupMove(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    ValidAction nextValidMove = referee.getValidMoveAfterSetup();
    if (nextValidMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, action, false);
    return update;
  }

  /**
   * Processes a setup reinforce action and returns a game update indicating
   * what happened.
   *
   * @param action - set up reinforce action
   * @return game update
   * @throws IllegalArgumentException - if the input is null
   */
  public GameUpdate processSetupReinforceAction(SetupReinforceAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) {
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateSetupReinforceMove(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    ValidAction nextValidMove = referee.getValidMoveAfterReinforceSetup();
    if (nextValidMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, action, false);
    return update;
  }

  /**
   * This method processes a reinforce action. It first checks that the given
   * player can make such an action; if so, it executes it. Otherwise, it does
   * not and returns an error messaging indicating the move was not valid.
   *
   * @param action of troops to place on the territory
   * @return GameUpdate object representing what happened
   * @throws IllegalArgumentException if the input is null
   */
  public GameUpdate processReinforceAction(ReinforceAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) {
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateReinforce(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    ValidAction nextValidMove = referee.getValidMoveAfterReinforce();
    if (nextValidMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, action, false);
    return update;
  }

  /**
   * Executes a card turn in. If the the move is valid, the game will execute
   * it. Otherwise, it will return an error.
   *
   * @param action - card turn in action
   * @return game update
   * @throws IllegalArgumentException - if the input is null
   */
  public GameUpdate processCardTurnInAction(CardTurnInAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) {
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateCardTurnIn(action);
    if (!isValidMove) { // move is not valid
      update.setValidMoves(referee.getValidMove(), null, true);
      return update;
    }
    action.executeAction();
    ValidAction nextValidMove = referee.getValidMoveAfterCardTurnIn();
    if (nextValidMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, action, false);
    return update;
  }

  /**
   * Executes an attack.
   *
   * @param action - action
   * @return game update
   * @throws IllegalArgumentException - if the input is null
   */
  public GameUpdate processAttackAction(AttackAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) {
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateAttackMove(action);
    if (isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    ValidAction validMove = referee.getValidMoveAfterAttack();
    update.setValidMoves(validMove, action, false);
    return update;
  }

  /**
   *
   * @param action - defend action
   * @return game update
   * @throws IllegalArgumentException - if the input is null
   */
  public GameUpdate processDefendAction(DefendAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) {
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateDefendMove(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    if (action.getDefenderLostTerritory()) {
      if (referee.playerLost(action.getMovePlayer())) {
        update.setLostGame(action.getMovePlayer().getPlayerId());
      }
      ValidAction nextValidMove = referee.getValidMoveAfterDefend(action);
      update.setValidMoves(nextValidMove, action, false);
      return update;
    } else {
      ValidAction nextValidMove = referee.getValidMoveAfterDefend(action);
      if (nextValidMove == null) {
        return this.switchPlayers(action, action.getAttackerId());
      }
      update.setValidMoves(nextValidMove, action, false);
      return update;
    }
  }

  /**
   * Executes a claim territory move. A player claims a territory if, during an
   * attack, the number of troops on the defending territory decreases to 0.
   * This move checks that the claim territory move is valid, and executes if
   * so.
   *
   * @param action - action
   * @return update specifying what happened
   * @throws IllegalArgumentException - if the input is null
   */
  public GameUpdate processClaimTerritoryAction(ClaimTerritoryAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) {
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateClaimTerritory(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    if (referee.isWinner(action.getMovePlayer())) {
      update.setWonGame(action.getMovePlayer().getPlayerId());
      update.setValidMoves(null, action, false);
      return update;
    }
    ValidAction validNextMove = referee.getValidMoveAfterClaimTerritory();
    if (validNextMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(validNextMove, action, false);
    return update;
  }

  /**
   * This method moves the specified number of troops from a player's territory
   * to another an adjacent one. If the move is valid, it will execute.
   * Otherwise, the move will not be executed and RiskGame will return an error
   * in the GameUpdate object.
   *
   * @param action - action
   * @return GameUpdate specifying what happened and the next possible move
   * @throws IllegalArgumentException - if the input is null
   */
  public GameUpdate processMoveTroopsAction(MoveTroopsAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) {
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateMoveTroopsMove(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    return this.switchPlayers(action, action.getMovePlayer());
  }

  /**
   * Processes a skip action.
   *
   * @param player - player trying to skip an action
   * @return game update
   * @throws IllegalArgumentException if the input is null
   */
  public GameUpdate processSkipAction(RiskPlayer player)
      throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (referee.validSkipMove(player)) {
      ValidAction action = referee.getActionAfterSkip();
      if (action == null) {
        return this.switchPlayers(null, player);
      } else {
        GameUpdate update = new GameUpdate();
        update.setValidMoves(action, null, false);
        return update;
      }
    } else {
      GameUpdate update = new GameUpdate();
      update.setValidMoves(referee.getValidMove(), null, true);
      return update;
    }
  }

  /**
   * Switches the current player.
   *
   * @param prevMove
   * @return game update
   */
  private GameUpdate switchPlayers(Action prevMove, RiskPlayer player) {
    GameUpdate update = new GameUpdate();
    if (cardToHandOut > 0) {
      update.setCardToHandOut(player.getPlayerId(), cardToHandOut,
          referee.emptyCardDeck());
      cardToHandOut = -1;
    }
    ValidAction action = referee.switchPlayer(prevMove);
    update.setValidMoves(action, prevMove, false);
    update.playerChanged();
    return update;
  }
}
