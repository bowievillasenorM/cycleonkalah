package com.cycleon.boardgames.services.strategies;

import com.cycleon.boardgames.services.models.KalahGame;
import com.cycleon.boardgames.services.models.KalahGame.Player;

public class TraditionalStrategy implements KalahGameStrategy {

  //static first because not needed for multiple games for now
  public static int PLAYER1_STORE_INDEX;
  public static int PLAYER2_STORE_INDEX;

  @Override
  public KalahGame.KalahType getVariationType() {
    return KalahGame.KalahType.STANDARD;
  }

  @Override
  public KalahGame initializeKalahGame(KalahGame kalahGame, int numberOfHouses, int numberOfSeeds,
      Boolean isEmptyCapture, Boolean doesCountRemainingSeed) {
    PLAYER1_STORE_INDEX = numberOfHouses;
    PLAYER2_STORE_INDEX = numberOfHouses * 2 + 1;

    kalahGame = new KalahGame();
    int[] housesAndStoresDefault = new int[PLAYER2_STORE_INDEX + 1];
    for (int iteration = 0; iteration < housesAndStoresDefault.length; iteration++) {
      housesAndStoresDefault[iteration] =
          (iteration == PLAYER1_STORE_INDEX || iteration == PLAYER2_STORE_INDEX) ? 0
              : numberOfSeeds;
    }

    kalahGame.setHousesAndStores(housesAndStoresDefault);
    kalahGame.setPlayerToMove(KalahGame.Player.PLAYER1);
    kalahGame.setType(KalahGame.KalahType.STANDARD);
    kalahGame.setDoesCountRemainingSeed(doesCountRemainingSeed);
    kalahGame.setEmptyCapture(isEmptyCapture);
    kalahGame.setWinningPlayer(null);
    return kalahGame;
  }

  private int sumOfPlayer1Houses(KalahGame game) {
    int PLAYER1_STORE_INDEX = game.getHousesAndStores().length / 2 - 1;
    int sumOfPlayer1 = 0;
    int ctr = 0;
    while (ctr < PLAYER1_STORE_INDEX) {
      sumOfPlayer1 = sumOfPlayer1 + game.getHousesAndStores()[ctr];
      ctr++;
    }

    return sumOfPlayer1;
  }

  private int sumOfPlayer2Houses(KalahGame game) {
    int PLAYER1_STORE_INDEX = game.getHousesAndStores().length / 2 - 1;
    int PLAYER2_STORE_INDEX = game.getHousesAndStores().length - 1;
    int sumOfPlayer2 = 0;
    int ctr = PLAYER1_STORE_INDEX + 1;
    while (ctr < PLAYER2_STORE_INDEX) {
      sumOfPlayer2 = sumOfPlayer2 + game.getHousesAndStores()[ctr];
      ctr++;
    }

    return sumOfPlayer2;
  }

