package com.example.thatalextaylor;

import java.util.List;
import java.util.Set;

public interface Connect4Board {
    PlayPosition makePlay(Team team, int column);
    boolean isWinningMove(PlayPosition atPosition);
    boolean hasColumn(int column);
    List<List<Team>> getState();
    int getWidth();
    Set<PlayPosition> getWinningPositions();
}
