package com.example.thatalextaylor;

import java.util.Arrays;

public class ArrayBoard implements Connect4Board {
    private final int width;
    private final int height;
    private final Team[] board;

    public ArrayBoard(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException(String.format("Both board dimensions must be greater than 0 (was %d, %d)", width, height));
        }
        this.width = width;
        this.height = height;

        this.board = new Team[width * height];
        Arrays.fill(board, Team.None);
    }

    public PlayPosition play(Team team, int column) {
        PlayPosition move = getFreePosition(column);
        if (move != PlayPosition.FULL_COLUMN) {
            setMove(team, move);
        }
        return move;
    }

    public PlayPosition getFreePosition(int column) {
        for (int y = 0; y < height; y++) {
            PlayPosition candidate = new PlayPosition(column, y);
            if (isFreePosition(candidate)) {
                return candidate;
            }
        }
        return PlayPosition.FULL_COLUMN;
    }

    private boolean isFreePosition(PlayPosition position) {
        return getMove(position) == Team.None;
    }

    private void setMove(Team team, PlayPosition position) {
        checkBounds(position);
        board[position.getX() + position.getY() * width] = team;
    }

    private Team getMove(PlayPosition position) {
        checkBounds(position);
        return board[position.getX() + position.getY() * width];
    }

    private void checkBounds(PlayPosition position) {
        if ((position == PlayPosition.FULL_COLUMN) ||
                (position == null) ||
                (position.getX() < 0) ||
                (position.getY() < 0) ||
                (position.getX() >= width) ||
                (position.getY() >= height))
            throw new InvalidPlayPosition(String.format("PlayPosition(%d, %d) is outside the play area", position.getX(), position.getY()));
    }
}
