package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
  private static final TerritoryEnum[] IDS = TerritoryEnum.values();

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
      int attack = object.get("attackingTerritory").getAsInt();
      int defend = object.get("defendingTerritory").getAsInt();
      return new Pair<>(IDS[attack], IDS[defend]);
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
      int attack = object.get("attackingTerritory").getAsInt();
      int claim = object.get("claimingTerritory").getAsInt();
      return new Pair<>(IDS[attack], IDS[claim]);
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
      int moveFrom = object.get("moveFromTerritory").getAsInt();
      int moveTo = object.get("moveToTerritory").getAsInt();
      return new Pair<>(IDS[moveFrom], IDS[moveTo]);
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
      int ordinal = object.get("selectedTerritory").getAsInt();
      return IDS[ordinal];
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
      Map<Integer, Integer> ordinalMap = GSON.fromJson(
          object.get("reinforceMap"), new TypeToken<Map<Integer, Integer>>() {
          }.getType());
      Map<TerritoryEnum, Integer> territoryMap = new HashMap<>();
      for (Entry<Integer, Integer> entry : ordinalMap.entrySet()) {
        territoryMap.put(IDS[entry.getKey()], entry.getValue());
      }
      return territoryMap;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

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
    Map<Integer, Integer> ordinalReinforced = this.getOrdinalMap(reinforced);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.REINFORCE.ordinal());
    jsonObject.addProperty("movePlayer", move.getMovePlayer().toString());
    jsonObject.addProperty("reinforced", GSON.toJson(ordinalReinforced));
    return jsonObject;
  }

  private JsonObject prevCardMove(CardTurnInAction move) {
    Map<TerritoryEnum, Integer> reinforced = move.getTerritoriesReinforced();
    Map<Integer, Integer> ordinalReinforced = this.getOrdinalMap(reinforced);
    int card = move.getCard();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("movePlayer", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", MoveType.TURN_IN_CARD.ordinal());
    jsonObject.addProperty("card", card);
    jsonObject.addProperty("reinforced", GSON.toJson(ordinalReinforced));
    return jsonObject;
  }

  private JsonObject prevAttackMove(AttackAction move) {
    TerritoryEnum attackFrom = move.getAttackingTerritory();
    TerritoryEnum attacking = move.getDefendingTerritory();
    List<Integer> roll = move.getRoll();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("movePlayer", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", MoveType.CHOOSE_ATTACK_DIE.ordinal());
    jsonObject.addProperty("attackFrom", attackFrom.ordinal());
    jsonObject.addProperty("attackTo", attacking.ordinal());
    jsonObject.addProperty("roll", GSON.toJson(roll));
    return jsonObject;
  }

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
    jsonObject.addProperty("attackTerritory", attacking.ordinal());
    jsonObject.addProperty("defendTerritory", defending.ordinal());
    jsonObject.addProperty("attackerTroopsLost", attackerTroopsLost);
    jsonObject.addProperty("defenderTroopsLost", defenderTroopsLost);
    jsonObject.addProperty("defenderLostTerritory", defenderLost);
    return jsonObject;
  }

  private JsonObject prevClaimMove(ClaimTerritoryAction move) {
    UUID player = move.getMovePlayer().getPlayerId();
    TerritoryEnum claimFrom = move.getAttackingTerritory();
    TerritoryEnum claimed = move.getClaimingTerritory();
    int numberTroops = move.getNumberTroops();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("movePlayer", GSON.toJson(player));
    jsonObject.addProperty("moveType", MoveType.CLAIM_TERRITORY.ordinal());
    jsonObject.addProperty("claimedFrom", claimFrom.ordinal());
    jsonObject.addProperty("claimedTerritory", claimed.ordinal());
    jsonObject.addProperty("numberTroops", numberTroops);
    return jsonObject;
  }

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
    Set<Integer> ordTerr = this.getOrdinalSet(terrs);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.SETUP_REINFORCE.ordinal());
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("territories", GSON.toJson(ordTerr));
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
    Set<Integer> ordTerr = this.getOrdinalSet(terrs);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.TURN_IN_CARD.ordinal());
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("cards", GSON.toJson(cards));
    jsonObject.addProperty("territories", GSON.toJson(ordTerr));
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
    Map<Integer, Integer> ordNumberDie = this.getOrdinalMap(numberDie);
    Map<Integer, Collection<Integer>> ordToAttack = this
        .getOrdinalCollectionMap(whoToAttack.asMap());
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.CHOOSE_ATTACK_DIE.ordinal());
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("maxDieRoll", GSON.toJson(ordNumberDie));
    jsonObject.addProperty("whoCanAttack", GSON.toJson(ordToAttack));
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
    jsonObject.addProperty("defendTerritory", toDefend.ordinal());
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
    jsonObject.addProperty("territoryToClaim", claimed.ordinal());
    jsonObject.addProperty("territoryClaimingFrom", attacker.ordinal());
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
    Map<Integer, Collection<Integer>> ordReachable = this
        .getOrdinalCollectionMap(reachable.asMap());
    Map<Integer, Integer> ordMaxMove = this.getOrdinalMap(maxMove);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("moveType", MoveType.MOVE_TROOPS.ordinal());
    jsonObject.addProperty("canMove", GSON.toJson(ordReachable));
    jsonObject.addProperty("maxTroopsMove", GSON.toJson(ordMaxMove));
    return jsonObject;
  }

  private Map<Integer, Integer> getOrdinalMap(Map<TerritoryEnum, Integer> map) {
    Map<Integer, Integer> ordMap = new HashMap<>();
    for (Entry<TerritoryEnum, Integer> entry : map.entrySet()) {
      ordMap.put(entry.getKey().ordinal(), entry.getValue());
    }
    return ordMap;
  }

  private Map<Integer, Collection<Integer>> getOrdinalCollectionMap(
      Map<TerritoryEnum, Collection<TerritoryEnum>> map) {
    Map<Integer, Collection<Integer>> ordMap = new HashMap<>();

    for (Entry<TerritoryEnum, Collection<TerritoryEnum>> entry : map
        .entrySet()) {
      Collection<Integer> collection = new ArrayList<>();
      for (TerritoryEnum terr : entry.getValue()) {
        collection.add(terr.ordinal());
      }
      ordMap.put(entry.getKey().ordinal(), collection);
    }
    return ordMap;
  }

  private Set<Integer> getOrdinalSet(Set<TerritoryEnum> set) {
    Set<Integer> ordSet = new HashSet<>();
    for (TerritoryEnum terr : set) {
      ordSet.add(terr.ordinal());
    }
    return ordSet;
  }
}
