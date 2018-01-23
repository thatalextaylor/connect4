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
        PlayPosition firstPlay = board.play(Team.Green, 0);
        assertThat(firstPlay.getX(), equalTo(0));
        assertThat(firstPlay.getY(), equalTo(0));
        PlayPosition secondPlay = board.play(Team.Red, 0);
        assertThat(secondPlay.getX(), equalTo(0));
        assertThat(secondPlay.getY(), equalTo(1));
    }

    @Test
    public void shouldFillColumn() {
        ArrayBoard board = new ArrayBoard(1, 1);
        PlayPosition firstPlay = board.play(Team.Green, 0);
        assertThat(firstPlay.getX(), equalTo(0));
        assertThat(firstPlay.getY(), equalTo(0));
        PlayPosition secondPlay = board.play(Team.Red, 0);
        assertThat(secondPlay, is(PlayPosition.FULL_COLUMN));
    }
}