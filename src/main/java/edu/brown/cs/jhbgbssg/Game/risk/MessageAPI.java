package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssh.tuple.Pair;

/**
 *
 * API for Risk.
 *
 * @author sarahgilmore
 *
 */
public class MessageAPI {
  private static final Gson GSON = new Gson();

  /**
   * Constructor for MessageAPI.
   */
  public MessageAPI() {

  }

  /**
   * Returns the type of move encoded by the JsonObject.
   *
   * @param received - json object
   * @return the MoveType
   */
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
      int index = object.get("attackTerritory").getAsInt();
      TerritoryEnum attack = TerritoryEnum.values()[index];
      index = object.get("defendTerritory").getAsInt();
      TerritoryEnum claim = TerritoryEnum.values()[index];
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
      int index = object.get("attackTerritory").getAsInt();
      TerritoryEnum attack = TerritoryEnum.values()[index];
      index = object.get("claimTerritory").getAsInt();
      TerritoryEnum claim = TerritoryEnum.values()[index];
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
      int index = object.get("moveFromTerritory").getAsInt();
      TerritoryEnum moveFrom = TerritoryEnum.values()[index];
      index = object.get("moveToTerritory").getAsInt();
      TerritoryEnum moveTo = TerritoryEnum.values()[index];
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
   * Gets the cards turned in.
   *
   * @param object - json object
   * @return card to turn in
   */
  public List<Integer> getCardTurnedIn(JsonObject object) {
    try {
      return GSON.fromJson(object.get("cards").getAsString(),
          new TypeToken<List<Integer>>() {
          }.getType());
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
      int index = object.get("territoryId").getAsInt();
      TerritoryEnum selected = TerritoryEnum.values()[index];
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
      List<List<Integer>> territories = GSON.fromJson(object.get("territories"),
          new TypeToken<List<List<Integer>>>() {
          }.getType());
      Map<TerritoryEnum, Integer> toReinforce = new HashMap<>();
      for (List<Integer> el : territories) {
        if (el.size() == 2) {
          TerritoryEnum key = TerritoryEnum.values()[el.get(0)];
          toReinforce.put(key, el.get(1));
        }
      }
      return toReinforce;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(
          "ERROR: no property in the json object");
    }
  }

