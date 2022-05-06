package com.cycleon.boardgames.services.service;

import com.cycleon.boardgames.services.models.KalahGame;

public interface KalahGameService {

    /**
     * restarting a kalah game
     *
     * @param id id of the kalah game
     * @param numberOfHouses number of houses to setup for each player's side
     * @param numberOfSeeds number of seeds to setup for each player's side
     * @return #{@link {@link KalahGame}}
     */
    KalahGame initializeKalahGame(int id, int numberOfHouses, int numberOfSeeds);

    /**
     *
     * @param id id of the kalah game
     * @param index index to be sowed
     * @return #{@link {@link KalahGame}}
     */
    KalahGame sowAndSimulate(int id, int index);

    /**
     *
     * @param id id of the kalah game
     * @return @return#{@link {@link KalahGame}}
     */
    KalahGame getKalahGame(int id);
}
