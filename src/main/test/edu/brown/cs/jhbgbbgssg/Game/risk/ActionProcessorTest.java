package edu.brown.cs.jhbgbbgssg.Game.risk;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import edu.brown.cs.jhbgbssg.Game.risk.RiskActionProcessor;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
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
   processor.processSetupAction(action);
  }
  
  @Test
  public void testSetupReinforceAction() {
    SetupReinforceAction action = new SetupReinforceAction(player, board,
        TerritoryEnum.NORTHWEST_TERRITORY);
    processor.processSetupReinforceAction(action);
  }
  
  @Test
  public void testReinforceAction() {
    ReinforceAction action = new ReinforceAction(player, board,
        Map<TerritoryEnum, Integer> reinforcedTerritories);
    processor.processReinforceAction(action);
  }
  
  @Test
  public void testCardTurnIn() {
    CardTurnInAction action = new CardTurnInAction(player, board, 1,
        Map<TerritoryEnum, Integer> territoriesReinforced);
    processor.processCardTurnInAction(action); 
  }
  
  @Test
  public void testAttackAction() {
    AttackAction action = new AttackAction(player, TerritoryEnum.CHINA,
        TerritoryEnum.JAPAN, 1);
    processor.processAttackAction(action);
  }
  
  @Test
  public void testDefendAction() {
    AttackAction attack = new AttackAction(player, TerritoryEnum.CHINA,
        TerritoryEnum.JAPAN, 1);
    DefendAction action = new DefendAction(player, board, attack,
        2);
    processor.processDefendAction(action);
  }
  
  @Test
  public void testClaimTerritory() {
    ClaimTerritoryAction action = new ClaimTerritoryAction(player, board,
        TerritoryEnum.BRAZIL, TerritoryEnum.CENTRAL_AMERICA, 1);
    processor.processClaimTerritoryAction(action);
  }
  
  @Test
  public void testMoveTroops() {
    MoveTroopsAction action = new MoveTroopsAction(player, board, TerritoryEnum.ICELAND, TerritoryEnum.GREENLAND, 1);
    processor.processMoveTroopsAction(action);
  }
  
  @Test
  public void testSkipAction() {
    processor.processSkipAction(player);
  }
}
