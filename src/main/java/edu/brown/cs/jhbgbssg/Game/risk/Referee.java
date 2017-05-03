package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multiset;

import edu.brown.cs.jhbgbssg.Game.CardPool;
import edu.brown.cs.jhbgbssg.Game.GameUpdate;
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
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidCardAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidDieDefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidMoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupReinforceAction;
import edu.brown.cs.jhbgbssg.RiskWorld.Territory;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * This class represents the Referee of a Risk Game. It controls the flow of the
 * game, deciding if actions are valid and hands out ValidAction objects which
 * describe the type of action that is valid, who can make the action and the
 * bounds on the action.
 *
 * @author Ben
 *
 */
public class Referee {
  private RiskBoard board;
  private RiskPlayer winner;
  private List<RiskPlayer> turnOrder; // a list representing the turn order
  private Set<RiskPlayer> originalPlayers; // a set of the original players
  private CardPool cardPool;
  private ValidAction validMove = null;
  private RiskPlayer currPlayer;
  private AttackAction lastAttack;
  private boolean gameStarted = false;
  private boolean handoutCard = false;

  /**
   * Constructor for Referee. It takes in the RiskBoard and a collection of
   * RiskPlayers.
   *
   * @param board - risk game board
   * @param players - collection of risk players
   * @throws IllegalArgumentException - if the input is null or if the number of
   *           players is less than 2 or greater than 6 or if the risk board
   *           given has an illegal starting state.
   */
  public Referee(RiskBoard board, Set<RiskPlayer> players)
      throws IllegalArgumentException {
    if (board == null || players == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (players.size() < 2 || players.size() > 6) {
      throw new IllegalArgumentException("ERROR: illegal number of players");
    }
    for (Territory terr : board.getTerritories()) {
      if (terr.occuppied() || terr.getOwner() != null) {
        throw new IllegalArgumentException(
            "ERROR: illegal starting state of a risk board");
      }
    }
    for (RiskPlayer player : players) {
      if (player.getTerritories().size() != 0
          || player.getContinents().size() != 0) {
        throw new IllegalArgumentException(
            "ERROR: illegal starting state of a risk player");
      }
    }
    this.board = board;
    turnOrder = new ArrayList<>(players);
    originalPlayers = new HashSet<>(turnOrder);
    originalPlayers = Collections.unmodifiableSet(originalPlayers);
    Collections.shuffle(turnOrder);
    int numberPlayers = players.size();
    for (RiskPlayer player : turnOrder) {
      player.setIntialReinforcement(numberPlayers);
    }
    currPlayer = turnOrder.get(0);
    cardPool = new RiskCardPool();
    validMove = new ValidSetupAction(turnOrder.get(0), board);
  }

  /**
   * Returns a unmodifiable list of player ids representing the turn order in
   * the game.
   *
   * @return list of player ids
   */
  public List<UUID> getPlayerOrder() {
    List<UUID> playerOrder = new ArrayList<>();
    for (int i = 0; i < turnOrder.size(); i++) {
      playerOrder.add(turnOrder.get(i).getPlayerId());
    }
    return Collections.unmodifiableList(playerOrder);
  }

  /**
   * Removes a player with the given UUID from the game. It returns a boolean
   * indicating if the player was removed from the list.
   *
   * @param playerId Id of player to remove.
   * @return true if the player was removed; false if not
   * @throws IllegalArgumentException if the UUID given is null
   */
  public boolean removePlayer(UUID playerId) throws IllegalArgumentException {
    if (playerId == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    for (int i = 0; i < turnOrder.size(); i++) {
      if (turnOrder.get(i).getPlayerId() == playerId) {
        return turnOrder.remove(i) != null;
      }
    }
    return false;
  }

  /**
   * This method starts the game. It returns the first GameUpdate and sets the
   * gameStarted method to true. It can be called only once. After the first
   * call, it returns null.
   *
   * @return first game update
   */
  public GameUpdate startGame() {
    if (!gameStarted) {
      gameStarted = true;
      GameUpdate update = new GameUpdate();
      update.setValidMoves(validMove, null);
      return update;
    }
    return null;
  }

  /**
   * Indicates whether the game has started.
   *
   * @return Started boolean.
   */
  public boolean gameStarted() {
    return gameStarted;
  }

  /**
   * Returns the winner if there is one.
   *
   * @return winner of the game
   */
  public RiskPlayer getWinner() {
    return winner;
  }

  /**
   * Determines if there the player is a winner and sets the winner field if it
   * has no already been set. If the player is the winner, the method returns
   * true.
   *
   * @param player - player to determine winner
   * @return true if there is a winner; false otherwise
   * @throws IllegalArgumentException if the player given is null or if the
   *           player was not originally part of the game.
   */
  protected boolean isWinner(RiskPlayer player)
      throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (!originalPlayers.contains(player)) {
      throw new IllegalArgumentException(
          "ERROR: player not part of this risk game");
    }
    if (winner != null && winner.equals(player)) {
      return true;
    } else if (player.getTerritories().containsAll(board.getTerritoryIds())) {
      winner = player;
      assert (turnOrder.size() == 1);
      assert (turnOrder.get(0).equals(winner));
      return true;
    }
    return false;
  }

  /**
   * Determines if the player has lost the game. If so, the player is removed
   * from the turnOrder list and the method returns true.
   *
   * @param player - player to check for losing the game
   * @return true if the player lost the game; false otherwise
   * @throws IllegalArgumentException if the player given is null or if the
   *           player was not part of the game originally..
   */
  protected boolean playerLost(RiskPlayer player)
      throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (!originalPlayers.contains(player)) {
      throw new IllegalArgumentException(
          "ERROR: player not part of original game");
    }
    if (!player.hasTerritories()) {
      turnOrder.remove(player);
      return true;
    }
    return false;
  }

