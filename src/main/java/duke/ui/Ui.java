package duke.ui;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Creates the welcome message to be displayed on Duke's startup.
     * @return the welcome message
     */
    public static String printWelcomeMessage() {
        StringBuilder result = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        result.append("Greetings from Duke! How can I help you?");
        return result.toString();
    }

    public static void displayAddTaskSuccessMsg(Task task, int numOfTasks) {
        Ui.printMessage("Got it! I've added the task:\n\t" + task + "\nNow you have " + numOfTasks
                        + " tasks in the list.");
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }
}