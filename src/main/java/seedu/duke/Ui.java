package seedu.duke;

import java.io.IOException;

/**
 * Deals with interactions with the user.
 *
 * <p>Contains methods to greet, show file loading error, and handle user commands.
 */
public class Ui {
    Parser parser;
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String DONE = "done";
    public static final String FIND = "find";

    public Ui() {
        parser = new Parser();
    }

    public void greet() {
        System.out.println("Hi there, I'm Dodo!\nHow may I help you today?");
    }

    public void showLoadingError() {
        System.out.println("Loading error. Try again!");
    }



}
