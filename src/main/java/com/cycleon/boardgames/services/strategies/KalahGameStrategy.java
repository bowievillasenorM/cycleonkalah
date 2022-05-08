package com.cycleon.boardgames.services.strategies;

import com.cycleon.boardgames.services.models.KalahGame;


public interface KalahGameStrategy {

  KalahGame initializeKalahGame(KalahGame kalahGame, int numberOfHouses, int numberOfSeeds,
      Boolean isEmptyCapture, Boolean doesCountRemainingSeed);

  KalahGame sowAndSimulate(int index, KalahGame kalahGame);

  KalahGame.KalahType getVariationType();
}
