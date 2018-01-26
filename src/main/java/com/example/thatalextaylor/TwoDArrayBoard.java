package com.example.thatalextaylor;

import java.util.ArrayList;
import java.util.List;

//A board backed by a 2D array
public final class TwoDArrayBoard implements Connect4Board {
    private final static int WIN_LENGTH = 4;
    private final int width;
    private final int height;
    private final Team[][] state;

    public TwoDArrayBoard(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException(
                    String.format("Both board dimensions must be greater than 0 (was %d, %d)", width, height));
        }
        this.width = width;
        this.height = height;

        this.state = new Team[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.state[x][y] = Team.None;
            }
        }
    }

    @Override
    public PlayPosition makePlay(Team team, int column) {
        PlayPosition move = getFreePosition(column);
        if (move != PlayPosition.FULL_COLUMN) {
            setMove(team, move);
        }
        return move;
    }

    @Override
    public boolean isWinningMove(PlayPosition atPosition) {
        int lowX = atPosition.getX() - WIN_LENGTH + 1;
        int lowY = atPosition.getY() - WIN_LENGTH + 1;
        int highY = atPosition.getX() + WIN_LENGTH - 1;
        //Checks 7 tiles in four directions for a possible win in a formation like
        //
        //    4  1  2
        //     * * *
        //      ***
        //    3*****3
        //      ***
        //     * * *
        //    2  1  4
        //
        // (1 -> 1, 2 -> 2, etc.
        return
                checkWin(new PlayPosition(atPosition.getX(), lowY), Direction.Up) != Team.None ||
                checkWin(new PlayPosition(lowX, lowY), Direction.UpRight) != Team.None ||
                checkWin(new PlayPosition(lowX, atPosition.getY()), Direction.Right) != Team.None ||
                checkWin(new PlayPosition(lowX, highY), Direction.DownRight) != Team.None;
    }

    @Override
    public boolean hasColumn(int column) {
        return column >= 0 && column < width;
    }

    @Override
    public List<List<Team>> getState() {
        List<List<Team>> result = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Team> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                row.add(state[x][y]);
            }
            result.add(row);
        }
        return result;
    }

    @Override
    public int getWidth() {
        return width;
    }

    //Find where a piece can drop to in the column
    private PlayPosition getFreePosition(int column) {
        for (int y = 0; y < height; y++) {
            PlayPosition candidate = new PlayPosition(column, y);
            if (isFreePosition(candidate)) {
                return candidate;
            }
        }
        return PlayPosition.FULL_COLUMN;
    }

    //Can a piece be placed in this position?
    private boolean isFreePosition(PlayPosition position) {
        return getMove(position) == Team.None;
    }

    //Place a team's piece in a position
    private void setMove(Team team, PlayPosition position) {
        checkBounds(position);
        state[position.getX()][position.getY()] = team;
    }

    //Which team's piece is in a position
    private Team getMove(PlayPosition position) {
        checkBounds(position);
        return state[position.getX()][position.getY()];
    }

    //Make absolutely sure the position is on the board
    //A user should never see this exception. It is here defensively
    private void checkBounds(PlayPosition position) {
        if (!isInBounds(position))
            throw new InvalidPlayPosition(String.format(
                    "PlayPosition(%d, %d) is outside the play area", position.getX(), position.getY()));
    }

    //Check a position is on the board
    private boolean isInBounds(PlayPosition position) {
        return (position != PlayPosition.FULL_COLUMN) &&
                (position != null) &&
                (position.getX() >= 0) &&
                (position.getY() >= 0) &&
                (position.getX() < width) &&
                (position.getY() < height);
    }

    //Check from a position for 7 steps in the direction for a run of 4 tiles the same
    private Team checkWin(PlayPosition position, Direction direction) {
        Team team = Team.None;
        int runLength = 0;
        for (int i = 0; i < WIN_LENGTH * 2 - 2; i++) {
            if (isInBounds(position)) {
                Team candidateTeam = getMove(position);
                if (candidateTeam == team) {
                    runLength++;
                    if (runLength == WIN_LENGTH) {
                        return team;
                    }
                } else {
                    team = candidateTeam;
                    runLength = 1;
                }
            }
            position = position.offset(direction);
        }
        return Team.None;
    }
}
