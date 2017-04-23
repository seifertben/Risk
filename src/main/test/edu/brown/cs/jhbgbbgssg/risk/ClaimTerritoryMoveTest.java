package edu.brown.cs.jhbgbbgssg.risk;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ClaimTerritoryMove;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

/**
 * JUnit tests for ClaimTerritoryMove.
 *
 * @author sarahgilmore
 *
 */
public class ClaimTerritoryMoveTest {

  /**
   * Tests constructor returns a non-null object.
   */
  @Test
  public void testConstructor() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(UUID.randomUUID(),
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
    assertNotNull(claim);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the player id is
   * null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullId() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(null,
        TerritoryEnum.ALASKA, TerritoryEnum.ALBERTA, 3);
    assertNotNull(claim);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the attacking
   * territory id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullAttackTerr() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(UUID.randomUUID(), null,
        TerritoryEnum.ALBERTA, 3);
    assertNotNull(claim);
  }

  /**
   * Tests constructor throws an IllegalArgumentException if the claimed
   * territory id is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullClaimTerr() {
    ClaimTerritoryMove claim = new ClaimTerritoryMove(UUID.randomUUID(),
        TerritoryEnum.ALASKA, null, 3);
    assertNotNull(claim);
  }
}
