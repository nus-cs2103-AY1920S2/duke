package duke.ui;

import duke.exception.DukeException;
import duke.task.DeadlineEventHash;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * The type Ui.
 */
// Deals with interactions with the user
// Shows the lines, errors etc
public class Ui {

    //private duke.task.TaskList taskList = new duke.task.TaskList();
    private DeadlineEventHash deadlineEventHash = new DeadlineEventHash();
    /**
     * The Lines.
     */
    String lines = "        ________________________________________________________";
    /**
     * The Space.
     */
    String space = "        ";

    /**
     * The Command.
     */
    String command;

    /**
     * Instantiates a new Ui.
     *
     * @param command the command
     */
    public Ui(String command) {
        this.command = command;
    }

    /**
     * Instantiates a new Ui.
     */
    public Ui() {
    }

    /**
     * Show welcome.
     */
    public void showWelcome() {
        String welcomeMessage = "____________________________________________________________\n"
                + "Hello! I'm Duke\n" + "What can I do for you today?\n"
                + "____________________________________________________________";

        // Lines are for in between the two words
        System.out.println(welcomeMessage);

    }

    /**
     * Print bye.
     */
    public String printBye() {
        return lines + System.lineSeparator() + "        Bye. Hope to see you again soon"
                + System.lineSeparator() + lines;
    }


    /**
     * Print list.
     *
     * @param taskList the task list
     */
    public String printList(TaskList taskList) {
        return lines + System.lineSeparator() + taskList.print_elements() + lines + System.lineSeparator();
    }

    /**
     * Invalid number exception.
     *
     * @throws DukeException the duke exception
     */
    // When the user enters a number which is larger than the taskList
    public void invalidNumberException() throws DukeException {
        throw new DukeException("You have entered an invalid number!");
    }

    public String invalidAddTaskException() throws DukeException {
        return "You have entered a wrong AddTaskCommand";
    }

    /**
     * Print done.
     *
     * @param finishedTask the finished task
     */
    // Tells the user that the task is done.
    public String printDone(Task finishedTask) {
        return lines + System.lineSeparator() + space + "Nice! I've marked this task as done" + System.lineSeparator()
                + space + " [" + finishedTask.getStatusIcon() + "]" + " " + finishedTask.getDescription()
                + System.lineSeparator() + lines + System.lineSeparator();
    }

    /**
     * Print tasks.
     *
     * @param task the task
     * @param list the list
     */
    public String printTasks(Task task, ArrayList<Task> list) {
        return lines + System.lineSeparator() + space + " Got it. I've added this task: " + System.lineSeparator()
                + space + task + System.lineSeparator() + space + " Now you have " + list.size() + " tasks in the list."
                + System.lineSeparator() + lines;
    }

    /**
     * Print delete.
     *
     * @param deletedTask the deleted task
     * @param taskList    the task list
     */
    public String printDelete(Task deletedTask, TaskList taskList) {
        return "The deleted task is " + deletedTask + System.lineSeparator() + lines + System.lineSeparator()
                + space + "Noted. I've removed this task:" + System.lineSeparator() + space + deletedTask
                + System.lineSeparator() + space + "Now you have " + taskList.sizeOfList() + " tasks in the list."
                + System.lineSeparator() + lines + System.lineSeparator();
    }

    /**
     * Print dont understand input.
     *
     * @throws DukeException the duke exception
     */
    void printDontUnderstandInput() throws DukeException {
        System.out.println(lines);
        throw new DukeException("I'm sorry but I do not know what taht means :-(");
    }

    /**
     * Duke exceptions.
     *
     * @param s the s
     * @throws DukeException the duke exception
     */
    // Handles any other forms of exceptions. Eg in deadline etc.
    void dukeExceptions(String s) throws DukeException {
        throw new DukeException(s);
    }

    /**
     * Read command string.
     *
     * @param command the command
     * @return the string
     * @throws DukeException the duke exception
     */
    // Reads the commands
    public String readCommand(String command) throws DukeException {
        if (command.contains("todo") || command.contains("deadline")
                || command.contains("event")) {
            return "duke.command.AddCommand";
        } else if (command.contains("bye")) {
            return "duke.command.ByeCommand";
        } else if (command.contains("delete")) {
            return "duke.command.DeleteCommand";
        } else if (command.contains("list")) {
            return "duke.command.ListCommand";
        } else if (command.contains("done")) {
            return "duke.command.DoneCommand";
        } else if (command.contains("find")) {
            return "duke.command.FindCommand";
        } else {
            throw new DukeException("You have entered a wrong command");
        }
    }

    public String printTest(String s) {
        return s + lines + "\n" + "        Bye. Hope to see you again soon" + "\n" + lines;
    }

}
