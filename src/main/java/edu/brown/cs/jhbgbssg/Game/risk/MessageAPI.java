package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import edu.brown.cs.jhbgbssg.Game.risk.riskaction.Action;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.DefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidCardAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidClaimTerritoryAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidDieDefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidMoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupAction;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssh.tuple.Pair;

public class MessageAPI {
  private static final Gson GSON = new Gson();

  private enum MESSAGE_TYPE {
    WINNER, LOSER, HANDOUT_CARD, NO_CARDS_LEFT, PREVIOUS_ACTION, VALID_ACTIONS, ERROR;
  }

  public MessageAPI() {

  }

  public MoveType getMoveType(JsonObject received) {
    try {
      int type = received.get("moveType").getAsInt();
      return MoveType.values()[type];
    } catch (NullPointerException | IllegalArgumentException e) {
      throw new IllegalArgumentException("ERROR: illegal message");
    }
  }

  /**
   * Gets the player id.
   *
   * @param object - json object
   * @return player id
   */
  public UUID getPlayerId(JsonObject object) {
    return UUID.fromString(object.get("playerId").getAsString());
  }

  /**
   * Returns a pair of attacking and defending territories.
   *
   * @param object - json object.
   * @return attacking and defending territory pair
   */
  public Pair<TerritoryEnum, TerritoryEnum> getAttackingDefendingTerritory(
      JsonObject object) {
    try {
      TerritoryEnum attack = GSON.fromJson(
          object.get("moveFromTerritory").getAsString(), TerritoryEnum.class);
      TerritoryEnum claim = GSON.fromJson(
          object.get("moveToTerritory").getAsString(), TerritoryEnum.class);
      return new Pair<>(attack, claim);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }

  }

