package edu.brown.cs.jhbgbbgssg.risk.riskmove;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import edu.brown.cs.jhbgbssg.Game.risk.riskmove.ValidReinforceMove;
import edu.brown.cs.jhbgbssg.RiskWorld.TerritoryEnum;

public class ValidReinforceMoveTest {

  @Test
  public void testConstructor() {
    Set<TerritoryEnum> terrs = new HashSet<>();
    terrs.add(TerritoryEnum.ALASKA);
    terrs.add(TerritoryEnum.ALBERTA);
    terrs.add(TerritoryEnum.GREENLAND);
    ValidReinforceMove valid = new ValidReinforceMove(UUID.randomUUID(), terrs,
        3);
    assertNotNull(valid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullId() {
    Set<TerritoryEnum> terrs = new HashSet<>();
    terrs.add(TerritoryEnum.ALASKA);
    terrs.add(TerritoryEnum.ALBERTA);
    terrs.add(TerritoryEnum.GREENLAND);
    new ValidReinforceMove(null, terrs, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullSet() {
    new ValidReinforceMove(UUID.randomUUID(), null, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeNumber() {
    Set<TerritoryEnum> terrs = new HashSet<>();
    terrs.add(TerritoryEnum.ALASKA);
    terrs.add(TerritoryEnum.ALBERTA);
    terrs.add(TerritoryEnum.GREENLAND);
    new ValidReinforceMove(UUID.randomUUID(), terrs, -3);
  }
}
