package com.example.thatalextaylor;

public interface Connect4Board {
    PlayPosition makePlay(Team team, int column);
    boolean isWinningMove(PlayPosition atPosition);
}
