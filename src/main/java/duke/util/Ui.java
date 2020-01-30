package duke.util;

import java.util.Scanner;

/**
 * Ui
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 30 Jan 2020
 *
 * @author Jel
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a Ui instance and instantiates a Scanner instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a horizontal bar to separate commands.
     */
    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Driver method that introduces Duke.
     */
    public void run() {
        printSeparator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printSeparator();
    }

    /**
     * Gets input from user.
     * @return The next line of user input from the Scanner instance.
     */
    public String getInput() {
        return this.sc.nextLine();
    }

    /**
     * Handles user input of bye that signals exiting from program.
     */
    protected void bye() {
        printSeparator();
        System.out.println("Bye! Come back again soon!");
        printSeparator();
        sc.close();
    }
}
