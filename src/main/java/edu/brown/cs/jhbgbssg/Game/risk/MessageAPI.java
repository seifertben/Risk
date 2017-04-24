package edu.brown.cs.jhbgbssg.Game.risk;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

public class MessageAPI {
  private static final Gson GSON = new Gson();

  private enum Message_Type {
    SELECT, SETUP_REINFORCE, REINFORCE, TURN_IN_CARD, ATTACK, DEFEND, CLAIM_TERRITORY;
  }

  public MessageAPI(List<UUID> sessions) {

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

  public Move jsonToMove(String message) {
    JsonObject recieved = GSON.fromJson(message, JsonObject.class);
    int ordinal = recieved.get("MESSAGE_TYPE").getAsInt();
    Message_Type type = Message_Type.values()[ordinal];
    switch (type) {
    }
    return null;
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

  private JsonObject prevSetupMove(SetupMove move) {
    TerritoryEnum selected = move.getSelectedTerritory();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", "select");
    jsonObject.addProperty("selected", selected.toString());
    return jsonObject;
  }

  private JsonObject prevReinforceMove(ReinforceMove move) {
    Map<TerritoryEnum, Integer> reinforce = move.getReinforcedTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", "reinforce");
    jsonObject.addProperty("reinforce", reinforce.toString());
    return jsonObject;
  }

  private JsonObject prevCardMove(CardTurnInMove move) {
    Map<TerritoryEnum, Integer> reinforced = move.getTerritoriesReinforced();
    int card = move.getCard();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", "card");
    jsonObject.addProperty("card", card);
    jsonObject.addProperty("reinforced", reinforced.toString());
    return jsonObject;
  }

  private JsonObject prevAttackMove(AttackMove move) {
    TerritoryEnum attackFrom = move.getAttackFrom();
    TerritoryEnum attacking = move.getAttackTo();
    List<Integer> dieRoll = move.getDieResults();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", "attack");
    jsonObject.addProperty("attackFrom", attackFrom.toString());
    jsonObject.addProperty("attackTo", attacking.toString());
    jsonObject.addProperty("dieRolled", dieRoll.toString());
    return jsonObject;
  }

  private JsonObject prevDefendMove(DefendMove move) {
    UUID attacker = move.getAttackingMove().getMovePlayer();
    UUID defender = move.getMovePlayer();
    TerritoryEnum attacking = move.getAttackingTerritory();
    TerritoryEnum defending = move.getDefendedTerritory();
    List<Integer> dieRoll = move.getRoll();
    int attackerTroopsLost = move.getTroopsAttackerLost();
    int defenderTroopsLost = move.getTroopsDefenderLost();
    boolean defenderLost = move.getDefenderLostTerritory();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("defender", defender.toString());
    jsonObject.addProperty("attacker", attacker.toString());
    jsonObject.addProperty("moveType", "defend");
    jsonObject.addProperty("dieRolled", dieRoll.toString());
    jsonObject.addProperty("attackTerritory", attacking.toString());
    jsonObject.addProperty("defendTerritory", defending.toString());
    jsonObject.addProperty("attackerTroopLost", attackerTroopsLost);
    jsonObject.addProperty("defendTerritory", defenderTroopsLost);
    jsonObject.addProperty("isTerritoryLost", defenderLost);
    return jsonObject;
  }

  private JsonObject prevClaimMove(ClaimTerritoryMove move) {
    UUID player = move.getMovePlayer();
    TerritoryEnum claimFrom = move.getTerritoryFrom();
    TerritoryEnum claimed = move.getTerritoryClaimed();
    int numberTroops = move.getNumberTroops();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", player.toString());
    jsonObject.addProperty("moveType", "claim");
    jsonObject.addProperty("claimedFrom", claimFrom.toString());
    jsonObject.addProperty("claimedTerritory", claimed.toString());
    jsonObject.addProperty("numberTroops", numberTroops);
    return jsonObject;
  }

  private JsonObject prevMoveTroops(MoveTroopsMove move) {
    UUID player = move.getMovePlayer();
    TerritoryEnum moveFrom = move.getFromTerritory();
    TerritoryEnum moveTo = move.getToTerrtiory();
    int numberTroops = move.troopsMoved();

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", player.toString());
    jsonObject.addProperty("moveType", "moveTroops");
    jsonObject.addProperty("moveFrom", moveFrom.toString());
    jsonObject.addProperty("moveTo", moveTo.toString());
    jsonObject.addProperty("numberTroops", numberTroops);
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

  private JsonObject setUpReinforceMove(ValidReinforceMove move) {
    Set<TerritoryEnum> terrs = move.getTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("territories", terrs.toString());
    jsonObject.addProperty("numberTroops", move.getNumberToReinforce());
    return jsonObject;
  }

  private JsonObject setUpClaimTerritoryMove(ValidClaimTerritoryMove move) {
    TerritoryEnum attacker = move.getFromTerritory();
    TerritoryEnum claimed = move.getClaimedTerritory();
    int maxTroops = move.getMaxNumberTroops();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", "validClaim");
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("toClaim", claimed.toString());
    jsonObject.addProperty("claimFrom", attacker.toString());
    jsonObject.addProperty("numberTroops", maxTroops);
    return jsonObject;
  }

  private JsonObject setUpAttackMove(ValidAttackMove move) {
    move.whoToAttack();
    Map<TerritoryEnum, Integer> numberDie = move.getAttackableTerritories();
    Multimap<TerritoryEnum, TerritoryEnum> whoToAttack = move.whoToAttack();
    Collection<Entry<TerritoryEnum, TerritoryEnum>> who = whoToAttack.entries();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("moveType", "validAttack");
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("territoryMaxDie", numberDie.toString());
    jsonObject.addProperty("whoAttack", who.toString());
    return jsonObject;
  }

  private JsonObject setUpDefendMove(ValidDieDefendMove move) {
    TerritoryEnum toDefend = move.getDefendTerritory();
    int maxDie = move.getMaxNumberDie();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", "validDefend");
    jsonObject.addProperty("toDefend", toDefend.toString());
    jsonObject.addProperty("maxNumberDie", maxDie);
    return jsonObject;
  }

  private JsonObject setUpMoveTroops(ValidMoveTroopsMove move) {
    Multimap<TerritoryEnum, TerritoryEnum> toMove = move
        .getReachableTerritores();
    Map<TerritoryEnum, Integer> maxMove = move.maxTroopsToMove();
    Collection<Entry<TerritoryEnum, TerritoryEnum>> toMoveCollection = toMove
        .entries();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", "validMoveTroops");
    jsonObject.addProperty("toMove", toMoveCollection.toString());
    jsonObject.addProperty("maxMove", maxMove.toString());
    return jsonObject;
  }

  private JsonObject setUpTurnInCards(ValidCardMove move) {
    Multiset<Integer> cards = move.getCards();
    Set<TerritoryEnum> terrs = move.getTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", "validCardTurnIn");
    jsonObject.addProperty("cards", cards.toString());
    jsonObject.addProperty("territories", terrs.toString());
    return jsonObject;
  }

  private JsonObject setUpMove(ValidSetupMove move) {
    List<TerritoryEnum> territories = move.getTerritories();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("playerId", move.getMovePlayer().toString());
    jsonObject.addProperty("moveType", "validSelect");
    jsonObject.addProperty("selectable", territories.toString());
    return jsonObject;
  }
}
