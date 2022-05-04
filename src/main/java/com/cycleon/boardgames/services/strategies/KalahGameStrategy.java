package com.cycleon.boardgames.services.strategies;

import com.cycleon.boardgames.services.models.KalahGame;

public interface KalahGameStrategy {
    KalahGame restartKalahGame(KalahGame kalahGame);

    KalahGame sowAndSimulate(int index, KalahGame kalahGame);

    KalahGame.KalahType getVariationType();
}
