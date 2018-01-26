package com.example.thatalextaylor;

public interface GamePresenter {
    //Draw the game board
    void render(Game game);

    //Ask the user which column they want their piece added to
    String getColumn(Game game);

    //Inform the user of a mistake
    void showErrorText(String errorText);
}
