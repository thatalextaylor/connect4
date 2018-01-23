package com.example.thatalextaylor;

public interface Connect4Board {
    PlayPosition play(Team team, int column);

    PlayPosition getFreePosition(int column);
}
