package com.example.thatalextaylor;

//X/Y deltas for traversing a board
public enum Direction {
    Up(0, 1),
    UpRight(1, 1),
    Right(1, 0),
    DownRight(1, -1);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {

        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
