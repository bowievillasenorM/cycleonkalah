package com.cycleon.boardgames.services.strategies;

import com.cycleon.boardgames.services.models.KalahGame;

import java.util.HashMap;
import java.util.Map;

public class TraditionalStrategy implements KalahGameStrategy {

    public static final int NUMBER_OF_HOUSES_ON_EACH_SIDE = 6;

    public static final int NUMBER_OF_SEEDS_PER_HOUSE = 4;

    public static final int PLAYER1_STORE_INDEX = NUMBER_OF_HOUSES_ON_EACH_SIDE;
    public static final int PLAYER2_STORE_INDEX = NUMBER_OF_HOUSES_ON_EACH_SIDE * 2 + 1;

    Map<KalahGame.Player, Integer> playerStoreIndexMap;

    public TraditionalStrategy() {
        playerStoreIndexMap = new HashMap<>();
        playerStoreIndexMap.put(KalahGame.Player.PLAYER1, PLAYER1_STORE_INDEX);
        playerStoreIndexMap.put(KalahGame.Player.PLAYER2, PLAYER2_STORE_INDEX);
    }

    @Override
    public KalahGame.KalahType getVariationType() {
        return KalahGame.KalahType.STANDARD;
    }

    @Override
    public KalahGame restartKalahGame(KalahGame kalahGame) {
        kalahGame = new KalahGame();
        int[] housesAndStoresDefault = new int[PLAYER2_STORE_INDEX + 1];
        for (int iteration = 0; iteration < housesAndStoresDefault.length; iteration++) {
            if (iteration == PLAYER1_STORE_INDEX || iteration == PLAYER2_STORE_INDEX) {
                housesAndStoresDefault[iteration] = 0;
            } else {
                housesAndStoresDefault[iteration] = NUMBER_OF_SEEDS_PER_HOUSE;
            }

        }
        kalahGame.setHousesAndStores(housesAndStoresDefault);
        kalahGame.setPlayerToMove(KalahGame.Player.PLAYER1);
        kalahGame.setType(KalahGame.KalahType.STANDARD);
        return kalahGame;
    }

    @Override
    public KalahGame sowAndSimulate(int index, KalahGame kalahGame) {
        int[] housesAndStores = kalahGame.getHousesAndStores();
        int nextIndexToSpreadSeed = index + 1;
        int numToAddFromIndex = housesAndStores[index];


        if (housesAndStores[index] < 1) { //meaning nothing to add exit function
            return kalahGame;
        }

        if (!isIndexInPlayerHouse(index, kalahGame)) { // meaning invalid move from a play exit function
            return kalahGame;
        }

        for (int iteration = housesAndStores[index]; iteration > 0; iteration--) {
            if (isIndexInPlayerHouse(nextIndexToSpreadSeed, kalahGame)
                    && housesAndStores[nextIndexToSpreadSeed] == 0
                    && iteration == 1) {
                stealFromOpponent(kalahGame, housesAndStores, indexToStealFrom(nextIndexToSpreadSeed, kalahGame.getPlayerToMove()));
            } else {
                housesAndStores[nextIndexToSpreadSeed] = housesAndStores[nextIndexToSpreadSeed] + 1;
            }
            nextIndexToSpreadSeed++;
            if (nextIndexToSpreadSeed == PLAYER2_STORE_INDEX + 1) {
                nextIndexToSpreadSeed = 0; //reset to 0 if more than length
            }
        }

        housesAndStores[index] = 0;

        if (!willLastSeedFallInStore(kalahGame.getPlayerToMove(), index, numToAddFromIndex)) {
            switchTurn(kalahGame);
        }

        return kalahGame;
    }

    private void stealFromOpponent(KalahGame kalahGame, int[] housesAndStores, int indexToStealFrom) {
        KalahGame.Player player = kalahGame.getPlayerToMove();
        housesAndStores[getHouseIndexOfPlayer(player)] = housesAndStores[getHouseIndexOfPlayer(player)]
                + housesAndStores[indexToStealFrom] + 1;
        housesAndStores[indexToStealFrom] = 0;
    }

    private Integer getHouseIndexOfPlayer(KalahGame.Player player) {
        return playerStoreIndexMap.get(player);
    }

    private int indexToStealFrom(int index, KalahGame.Player player) {
        return PLAYER2_STORE_INDEX - index - 1;
    }

    private Boolean willLastSeedFallInStore(KalahGame.Player player, int index, int numOfSeedToSpread) {
        if (numOfSeedToSpread + index == playerStoreIndexMap.get(player)) {
            return true;
        }
        return false;
    }

    private void switchTurn(KalahGame kalahGame) {
        KalahGame.Player player = kalahGame.getPlayerToMove();
        if (player == KalahGame.Player.PLAYER1) {
            kalahGame.setPlayerToMove(KalahGame.Player.PLAYER2);
        } else {
            kalahGame.setPlayerToMove(KalahGame.Player.PLAYER1);
        }
    }

    private Boolean isIndexInPlayerHouse(int index, KalahGame kalahGame) {
        if (kalahGame.getPlayerToMove() == KalahGame.Player.PLAYER1 && index < PLAYER1_STORE_INDEX) {
            return true;
        }

        if (kalahGame.getPlayerToMove() == KalahGame.Player.PLAYER2 && index > PLAYER1_STORE_INDEX && index < PLAYER2_STORE_INDEX) {
            return true;
        }
        return false;
    }


}
