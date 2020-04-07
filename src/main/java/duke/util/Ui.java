package duke.util;

import java.util.Scanner;

/**
 * Represents the Ui displayed on the CLI.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 11 Feb 2020
 *
 * @author Jel
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
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
        StringBuilder sb = new StringBuilder("____________________________________________________________");
        sb.append("\n").append("Bye! Come back again soon!\n")
                .append("____________________________________________________________");
        sc.close();
        return sb.toString();
    }
}
