package duke.commands;

import duke.commands.Command;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

/**
 * AddCommand class handles user's request to add a task to the task list.
 */
public class AddCommand extends Command {
    String type;
    String description;

    /**
     * Creates a new AddCommand.
     * @param type type of task (todo, deadline, event).
     * @param description description of task.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Executes the add command. Creates a task according to the task type and adds it to the task list.
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage makeshift database for tasks.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task t = new Task(description);
        switch (type) {
        case "todo":
            t = new Todo(description);
            tasks.add(t);
            break;
        case "deadline":
            checkDateException("deadline");
            t = new Deadline(description);
            tasks.add(t);
            break;
        case "event":
            checkDateException("event");
            t = new Event(description);
            tasks.add(t);
            break;
        default:
        }

        storage.writeToFile(tasks.saveList());
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + t);
        tasks.printSize();
    }

    /**
     * Returns a boolean that determines if command exits the program.
     * @return boolean.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks task (deadline or event) for the presence of date.
     * @param deadlineOrEvent indicates whether method checks the event or deadline for
     *                        presence of date in task.
     * @throws DukeException if user input does not follow input format.
     */
    public void checkDateException(String deadlineOrEvent) throws DukeException {
        switch (deadlineOrEvent) {
        case "deadline":
            String[] deadlineArr = description.split(" /by ");
            // error: task is missing deadline
            if (deadlineArr.length <= 1) {
                throw new DukeException("Deadline of a task cannot be empty.");
            }
            break;
        case "event":
            String[] eventArr = description.split(" /at ");
            // error: event is missing time
            if (eventArr.length <= 1) {
                throw new DukeException("Time of an event cannot be empty.");
            }
            break;
        default:
        }
    }
}