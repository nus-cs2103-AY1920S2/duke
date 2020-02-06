package duke;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Performs basic interaction with the user.
 */
public class Ui {
    public static final String LF = "\n";
    private static final String WELCOME_MSG = "Hello! I'm duke.Duke" + LF + "What can I do for you?" + LF;
    private static final String BYE_MSG = "Bye, hope to see you again soon!" + LF;

    private BufferedReader br;

    /**
     * Constructs a new UI object.
     */
    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMsg() {
        System.out.println(WELCOME_MSG);
    }

    /**
     * Print the bye message.
     */
    public void printByeMsg() {
        System.out.println(BYE_MSG);
    }

    /**
     * Reads and trim a raw user command, for parsing.
     *
     * @return A trimmed version of the user command
     *
     * @throws IOException If there's an IO error which has occurred.
     */
    public String readCmd() throws IOException {
        return br.readLine().trim();
    }

    /**
     * Prints the content using a line feed thereafter.
     *
     * @param line Content to be printed.
     */
    public void printLine(String line) {
        System.out.println(line);
    }

    /**
     * Prints a blank line.
     */
    public void printLine() {
        System.out.println();
    }

    /**
     * Print the Task which has been added to the task-list.
     *
     * @param t Task to add to the task-list.
     * @param taskList The task-list containing all currently added Tasks.
     */
    public void printAddedTask(Task t, TaskList taskList) {
        this.printLine("Got it! I've added this task:" + Ui.LF + "    " + t + Ui.LF
                + "Now, you have " + taskList.getNumTasks() + " item(s) in your list." + Ui.LF);
    }

    /**
     * Prints all Tasks in the task-list.
     *
     * @param taskList The current task-list containing all Tasks.
     */
    public void printTaskList(TaskList taskList) {
        this.printLine("Here are your task(s):");

        int len = taskList.getNumTasks();

        for (int i = 0; i < len; ++i) {
            Task t = taskList.getTask(i);
            this.printLine("    " + (i + 1) + ". " + t);
        }

        this.printLine();
    }

    /**
     * Prints all Tasks found in the task-list.
     *
     * @param taskList The current task-list containing all Tasks.
     */
    public void printFoundTaskList(TaskList taskList) {
        this.printLine("Here are the matching task(s) your list:");

        int len = taskList.getNumTasks();

        for (int i = 0; i < len; ++i) {
            Task t = taskList.getTask(i);
            this.printLine("    " + (i + 1) + ". " + t);
        }

        this.printLine();
    }

    /**
     * Prints feedback on Tasks being marked as done.
     *
     * @param t Task being marked as done.
     */
    public void printTaskMarkedDone(Task t) {
        this.printLine("Nice! I've marked this task as done:" + Ui.LF + "    " + t + Ui.LF);
    }

    /**
     * Prints feedback on the Task being deleted from the task-list.
     *
     * @param t Task which has been removed.
     * @param taskList The current task-list representing all Tasks currently in the list.
     */
    public void printTaskDeleted(Task t, TaskList taskList) {
        this.printLine("Noted! I've removed this task:" + Ui.LF + "    " + t + Ui.LF
                + "Now, you have " + taskList.getNumTasks() + " item(s) in your list." + Ui.LF);
    }
}
