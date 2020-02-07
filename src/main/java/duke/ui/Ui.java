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

    private DeadlineEventHash deadlineEventHash = new DeadlineEventHash();
    /**
     * The Lines.
     */
    private final String LINES = "        ________________________________________________________";
    /**
     * The Space.
     */
    private final String SPACE = "        ";

    /**
     * Instantiates a new Ui.
     */
    public Ui() {
    }

    /**
     * Print bye.
     */
    public String printBye() {
        return LINES + System.lineSeparator() + "        Bye. Hope to see you again soon"
                + System.lineSeparator() + LINES;
    }


    /**
     * Print list.
     *
     * @param taskList the task list
     */
    public String printList(TaskList taskList) {
        return LINES + System.lineSeparator() + taskList.print_elements() + LINES + System.lineSeparator();
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
        return LINES + System.lineSeparator() + SPACE + "Nice! I've marked this task as done" + System.lineSeparator()
                + SPACE + " [" + finishedTask.getStatusIcon() + "]" + " " + finishedTask.getDescription()
                + System.lineSeparator() + LINES + System.lineSeparator();
    }

    /**
     * Print tasks.
     *
     * @param task the task
     * @param list the list
     */
    public String printTasks(Task task, ArrayList<Task> list) {
        return LINES + System.lineSeparator() + SPACE + " Got it. I've added this task: " + System.lineSeparator()
                + SPACE + task + System.lineSeparator() + SPACE + " Now you have " + list.size() + " tasks in the list."
                + System.lineSeparator() + LINES;
    }

    /**
     * Print delete.
     *
     * @param deletedTask the deleted task
     * @param taskList    the task list
     */
    public String printDelete(Task deletedTask, TaskList taskList) {
        return "The deleted task is " + deletedTask + System.lineSeparator() + LINES + System.lineSeparator()
                + SPACE + "Noted. I've removed this task:" + System.lineSeparator() + SPACE + deletedTask
                + System.lineSeparator() + SPACE + "Now you have " + taskList.sizeOfList() + " tasks in the list."
                + System.lineSeparator() + LINES + System.lineSeparator();
    }

    /**
     * Read command string.
     *
     * @param command the command
     * @return the string
     * @throws DukeException the duke exception
     */
    // Reads the command
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

}
