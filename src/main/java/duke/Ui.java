package duke;

import java.util.Scanner;

public class Ui {
    public void showLoadingError() {
        System.out.println("Error loading from database. Starting with empty task list");
    }

    /**
     * Displays the welcome message with ASCII text spelling Duke to welcome the user.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showLine();
        System.out.println(logo);
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
        showLine();
    }

    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showSavingTasks() {
        System.out.println("Saving Tasks!");
    }

    public void printString(String s) {
        System.out.println(s);
    }
}
