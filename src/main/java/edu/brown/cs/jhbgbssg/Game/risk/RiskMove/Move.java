package edu.brown.cs.jhbgbssg.Game.risk.RiskMove;

import java.util.UUID;

public interface Move {

  MoveType getMoveType();

  UUID getMovePlayer();
}
