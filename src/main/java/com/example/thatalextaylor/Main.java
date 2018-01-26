package com.example.thatalextaylor;

public class Main {
    //Accepts 3 command line arguments
    // --ansi - use colored output on ANSI compatible terminals
    // --big - play on a 15x12 board
    // --tiny - play on a 5x5 board
    public static void main(String[] args) {
        boolean useAnsiPresenter = false;
        int width = 7;
        int height = 6;

        for (String arg: args) {
            arg = arg.toLowerCase();
            if (arg.equals("--ansi")) {
                useAnsiPresenter = true;
            } else if (arg.equals("--big")) {
                width = 15;
                height = 12;
            } else if (arg.equals("--tiny")) {
                width = 5;
                height = 5;
            } else {
                throw new IllegalArgumentException(
                        String.format("Argument '%s' was not recognized", arg));
            }
        }

        GamePresenter presenter = useAnsiPresenter ? new AnsiPresenter() : new CommandLinePresenter();
        GameController controller = new GameController(new Game(new TwoDArrayBoard(width, height)), presenter);
        controller.run();
    }
}
