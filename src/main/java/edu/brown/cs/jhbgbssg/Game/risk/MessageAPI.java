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

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.AttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.CardTurnInMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.DefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.Move;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveType;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ReinforceMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.SetupMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidAttackMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidCardMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidDieDefendMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidMoveTroopsMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidReinforceMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidSetupMove;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import edu.brown.cs.jhbgbssh.tuple.Pair;

public class MessageAPI {
  private static final Gson GSON = new Gson();
  private static final TerritoryEnum[] IDS = TerritoryEnum.values();

  private enum MESSAGE_TYPE {
    SELECT, SETUP_REINFORCE, REINFORCE, TURN_IN_CARD, ATTACK, DEFEND, CLAIM_TERRITORY, MOVE_TROOPS;
  }

  public MessageAPI() {

  }

  public JsonObject getJsonObjectMessage(GameUpdate update) {
    JsonObject message = new JsonObject();
    UUID winner = update.getGameWon();
    if (winner == null) {
      message.addProperty("isWinner", false);
    } else {
      message.addProperty("isWinner", true);
      message.addProperty("winner", winner.toString());
    }
    UUID loser = update.getLoser();
    if (loser == null) {
      message.addProperty("isLoser", false);
    } else {
      message.addProperty("isLoser", true);
      message.addProperty("loser", loser.toString());
    }
    if (update.getErrors()) {
      message.addProperty("error", true);
    } else {
      message.addProperty("error", false);
    }
    if (update.getValidMoves() == null) {
      message.addProperty("isNextMove", false);
    } else {
      message.addProperty("isNextMove", false);
      JsonObject obj = this.validJsonMove(update);
      message.add("nextMove", obj);
    }
    if (update.getPrevMove() == null) {
      message.addProperty("isPrevMove", false);
    } else {
      JsonObject obj = this.getPrevMove(update.getPrevMove());
      message.add("prevMove", obj);
    }
    return message;
  }

  /**
   * Converts a json string into the corresponding Java move object.
   *
   * @param message - json message
   * @return move oject
   */
  public Move jsonToMove(String message) {
    JsonObject recieved = GSON.fromJson(message, JsonObject.class);
    int ordinal = recieved.get("type").getAsInt();
    MESSAGE_TYPE type = MESSAGE_TYPE.values()[ordinal];
    switch (type) {
      case SELECT:
        return this.jsonToSelect(recieved);
      case REINFORCE:
        return this.jsonToReinforce(recieved);
      case TURN_IN_CARD:
        return this.jsonToCard(recieved);
      case ATTACK:
        return this.jsonToAttack(recieved);
      case DEFEND:
        return this.jsonToDefend(recieved);
      case CLAIM_TERRITORY:
        return this.jsonToClaim(recieved);
      case MOVE_TROOPS:
        return this.jsonToMoveTroops(recieved);
      default:
        return null;
    }
  }

  /**
   * Translates a jsonObject indicating which territory was selected to a
   * SetupMove object.
   *
   * @param object - json message
   * @return setup move
   */
  private Move jsonToSelect(JsonObject object) {
    UUID playerId = UUID.fromString(object.get("playerId").getAsString());
    int index = object.get("territoryId").getAsInt();
    TerritoryEnum terr = IDS[index];
    SetupMove setup = new SetupMove(playerId, terr);
    return setup;
  }

  /**
   * Translates a jsonObject indicating which territories to reinforce to a
   * ReinforceMove.
   *
   * @param object - json message
   * @return reinforce move
   */
  private Move jsonToReinforce(JsonObject object) {
    UUID playerId = UUID.fromString(object.get("playerId").getAsString());
    Map<TerritoryEnum, Integer> map = GSON.fromJson("reinforced",
        new TypeToken<Map<TerritoryEnum, Integer>>() {
        }.getType());
    ReinforceMove move = new ReinforceMove(playerId, map);
    return move;
  }

  /**
   * Translates a jsonObject indicating which card was turned in and where the
   * troops were put.
   *
   * @param object - json message
   * @return card move
   */
  private Move jsonToCard(JsonObject object) {
    UUID playerId = UUID.fromString(object.get("playerId").getAsString());
    Map<TerritoryEnum, Integer> map = GSON.fromJson("reinforced",
        new TypeToken<Map<TerritoryEnum, Integer>>() {
        }.getType());
    int card = object.get("card").getAsInt();
    return new CardTurnInMove(playerId, card, map);
  }

