package com.example.thatalextaylor;

public interface GamePresenter {
    void render(Game game);

    String getColumn(Game game);

    void showErrorText(String errorText);
}
