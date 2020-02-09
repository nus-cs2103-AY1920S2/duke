package main;

import exceptions.DukeException;
import processor.DukeProcessor;

import java.util.Scanner;


/**
 * Duke's main class, where he will start his processor and begin to process user input.
 */
public class Duke {
    DukeProcessor processor;

    /**
     * Constructor of Duke.
     */
    public Duke() {
        processor = new DukeProcessor();
    }

    public String getResponse(String input) {
        return processor.processInput(input);
    }

    public DukeProcessor getProcessor() {
        return processor;
    }
}
