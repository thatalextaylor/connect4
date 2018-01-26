package com.example.thatalextaylor;

import java.util.List;
import java.util.Scanner;

import static java.util.Collections.reverse;

public class CommandLinePresenter implements GamePresenter {
    private Scanner scanner;

    public CommandLinePresenter() {
        this.scanner = new Scanner(System.in);
    }

    public void render(Game game) {
        List<List<Team>> state = game.getBoard().getState();
        reverse(state);
        for (List<Team> row: state) {
            for (Team team: row) {
                System.out.print("|");
                System.out.print(team.getTeamCharacter());
            }
            System.out.println("|");
        }

        System.out.println();
        Team team = game.getCurrentTurnsTeam();
        if (game.isComplete()) {
            System.out.println(
                    String.format("Player %d [%s] wins!", team.getPlayerNumber(), team.toString().toUpperCase()));
        }
    }

    public String getColumn(Game game) {
        System.out.println();
        Team team = game.getCurrentTurnsTeam();
        System.out.print(
                String.format("Player %d [%s] - choose column (1-%s): ",
                        team.getPlayerNumber(),
                        team.toString().toUpperCase(),
                        game.getBoard().getWidth()));

        return scanner.next();
    }

    @Override
    public void showErrorText(String errorText) {
        System.out.println(errorText);
    }
}
