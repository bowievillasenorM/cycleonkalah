package com.cycleon.boardgames.services.service;

import com.cycleon.boardgames.services.models.KalahGame;

import java.util.List;

public interface KalahGameService {

    KalahGame restartKalahGame(int id);

    KalahGame sowAndSimulate(int id, int index);

    KalahGame getKalahGame(int id);
}
