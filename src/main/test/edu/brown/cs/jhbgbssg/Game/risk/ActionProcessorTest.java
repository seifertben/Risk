package edu.brown.cs.jhbgbssg.Game.risk;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.brown.cs.jhbgbssg.Game.risk.GameUpdate;
import edu.brown.cs.jhbgbssg.Game.risk.RiskActionProcessor;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.*;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;
import java.util.UUID;

public class ActionProcessorTest {
  
  private RiskActionProcessor processor;
  private RiskPLayer player;
  private RiskBoard board;
  /**
   * Construct the ActionProcessorTest.
   */
  public ActionProcessorTest() {
   processor = new RiskActionProcessor();
   board = new RiskBoard();
   UUID id = new UUID(0, 1);
   pkayer = new RiskPlayer(id)
  }
  
  @Test
  public void testSetupAction() {
   SetupAction action = new SetupAction(player, board,
       TerritoryEnum.NORTHWEST_TERRITORY);
   ValidSetupAction validAction = new ValidSetupAction(player, board);
   GameUpdate update = processor.processSetupAction(action);
   boolean test = validAction.validSetupMove(action);
   assertTrue(test);
   SetupAction actionAfter = new SetupAction(player, board,
       TerritoryEnum.NORTHWEST_TERRITORY);
   boolean test = validAction.validSetupMove(actionAfter);
   assertFalse(test);
   assertNotNull(update.getPrevMove());
  }
  
  @Test
  public void testSetupReinforceAction() {
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.NORTHWEST_TERRITORY);
    ValidSetupReinforceAction validAction = new SetupReinforceAction(player);
    boolean test = validAction.validSetupReinforceMove(action);
    assertTrue(test);
    GameUpdate update = processor.processSetupReinforceAction(action);
    assertNull(update.getPrevMove());
    actionAfter = new SetupReinforceAction(player, board,
        TerritoryEnum.NORTHWEST_TERRITORY);
    test = validAction.validSetupReinforceMove(actionAfter);
    assertFalse(test);
  }
  
  @Test
  public void testReinforceAction() {
    Map<TerritoryEnum, Integer> reinforcedTerritories = new HashMap<>();
    reinforcedTerritories.put(TerritoryEnum.ARGENTINA, 1);
    ReinforceAction action = new ReinforceAction(player, board, reinforcedTerritories);
    ValidReinforceAction validAction = new ValidReinforceAction(player, board, null);
    boolean test = validAction.validReinforceMove(action);
    assertTrue(test);
    GameUpdate update = processor.processReinforceAction(action);
  }
  
  @Test
  public void testCardTurnIn() {
    Map<TerritoryEnum, Integer> territoriesReinforced = new HashMap<>();
    territoriesReinforced.put(TerritoryEnum.ALASKA, 1);
    CardTurnInAction action = new CardTurnInAction(player, board, 1,
        territories);
    ValidCardAction validAction = new ValidCardAction(player);
    boolean test = validAction.validateCardMove(action);
    assertTrue(test);
    GameUpdate update = processor.processCardTurnInAction(action); 
  }
  
  @Test
  public void testAttackAction() {
    AttackAction action = new AttackAction(player, TerritoryEnum.CHINA,
        TerritoryEnum.JAPAN, 1);
    ValidAttackAction validAttack = new ValidAttackAction(player, board);
    boolean test = validAttack.validAttackMove(action);
    assertTrue(test);
    GameUpdate update = processor.processAttackAction(action);
  }
  
  @Test
  public void testDefendAction() {
    AttackAction attack = new AttackAction(player, TerritoryEnum.CHINA,
        TerritoryEnum.JAPAN, 1);
    DefendAction action = new DefendAction(player, board, attack,
        2);
    ValidAttackAction validAttack = new ValidAttackAction(player, board);
    ValidDieDefendAction validDefend = new ValidDieDefendAction(player, board, TerritoryEnum.MONGOLIA);
    GameUpdate update = processor.processDefendAction(action);
  }
  
  @Test
  public void testClaimTerritory() {
    AttackAction attack = new AttackAction(player, TerritoryEnum.CHINA,
        TerritoryEnum.JAPAN, 1);
    ClaimTerritoryAction action = new ClaimTerritoryAction(player, board,
        TerritoryEnum.BRAZIL, TerritoryEnum.CENTRAL_AMERICA, 1);
    ValidCalimTerritoryAction validAction = new ValidClaimTerritoryAction(player, board, attack);
    GameUpdate update = processor.processClaimTerritoryAction(action);
  }
  
  @Test
  public void testMoveTroops() {
    MoveTroopsAction action = new MoveTroopsAction(player, board, TerritoryEnum.ICELAND, TerritoryEnum.GREENLAND, 1);
    GameUpdate update = processor.processMoveTroopsAction(action);
  }
  
  @Test
  public void testSkipAction() {
    processor.processSkipAction(player);
  }
}
