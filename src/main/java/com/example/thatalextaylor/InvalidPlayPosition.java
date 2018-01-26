package com.example.thatalextaylor;

//An attempt has been made to play outside the bounds of the board
public class InvalidPlayPosition extends RuntimeException {
    public InvalidPlayPosition(String message) {
        super(message);
    }
}
