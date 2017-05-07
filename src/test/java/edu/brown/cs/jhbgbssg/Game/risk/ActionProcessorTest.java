package edu.brown.cs.jhbgbssg.Game.risk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.GameUpdate;
import edu.brown.cs.jhbgbssg.Game.risk.Referee;
import edu.brown.cs.jhbgbssg.Game.risk.RiskActionProcessor;
import edu.brown.cs.jhbgbssg.Game.risk.RiskBoard;
import edu.brown.cs.jhbgbssg.Game.risk.RiskPlayer;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.AttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.CardTurnInAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.DefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.SetupAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidAttackAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidCardAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidDieDefendAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidReinforceAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.ValidSetupAction;
import edu.brown.cs.jhbgbssg.Game.riskworld.TerritoryEnum;

public class ActionProcessorTest {

 private RiskActionProcessor processor;
 private RiskPlayer player;
 private RiskBoard board;
 private Referee referee;
 private RiskPlayer one;
 private RiskPlayer two;


 /**
 * Construct the ActionProcessorTest.
 */
 public ActionProcessorTest() {
 board = new RiskBoard();
 UUID id = new UUID(0, 1);
 player = new RiskPlayer(id);
 one =  new RiskPlayer(UUID.randomUUID());
 two =  new RiskPlayer(UUID.randomUUID());
 Set<RiskPlayer> originalPlayers = new HashSet<>();
 originalPlayers.add(one);
 originalPlayers.add(two);
 referee = new Referee(board, originalPlayers);
 processor = new RiskActionProcessor(referee);
 }

 @Test
 public void testSetupAction() {
 SetupAction action =
 new SetupAction(one, board, TerritoryEnum.NORTHWEST_TERRITORY);
 ValidSetupAction validAction = new ValidSetupAction(one, board);
 GameUpdate update = processor.processSetupAction(action);
 boolean test = validAction.validSetupMove(action);
 assertTrue(test);
 }

 @Test
 public void testReinforceAction() {
 Map<TerritoryEnum, Integer> reinforcedTerritories = new HashMap<>();
 reinforcedTerritories.put(TerritoryEnum.ARGENTINA, 1);
 one.setIntialReinforcement(15);
 board.getTerritory(TerritoryEnum.ARGENTINA).changePlayer(one, 3);
 one.conqueredTerritory(TerritoryEnum.ARGENTINA);
 ReinforceAction action =
 new ReinforceAction(one, board, reinforcedTerritories);
 ValidReinforceAction validAction =
 new ValidReinforceAction(one, new ArrayList());
 boolean test = validAction.validReinforceMove(action);
 assertFalse(test);
 GameUpdate update = processor.processReinforceAction(action);
 }

 @Test
 public void testCardTurnIn() {
 Map<TerritoryEnum, Integer> territoriesReinforced = new HashMap<>();
 territoriesReinforced.put(TerritoryEnum.ALASKA, 1);
CardTurnInAction action =
 new CardTurnInAction(new ArrayList(), player);
 ValidCardAction validAction = new ValidCardAction(player);
 boolean test = validAction.validateCardMove(action);
 assertTrue(test);
 GameUpdate update = processor.processCardTurnInAction(action);
 }


 @Test
 public void testDefendAction() {
 RiskBoard board = new RiskBoard();
 board.getTerritory(TerritoryEnum.CHINA).changePlayer(player, 3);
 player.conqueredTerritory(TerritoryEnum.CHINA);
 AttackAction attack =
 new AttackAction(player, TerritoryEnum.CHINA, TerritoryEnum.JAPAN, 1);
 ValidAttackAction validAttack = new ValidAttackAction(player, board);
 RiskPlayer defender = new RiskPlayer(UUID.randomUUID());
 board.getTerritory(TerritoryEnum.JAPAN).changePlayer(defender, 3);
 DefendAction action = new DefendAction(defender, board, attack, 2);
 defender.conqueredTerritory(TerritoryEnum.JAPAN);
 ValidDieDefendAction validDefend =
 new ValidDieDefendAction(defender, board, TerritoryEnum.JAPAN);
 boolean test = validDefend.validateDefendMove(action);
 assertTrue(test);
 GameUpdate update = processor.processDefendAction(action);
 }

 @Test
 public void testMoveTroops() {
 MoveTroopsAction action = new MoveTroopsAction(player, board,
 TerritoryEnum.ICELAND, TerritoryEnum.GREENLAND, 1);
 GameUpdate update = processor.processMoveTroopsAction(action);
 assertNotNull(update);
 }

 }
