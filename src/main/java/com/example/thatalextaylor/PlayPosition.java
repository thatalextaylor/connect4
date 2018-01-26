package com.example.thatalextaylor;

//Board coordinates for a team to place a piece
public class PlayPosition {
    public static final PlayPosition FULL_COLUMN = new PlayPosition(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private int x;
    private int y;

    public PlayPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PlayPosition offset(Direction direction) {
        return new PlayPosition(this.x + direction.getDeltaX(), this.y + direction.getDeltaY());
    }
}
