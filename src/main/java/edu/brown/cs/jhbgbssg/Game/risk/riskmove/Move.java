package edu.brown.cs.jhbgbssg.Game.risk.riskmove;

import java.util.UUID;

public interface Move {

  MoveType getMoveType();

  UUID getMovePlayer();
}
