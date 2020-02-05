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
        String[] taskDate;
        String[] details;
        String output = "OOPS!!! Something went wrong.";
        switch (type) {
        case "todo":
            task = new ToDo(description);
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = ui.showAddMessages(details);
            break;
        case "deadline":
            taskDate = description.split(" /by ");
            if (taskDate.length == 1 || taskDate[0].trim().equals("")
                    || taskDate[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description or by of a deadline cannot be empty.");
            }
            task = new Deadline(taskDate[0], taskDate[1]);
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = ui.showAddMessages(details);
            break;
        case "event":
            taskDate = description.split(" /at ");
            if (taskDate.length == 1 || taskDate[0].trim().equals("")
                    || taskDate[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description or at of an event cannot be empty.");
            }
            task = new Event(taskDate[0], taskDate[1]);
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = ui.showAddMessages(details);
            break;
        default:
            break;
        }

        return output;
    }
}
