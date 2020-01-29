package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    /** The logo for Duke. */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** For user input. */
    private Scanner sc;

    /** Constructs a new Ui object for console input and output. */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads and returns a line of user input.
     *
     * @return a line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a message in the console.
     * All lines will be indented 5 spaces to the right.
     *
     * @param message the message to display in the console.
     */
    public void echo(String message) {
        System.out.println(indent(message + "\n", 5));
    }

    /** Displays a welcome message in the console. */
    public void showWelcome() {
        String header = indent("\nHello from\n" + LOGO, 4);
        System.out.println(header);
    }

    /**
     * Displays in the console a task that has been added to the list.
     *
     * @param task the tasks that has been added to the list.
     */
    public void showAdd(Task task) {
        String confirmation = "Okie! I've added this task:\n";
        echo(confirmation + indent(task.toString(), 2));
    }

    /**
     * Displays a completed task in the console.
     *
     * @param task the completed task.
     */
    public void showDone(Task task) {
        String praise = "Good job completing this task!"
                + " Here's a tick for completing it!\n";

        echo(praise + indent(task.toString(), 2));
    }

    /**
     * Lists all tasks in the console.
     *
     * @param tasks the list of tasks to display.
     */
    public void showList(TaskList tasks) {
        String title = "Here are the tasks in your list:\n";
        echo(title + tasks.toString());
    }

    /**
     * Displays a deleted task in the console.
     *
     * @param task the deleted task.
     */
    public void showDelete(Task task) {
        String comment = "Alright! I'll remove this task:\n";
        echo(comment + indent(task.toString(), 2));
    }

    /**
     * Displays the number of tasks in the list in the console.
     *
     * @param tasks the task list to count and display.
     */
    public void showTaskCount(TaskList tasks) {
        String count = "There are now " + tasks.getNumTasks() + " tasks in the list.";
        echo(count);
    }

    /** Farewells the user. */
    public void showGoodbye() {
        echo("Bye! Please give a review if you like this program!");
    }

    /** Displays a line in the console. */
    public void showLine() {
        int lineWidth = 60;
        String lineSymbol = "-";

        System.out.println(indent(lineSymbol.repeat(lineWidth), 4));
    }

    /**
     * Displays an error message in the console.
     *
     * @param error the error message to display.
     */
    public void showError(String error) {
        String message = "☹ OOPS!!!\n";
        echo(message + error);
    }

    /** Displays a loading error message in the console. */
    public void showLoadingError() {
        String message = "Sorry... the task list could not be loaded from a save file.\n"
                + "Here's a new task list for you to use.";

        showLine();
        echo(message);
        showLine();
    }

    /** Displays a loading error message in the console. */
    public void showFind(TaskList tasks, List<Integer> taskIds) {
        String title = "Here are the matching tasks in your list:\n";
        StringBuilder foundTasks = new StringBuilder();

        for (int i = 0; i < taskIds.size(); i++) {
            int taskId = taskIds.get(i);
            foundTasks.append(tasks.getFormattedTask(taskId)).append("\n");
        }
        echo(title + foundTasks.toString());
    }

    /**
     * Adds an indent to all lines of a given text.
     *
     * @param text the text to indent.
     * @param indentWidth the character width of the indentation.
     * @return the indented text.
     */
    private String indent(String text, int indentWidth) {
        String indent = " ".repeat(indentWidth);
        return text.replaceAll("(?m)^", indent);
    }
}