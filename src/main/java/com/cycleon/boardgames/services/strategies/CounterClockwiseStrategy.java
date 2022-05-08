package com.cycleon.boardgames.services.strategies;

import com.cycleon.boardgames.services.models.KalahGame;

//not yet done
public class CounterClockwiseStrategy implements KalahGameStrategy {

  @Override
  public KalahGame initializeKalahGame(KalahGame kalahGame, int numberOfHouses, int numberOfSeeds) {
    return null;
  }

  @Override
  public KalahGame sowAndSimulate(int index, KalahGame kalahGame) {
    return null;
  }

  @Override
  public KalahGame.KalahType getVariationType() {
    return KalahGame.KalahType.C0UNTER_CLOCKWISE;
  }
}
