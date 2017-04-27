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
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ActionProcessorTest {
  
  private RiskActionProcessor processor;
  /**
   * Construct the ActionProcessorTest.
   */
  public ActionProcessorTest() {
   processor = new RiskActionProcessor();
  }
  
  @Test
  public void testSetupAction() {
   processor.processAttackAction(action);
  }
  
  @Test
  public void testSetupReinforceAction() {
    processor.processSetupReinforceAction(action);
  }
  
  @Test
  public void testReinforceAction() {
    processor.processReinforceAction(action);
  }
  
  @Test
  public void testCardTurnIn() {
    processor.processCardTurnInAction(action); 
  }
  
  @Test
  public void testAttackAction() {
    
  }
  
  @Test
  public void testDefendAction() {
    
  }
  
  @Test
  public void testClaimTerritory() {
    
  }
  
  @Test
  public void testMoveTroops() {
    
  }
  
  @Test
  public void testSkipAction() {
    
  }
  
  @Test
  public void testSkipPlayer() {
    
  }
}
