package duke.pack;

import java.util.Scanner;

/**
 * Handles interactions with user.
 */
public class Ui {
    /**
     * Creates a UI.
     */
    public Ui() {

    }

    /**
     * Prints error.
     * @param e the DukeException thrown
     */
    public void showError(DukeException e) {
        System.out.println(e);
    }

    /**
     * Takes in user's input.
     * @return string representing command
     */
    public String receiveInput() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim();
        return command;
    }

    /**
     * Prints greeting to user.
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
     * Prints formatting line.
     */
    public void showLine() {
        System.out.println("    ------------------------------------------------------------------");
    }

    /**
     * Prints task added.
     * @param t added task
     */
    public void showAdd(Task t) {
        System.out.println("    Alright! I have added: \n    " + t);
    }

    /**
     * Prints task marked as done.
     * @param t task marked as done
     */
    public void showDone(Task t) {
        System.out.println("    Fantastic job! I have marked this task as done: \n    " + t);
    }

    /**
     * Prints task deleted.
     * @param t deleted task
     */
    public void showDelete(Task t) {
        System.out.println("    Yeet! I have tossed the task: \n    " + t);
    }

    /**
     * Prints total number of tasks.
     * @param tasks the TaskList
     */
    public void showCount(TaskList tasks) {
        System.out.println("    You now have " + tasks.getSize() + " tasks in your list!");
    }

    public void showSort() {
        System.out.println("    I have sorted the tasks chronologically!");
    }

    public void showFind() {
        System.out.println("    I have found these matching tasks!");
    }

    /**
     * Exits.
     */
    public void exit() {
        System.out.println("    See you later! :)");
    }

}
