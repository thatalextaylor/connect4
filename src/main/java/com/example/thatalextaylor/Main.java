package com.example.thatalextaylor;

public class Main {
    public static void main(String[] args) {
        boolean useAnsiPresenter = false;
        for (String arg: args) {
            arg = arg.toLowerCase();
            if (arg.equals("--ansi")) {
                useAnsiPresenter = true;
            } else {
                throw new IllegalArgumentException(
                        String.format("Argument '%s' was not recognized", arg));
            }
        }
        GamePresenter presenter = useAnsiPresenter ? new AnsiPresenter() : new CommandLinePresenter();
        GameController controller = new GameController(new Game(new SingleArrayBoard(7, 6)), presenter);
        controller.run();
    }
}
