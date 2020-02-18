package duke;

import java.util.Scanner;

public class Ui {
    public static final String WELCOME_MESSAGE =
                /*
            " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                +
             */
                "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "\n"
                + "Type \"help\" to see a list of possible commands.";

    public static final String HELP_PAGE =
            "Here are the commands I understand:\n"
            + "help\n"
            + "list\n"
            + "todo TASK_NAME\n"
            + "event EVENT_NAME /at LOCATION\n"
            + "deadline DEADLINE_NAME /by YYYY-MM-DD\n"
            + "find\n"
            + "done INDEX\n"
            + "undo INDEX\n"
            + "delete INDEX\n"
            + "clear\n"
            + "bye\n";



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
