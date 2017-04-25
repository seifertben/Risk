package edu.brown.cs.jhbgbbgssg.risk.riskmove;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveTroopsAction;
import edu.brown.cs.jhbgbssg.Game.risk.riskaction.MoveType;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for MoveTroopsMove.
 *
 * @author sarahgilmore
 *
 */
public class MoveTroopsMoveTest {

  /**
   * Tests constructor returns non-null object.
   */
  @Test
  public void testConstructor() {
    MoveTroopsAction move = new MoveTroopsAction(UUID.randomUUID(),
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertNotNull(move);
  }

  /**
   * Tests constructor throws an IllegalArgumentExcetpion if the player id is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullId() {
    new MoveTroopsAction(null, TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
  }

  /**
   * Tests constructor throws an IllegalArgumentExcetpion if the from territory
   * is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullTerrFrom() {
    new MoveTroopsAction(UUID.randomUUID(), null, TerritoryEnum.ALBERTA, 4);
  }

  /**
   * Tests constructor throws an IllegalArgumentExcetpion if the to territory is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullTerrTo() {
    new MoveTroopsAction(UUID.randomUUID(), TerritoryEnum.ALASKA, null, 4);
  }

  /**
   * Tests getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    MoveTroopsAction move = new MoveTroopsAction(UUID.randomUUID(),
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertTrue(move.getMoveType() == MoveType.MOVE_TROOPS);
  }

  /**
   * Tests getMoveType returns the from territory.
   */
  @Test
  public void testGetFromTerritory() {
    MoveTroopsAction move = new MoveTroopsAction(UUID.randomUUID(),
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertTrue(move.getFromTerritory() == TerritoryEnum.ALASKA);
  }

  /**
   * Tests getMoveType returns the from territory.
   */
  @Test
  public void testGetToTerritory() {
    MoveTroopsAction move = new MoveTroopsAction(UUID.randomUUID(),
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertTrue(move.getToTerrtiory() == TerritoryEnum.ALBERTA);
  }

  /**
   * Tests troopsMoved returns the correct number of troops.
   */
  @Test
  public void testGetTroopsMoved() {
    MoveTroopsAction move = new MoveTroopsAction(UUID.randomUUID(),
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 4);
    assertTrue(move.troopsMoved() == 4);

  }
}
