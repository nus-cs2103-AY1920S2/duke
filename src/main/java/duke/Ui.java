package duke;

import java.util.Scanner;

/**
 * Helps Duke interact with the user.
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static String getWelcomeMessage() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    public String byeMessage() {

        return "Bye. Hope to see you again soon!";
    }

    public void timeErrorMessage() {
        promptUser("Event and Deadline tasks require a '/' character before specifying time");
    }

    public String getInput() {
        return this.sc.nextLine();
    }

    public static void promptUser(String message) {
        System.out.println(message);
    }
}
