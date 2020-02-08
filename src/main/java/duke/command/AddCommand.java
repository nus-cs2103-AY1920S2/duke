package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a add command.
 * The command deals with adding tasks to the list of tasks.
 */
public class AddCommand extends Command {

    protected String type;
    protected String description;

    /**
     * Constructs an AddCommand with specified type of task and its description.
     *
     * @param type The type of task.
     * @param description The description of the task.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Adds the task to the list of tasks and returns the relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @return The relevant messages in the form of String.
     * @throws DukeException If description of the task is missing or in wrong format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        String[] taskAndDate;
        String[] details;
        String output = "OOPS!!! Something went wrong.";
        switch (type) {
        case "todo":
            task = new ToDo(description);
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = ui.generateAddMessage(details);
            break;
        case "deadline":
            taskAndDate = description.split(" /by ");
            boolean isEmptyDescription = taskAndDate[0].trim().equals("");
            boolean isEmptyDate = taskAndDate.length == 1 || taskAndDate[1].trim().equals("");
            if (isEmptyDescription || isEmptyDate) {
                throw new DukeException("OOPS!!! The description or by of a deadline cannot be empty.");
            }
            task = new Deadline(taskAndDate[0].trim(), taskAndDate[1].trim());
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = ui.generateAddMessage(details);
            break;
        case "event":
            taskAndDate = description.split(" /at ");
            isEmptyDescription = taskAndDate[0].trim().equals("");
            isEmptyDate = taskAndDate.length == 1 || taskAndDate[1].trim().equals("");
            if (isEmptyDescription || isEmptyDate) {
                throw new DukeException("OOPS!!! The description or at of an event cannot be empty.");
            }
            task = new Event(taskAndDate[0].trim(), taskAndDate[1].trim());
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = ui.generateAddMessage(details);
            break;
        default:
            break;
        }

        return output;
    }
}
