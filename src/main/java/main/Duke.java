package main;

import processor.DukeProcessor;

import java.util.Scanner;


/**
 * Duke's main class, where he will start his processor and begin to process user input.
 */
public class Duke {
    /**
     * Main method of Duke to instantiate Duke.
     * @param args General arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeProcessor processor = new DukeProcessor();
        while (processor.isActive()) {
            String input = sc.nextLine();
            processor.processInput(input);
        }
    }
}