  /**
   * Hands out a card if the card pool is not empty and a card needs to be
   * handed out. Otherwise, the method returns -1.
   *
   * @return card value to hand out
   */
  protected int handOutCard() {
    if (!cardPool.isEmpty() && handoutCard) {
      handoutCard = false;
      return cardPool.handOutCard();
    }
    return -1;
  }

  /**
   * Returns whether or not the card deck is empty.
   *
   * @return true if empty; false otherwise
   */
  protected boolean emptyCardDeck() {
    return cardPool.isEmpty();
  }

  /**
   * Gets the current ValidAction. If the game has not been started, the
   * gameStarted indicator variable is set to true and the first valid action is
   * returned for the first player.
   *
   * @return the current valid action
   */
  public ValidAction getValidMove() {
    if (!gameStarted) {
      gameStarted = true;
    }
    return validMove;
  }

  /**
   * Returns the current attack action.
   *
   * @return AttackAction
   */
  public AttackAction getCurrentAttack() {
    return lastAttack;
  }

  /**
   * Switches the current player and determines which the valid actions the
   * player can do.
   *
   * @return valid action of the next player
   */
  protected ValidAction switchPlayer(Action prevMove) {
    int index = turnOrder.indexOf(currPlayer);
    index = (index + 1) % turnOrder.size();
    currPlayer = turnOrder.get(index);
    validMove = new ValidCardAction(currPlayer);
    if (!validMove.actionAvailable()) {
      validMove = new ValidReinforceAction(currPlayer, new ArrayList<>());
    }
    if (prevMove.getMoveType() == MoveType.SETUP) {
      validMove = new ValidSetupAction(currPlayer, board);
      if (validMove.actionAvailable()) {
        return validMove;
      } else {
        validMove = new ValidSetupReinforceAction(currPlayer);
        return validMove;
      }
    } else if (prevMove.getMoveType() == MoveType.SETUP_REINFORCE) {
      validMove = new ValidSetupReinforceAction(currPlayer);
      if (validMove.actionAvailable()) {
        return validMove;
      } else {
        currPlayer = turnOrder.get(0);
        validMove = new ValidReinforceAction(currPlayer, new ArrayList<>());
        return validMove;
      }
    }
    return validMove;
  }

  /**
   * Gets the next valid action after turning in a card, which is a
   * reinforcement move.
   *
   * @param cards - cards turned in.
   * @return next valid reinforcement move.
   * @throws IllegalArgumentException if the input is null
   */
  protected ValidAction getValidMoveAfterCardTurnIn(Multiset<Integer> cards)
      throws IllegalArgumentException {
    if (cards == null) {
      throw new IllegalArgumentException("ERROR: null input");
    } else if (winner != null) {
      return null;
    }
    assert (validMove.getMoveType() == MoveType.TURN_IN_CARD);
    List<Integer> cardList = new ArrayList<>(cards);
    ValidReinforceAction move = new ValidReinforceAction(currPlayer, cardList);
    validMove = move;
    return validMove;
  }

