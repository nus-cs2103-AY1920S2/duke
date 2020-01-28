package ui;

import taskList.TaskList;
import task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.InputStream;

/**
 * The class that deals with interactions with the user. It will give responses to the user.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LINE_PREFIX = "|| ";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in,System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * This is the main command that will read what the user's input is for processing by the Parser.
     *
     * @return The user's input.
     */
    public String readCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * This shows the basic welcome message of the program and any tasks that was previously saved into
     * the data file.
     *
     * @param tasks Tasks that were saved into the data file during the previous execution.
     */
    public void showWelcomeMessage(TaskList tasks) {
        System.out.println("My name is Jarvis!\nHow may I provide my services on this fine day?\n" + DIVIDER);
        if (!tasks.getList().isEmpty()) {
            System.out.println("Welcome back, you have the following tasks on hand:");
            for(Task t:tasks.getList()){
                System.out.println(t);
            }
            System.out.println(DIVIDER);
        }
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showGoodbyeMessage() {
        System.out.println("Hope my service has been of great help! See you again!");
    }

    public void showError(String s){
        System.out.println(s);
    }

    public void showLoadingError() {
        System.out.println("Sorry, there was an error with the loading...");
    }
}