  /**
   * Translates a jsonObject indicating which territory to attack, from where
   * and with how many die.
   *
   * @param object - json message
   * @return attack move
   */
  private Move jsonToAttack(JsonObject object) {
    UUID playerId = UUID.fromString(object.get("playerId").getAsString());
    int index = object.get("attack_from").getAsInt();
    TerritoryEnum attackFrom = IDS[index];
    index = object.get("attacking").getAsInt();
    TerritoryEnum attacking = IDS[index];
    int numberDie = object.get("number_die").getAsInt();
    return new AttackMove(playerId, attackFrom, attacking, numberDie);
  }

  /**
   * Translates a jsonObject indicating which territory is defending, against
   * which territory, and how many die was rolled.
   *
   * @param object
   * @return
   */
  private Move jsonToDefend(JsonObject object) {
    UUID playerId = UUID.fromString(object.get("player_id").getAsString());
    UUID attackerId = UUID.fromString(object.get("attacker_id").getAsString());
    int index = object.get("attacking").getAsInt();
    TerritoryEnum attacking = IDS[index];
    index = object.get("defending").getAsInt();
    TerritoryEnum defending = IDS[index];
    int numberDie = object.get("number_die").getAsInt();
    return new DefendMove(new Pair<>(playerId, defending),
        new Pair<>(attackerId, attacking), numberDie);
  }

  /**
   * Translates a json object indicating which territory to claim and with how
   * many troops to a ClaimTerritoryMove.
   *
   * @param object - json message
   * @return claim territory move
   */
  private Move jsonToClaim(JsonObject object) {
    UUID playerId = UUID.fromString(object.get("player_id").getAsString());
    int index = object.get("claiming_from").getAsInt();
    TerritoryEnum claimingFrom = IDS[index];
    index = object.get("claiming").getAsInt();
    TerritoryEnum claiming = IDS[index];
    int troopsMoved = object.get("troops_moved").getAsInt();
    return new ClaimTerritoryMove(playerId, claimingFrom, claiming,
        troopsMoved);
  }

  /**
   * Translates a json object indicating which territory to move troops to, from
   * where and how many.
   *
   * @param object - json message
   * @return move troops move
   */
  private Move jsonToMoveTroops(JsonObject object) {
    UUID playerId = UUID.fromString(object.get("player_id").getAsString());
    int index = object.get("move_from").getAsInt();
    TerritoryEnum moveFrom = IDS[index];
    index = object.get("move_to").getAsInt();
    TerritoryEnum moveTo = IDS[index];
    int troopsMoved = object.get("troops_moved").getAsInt();
    return new MoveTroopsMove(playerId, moveFrom, moveTo, troopsMoved);
  }

  private JsonObject getPrevMove(Move prevMove) {
    MoveType type = prevMove.getMoveType();
    switch (type) {
      case SETUP:
        return this.prevSetupMove((SetupMove) prevMove);
      case REINFORCE:
        return this.prevReinforceMove((ReinforceMove) prevMove);
      case TURN_IN_CARD:
        return this.prevCardMove((CardTurnInMove) prevMove);
      case CHOOSE_ATTACK_DIE:
        return this.prevAttackMove((AttackMove) prevMove);
      case CHOOSE_DEFEND_DIE:
        return this.prevDefendMove((DefendMove) prevMove);
      case CLAIM_TERRITORY:
        return this.prevClaimMove((ClaimTerritoryMove) prevMove);
      default:
        return this.prevMoveTroops((MoveTroopsMove) prevMove);
    }
  }

  /**
   * Sets up a JsonObject that represents a setup move.
   *
   * @param move - set up move to convert to json
   * @return json object
   */
  private JsonObject prevSetupMove(SetupMove move) {
    TerritoryEnum selected = move.getSelectedTerritory();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("move", "select");
    jsonObject.addProperty("player_id", move.getMovePlayer().toString());
    jsonObject.addProperty("selected", selected.ordinal());
    return jsonObject;
  }

