package com.example.thatalextaylor;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameTest {
    @Test
    public void shouldRecognizeWonGame() {
        Connect4Board board = new TestBoard(1, 10);
        Game game = new Game(board);
        assertThat(game.isComplete(), is(false));
        game.makePlay(0);
        assertThat(game.isComplete(), is(true));
    }

    @Test
    public void shouldAlternateTeams() {
        Connect4Board board = new TestBoard(10, 10);
        Game game = new Game(board);
        assertThat(game.getCurrentTurnsTeam(), is(Team.Red));
        game.makePlay(0);
        assertThat(game.getCurrentTurnsTeam(), is(Team.Green));
        game.makePlay(0);
        assertThat(game.getCurrentTurnsTeam(), is(Team.Red));
        game.makePlay(0);
        assertThat(game.getCurrentTurnsTeam(), is(Team.Green));
    }

    @Test
    public void shouldReportCorrectWinningTeam() {
        Connect4Board board = new TestBoard(2, 10);
        Game game = new Game(board);
        assertThat(game.isComplete(), is(false));
        assertThat(game.getCurrentTurnsTeam(), is(Team.Red));
        game.makePlay(0);
        game.makePlay(0);
        assertThat(game.isComplete(), is(true));
        assertThat(game.getCurrentTurnsTeam(), is(Team.Green));
    }

    @Test
    public void shouldReportFullColumns() {
        Connect4Board board = new TestBoard(10, 3);
        Game game = new Game(board);
        assertThat(game.makePlay(0), is(true));
        assertThat(game.makePlay(0), is(true));
        assertThat(game.makePlay(0), is(false));
    }

    private class TestBoard implements Connect4Board {
        private int winAfterMoves;
        private int columnFullAfterMoves;
        private PlayPosition lastPosition;

        TestBoard(int winAfterMoves, int columnFullAfterMoves) {
            this.winAfterMoves = winAfterMoves;
            this.columnFullAfterMoves = columnFullAfterMoves;
        }

        @Override
        public PlayPosition makePlay(Team team, int column) {
            winAfterMoves--;
            columnFullAfterMoves--;
            if (columnFullAfterMoves == 0)
                return PlayPosition.FULL_COLUMN;
            return new PlayPosition(0, 0);
        }

        @Override
        public boolean isWinningMove(PlayPosition atPosition) {
            this.lastPosition = atPosition;
            return winAfterMoves == 0;
        }

        @Override
        public boolean hasColumn(int column) {
            return true;
        }

        @Override
        public List<List<Team>> getState() {
            return null;
        }

        @Override
        public int getWidth() {
            return 5;
        }

        @Override
        public Set<PlayPosition> getWinningPositions() {
            Set<PlayPosition> winningPositions = new HashSet<>();
            winningPositions.add(lastPosition);
            return winningPositions;
        }
    }
}