package ua.in.boyaryn.client;

public class ConsoleOutput implements Observer {
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_RESET = "\u001B[0m";

    @Override
    public void update(String output, boolean error) {
        if (!error) {
            System.out.println(output);
        } else {
            System.err.println(ANSI_RED + output + ANSI_RESET);
        }
    }
}
