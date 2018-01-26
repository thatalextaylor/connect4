package com.example.thatalextaylor;

import java.util.*;

public final class SingleArrayBoard implements Connect4Board {
    private final static int WIN_LENGTH = 4;
    private final int width;
    private final int height;
    private final Team[] state;
    private Set<PlayPosition> winningPositions;

    public SingleArrayBoard(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException(
                    String.format("Both board dimensions must be greater than 0 (was %d, %d)", width, height));
        }
        this.width = width;
        this.height = height;

        this.state = new Team[width * height];
        Arrays.fill(state, Team.None);
        winningPositions = new HashSet<>();
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
        winningPositions = new HashSet<>();
        winningPositions.addAll(checkWin(new PlayPosition(atPosition.getX(), lowY), Direction.Up));
        winningPositions.addAll(checkWin(new PlayPosition(lowX, lowY), Direction.UpRight));
        winningPositions.addAll(checkWin(new PlayPosition(lowX, atPosition.getY()), Direction.Right));
        winningPositions.addAll(checkWin(new PlayPosition(lowX, highY), Direction.DownRight));
        return winningPositions.size() > 0;
    }

    @Override
    public boolean hasColumn(int column) {
        return column >= 0 && column < width;
    }

    @Override
    public List<List<Team>> getState() {
        List<List<Team>> result = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Team> row = new ArrayList<>(Arrays.asList(state).subList(y * width, (y + 1) * width));
            result.add(row);
        }
        return result;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Set<PlayPosition> getWinningPositions() {
        return winningPositions;
    }

    private PlayPosition getFreePosition(int column) {
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
        state[position.getX() + position.getY() * width] = team;
    }

    private Team getMove(PlayPosition position) {
        checkBounds(position);
        return state[position.getX() + position.getY() * width];
    }

    private void checkBounds(PlayPosition position) {
        if (!isInBounds(position))
            throw new InvalidPlayPosition(String.format(
                    "PlayPosition(%d, %d) is outside the play area", position.getX(), position.getY()));
    }

    private boolean isInBounds(PlayPosition position) {
        return (position != PlayPosition.FULL_COLUMN) &&
                (position != null) &&
                (position.getX() >= 0) &&
                (position.getY() >= 0) &&
                (position.getX() < width) &&
                (position.getY() < height);
    }

    private Set<PlayPosition> checkWin(PlayPosition position, Direction direction) {
        Team team = Team.None;
        int runLength = 0;
        Set<PlayPosition> winningRun = new HashSet<>();
        for (int i = 0; i < WIN_LENGTH * 2 - 2; i++) {
            if (isInBounds(position)) {
                Team candidateTeam = getMove(position);
                if (candidateTeam == team) {
                    winningRun.add(position);
                    runLength++;
                    if (runLength == WIN_LENGTH && team != Team.None) {
                        return winningRun;
                    }
                } else {
                    team = candidateTeam;
                    winningRun.clear();
                    winningRun.add(position);
                    runLength = 1;
                }
            }
            position = position.offset(direction);
        }
        return new HashSet<>();
    }
}
