package com.cycleon.boardgames.services.models;

public class KalahGame {
    private int[] housesAndStores;
    private Player playerToMove;

    private KalahType type;

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

    public enum Player {
        PLAYER1,
        PLAYER2
    }

    public enum KalahType {
        STANDARD,
        CLOCKWISE,
        EMPTY_CAPTURE,
        EXEMPT_HOUSE_SEED
    }
}
