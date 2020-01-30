package duke.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.exception.DukeNoSuchInputException;

public class Ui {
    public static final String LOGO = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String INDENTATION = "    ";
    public static final String WELCOME_MESSAGE = String.format(
            "Hello from \n%s", Ui.LOGO);
    public static final String GOODBYE_MESSAGE = "Goodbye!";
    public static final String LOAD_SUCCESS_MESSAGE_TEMPLATE
            = "Tasks loaded from %s";
    public static final String LOAD_FAIL_MESSAGE_TEMPLATE
            = "Cannot load tasks from %s";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public boolean hasNextInput() {
        return sc.hasNext();
    }

    public String readInput() throws DukeNoSuchInputException {
        try {
            return sc.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeNoSuchInputException();
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printWelcome() {
        print(Ui.WELCOME_MESSAGE);
    }

    public void printException(Exception e) {
        print(e.toString());
    }

    public void printLoadSuccess(String filePath) {
        print(String.format(Ui.LOAD_SUCCESS_MESSAGE_TEMPLATE, filePath));
    }

    public void printLoadFail(Exception e, String filePath) {
        printException(e);
        print(String.format(Ui.LOAD_FAIL_MESSAGE_TEMPLATE, filePath));
    }

    public void printGoodbye() {
        print(Ui.GOODBYE_MESSAGE);
    }

    public void close() {
        sc.close();
    }
}