  /**
   * Returns a pair of attacking and claiming territories.
   *
   * @param object - json object
   * @return attacking and claiming territory
   */
  public Pair<TerritoryEnum, TerritoryEnum> getAttackClaimingTerritory(
      JsonObject object) {
    try {
      TerritoryEnum attack = GSON.fromJson(
          object.get("moveFromTerritory").getAsString(), TerritoryEnum.class);
      TerritoryEnum claim = GSON.fromJson(
          object.get("moveToTerritory").getAsString(), TerritoryEnum.class);
      return new Pair<>(attack, claim);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

  /**
   * Returns a pair territories that troops are moved to and from.
   *
   * @param object - json object
   * @return pair of territories
   */
  public Pair<TerritoryEnum, TerritoryEnum> getMoveTroopsTerritories(
      JsonObject object) {
    try {
      TerritoryEnum moveFrom = GSON.fromJson(
          object.get("moveFromTerritory").getAsString(), TerritoryEnum.class);
      TerritoryEnum moveTo = GSON.fromJson(
          object.get("moveToTerritory").getAsString(), TerritoryEnum.class);
      return new Pair<>(moveFrom, moveTo);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

  /**
   * Gets number of die rolled.
   *
   * @param object - json object
   * @return number of die to roll
   */
  public int getNumberDieToRoll(JsonObject object) {
    try {
      return object.get("numberDieToRoll").getAsInt();
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

  /**
   * Gets number of troops to move.
   *
   * @param object - json object
   * @return number of troops to move
   */
  public int getNumberTroopsToMove(JsonObject object) {
    try {
      return object.get("troopsToMove").getAsInt();
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

  /**
   * Gets card turned in.
   *
   * @param object - json object
   * @return card to turn in
   */
  public int getCardTurnedIn(JsonObject object) {
    try {
      return object.get("card").getAsInt();
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

  /**
   * Gets the selected territory.
   *
   * @param object - json object
   * @return selected territory
   */
  public TerritoryEnum getSelectedTerritory(JsonObject object) {
    try {
      TerritoryEnum selected = GSON.fromJson(
          object.get("selectedTerritory").getAsString(), TerritoryEnum.class);
      return selected;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

  /**
   * Gets the territories reinforced with their assocaited number of troops.
   *
   * @param object - json object
   * @return reinforced territories.
   */
  public Map<TerritoryEnum, Integer> getNumberReinforced(JsonObject object) {
    try {
      Map<TerritoryEnum, Integer> territoryMap = GSON.fromJson(
          object.get("reinforceMap"),
          new TypeToken<Map<TerritoryEnum, Integer>>() {
          }.getType());
      return territoryMap;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

  /**
   *
   * @param update
   * @return
   */
  public List<JsonObject> getUpdateMessages(GameUpdate update) {
    List<JsonObject> messages = new ArrayList<>();
    if (update.getPrevMove() != null) {
      messages.add(this.prevActionToJson(update.getPrevMove()));
    }
    if (update.getLoser() != null) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", MESSAGE_TYPE.LOSER.ordinal());
      obj.addProperty("loser", GSON.toJson(update.getLoser()));
      messages.add(obj);
    }
    if (update.getGameWon() != null) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", MESSAGE_TYPE.WINNER.ordinal());
      obj.addProperty("winner", GSON.toJson(update.getGameWon()));
      messages.add(obj);
    }
    if (update.getErrors()) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", MESSAGE_TYPE.ERROR.ordinal());
      obj.addProperty("player", GSON.toJson(update.getCurrentPlayer()));
      messages.add(obj);
    }
    if (update.getCardHandOut() != null) {
      JsonObject obj = new JsonObject();
      int card = update.getCardHandOut().getSecondElement();
      UUID player = update.getCardHandOut().getFirstElement();
      obj.addProperty("type", MESSAGE_TYPE.HANDOUT_CARD.ordinal());
      obj.addProperty("player", GSON.toJson(player));
      obj.addProperty("cardValue", card);
      messages.add(obj);
    }
    if (!update.cardsLeft()) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", MESSAGE_TYPE.NO_CARDS_LEFT.ordinal());
      messages.add(obj);
    }
    if (update.getValidMoves() != null) {
      JsonObject obj = this.validJsonMove(update.getValidMoves());
      obj.addProperty("type", MESSAGE_TYPE.VALID_ACTIONS.ordinal());
      messages.add(obj);
    }
    return messages;
  }

  private JsonObject prevActionToJson(Action prevAction) {
    MoveType type = prevAction.getMoveType();
    JsonObject object = new JsonObject();
    JsonObject payload;
    object.addProperty("messageType", "previousMove");
    switch (type) {
      case SETUP:
        payload = this.prevSetupMove((SetupAction) prevAction);
        object.addProperty("moveType", "SETUP");
        object.add("payload", payload);
        return object;
      case SETUP_REINFORCE:
        return null;
      case REINFORCE:
        object.addProperty("moveType", "REINFORCE");
        payload = this.prevReinforceMove((ReinforceAction) prevAction);
        object.add("payload", payload);
        return object;
      case TURN_IN_CARD:
        object.addProperty("moveType", "TURN_IN_CARD");
        payload = this.prevCardMove((CardTurnInAction) prevAction);
        object.add("payload", payload);
        return object;
      case CHOOSE_ATTACK_DIE:
        object.addProperty("moveType", "CHOOSE_ATTACK_DIE");
        payload = this.prevAttackMove((AttackAction) prevAction);
        object.add("payload", payload);
        return object;
      case CHOOSE_DEFEND_DIE:
        object.addProperty("moveType", "CHOOSE_DEFEND_DIE");
        payload = this.prevDefendMove((DefendAction) prevAction);
        object.add("payload", payload);
        return object;
      case CLAIM_TERRITORY:
        object.addProperty("moveType", "CLAIM_TERRITORY");
        return this.prevClaimMove((ClaimTerritoryAction) prevAction);
      default:
        object.addProperty("moveType", "MOVE_TROOPS");
        payload = this.prevMoveTroops((MoveTroopsAction) prevAction);
        object.add("payload", payload);
        return object;
    }
  }

  /**
   * Sets up a JsonObject that represents a setup move.
   *
   * @param move - set up move to convert to json
   * @return json object
   */
  private JsonObject prevSetupMove(SetupAction move) {
    TerritoryEnum selected = move.getSelectedTerritory();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.SETUP.ordinal());
    jsonObject.addProperty("movePlayer", move.getMovePlayer().toString());
    jsonObject.addProperty("selected", selected.ordinal());
    return jsonObject;
  }

  /**
   * Sets up a JsonObject that represents a reinforce move.
   *
   * @param move - reinforce move
   * @return json object
   */
  private JsonObject prevReinforceMove(ReinforceAction move) {
    Map<TerritoryEnum, Integer> reinforced = move.getReinforcedTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.REINFORCE.ordinal());
    jsonObject.addProperty("movePlayer", move.getMovePlayer().toString());
    jsonObject.addProperty("reinforced", GSON.toJson(reinforced));
    return jsonObject;
  }

  private JsonObject prevCardMove(CardTurnInAction move) {
    Map<TerritoryEnum, Integer> reinforced = move.getTerritoriesReinforced();
    int card = move.getCard();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("movePlayer", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", MoveType.TURN_IN_CARD.ordinal());
    jsonObject.addProperty("card", card);
    jsonObject.addProperty("reinforced", GSON.toJson(reinforced));
    return jsonObject;
  }

  private JsonObject prevAttackMove(AttackAction move) {
    TerritoryEnum attackFrom = move.getAttackingTerritory();
    TerritoryEnum attacking = move.getDefendingTerritory();
    List<Integer> roll = move.getRoll();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("movePlayer", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", MoveType.CHOOSE_ATTACK_DIE.ordinal());
    jsonObject.addProperty("attackFrom", attackFrom.toString());
    jsonObject.addProperty("attackTo", attacking.toString());
    jsonObject.addProperty("roll", GSON.toJson(roll));
    return jsonObject;
  }

  /**
   * Creates json object that contains information about the defend action.
   *
   * @param move - defend action
   * @return json object
   */
  private JsonObject prevDefendMove(DefendAction move) {
    UUID attacker = move.getAttackerId().getPlayerId();
    UUID defender = move.getMovePlayer().getPlayerId();
    TerritoryEnum attacking = move.getAttackingTerritory();
    TerritoryEnum defending = move.getDefendedTerritory();
    List<Integer> roll = move.getRoll();
    int attackerTroopsLost = move.getTroopsAttackerLost();
    int defenderTroopsLost = move.getTroopsDefenderLost();
    boolean defenderLost = move.getDefenderLostTerritory();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.CHOOSE_DEFEND_DIE.ordinal());
    jsonObject.addProperty("defender", GSON.toJson(defender));
    jsonObject.addProperty("attacker", GSON.toJson(attacker));
    jsonObject.addProperty("roll", GSON.toJson(roll));
    jsonObject.addProperty("attackTerritory", attacking.toString());
    jsonObject.addProperty("defendTerritory", defending.toString());
    jsonObject.addProperty("attackerTroopsLost", attackerTroopsLost);
    jsonObject.addProperty("defenderTroopsLost", defenderTroopsLost);
    jsonObject.addProperty("defenderLostTerritory", defenderLost);
    return jsonObject;
  }

  /**
   * Creates a json object that contains information about the claim territory
   * action.
   *
   * @param move - claim territory action
   * @return json object
   */
  private JsonObject prevClaimMove(ClaimTerritoryAction move) {
    UUID player = move.getMovePlayer().getPlayerId();
    TerritoryEnum claimFrom = move.getAttackingTerritory();
    TerritoryEnum claimed = move.getClaimingTerritory();
    int numberTroops = move.getNumberTroops();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("movePlayer", GSON.toJson(player));
    jsonObject.addProperty("moveType", MoveType.CLAIM_TERRITORY.ordinal());
    jsonObject.addProperty("claimedFrom", claimFrom.toString());
    jsonObject.addProperty("claimedTerritory", claimed.toString());
    jsonObject.addProperty("numberTroops", numberTroops);
    return jsonObject;
  }

  /**
   * Creates a json object that contains information about the move troops
   * action.
   *
   * @param move - move troops action
   * @return json object
   */
  private JsonObject prevMoveTroops(MoveTroopsAction move) {
    UUID player = move.getMovePlayer().getPlayerId();
    TerritoryEnum moveFrom = move.getFromTerritory();
    TerritoryEnum moveTo = move.getToTerrtiory();
    int numberTroops = move.troopsMoved();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("movePlayer", GSON.toJson(player));
    jsonObject.addProperty("moveType", MoveType.MOVE_TROOPS.ordinal());
    jsonObject.addProperty("moveGrom", moveFrom.toString());
    jsonObject.addProperty("moveTo", moveTo.toString());
    jsonObject.addProperty("numberTroops", numberTroops);
    return jsonObject;
  }

  private JsonObject validJsonMove(ValidAction action) {
    MoveType type = action.getMoveType();
    switch (type) {
      case SETUP:
        return this.setUpMove((ValidSetupAction) action);
      case REINFORCE:
        return this.setUpReinforceMove((ValidReinforceAction) action);
      case TURN_IN_CARD:
        return this.setUpTurnInCards((ValidCardAction) action);
      case CHOOSE_ATTACK_DIE:
        return this.setUpAttackMove((ValidAttackAction) action);
      case CHOOSE_DEFEND_DIE:
        return this.setUpDefendMove((ValidDieDefendAction) action);
      case CLAIM_TERRITORY:
        return this.setUpClaimTerritoryMove((ValidClaimTerritoryAction) action);
      default:
        return this.setUpMoveTroops((ValidMoveTroopsAction) action);
    }
  }

  /**
   * sets up set up move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpMove(ValidSetupAction move) {
    List<TerritoryEnum> territories = move.getTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.SETUP.ordinal());
    jsonObject.addProperty("player", move.getMovePlayer().toString());
    jsonObject.addProperty("selectable", GSON.toJson(territories));
    return jsonObject;
  }

  /**
   * Sets up the reinforce move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpReinforceMove(ValidReinforceAction move) {
    Set<TerritoryEnum> terrs = move.getTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.SETUP_REINFORCE.ordinal());
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("territories", GSON.toJson(terrs));
    jsonObject.addProperty("numberTroops", move.getNumberToReinforce());
    return jsonObject;
  }

  /**
   * sets up card turn in options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpTurnInCards(ValidCardAction move) {
    Multiset<Integer> cards = move.getCards();
    Set<TerritoryEnum> terrs = move.getTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.TURN_IN_CARD.ordinal());
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("cards", GSON.toJson(cards));
    jsonObject.addProperty("territories", GSON.toJson(terrs));
    return jsonObject;
  }

  /**
   * sets up the attack move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpAttackMove(ValidAttackAction move) {
    move.whoToAttack();
    Map<TerritoryEnum, Integer> numberDie = move.getAttackableTerritories();
    Multimap<TerritoryEnum, TerritoryEnum> whoToAttack = move.whoToAttack();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.CHOOSE_ATTACK_DIE.ordinal());
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("maxDieRoll", GSON.toJson(numberDie));
    jsonObject.addProperty("whoCanAttack", GSON.toJson(whoToAttack.asMap()));
    return jsonObject;
  }

  /**
   * sets up the defend move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpDefendMove(ValidDieDefendAction move) {
    TerritoryEnum toDefend = move.getDefendTerritory();
    int maxDie = move.getMaxNumberDie();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.CHOOSE_DEFEND_DIE.ordinal());
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("defendTerritory", toDefend.toString());
    jsonObject.addProperty("maxDieRoll", maxDie);
    return jsonObject;
  }

  /**
   * sets up the claim territory move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpClaimTerritoryMove(ValidClaimTerritoryAction move) {
    TerritoryEnum attacker = move.getFromTerritory();
    TerritoryEnum claimed = move.getClaimedTerritory();
    int maxTroops = move.getMaxNumberTroops();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.CLAIM_TERRITORY.ordinal());
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("territoryToClaim", claimed.toString());
    jsonObject.addProperty("territoryClaimingFrom", attacker.toString());
    jsonObject.addProperty("maxNumberTroops", maxTroops);
    return jsonObject;
  }

  /**
   * This method sets up a JsonObject representing the ValidMoveTroopsMove
   * object.
   *
   * @param move - valid move troops move
   * @return json object
   */
  private JsonObject setUpMoveTroops(ValidMoveTroopsAction move) {
    Multimap<TerritoryEnum, TerritoryEnum> reachable = move
        .getReachableTerritores();
    Map<TerritoryEnum, Integer> maxMove = move.maxTroopsToMove();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("moveType", MoveType.MOVE_TROOPS.ordinal());
    jsonObject.addProperty("canMove", GSON.toJson(reachable.asMap()));
    jsonObject.addProperty("maxTroopsMove", GSON.toJson(maxMove));
    return jsonObject;
  }
}