  /**
   * Gets the next valid action after a player has reinforced. If current player
   * does not have any valid moves left, the method returns null.
   *
   * @return ValidAction - next set of valid actions
   */
  protected ValidAction getValidMoveAfterReinforce() {
    if (winner != null) {
      return null;
    }
    assert (validMove.getMoveType() == MoveType.REINFORCE);
    ValidAttackAction move = new ValidAttackAction(currPlayer, board);
    if (move.actionAvailable()) {
      validMove = move;
      return move;
    }
    ValidMoveTroopsAction troopMove =
        new ValidMoveTroopsAction(currPlayer, board);
    if (troopMove.getReachableTerritores().size() != 0) {
      validMove = troopMove;
      return troopMove;
    }
    return null;
  }

  /**
   * Gets the next valid action after an attack. After an attack, the only valid
   * action is defending.
   *
   * @return next valid action after attacking
   */
  protected ValidAction getValidMoveAfterAttack() {
    if (winner != null) {
      return null;
    }
    assert (validMove.getMoveType() == MoveType.CHOOSE_ATTACK_DIE);
    TerritoryEnum defending = lastAttack.getDefendingTerritory();
    RiskPlayer defender = board.getTerritory(defending).getOwner();
    ValidDieDefendAction move = new ValidDieDefendAction(defender, board,
        lastAttack.getDefendingTerritory());
    validMove = move;
    return validMove;
  }

  /**
   * Gets the next valid action after defending. If the the defender lost his
   * territory, the next valid action is the attacker claiming the territory.
   * Otherwise, the next valid action is either another attack or a moving
   * troops. If no action is available for the current player, the method
   * returns null.
   *
   * @param defend - defend move
   * @return next valid action after defending
   */
  protected ValidAction getValidMoveAfterDefend(DefendAction defend) {
    if (winner != null) {
      return null;
    }
    assert (validMove.getMoveType() == MoveType.CHOOSE_DEFEND_DIE);
    if (defend.getDefenderLostTerritory()) {
      validMove = new ValidClaimTerritoryAction(currPlayer, board, lastAttack);
      if (!cardPool.isEmpty()) {
        handoutCard = true;
      }
      lastAttack = null;
      return validMove;
    }
    ValidAttackAction validAttack = new ValidAttackAction(currPlayer, board);
    if (validAttack.actionAvailable()) {
      lastAttack = null;
      validMove = validAttack;
      return validMove;
    }
    ValidMoveTroopsAction moveTroops =
        new ValidMoveTroopsAction(currPlayer, board);
    if (moveTroops.actionAvailable()) {
      lastAttack = null;
      validMove = moveTroops;
      return validMove;
    }
    lastAttack = null;
    return null;
  }

  /**
   * Gets the next valid action after claiming a territory. If the current
   * player can attack, a valid attack action is returned. If not, a valid move
   * troops action is returned if the current player can move their troops.
   * Otherwise, null is returned if the current player has no valid action.
   *
   * @return next valid action after claiming a territory
   */
  protected ValidAction getValidMoveAfterClaimTerritory() {
    if (winner != null) {
      return null;
    }
    assert (validMove.getMoveType() == MoveType.CLAIM_TERRITORY);
    ValidAttackAction attack = new ValidAttackAction(currPlayer, board);
    if (attack.actionAvailable()) {
      validMove = attack;
      return validMove;
    }
    ValidMoveTroopsAction moveTroops =
        new ValidMoveTroopsAction(currPlayer, board);
    if (moveTroops.actionAvailable()) {
      validMove = moveTroops;
      return validMove;
    }
    return null;
  }

  /**
   * Determines if the given player is allowed to skip a move based on the
   * current type of move they are trying to skip and if the player is in
   * control of the game at the time.
   *
   * @param player - player trying to skip
   * @return true if the player can skip the move; false otherwise
   * @throws IllegalArgumentException if the player given is null.
   */
  protected boolean validSkipMove(RiskPlayer player)
      throws IllegalArgumentException {
    if (player == null) {
      throw new IllegalArgumentException("ERROR: null player");
    }
    if (!player.equals(currPlayer)) {
      return false;
    }
    MoveType type = validMove.getMoveType();
    if (type == MoveType.TURN_IN_CARD || type == MoveType.CHOOSE_ATTACK_DIE
        || type == MoveType.MOVE_TROOPS) {
      return true;
    }
    return false;
  }

