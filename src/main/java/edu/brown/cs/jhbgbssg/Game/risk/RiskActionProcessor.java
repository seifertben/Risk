package edu.brown.cs.jhbgbssg.Game.risk;

import edu.brown.cs.jhbgbssg.Game.risk.riskaction.Action;
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
   * Initializes the game state.
   *
   */
  public RiskActionProcessor(Referee referee) {
    this.referee = referee;
  }

  public GameUpdate processSetupAction(SetupAction action) {
    GameUpdate update = new GameUpdate();
    boolean isValidMove = referee.validateSetupMove(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    ValidAction nextValidMove = (ValidAction) referee
        .getValidMoveAfterSetup(action.getMovePlayer(), action);
    if (nextValidMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, action, false);
    return update;
  }

  public GameUpdate executeSetupReinforceAction(SetupReinforceAction action) {
    GameUpdate update = new GameUpdate();
    boolean isValidMove = referee.validateSetupReinforceMove(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    ValidAction nextValidMove = referee
        .getValidMoveAfterReinforceSetup(action.getMovePlayer(), action);
    if (nextValidMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, action, false);
    return update;
  }

  /**
   * This method executes a reinforce action. It first checks that the given
   * player can make such an action; if so, it executes it. Otherwise, it does
   * not and returns an error messaging indicating the move was not valid.
   *
   * @param move of troops to place on the territory
   * @return GameUpdate object representing what happened
   */
  public GameUpdate executeReinforceAction(ReinforceAction move) {
    GameUpdate update = new GameUpdate();
    boolean isValidMove = referee.validateReinforce(move);
    if (!isValidMove) { // not a valid move
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    move.executeAction();
    ValidAction nextValidMove = referee
        .getValidMoveAfterReinforce(move.getMovePlayer());
    if (nextValidMove == null) {
      return this.switchPlayers(move, move.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, move, false);
    return update;
  }

  /**
   * Executes a card turn in. If the the move is valid, the game will execute
   * it. Otherwise, it will return an error.
   *
   * @param move
   * @return game update
   */
  public GameUpdate executeCardTurnInAction(CardTurnInAction move) {
    GameUpdate update = new GameUpdate();
    boolean isValidMove = referee.validateCardTurnIn(move);
    if (!isValidMove) { // move is not valid
      update.setValidMoves(referee.getValidMove(), null, true);
      return update;
    }
    move.executeAction();
    ValidAction nextValidMove = referee
        .getValidMoveAfterCardTurnIn(move.getMovePlayer());
    if (nextValidMove == null) {
      return this.switchPlayers(move, move.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, move, false);
    return update;
  }

  /**
   * Executes an attack.
   *
   * @param action - action
   * @return game update
   */
  public GameUpdate executeAttackAction(AttackAction action) {
    GameUpdate update = new GameUpdate();
    boolean isValidMove = referee.validateAttackMove(action);
    if (isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    ValidAction validMove = referee.getValidMoveAfterAttack(action);
    update.setValidMoves(validMove, action, false);
    return update;
  }

  /**
   *
   * @return game update
   */
  public GameUpdate executeDefendAction(DefendAction move) {
    GameUpdate update = new GameUpdate();
    boolean isValidMove = referee.validateDefendMove(move);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    move.executeAction();
    if (move.getDefenderLostTerritory()) {
      if (referee.playerLost(move.getMovePlayer())) {
        update.setLostGame(move.getMovePlayer().getPlayerId());
      }
      ValidAction nextValidMove = referee.getValidMoveAfterDefend(move);
      update.setValidMoves(nextValidMove, move, false);
      return update;
    } else {
      ValidAction nextValidMove = referee.getValidMoveAfterDefend(move);
      if (nextValidMove == null) {
        return this.switchPlayers(move, move.getAttackerId());
      }
      update.setValidMoves(nextValidMove, move, false);
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
   */
  public GameUpdate executeClaimTerritory(ClaimTerritoryAction action) {
    GameUpdate update = new GameUpdate();
    boolean isValidMove = referee.validateClaimTerritory(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    if (referee.isWinner(action.getMovePlayer())) {
      update.setWonGame(action.getMovePlayer().getPlayerId());
    }
    ValidAction validNextMove = referee
        .getValidMoveAfterClaimTerritory(action.getMovePlayer());
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
   * @return GameUpdate specifying what happend and the next possible move
   */
  public GameUpdate executeMoveTroops(MoveTroopsAction action) {
    GameUpdate update = new GameUpdate();
    boolean isValidMove = referee.validateMoveTroopsMove(action);
    if (!isValidMove) {
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();
    return this.switchPlayers(action, action.getMovePlayer());
  }

  public GameUpdate executeSkipPhase(RiskPlayer player) {
    MoveType phase = referee.getValidMove().getMoveType();
    GameUpdate update = new GameUpdate();
    switch (phase) {
      case TURN_IN_CARD:
        // find next valid move
        break;
      case CHOOSE_ATTACK_DIE:
        // find next valid move
        break;
      case MOVE_TROOPS:
        return this.switchPlayers(null, player);
      default:
        ValidAction valid = referee.getValidMove();
        update.setValidMoves(valid, null, true);
        return update;
    }
    return null;
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
    ValidAction action = referee.switchPlayer();
    update.setValidMoves(action, prevMove, false);
    return update;
  }
}