  public void setWinner(KalahGame game) {
    int PLAYER1_STORE_INDEX = game.getHousesAndStores().length / 2 - 1;
    int PLAYER2_STORE_INDEX = game.getHousesAndStores().length - 1;

    int sumPlayer1 = sumOfPlayer1Houses(game);
    int sumPlayer2 = sumOfPlayer2Houses(game);
    int player1Final = 0;
    int player2Final = 0;

    if (sumPlayer1 == 0 || sumPlayer2 == 0) {

      if (game.getDoesCountRemainingSeed()) {
        player1Final = sumPlayer1 + game.getHousesAndStores()[PLAYER1_STORE_INDEX];
        player2Final = sumPlayer2 + game.getHousesAndStores()[PLAYER2_STORE_INDEX];
      } else {
        player2Final = game.getHousesAndStores()[PLAYER2_STORE_INDEX];
        player1Final = game.getHousesAndStores()[PLAYER1_STORE_INDEX];
      }

      if (player1Final > player2Final) {
        game.setWinningPlayer(KalahGame.GameStatus.WINNING_PLAYER1);
      }
      if (player1Final == player2Final) {
        game.setWinningPlayer(KalahGame.GameStatus.DRAW);
      }
      if (player2Final > player1Final) {
        game.setWinningPlayer(KalahGame.GameStatus.WINNING_PLAYER2);
      }
    }
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

      int indexToSteal = indexToStealFrom(nextIndexToSpreadSeed);

      if (iteration == 1 //if last seed
          && housesAndStores[nextIndexToSpreadSeed] == 0 //fell in house is 0
          && isIndexInPlayerHouse(nextIndexToSpreadSeed, kalahGame)) { //counterpart house is 0
        stealFromOpponent(kalahGame, indexToSteal, nextIndexToSpreadSeed);
      } else {
        housesAndStores[nextIndexToSpreadSeed] = housesAndStores[nextIndexToSpreadSeed] + 1;
      }
      nextIndexToSpreadSeed++;
      if (nextIndexToSpreadSeed == PLAYER2_STORE_INDEX + 1) {
        nextIndexToSpreadSeed = 0; //reset to 0 if more than length, makes the array circular rather than linear
      }
    }

    housesAndStores[index] = 0;

    if (!willLastSeedFallInStore(kalahGame.getPlayerToMove(), index,
        numToAddFromIndex)) { //if seed did not fall in player's store
      switchTurn(kalahGame);
    }
    setWinner(kalahGame);
    return kalahGame;
  }

  public void stealFromOpponent(KalahGame kalahGame, int indexToStealFrom,
      int nextIndexToSpreadSeed) {
    KalahGame.Player player = kalahGame.getPlayerToMove();
    kalahGame.getHousesAndStores()[getStoreIndexOfPlayer(player)] =
        kalahGame.getHousesAndStores()[getStoreIndexOfPlayer(player)]
            + kalahGame.getHousesAndStores()[indexToStealFrom] + 1;

    if (kalahGame.getEmptyCapture() == false
        && kalahGame.getHousesAndStores()[indexToStealFrom] == 0) {
      kalahGame.getHousesAndStores()[nextIndexToSpreadSeed] = 1;
      kalahGame.getHousesAndStores()[getStoreIndexOfPlayer(player)] =
          kalahGame.getHousesAndStores()[getStoreIndexOfPlayer(player)] - 1;
    }

    kalahGame.getHousesAndStores()[indexToStealFrom] = 0;
  }

  private Integer getStoreIndexOfPlayer(KalahGame.Player player) {
    if (player == Player.PLAYER1) {
      return PLAYER1_STORE_INDEX;
    }
    if (player == Player.PLAYER2) {
      return PLAYER2_STORE_INDEX;
    }
    return -1;
  }

  public int indexToStealFrom(int index) {
    return PLAYER2_STORE_INDEX - index - 1;
  }

  public Boolean willLastSeedFallInStore(KalahGame.Player player, int index,
      int numOfSeedToSpread) {
    return numOfSeedToSpread + index == getStoreIndexOfPlayer(player);
  }

  public void switchTurn(KalahGame kalahGame) {
    KalahGame.Player player = kalahGame.getPlayerToMove();
    if (player == KalahGame.Player.PLAYER1) {
      kalahGame.setPlayerToMove(KalahGame.Player.PLAYER2);
    } else {
      kalahGame.setPlayerToMove(KalahGame.Player.PLAYER1);
    }
  }

  public Boolean isIndexInPlayerHouse(int index, KalahGame kalahGame) {
    if (kalahGame.getPlayerToMove() == KalahGame.Player.PLAYER1 && index < PLAYER1_STORE_INDEX) {
      return true;
    }
    return kalahGame.getPlayerToMove() == Player.PLAYER2 && index > PLAYER1_STORE_INDEX
        && index < PLAYER2_STORE_INDEX;
  }

}
