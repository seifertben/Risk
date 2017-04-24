package edu.brown.cs.jhbgbbgssg.risk.riskmove;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.Game.risk.riskmove.MoveType;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for ClaimTerritoryMove.
 *
 * @author sarahgilmore
 *
 */
public class ClaimTerritoryMoveTest {

  /**
   * Tests the constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(UUID.randomUUID(),
        TerritoryEnum.AFGHANISTAN, TerritoryEnum.INDIA, 4);
    assertNotNull(claim);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullId() {
    new ClaimTerritoryMove(null, TerritoryEnum.AFGHANISTAN, TerritoryEnum.INDIA,
        4);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if territory the
   * user attacked with is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullClaimFromTerr() {
    new ClaimTerritoryMove(UUID.randomUUID(), null, TerritoryEnum.INDIA, 4);
  }

  /**
   * Tests the constructor throws an IllegalArgumentException if the territory
   * the player is claiming is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullClaimTerr() {
    new ClaimTerritoryMove(UUID.randomUUID(), TerritoryEnum.AFGHANISTAN, null,
        4);
  }

  /**
   * Tests getMoveType returns the correct MoveType.
   */
  @Test
  public void testGetMoveType() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(UUID.randomUUID(),
        TerritoryEnum.AFGHANISTAN, TerritoryEnum.INDIA, 4);
    assertTrue(claim.getMoveType() == MoveType.CLAIM_TERRITORY);
  }

  /**
   * Tests getTerritoryFrom returns the right Territory.
   */
  @Test
  public void testGetAttackTerritory() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(UUID.randomUUID(),
        TerritoryEnum.AFGHANISTAN, TerritoryEnum.INDIA, 4);
    assertTrue(claim.getTerritoryFrom() == TerritoryEnum.AFGHANISTAN);
  }

  /**
   * Tests getTerritoryClaimed returns the correct Territory.
   */
  @Test
  public void testGetTerritoryClaimed() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(UUID.randomUUID(),
        TerritoryEnum.AFGHANISTAN, TerritoryEnum.INDIA, 4);
    assertTrue(claim.getTerritoryClaimed() == TerritoryEnum.INDIA);
  }

  /**
   * Tests getNumberTroops returns the right number of troops.
   */
  @Test
  public void testGetNumberTroops() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(UUID.randomUUID(),
        TerritoryEnum.AFGHANISTAN, TerritoryEnum.INDIA, 4);
    assertTrue(claim.getNumberTroops() == 4);

  }
}
