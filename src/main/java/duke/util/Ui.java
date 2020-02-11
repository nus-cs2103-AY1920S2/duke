package duke.util;

import java.util.Scanner;

/**
 * Ui
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 11 Feb 2020
 *
 * @author Jel
 */
public class Ui {
    private Scanner sc;
    private String separator = "____________________________________________________________";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Driver method that introduces Duke.
     */
    public void run() {
        System.out.println(separator);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(separator);
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
    public String bye() {
        StringBuilder sb = new StringBuilder(separator);
        sb.append("\n").append("Bye! Come back again soon!\n").append(separator);
        sc.close();
        return sb.toString();
    }
}