  /**
   * Sets up a JsonObject that represents a reinforce move.
   *
   * @param move - reinforce move
   * @return json object
   */
  private JsonObject prevReinforceMove(ReinforceMove move) {
    Map<TerritoryEnum, Integer> reinforced = move.getReinforcedTerritories();
    Map<Integer, Integer> ordinalReinforced = this.getOrdinalMap(reinforced);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player_id", move.getMovePlayer().toString());
    jsonObject.addProperty("move", "reinforce");
    jsonObject.addProperty("reinforce", GSON.toJson(ordinalReinforced));
    return jsonObject;
  }

  private JsonObject prevCardMove(CardTurnInMove move) {
    Map<TerritoryEnum, Integer> reinforced = move.getTerritoriesReinforced();
    Map<Integer, Integer> ordinalReinforced = this.getOrdinalMap(reinforced);
    int card = move.getCard();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player_id", move.getMovePlayer().toString());
    jsonObject.addProperty("move", "card");
    jsonObject.addProperty("card", card);
    jsonObject.addProperty("reinforced", GSON.toJson(ordinalReinforced));
    return jsonObject;
  }

  private JsonObject prevAttackMove(AttackMove move) {
    TerritoryEnum attackFrom = move.getAttackFrom();
    TerritoryEnum attacking = move.getAttackTo();
    List<Integer> roll = move.getDieResults();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player_id", move.getMovePlayer().toString());
    jsonObject.addProperty("move", "attack");
    jsonObject.addProperty("attack_from", attackFrom.ordinal());
    jsonObject.addProperty("attack_to", attacking.ordinal());
    jsonObject.addProperty("roll", GSON.toJson(roll));
    return jsonObject;
  }

  private JsonObject prevDefendMove(DefendMove move) {
    UUID attacker = move.getAttackerId();
    UUID defender = move.getMovePlayer();
    TerritoryEnum attacking = move.getAttackingTerritory();
    TerritoryEnum defending = move.getDefendedTerritory();
    List<Integer> roll = move.getRoll();
    int attackerTroopsLost = move.getTroopsAttackerLost();
    int defenderTroopsLost = move.getTroopsDefenderLost();
    boolean defenderLost = move.getDefenderLostTerritory();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("move", "defend");
    jsonObject.addProperty("defender", GSON.toJson(defender));
    jsonObject.addProperty("attacker", GSON.toJson(attacker));
    jsonObject.addProperty("roll", GSON.toJson(roll));
    jsonObject.addProperty("attack_territory", attacking.ordinal());
    jsonObject.addProperty("defend_territory", defending.ordinal());
    jsonObject.addProperty("attacker_troops_lost", attackerTroopsLost);
    jsonObject.addProperty("defender_troops_lost", defenderTroopsLost);
    jsonObject.addProperty("is_territory_lost", defenderLost);
    return jsonObject;
  }

  private JsonObject prevClaimMove(ClaimTerritoryMove move) {
    UUID player = move.getMovePlayer();
    TerritoryEnum claimFrom = move.getTerritoryFrom();
    TerritoryEnum claimed = move.getTerritoryClaimed();
    int numberTroops = move.getNumberTroops();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player_id", GSON.toJson(player));
    jsonObject.addProperty("move", "claim");
    jsonObject.addProperty("claimed_from", claimFrom.ordinal());
    jsonObject.addProperty("claimed_territory", claimed.ordinal());
    jsonObject.addProperty("number_troops", numberTroops);
    return jsonObject;
  }

  private JsonObject prevMoveTroops(MoveTroopsMove move) {
    UUID player = move.getMovePlayer();
    TerritoryEnum moveFrom = move.getFromTerritory();
    TerritoryEnum moveTo = move.getToTerrtiory();
    int numberTroops = move.troopsMoved();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player_id", GSON.toJson(player));
    jsonObject.addProperty("move", "moved_troops");
    jsonObject.addProperty("move_from", moveFrom.toString());
    jsonObject.addProperty("move_to", moveTo.toString());
    jsonObject.addProperty("number_troops", numberTroops);
    return jsonObject;
  }

  private JsonObject validJsonMove(GameUpdate update) {
    ValidAction action = update.getValidMoves();
    MoveType type = action.getMoveType();
    switch (type) {
      case SETUP:
        return this.setUpMove((ValidSetupMove) action);
      case REINFORCE:
        return this.setUpReinforceMove((ValidReinforceMove) action);
      case TURN_IN_CARD:
        return this.setUpTurnInCards((ValidCardMove) action);
      case CHOOSE_ATTACK_DIE:
        return this.setUpAttackMove((ValidAttackMove) action);
      case CHOOSE_DEFEND_DIE:
        return this.setUpDefendMove((ValidDieDefendMove) action);
      case CLAIM_TERRITORY:
        return this.setUpClaimTerritoryMove((ValidClaimTerritoryMove) action);
      default:
        return this.setUpMoveTroops((ValidMoveTroopsMove) action);
    }
  }

