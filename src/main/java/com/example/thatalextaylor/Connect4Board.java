package com.example.thatalextaylor;

import java.util.List;

//The play area for the game
//It is implemented as an interface to allow other implementations for lower memory, better performance etc.
public interface Connect4Board {
    //Make a move for a team in a column
    PlayPosition makePlay(Team team, int column);

    //Check if a position is part of a winning move
    boolean isWinningMove(PlayPosition atPosition);

    //Check if a column is part of the board
    boolean hasColumn(int column);

    //Get an easy to understand representation of the game state for rendering
    List<List<Team>> getState();

    //Get the number of horizontal spaces the board holds
    int getWidth();
}