  /**
   * Returns the next valid action for the current player after a skip move. If
   * the current player does not have a valid action after skipping, the method
   * returns null.
   *
   * @return next valid action for the current player
   */
  protected ValidAction getActionAfterSkip() {
    MoveType type = validMove.getMoveType();
    assert (type == MoveType.TURN_IN_CARD || type == MoveType.CHOOSE_ATTACK_DIE
        || type == MoveType.MOVE_TROOPS);
    if (type == MoveType.TURN_IN_CARD) {
      validMove = new ValidReinforceAction(currPlayer, new ArrayList<>());
      return validMove;
    } else if (type == MoveType.CHOOSE_ATTACK_DIE) {
      ValidMoveTroopsAction move = new ValidMoveTroopsAction(currPlayer, board);
      if (move.actionAvailable()) {
        validMove = move;
        return move;
      } else {
        return null;
      }
    }
    return null;
  }

  /**
   * Switches players after a skip move if the skip move results in current
   * player changing.
   *
   * @return next valid action for a new player
   */
  protected ValidAction switchPlayersAfterSkip() {
    int index = turnOrder.indexOf(currPlayer);
    index = (index + 1) % turnOrder.size();
    currPlayer = turnOrder.get(index);
    validMove = new ValidCardAction(currPlayer);
    if (!validMove.actionAvailable()) {
      validMove = new ValidReinforceAction(currPlayer, new ArrayList<>());
    }
    return validMove;
  }

  /**
   * Checks that the CardTurnInMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  protected boolean validateCardTurnIn(CardTurnInAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (validMove == null || validMove.getMoveType() != MoveType.TURN_IN_CARD) {
      return false;
    }
    ValidCardAction card = (ValidCardAction) validMove;
    return card.validateCardMove(move);
  }

  /**
   * Checks that the ReinforceMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  protected boolean validateReinforce(ReinforceAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (validMove == null || validMove.getMoveType() != MoveType.REINFORCE) {
      return false;
    }
    ValidReinforceAction reinforce = (ValidReinforceAction) validMove;
    return reinforce.validReinforceMove(move);
  }

  /**
   * Checks that the AttackMove is valid.
   *
   * @param move - move to check the validity of
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  protected boolean validateAttackMove(AttackAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (validMove == null
        || validMove.getMoveType() != MoveType.CHOOSE_ATTACK_DIE) {
      return false;
    }
    ValidAttackAction validAttack = (ValidAttackAction) validMove;
    if (validAttack.isValidAttackAction(move)) {
      lastAttack = move;
      return true;
    }
    return false;
  }

  /**
   * Checks the the DefendMoveis valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  protected boolean validateDefendMove(DefendAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (validMove == null
        || validMove.getMoveType() != MoveType.CHOOSE_DEFEND_DIE) {
      return false;
    }
    ValidDieDefendAction defend = (ValidDieDefendAction) validMove;
    return defend.validateDefendMove(move);
  }

  /**
   * Checks that the ClaimTerritoryMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  protected boolean validateClaimTerritory(ClaimTerritoryAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (validMove == null
        || validMove.getMoveType() != MoveType.CLAIM_TERRITORY) {
      return false;
    }
    ValidClaimTerritoryAction claim = (ValidClaimTerritoryAction) validMove;
    return claim.validClaimTerritory(move);
  }

  /**
   * Checks that the MoveTroopsMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  protected boolean validateMoveTroopsMove(MoveTroopsAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (validMove == null || validMove.getMoveType() != MoveType.MOVE_TROOPS) {
      return false;
    }
    ValidMoveTroopsAction moveTroops = (ValidMoveTroopsAction) validMove;
    return moveTroops.validMoveTroopMove(move);
  }

  /**
   * Checks that the SetupMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  protected boolean validateSetupMove(SetupAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (validMove == null || validMove.getMoveType() != MoveType.SETUP) {
      return false;
    }
    ValidSetupAction setupMove = (ValidSetupAction) validMove;
    return setupMove.validSetupMove(move);
  }

  /**
   * Checks the SetupReinforcementMove is valid.
   *
   * @param move - move to check validity of
   * @return true if valid; false otherwise
   * @throws IllegalArgumentException if the input is null
   */
  protected boolean validateSetupReinforceMove(SetupReinforceAction move) {
    if (move == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    if (validMove == null
        || validMove.getMoveType() != MoveType.SETUP_REINFORCE) {
      return false;
    }
    ValidSetupReinforceAction setupReinforceMove =
        (ValidSetupReinforceAction) validMove;
    return setupReinforceMove.validSetupReinforceMove(move);
  }

  /**
   * Returns the current player whose turn it is. Note that if a player is
   * defending, the player is not considered the current player.
   *
   * @return current player
   */
  protected RiskPlayer getCurrentPlayer() {
    return currPlayer;
  }
}
