package duke.command;

import duke.exception.DukeException;
import duke.exception.MissingArgumentException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Adds a task to the TaskList.
 */
public class AddCommand extends Command {
    String type;
    String details;

    /**
     * Constructs an AddCommand with the specified type and details.
     *
     * @param type The type of task for the command.
     * @param details The details of the command.
     */
    public AddCommand(String type, String details) {
        this.type = type;
        this.details = details.trim();
    }

    /**
     * Adds the task to the TaskList and returns an acknowledgement message.
     *
     * @param tasks The TaskList where the task is to be added.
     * @return A string with the message to be printed.
     * @throws DukeException If the details is invalid.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task;
        assert (type.equals("todo") || type.equals("deadline") || type.equals("event")) : "Invalid task type";
        switch (type) {
        case "todo":
            checkEmptyDetails("The description of a todo cannot be empty.");
            task = new Todo(details);
            break;
        case "deadline":
            checkEmptyDetails("description and due date of a deadline");
            if (details.startsWith("/by")) {
                throw new MissingArgumentException("description of a deadline");
            }
            if (details.endsWith("/by") || !details.contains("/by")) {
                throw new MissingArgumentException("due date of a deadline");
            }
            String[] deadlineArr = details.split("/by");
            task = new Deadline(deadlineArr[0].trim(), deadlineArr[1].trim());
            break;
        case "event":
            checkEmptyDetails("description and date and time of an event");
            if (details.startsWith("/at")) {
                throw new MissingArgumentException("description of an event");
            }
            if (details.endsWith("/at") || !details.contains("/at")) {
                throw new MissingArgumentException("date and time of an event");
            }
            String[] eventArr = details.split(" /at ");
            task = new Event(eventArr[0].trim(), eventArr[1].trim());
            break;
        default:
            throw new UnknownCommandException();
        }
        tasks.add(task);
        return Ui.showToUser(Ui.MESSAGE_ADD, Ui.INDENT + task, Ui.getNumberOfTasksMessage(tasks));
    }

    /**
     * Checks if the details of the command is empty.
     * @param message The message to be printed.
     * @throws DukeException If the details of the command is empty.
     */
    private void checkEmptyDetails(String message) throws DukeException {
        if (this.details.equals("")) {
            throw new MissingArgumentException(message);
        }
    }
}