  /**
   * Returns a list of JsonObjects that represents messages to send to the
   * frontend.
   *
   * @param update - gameupdate that contains the information to send
   * @return list of messages
   */
  public List<JsonObject> getUpdateMessages(GameUpdate update) {
    List<JsonObject> messages = new ArrayList<>();
    if (update.getPrevMove() != null) {
      messages.add(this.prevActionToJson(update.getPrevMove()));
    }
    if (update.getLoser() != null) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", RiskMessageType.LOSER.ordinal());
      obj.addProperty("loser", GSON.toJson(update.getLoser()));
      messages.add(obj);
    }
    if (update.getGameWon() != null) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", RiskMessageType.WINNER.ordinal());
      obj.addProperty("winner", GSON.toJson(update.getGameWon()));
      messages.add(obj);
    }
    if (update.getErrors()) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", RiskMessageType.ERROR.ordinal());
      obj.addProperty("playerId", GSON.toJson(update.getCurrentPlayer()));
      messages.add(obj);
    }

    if (update.getCardHandOut() != null) {
      JsonObject obj = new JsonObject();
      int card = update.getCardHandOut().getSecondElement();
      UUID player = update.getCardHandOut().getFirstElement();
      obj.addProperty("type", RiskMessageType.HANDOUT_CARD.ordinal());
      obj.addProperty("playerId", GSON.toJson(player));
      obj.addProperty("cardValue", card);
      messages.add(obj);
    }
    if (!update.cardsLeft()) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", RiskMessageType.NO_CARDS_LEFT.ordinal());
      messages.add(obj);
    }
    if (update.getValidMoves() != null) {
      JsonObject obj = this.validJsonMove(update.getValidMoves());
      obj.addProperty("type", RiskMessageType.VALID_ACTIONS.ordinal());
      messages.add(obj);
    }
    return messages;
  }

  private JsonObject prevActionToJson(Action prevAction) {
    MoveType type = prevAction.getMoveType();
    switch (type) {
      case SETUP:
        return this.prevSetupMove((SetupAction) prevAction);
      case SETUP_REINFORCE:
        return this.prevSetupReinforce((SetupReinforceAction) prevAction);
      case REINFORCE:
        return this.prevReinforceMove((ReinforceAction) prevAction);
      case TURN_IN_CARD:
        return this.prevCardMove((CardTurnInAction) prevAction);
      case CHOOSE_ATTACK_DIE:
        return this.prevAttackMove((AttackAction) prevAction);
      case CHOOSE_DEFEND_DIE:
        return this.prevDefendMove((DefendAction) prevAction);
      case CLAIM_TERRITORY:
        return this.prevClaimMove((ClaimTerritoryAction) prevAction);
      default:
        return this.prevMoveTroops((MoveTroopsAction) prevAction);
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
    jsonObject.addProperty("type", RiskMessageType.PREVIOUS_ACTION.ordinal());
    jsonObject.addProperty("moveType", MoveType.SETUP.ordinal());
    jsonObject.addProperty("movePlayer",
        move.getMovePlayer().getPlayerId().toString());
    jsonObject.addProperty("territoryId", selected.ordinal());
    return jsonObject;
  }

  private JsonObject prevSetupReinforce(SetupReinforceAction action) {
    TerritoryEnum selected = action.getSelectedTerritory();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("type", RiskMessageType.PREVIOUS_ACTION.ordinal());
    jsonObject.addProperty("moveType", MoveType.SETUP_REINFORCE.ordinal());
    jsonObject.addProperty("territoryId", selected.ordinal());
    jsonObject.addProperty("movePlayer",
        action.getMovePlayer().getPlayerId().toString());
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
    Map<Integer, Integer> ordReinforced = this.getOrdinalMap(reinforced);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("type", RiskMessageType.PREVIOUS_ACTION.ordinal());
    jsonObject.addProperty("moveType", MoveType.REINFORCE.ordinal());
    jsonObject.addProperty("movePlayer",
        move.getMovePlayer().getPlayerId().toString());
    jsonObject.addProperty("territories", GSON.toJson(ordReinforced));
    return jsonObject;
  }

  private JsonObject prevCardMove(CardTurnInAction move) {
    List<Integer> cards = new ArrayList<>(move.getCards());
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("type", RiskMessageType.PREVIOUS_ACTION.ordinal());
    jsonObject.addProperty("movePlayer",
        move.getMovePlayer().getPlayerId().toString());
    jsonObject.addProperty("moveType", MoveType.TURN_IN_CARD.ordinal());
    jsonObject.addProperty("cards", GSON.toJson(cards));
    return jsonObject;
  }

  private JsonObject prevAttackMove(AttackAction move) {
    TerritoryEnum attackFrom = move.getAttackingTerritory();
    TerritoryEnum attacking = move.getDefendingTerritory();
    List<Integer> roll = move.getRoll();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("type", RiskMessageType.PREVIOUS_ACTION.ordinal());
    jsonObject.addProperty("movePlayer",
        move.getMovePlayer().getPlayerId().toString());
    jsonObject.addProperty("moveType", MoveType.CHOOSE_ATTACK_DIE.ordinal());
    jsonObject.addProperty("attackFrom", attackFrom.ordinal());
    jsonObject.addProperty("attackTo", attacking.ordinal());
    jsonObject.addProperty("roll", GSON.toJson(roll));
    System.out.println("attacker " + move.getMovePlayer().getPlayerId());
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
    jsonObject.addProperty("type", RiskMessageType.PREVIOUS_ACTION.ordinal());
    jsonObject.addProperty("moveType", MoveType.CHOOSE_DEFEND_DIE.ordinal());
    jsonObject.addProperty("defender", defender.toString());
    jsonObject.addProperty("attacker", attacker.toString());
    jsonObject.addProperty("roll", GSON.toJson(roll));
    jsonObject.addProperty("attackTerritory", attacking.ordinal());
    jsonObject.addProperty("defendTerritory", defending.ordinal());
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
    jsonObject.addProperty("type", RiskMessageType.PREVIOUS_ACTION.ordinal());
    jsonObject.addProperty("movePlayer", player.toString());
    jsonObject.addProperty("moveType", MoveType.CLAIM_TERRITORY.ordinal());
    jsonObject.addProperty("claimedFrom", claimFrom.ordinal());
    jsonObject.addProperty("claimedTerritory", claimed.ordinal());
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
    jsonObject.addProperty("type", RiskMessageType.PREVIOUS_ACTION.ordinal());
    jsonObject.addProperty("movePlayer", GSON.toJson(player));
    jsonObject.addProperty("moveType", MoveType.MOVE_TROOPS.ordinal());
    jsonObject.addProperty("moveGrom", moveFrom.ordinal());
    jsonObject.addProperty("moveTo", moveTo.ordinal());
    jsonObject.addProperty("numberTroops", numberTroops);
    return jsonObject;
  }

  private JsonObject validJsonMove(ValidAction action) {
    MoveType type = action.getMoveType();
    switch (type) {
      case SETUP:
        return this.setUpMove((ValidSetupAction) action);
      case SETUP_REINFORCE:
        return this.setUpSetupReinforceMove((ValidSetupReinforceAction) action);
      case REINFORCE:
        return this.setUpReinforceNormalMove((ValidReinforceAction) action);
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
   * Sets up the move options for setupReinforce.
   *
   * @param move
   * @return
   */
  private JsonObject setUpSetupReinforceMove(ValidSetupReinforceAction move) {
    Set<TerritoryEnum> terrs = move.getTerritories();
    Collection<Integer> ordTerrs = this.getOrdinalSet(terrs);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.SETUP_REINFORCE.ordinal());
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("territories", GSON.toJson(ordTerrs));
    jsonObject.addProperty("troopsToPlace", move.getTroopsLeftToPlace());
    return jsonObject;
  }

  /**
   * Sets up the move options for Reinforce.
   *
   * @param move
   * @return
   */
  private JsonObject setUpReinforceNormalMove(ValidReinforceAction move) {
    Set<TerritoryEnum> terrs = move.getTerritories();
    Collection<Integer> ordTerrs = this.getOrdinalSet(terrs);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.REINFORCE.ordinal());
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("territories", GSON.toJson(ordTerrs));
    jsonObject.addProperty("troopsToPlace", move.getNumberToReinforce());
    return jsonObject;
  }

  /**
   * sets up set up move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpMove(ValidSetupAction move) {
    List<TerritoryEnum> territories = move.getTerritories();
    Collection<Integer> ordTerrs = this.getOrdinalSet(territories);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.SETUP.ordinal());
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("selectable", GSON.toJson(ordTerrs));
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
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.TURN_IN_CARD.ordinal());
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("cards", GSON.toJson(cards));
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
    Map<Integer, Integer> ordDie = this.getOrdinalMap(numberDie);
    Map<Integer, Collection<Integer>> attackOrd =
        this.getOrdinalCollectionMap(whoToAttack.asMap());
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.CHOOSE_ATTACK_DIE.ordinal());
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("maxDieRoll", GSON.toJson(ordDie));
    jsonObject.addProperty("whoCanAttack", GSON.toJson(attackOrd));
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
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("defendTerritory", toDefend.ordinal());
    jsonObject.addProperty("maxDieRoll", maxDie);
    System.out.println(move.getMovePlayer().toString());
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
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
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
    Multimap<TerritoryEnum, TerritoryEnum> reachable =
        move.getReachableTerritores();
    Map<TerritoryEnum, Integer> maxMove = move.maxTroopsToMove();
    Map<Integer, Collection<Integer>> ordReachable =
        this.getOrdinalCollectionMap(reachable.asMap());
    Map<Integer, Integer> moveOrd = this.getOrdinalMap(maxMove);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", MoveType.MOVE_TROOPS.ordinal());
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("canMove", GSON.toJson(ordReachable));
    jsonObject.addProperty("maxTroopsMove", GSON.toJson(moveOrd));
    return jsonObject;
  }

  private Map<Integer, Integer> getOrdinalMap(Map<TerritoryEnum, Integer> map) {
    Map<Integer, Integer> ordMap = new HashMap<>();
    for (java.util.Map.Entry<TerritoryEnum, Integer> entry : map.entrySet()) {
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

  private Collection<Integer> getOrdinalSet(Collection<TerritoryEnum> set) {
    List<Integer> ordSet = new ArrayList<>();
    for (TerritoryEnum terr : set) {
      ordSet.add(terr.ordinal());
    }
    return ordSet;
  }
}
