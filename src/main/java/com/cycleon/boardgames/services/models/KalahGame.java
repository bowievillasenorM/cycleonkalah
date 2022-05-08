package com.cycleon.boardgames.services.models;

public class KalahGame {

  private int[] housesAndStores;
  private Player playerToMove;
  private KalahType type;
  private GameStatus winningPlayer;
  private Boolean isCounterClockwise;
  private Boolean isEmptyCapture;
  private Boolean doesCountRemainingSeed;

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

  public Boolean getCounterClockwise() {
    return isCounterClockwise;
  }

  public void setCounterClockwise(Boolean counterClockwise) {
    isCounterClockwise = counterClockwise;
  }

  public Boolean getEmptyCapture() {
    return isEmptyCapture;
  }

  public void setEmptyCapture(Boolean emptyCapture) {
    isEmptyCapture = emptyCapture;
  }

  public Boolean getDoesCountRemainingSeed() {
    return doesCountRemainingSeed;
  }

  public void setDoesCountRemainingSeed(Boolean doesCountRemainingSeed) {
    this.doesCountRemainingSeed = doesCountRemainingSeed;
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
