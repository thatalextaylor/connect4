package com.example.thatalextaylor;

import java.util.List;

public interface Connect4Board {
    PlayPosition makePlay(Team team, int column);
    boolean isWinningMove(PlayPosition atPosition);
    boolean hasColumn(int column);
    List<List<Team>> getState();
    int getWidth();
}
