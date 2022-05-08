package com.cycleon.boardgames.services.models;

public class KalahGame {

  private int[] housesAndStores;
  private Player playerToMove;
  private KalahType type;
  private GameStatus winningPlayer;

  public int[] getHousesAndStores() {
    return housesAndStores;
  }

  public void setHousesAndStores(int[] housesAndStores) {
    this.housesAndStores = housesAndStores;
  }

  public Player getPlayerToMove() {
    return playerToMove;
  }

  public void setPlayerToMove(Player playerToMove) {
    this.playerToMove = playerToMove;
  }

  public KalahType getType() {
    return type;
  }

  public void setType(KalahType type) {
    this.type = type;
  }

  public GameStatus getWinningPlayer() {
    return winningPlayer;
  }

  public void setWinningPlayer(GameStatus winningPlayer) {
    this.winningPlayer = winningPlayer;
  }

  public enum Player {
    PLAYER1,
    PLAYER2
  }

  public enum GameStatus {
    WINNING_PLAYER1,
    WINNING_PLAYER2,
    DRAW,
  }

  public enum KalahType {
    STANDARD,
    C0UNTER_CLOCKWISE
  }
}
