package ua.in.boyaryn;

import ua.in.boyaryn.client.ConsoleOutput;
import ua.in.boyaryn.invoker.BasicCalculator;
import ua.in.boyaryn.invoker.Processor;

import java.util.Scanner;

public class App {
    public static void main( String[] args) {
        System.out.println( "Welcome to Basic Calculator!" );

        Processor calc = new BasicCalculator();
        calc.addSubscriber(new ConsoleOutput());

        Scanner in = new Scanner(System.in);

        String input;
        boolean exit = false;
        while (!exit && in.hasNext()) {
            input = in.nextLine();
            exit = input.equals("q");
            if (!exit) {
                if (!input.equals("u")) {
                    calc.feed(input);
                } else {
                    calc.undo();
                }
            }
        }
    }
}
