package com.example.thatalextaylor;

//Turns user input into game actions
public class GameController {
    private Game game;
    private GamePresenter presenter;

    public GameController(Game game, GamePresenter presenter) {
        this.game = game;
        this.presenter = presenter;
    }

    public void run() {
        while (!game.isComplete()) {
            presenter.render(game);

            String rawInput = presenter.getColumn(game);
            int column;
            try {
                column = Integer.parseInt(rawInput) - 1;
            } catch (NumberFormatException e) {
                presenter.showErrorText(
                        String.format("A column number is required (input was '%s')", rawInput));
                continue;
            }

            if (!game.getBoard().hasColumn(column)) {
                presenter.showErrorText(
                        String.format("The column is off the board (input was '%d')", column + 1));
                continue;
            }

            if (!game.makePlay(column)) {
                Team team = game.getCurrentTurnsTeam();
                presenter.showErrorText(
                        String.format("Column %d is full - Player %d [%s] has another turn",
                                column + 1,
                                team.getPlayerNumber(),
                                team.toString().toUpperCase()));
            }
        }
        presenter.render(game);
    }

}
