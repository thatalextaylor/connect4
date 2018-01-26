package com.example.thatalextaylor;

import java.util.Set;

public class Game {
    private Connect4Board board;
    private State state;
    private Team turn;
    private Set<PlayPosition> winningMoves;

    public Game(Connect4Board board) {
        this.board = board;
        this.state = State.InProgress;
        this.turn = Team.Red;
    }

    public boolean makePlay(int column) {
        PlayPosition position = board.makePlay(turn, column);
        if (position == PlayPosition.FULL_COLUMN) {
            return false;
        }
        if (board.isWinningMove(position)) {
            this.state = State.Complete;
        } else {
            turn = turn.getNextPlayer();
        }
        return true;
    }

    public boolean isComplete() {
        return state == State.Complete;
    }

    public Connect4Board getBoard() {
        return board;
    }

    public Team getCurrentTurnsTeam() {
        return turn;
    }

    public Set<PlayPosition> getWinningPositions() {
        return board.getWinningPositions();
    }

    public enum State {
        InProgress,
        Complete
    }
}
