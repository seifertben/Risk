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
 * RiskActionProcessor processes the different actions of a risk game. If the
 * given action is valid according to the referee, the processor calls
 * executeAction on the the given action and asks the referee for the next valid
 * actions. The action executed and the next valid actions are then returned in
 * a GameUpdate object, including any other necessary information.
 *
 * @author Ben
 *
 */
public class RiskActionProcessor {

  private Referee referee;

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
   * Processes a set up action and returns a game update, including the
   * SetupAction if it was executed. If the action was not valid, it, the update
   * object's error field is set to true and the nextValidMove sent back is the
   * same one used to check the SetupAction.
   *
   * @param action - set up action to execute
   * @return GameUpdate - update object
   * @throws IllegalArgumentException - if the input is null
   */
  public GameUpdate processSetupAction(SetupAction action)
      throws IllegalArgumentException {
    if (action == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) { // game is already over
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateSetupMove(action);
    if (!isValidMove) { // not a valid move
      ValidAction validMove = referee.getValidMove(); // defines valid moves
      update.setValidMoves(validMove, null, true); // sets the fields
      return update;
    }
    action.executeAction(); // execute valid action

    // player switches after selecting a territory
    update = this.switchPlayers(action, action.getMovePlayer());
    return update;
  }

  /**
   * Processes a setup reinforce action and returns a game update, including the
   * SetupReinforceAction if it was executed. If the action was not valid, it,
   * the update object's error field is set to true and the nextValidMove sent
   * back is the same one used to check the SetupReinforceAction.
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
    if (referee.getWinner() != null) { // game already over
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateSetupReinforceMove(action);
    if (!isValidMove) { // move is not valid
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction(); // execute move

    // after reinforcing 1 territory during the setup phase, player switches
    update = this.switchPlayers(action, action.getMovePlayer());
    return update;
  }

  /**
   * Processes a card turn in action and returns a game update, including the
   * SetupReinforceAction if it was executed. If the action was not valid, it,
   * the update object's error field is set to true and the nextValidMove sent
   * back is the same one used to check the CardTurnInAction.
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
    if (referee.getWinner() != null) { // game is already over
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateCardTurnIn(action);
    if (!isValidMove) { // move is not valid
      update.setValidMoves(referee.getValidMove(), null, true);
      return update;
    }
    action.executeAction(); // execute action
    ValidAction nextValidMove =
        referee.getValidMoveAfterCardTurnIn(action.getCards());

    // if the player has no other moves after turning in a card, switch players
    if (nextValidMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, action, false);
    return update;
  }

  /**
   * Processes a reinforce action and returns a game update, including the
   * SetupReinforceAction if it was executed. If the action was not valid, it,
   * the update object's error field is set to true and the nextValidMove sent
   * back is the same one used to check the ReinforceAction.
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
    if (referee.getWinner() != null) { // game is already over
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateReinforce(action);
    if (!isValidMove) { // action is not valid
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction(); // execute action
    ValidAction nextValidMove = referee.getValidMoveAfterReinforce();

    // if the player has no moves available, switch players
    if (nextValidMove == null) {
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(nextValidMove, action, false);
    return update;
  }

  /**
   * Processes an AttackAction and returns a game update, including the
   * SetupReinforceAction if it was executed. If the action was not valid, it,
   * the update object's error field is set to true and the nextValidMove sent
   * back is the same one used to check the AttackAction.
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
    if (referee.getWinner() != null) { // game is already won
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateAttackMove(action);
    if (!isValidMove) { // move is not valid
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction(); // execute move

    // after an attack, the next move is always defending
    ValidAction validMove = referee.getValidMoveAfterAttack();
    update.setValidMoves(validMove, action, false);
    return update;
  }

  /**
   * Processes an DefendAction and returns a game update, including the
   * SetupReinforceAction if it was executed. If the action was not valid, it,
   * the update object's error field is set to true and the nextValidMove sent
   * back is the same one used to check the DefendAction.
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
    if (referee.getWinner() != null) { // game is already over
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateDefendMove(action);
    if (!isValidMove) { // defend move is not valid
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction(); // executes an defend action

    // if the territory was lost check if the defender lost the game
    if (action.getDefenderLostTerritory()) {
      if (referee.playerLost(action.getMovePlayer())) {
        update.setLostGame(action.getMovePlayer().getPlayerId());
      }
      ValidAction nextValidMove = referee.getValidMoveAfterDefend(action);
      update.setValidMoves(nextValidMove, action, false);
      return update;
    } else { // the defender did not lose the territory
      ValidAction nextValidMove = referee.getValidMoveAfterDefend(action);
      if (nextValidMove == null) { // if the attacker has no other moves left
        return this.switchPlayers(action, action.getAttackerId());
      }
      update.setValidMoves(nextValidMove, action, false);
      return update;
    }
  }

  /**
   * Processes an ClaimTerritoryAction and returns a game update, including the
   * SetupReinforceAction if it was executed. If the action was not valid, it,
   * the update object's error field is set to true and the nextValidMove sent
   * back is the same one used to check the ClaimTerritoryAction.
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
    if (referee.getWinner() != null) { // game alread over
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateClaimTerritory(action);
    if (!isValidMove) { // move is not valid
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction(); // execute action

    // if player won the game, set field in update
    if (referee.isWinner(action.getMovePlayer())) {
      update.setWonGame(action.getMovePlayer().getPlayerId());
      update.setValidMoves(null, action, false);
      return update;
    }
    ValidAction validNextMove = referee.getValidMoveAfterClaimTerritory();
    if (validNextMove == null) { // switch players
      return this.switchPlayers(action, action.getMovePlayer());
    }
    update.setValidMoves(validNextMove, action, false);
    return update;
  }

  /**
   * Processes an MoveTroopsAction and returns a game update, including the
   * SetupReinforceAction if it was executed. If the action was not valid, it,
   * the update object's error field is set to true and the nextValidMove sent
   * back is the same one used to check the MoveTroopsAction.
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
    if (referee.getWinner() != null) { // game already won
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    boolean isValidMove = referee.validateMoveTroopsMove(action);
    if (!isValidMove) { // move not valid
      ValidAction validMove = referee.getValidMove();
      update.setValidMoves(validMove, null, true);
      return update;
    }
    action.executeAction();

    // always switch players after a MoveTroopsAction
    return this.switchPlayers(action, action.getMovePlayer());
  }

  /**
   * Processes a Skip Action. If skipping the current move is valid, the referee
   * will produce the next valid move. Otherwise, an error is returned. If the
   * player has no other valid moves after skipping, the current player is
   * switched.
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
    GameUpdate update = new GameUpdate();
    if (referee.getWinner() != null) { // game is already over
      update.setWonGame(referee.getWinner().getPlayerId());
      return update;
    }
    if (referee.validSkipMove(player)) { // can skip the move
      ValidAction action = referee.getActionAfterSkip();

      // if the player has no other moves left switch players
      if (action == null) {
        return this.switchPlayers(null, player);
      }
      update.setValidMoves(action, null, false);
      return update;
    } else { // skipping is not valid
      update.setValidMoves(referee.getValidMove(), null, true);
      return update;
    }
  }

  /**
   * Switches the current player. If there is a card to handout, it sets the
   * card field of GameUpdate. If the previous move is null, it calls Referee's
   * switchPlayersAfterSkip in order to change the player. Otherwise, Referee's
   * switchPlayer method is called.
   *
   * @param prevMove - previous move
   * @return game update - game update
   */
  private GameUpdate switchPlayers(Action prevMove, RiskPlayer player) {
    GameUpdate update = new GameUpdate();
    int card = referee.handOutCard();
    if (card > 0) {
      player.addCard(card);
      update.setCardToHandOut(player.getPlayerId(), card,
          !referee.emptyCardDeck());
    }
    ValidAction action;
    if (prevMove == null) {
      action = referee.switchPlayersAfterSkip();
    } else {
      action = referee.switchPlayer(prevMove);
    }
    update.setValidMoves(action, prevMove, false);
    return update;
  }
}
