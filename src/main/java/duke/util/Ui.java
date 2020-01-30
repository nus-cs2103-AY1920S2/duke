package duke.util;

import java.io.IOException;
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
    private static String separator = "____________________________________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private void printSeparator() {
        System.out.println(separator);
    }

    public void run() {
        printSeparator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printSeparator();
    }

    /**
     * Get input from user.
     */
    public String getInput() {
        return this.sc.nextLine();
    }

    protected void bye() {
        printSeparator();
        System.out.println("Bye! Come back again soon!");
        printSeparator();
        sc.close();
    }
}
