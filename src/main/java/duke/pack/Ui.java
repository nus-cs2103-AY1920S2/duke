package duke.pack;

import java.util.Scanner;

/**
 * Handles interactions with user.
 */
public class Ui {
    public Ui() {

    }

    /**
     * prints error
     * @param e DukeException thrown
     */
    public void showError(DukeException e) {
        System.out.println(e);
    }

    /**
     * takes in user's input
     * @return string representing command
     */
    public String receiveInput() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim();
        return command;
    }

    /**
     * prints greeting to user
     */
    public void greet() {
        System.out.println("    ------------------------------------------------------------------");

        // logo obtained using https://www.kammerl.de/ascii/AsciiSignature.php
        String logo = "     __  __ _           _          _      \n" +
                "    |  \\/  | |         | |        | |     \n" +
                "    | \\  / | |__   ___ | |_       | |_ __ \n" +
                "    | |\\/| | '_ \\ / _ \\| __|  _   | | '__|\n" +
                "    | |  | | |_) | (_) | |_  | |__| | |   \n" +
                "    |_|  |_|_.__/ \\___/ \\__|  \\____/|_|   \n" +
                "\n";

        System.out.println(logo);
        System.out.println("    Hello, I'm Mbot Jr!\n" +
                "    How may I help you?");
        System.out.println("    ------------------------------------------------------------------");
    }

    /**
     * prints formatting line
     */
    public void showLine() {
        System.out.println("    ------------------------------------------------------------------");
    }

    /**
     * prints task added
     * @param t
     */
    public void showAdd(Task t) {
        System.out.println("    Alright! I have added: \n    " + t);
    }

    /**
     * prints task marked as done
     * @param t
     */
    public void showDone(Task t) {
        System.out.println("    Fantastic job! I have marked this task as done: \n    " + t);
    }

    /**
     * prints task deleted
     * @param t
     */
    public void showDelete(Task t) {
        System.out.println("    Yeet! I have tossed the task: \n    " + t);
    }

    /**
     * prints total number of tasks
     * @param tasks the TaskList
     */
    public void showCount(TaskList tasks) {
        System.out.println("    You now have " + tasks.getSize() + " tasks in your list!");
    }

    /**
     * exits
     */
    public void exit() {
        System.out.println("    See you later! :)");
    }

}
