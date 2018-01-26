package com.example.thatalextaylor;

import java.util.List;
import java.util.Scanner;

import static java.util.Collections.reverse;

//Presents the interface in ANSI-colored text on UNIX-like terminals
//Activated with the '--ansi' command line option
public class AnsiPresenter implements GamePresenter {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_BAR = ANSI_YELLOW + "|" + ANSI_RESET;

    private Scanner scanner;

    public AnsiPresenter() {
        this.scanner = new Scanner(System.in);
    }

    public void render(Game game) {
        List<List<Team>> state = game.getBoard().getState();
        reverse(state);
        for (List<Team> row: state) {
            for (Team team: row) {
                System.out.print(ANSI_BAR);
                System.out.print(colorFor(team));
                System.out.print(team.getTeamCharacter());
            }
            System.out.println(ANSI_BAR);
        }

        System.out.println();
        Team team = game.getCurrentTurnsTeam();
        if (game.isComplete()) {
            System.out.println(String.format("Player %d [%s%s%s] wins!",
                    team.getPlayerNumber(),
                    colorFor(team),
                    team.toString().toUpperCase(),
                    ANSI_RESET));
        }
    }

    public String getColumn(Game game) {
        System.out.println();
        Team team = game.getCurrentTurnsTeam();
        System.out.print(String.format("Player %d [%s%s%s] - choose column (1-%s): ",
                team.getPlayerNumber(),
                colorFor(team),
                team.toString().toUpperCase(),
                ANSI_RESET,
                game.getBoard().getWidth()));

        return scanner.next();
    }

    @Override
    public void showErrorText(String errorText) {
        System.out.println(String.format("%s%s%s", ANSI_PURPLE, errorText, ANSI_RESET));
    }

    private String colorFor(Team team) {
        switch (team) {
            case Red:
                return ANSI_RED;
            case Green:
                return ANSI_GREEN;
            default:
                return ANSI_RESET;
        }
    }
}
