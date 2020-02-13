package main;

import util.Parser;
import util.Storage;
import util.task.TaskList;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * gui.Main driver class for main.Duke.
 */
public class Duke {
    public static TaskList dl;

    /**
     * Driver method for main.Duke.
     * Retrieves list from stored data if available, else start a new list.
     * The util.task.TaskList is then parsed with using input.
     */
    public void init() {
        try {
            dl = Storage.retrieveTasks();
        } catch (FileNotFoundException | ParseException e) {
            dl = new TaskList();
        }
    }

    private String executeCommand(String input) {
        assert input != null;
        return Parser.parse(input);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert input != null;
        return executeCommand(input);
    }
}