  /**
   * sets up set up move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpMove(ValidSetupMove move) {
    List<TerritoryEnum> territories = move.getTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("move_options", "select");
    jsonObject.addProperty("player_id", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("selectable", GSON.toJson(territories));
    return jsonObject;
  }

  /**
   * Sets up the reinforce move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpReinforceMove(ValidReinforceMove move) {
    Set<TerritoryEnum> terrs = move.getTerritories();
    Set<Integer> ordTerr = this.getOrdinalSet(terrs);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("move_options", "reinforce");
    jsonObject.addProperty("player_id", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("territories", GSON.toJson(ordTerr));
    jsonObject.addProperty("number_troops", move.getNumberToReinforce());
    return jsonObject;
  }

  /**
   * sets up card turn in options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpTurnInCards(ValidCardMove move) {
    Multiset<Integer> cards = move.getCards();
    Set<TerritoryEnum> terrs = move.getTerritories();
    Set<Integer> ordTerr = this.getOrdinalSet(terrs);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("move_options", "card_turn_in");
    jsonObject.addProperty("playerId", GSON.toJson(move.getMovePlayer()));
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
  private JsonObject setUpAttackMove(ValidAttackMove move) {
    move.whoToAttack();
    Map<TerritoryEnum, Integer> numberDie = move.getAttackableTerritories();
    Multimap<TerritoryEnum, TerritoryEnum> whoToAttack = move.whoToAttack();
    Map<Integer, Integer> ordNumberDie = this.getOrdinalMap(numberDie);
    Map<Integer, Collection<Integer>> ordToAttack = this
        .getOrdinalCollectionMap(whoToAttack.asMap());
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("move_options", "attack");
    jsonObject.addProperty("player_id", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("territory_max_die_roll", GSON.toJson(ordNumberDie));
    jsonObject.addProperty("can_attack", GSON.toJson(ordToAttack));
    return jsonObject;
  }

  /**
   * sets up the defend move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpDefendMove(ValidDieDefendMove move) {
    TerritoryEnum toDefend = move.getDefendTerritory();
    int maxDie = move.getMaxNumberDie();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player_id", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("move_options", "defend");
    jsonObject.addProperty("to_defend", toDefend.ordinal());
    jsonObject.addProperty("max_number_die", maxDie);
    return jsonObject;
  }

  /**
   * sets up the claim territory move options.
   *
   * @param move
   * @return
   */
  private JsonObject setUpClaimTerritoryMove(ValidClaimTerritoryMove move) {
    TerritoryEnum attacker = move.getFromTerritory();
    TerritoryEnum claimed = move.getClaimedTerritory();
    int maxTroops = move.getMaxNumberTroops();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("move_options", "claim");
    jsonObject.addProperty("player_dd", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("to_claim", claimed.ordinal());
    jsonObject.addProperty("claim_from", attacker.ordinal());
    jsonObject.addProperty("max_troop_number", maxTroops);
    return jsonObject;
  }

  /**
   * This method sets up a JsonObject representing the ValidMoveTroopsMove
   * object.
   *
   * @param move - valid move troops move
   * @return json object
   */
  private JsonObject setUpMoveTroops(ValidMoveTroopsMove move) {
    Multimap<TerritoryEnum, TerritoryEnum> reachable = move
        .getReachableTerritores();
    Map<TerritoryEnum, Integer> maxMove = move.maxTroopsToMove();
    Map<Integer, Collection<Integer>> ordReachable = this
        .getOrdinalCollectionMap(reachable.asMap());
    Map<Integer, Integer> ordMaxMove = this.getOrdinalMap(maxMove);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("player_id", GSON.toJson(move.getMovePlayer()));
    jsonObject.addProperty("move_options", "move_troops");
    jsonObject.addProperty("can_move_move", GSON.toJson(ordReachable));
    jsonObject.addProperty("max_troop_move", GSON.toJson(ordMaxMove));
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
