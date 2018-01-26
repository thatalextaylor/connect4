package com.example.thatalextaylor;

public class Main {
    public static void main(String[] args) {
        boolean useAnsiPresenter = false;
        boolean use1DBoard = false;
        int width = 7;
        int height = 6;

        for (String arg: args) {
            arg = arg.toLowerCase();
            if (arg.equals("--ansi")) {
                useAnsiPresenter = true;
            } else if (arg.equals("--1d")) {
                use1DBoard = true;
            } else if (arg.equals("--big")) {
                width = 15;
                height = 12;
            } else {
                throw new IllegalArgumentException(
                        String.format("Argument '%s' was not recognized", arg));
            }
        }

        GamePresenter presenter = useAnsiPresenter ? new AnsiPresenter() : new CommandLinePresenter();
        Connect4Board board = use1DBoard ? new SingleArrayBoard(width, height) : new TwoDArrayBoard(width, height);
        GameController controller = new GameController(new Game(board), presenter);
        controller.run();
    }
}
