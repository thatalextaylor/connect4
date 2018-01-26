package com.example.thatalextaylor;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SingleArrayBoardTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowNegativeOrZeroDimensions() {
        new SingleArrayBoard(-1, 0);
    }

    @Test
    public void shouldPlaceMove() {
        SingleArrayBoard board = new SingleArrayBoard(2, 2);
        PlayPosition firstPlay = board.makePlay(Team.Green, 0);
        assertThat(firstPlay.getX(), equalTo(0));
        assertThat(firstPlay.getY(), equalTo(0));
        PlayPosition secondPlay = board.makePlay(Team.Red, 0);
        assertThat(secondPlay.getX(), equalTo(0));
        assertThat(secondPlay.getY(), equalTo(1));
    }

    @Test
    public void shouldFillColumn() {
        SingleArrayBoard board = new SingleArrayBoard(1, 1);
        PlayPosition firstPlay = board.makePlay(Team.Green, 0);
        assertThat(firstPlay.getX(), equalTo(0));
        assertThat(firstPlay.getY(), equalTo(0));
        PlayPosition secondPlay = board.makePlay(Team.Red, 0);
        assertThat(secondPlay, is(PlayPosition.FULL_COLUMN));
    }

    @Test
    public void shouldFindHorizontalWin() {
        SingleArrayBoard board = new SingleArrayBoard(4, 1);
        board.makePlay(Team.Green, 0);
        board.makePlay(Team.Green, 1);
        board.makePlay(Team.Green, 2);
        board.makePlay(Team.Green, 3);
        assertThat(board.isWinningMove(new PlayPosition(3, 0)), equalTo(true));
    }

    @Test
    public void shouldFindVerticalWin() {
        SingleArrayBoard board = new SingleArrayBoard(1, 4);
        board.makePlay(Team.Green, 0);
        board.makePlay(Team.Green, 0);
        board.makePlay(Team.Green, 0);
        board.makePlay(Team.Green, 0);
        assertThat(board.isWinningMove(new PlayPosition(0, 3)), equalTo(true));
    }

    @Test
    public void shouldFindComplicatedWinCondition() {
        SingleArrayBoard board = new SingleArrayBoard(7, 7);
        fillBoard(board,
                "R.....R",
                "GR...RG",
                "GGR.RGG",
                "RRR.RRR",
                "GGRRRGG",
                "GRGRGRG",
                "RGGRGGR");
        assertThat(board.isWinningMove(new PlayPosition(3, 3)), equalTo(false));
        board.makePlay(Team.Red, 3);
        assertThat(board.isWinningMove(new PlayPosition(3, 3)), equalTo(true));
    }

    @Test
    public void shouldNotMatchNearWinCondition() {
        SingleArrayBoard board = new SingleArrayBoard(7, 7);
        fillBoard(board,
                "R.....R",
                "GR...RG",
                "GGG.GGG",
                "RRG.GRR",
                "GGRGGRG",
                "GGGRGRG",
                "RGGRGGR");
        board.makePlay(Team.Red, 3);
        assertThat(board.isWinningMove(new PlayPosition(3, 3)), equalTo(false));
    }

    @Test
    public void shouldWinAtCorrectTime() {
        SingleArrayBoard board = new SingleArrayBoard(7, 7);
        assertThat(board.isWinningMove(board.makePlay(Team.Red, 0)), equalTo(false));
        assertThat(board.isWinningMove(board.makePlay(Team.Green, 1)), equalTo(false));
        assertThat(board.isWinningMove(board.makePlay(Team.Red, 0)), equalTo(false));
        assertThat(board.isWinningMove(board.makePlay(Team.Green, 2)), equalTo(false));
        assertThat(board.isWinningMove(board.makePlay(Team.Red, 0)), equalTo(false));
        assertThat(board.isWinningMove(board.makePlay(Team.Green, 3)), equalTo(false));
        assertThat(board.isWinningMove(board.makePlay(Team.Red, 0)), equalTo(true));
    }

    @Test
    public void shouldCheckColumnBounds() {
        SingleArrayBoard board = new SingleArrayBoard(3, 3);
        assertThat(board.hasColumn(-1), is(false));
        assertThat(board.hasColumn(0), is(true));
        assertThat(board.hasColumn(2), is(true));
        assertThat(board.hasColumn(3), is(false));
    }

    @Test(expected = InvalidPlayPosition.class)
    public void shouldCheckColumnLowerBound() {
        SingleArrayBoard board = new SingleArrayBoard(3, 3);
        board.makePlay(Team.Red, -1);
    }

    @Test(expected = InvalidPlayPosition.class)
    public void shouldCheckColumnUpperBound() {
        SingleArrayBoard board = new SingleArrayBoard(3, 3);
        board.makePlay(Team.Red, 3);
    }

    @Test
    public void shouldProvideRenderableBoardData() {
        SingleArrayBoard board = new SingleArrayBoard(2, 2);
        board.makePlay(Team.Red, 0);
        board.makePlay(Team.Green, 1);
        List<List<Team>> state = board.getState();
        assertThat(state.size(), is(2));
        assertThat(state.get(0).size(), is(2));
        assertThat(state.get(0).get(0), is(Team.Red));
        assertThat(state.get(0).get(1), is(Team.Green));
        assertThat(state.get(1).get(0), is(Team.None));
    }

    private void fillBoard(Connect4Board board, String... fill) {
        for (int y = fill.length - 1; y >=0; y--) {
            for (int x = 0; x < fill[0].length(); x++) {
                Team team = Team.None;
                if (fill[y].charAt(x) == 'R') {
                    team = Team.Red;
                } else if (fill[y].charAt(x) == 'G') {
                    team = Team.Green;
                }
                board.makePlay(team, x);
            }
        }
    }
}