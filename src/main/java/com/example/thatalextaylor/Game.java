package com.example.thatalextaylor;

import java.util.Set;

//The rules of the connect 4 game
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

    //Make a move for the current team in a column and see how that affects the game state
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

    //Which team's turn it is
    public Team getCurrentTurnsTeam() {
        return turn;
    }

    public enum State {
        InProgress,
        Complete
    }
}
