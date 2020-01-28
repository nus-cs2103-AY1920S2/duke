package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Adds a task to the TaskList.
 */
public class AddCommand extends Command {
    String type, details;

    /**
     * Constructs an AddCommand with the specified type and details.
     * @param type The type of task for the command.
     * @param details The details of the command.
     */
    public AddCommand(String type, String details) {
        this.type = type;
        this.details = details;
    }

    /**
     * Adds the task to the TaskList and prints an acknowledgement message.
     * @param tasks The TaskList where the task is to be added.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @throws DukeException If the details is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch (type) {
        case "todo":
            if (details.trim().equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            task = new Todo(details);
            break;
        case "deadline":
            if (details.trim().equals("")) {
                throw new DukeException("The description and due date of a deadline cannot be empty.");
            }
            if (details.trim().startsWith("/by")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            if (details.trim().endsWith("/by") || !details.contains("/by")) {
                throw new DukeException("The due date of a deadline cannot be empty.");
            }
            String[] deadlineArr = details.split("/by");
            task = new Deadline(deadlineArr[0].trim(), deadlineArr[1].trim());
            break;
        case "event":
            if (details.equals("")) {
                throw new DukeException("The description and date and time of an event cannot be empty.");
            }
            if (details.trim().startsWith("/at")) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            if (details.trim().endsWith("/at") || !details.contains("/at")) {
                throw new DukeException("The date and time of an event cannot be empty.");
            }
            String[] eventArr = details.split(" /at ");
            task = new Event(eventArr[0].trim(), eventArr[1].trim());
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(task);
        ui.showAddTask(task, tasks);
    }
}
