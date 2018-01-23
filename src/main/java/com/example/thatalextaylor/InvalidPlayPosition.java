package com.example.thatalextaylor;

public class InvalidPlayPosition extends RuntimeException {
    public InvalidPlayPosition(String message) {
        super(message);
    }
}
