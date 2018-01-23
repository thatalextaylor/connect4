package com.example.thatalextaylor;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArrayBoardTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowNegativeOrZeroDimensions() {
        new ArrayBoard(-1, 0);
    }

    @Test
    public void shouldPlaceMove() {
        ArrayBoard board = new ArrayBoard(2, 2);
        PlayPosition firstPlay = board.makePlay(Team.Green, 0);
        assertThat(firstPlay.getX(), equalTo(0));
        assertThat(firstPlay.getY(), equalTo(0));
        PlayPosition secondPlay = board.makePlay(Team.Red, 0);
        assertThat(secondPlay.getX(), equalTo(0));
        assertThat(secondPlay.getY(), equalTo(1));
    }

    @Test
    public void shouldFillColumn() {
        ArrayBoard board = new ArrayBoard(1, 1);
        PlayPosition firstPlay = board.makePlay(Team.Green, 0);
        assertThat(firstPlay.getX(), equalTo(0));
        assertThat(firstPlay.getY(), equalTo(0));
        PlayPosition secondPlay = board.makePlay(Team.Red, 0);
        assertThat(secondPlay, is(PlayPosition.FULL_COLUMN));
    }

    @Test
    public void shouldFindHorizontalWin() {
        ArrayBoard board = new ArrayBoard(4, 1);
        board.makePlay(Team.Green, 0);
        board.makePlay(Team.Green, 1);
        board.makePlay(Team.Green, 2);
        board.makePlay(Team.Green, 3);
        assertThat(board.isWinningMove(new PlayPosition(3, 0)), equalTo(true));
    }

    @Test
    public void shouldFindVerticalWin() {
        ArrayBoard board = new ArrayBoard(1, 4);
        board.makePlay(Team.Green, 0);
        board.makePlay(Team.Green, 0);
        board.makePlay(Team.Green, 0);
        board.makePlay(Team.Green, 0);
        assertThat(board.isWinningMove(new PlayPosition(0, 3)), equalTo(true));
    }
}