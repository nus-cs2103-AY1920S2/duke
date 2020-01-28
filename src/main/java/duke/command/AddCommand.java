package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     * Adds the task to the list of tasks and print the relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @throws DukeException If description of the task is missing or in wrong format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        String[] task_date;
        switch (type) {
        case "todo":
            task = new ToDo(description);
            tasks.addTask(task);
            ui.showMessages(new String[] {"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + tasks.getSize() + " tasks in the list."});
            break;
        case "deadline":
            task_date = description.split(" /by ");
            if (task_date.length == 1 || task_date[0].trim().equals("")
                    || task_date[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description or by of a deadline cannot be empty.");
            }
            task = new Deadline(task_date[0], task_date[1]);
            tasks.addTask(task);
            ui.showMessages(new String[] {"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + tasks.getSize() + " tasks in the list."});
            break;
        case "event":
            task_date = description.split(" /at ");
            if (task_date.length == 1 || task_date[0].trim().equals("")
                    || task_date[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description or at of an event cannot be empty.");
            }
            task = new Event(task_date[0], task_date[1]);
            tasks.addTask(task);
            ui.showMessages(new String[] {"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + tasks.getSize() + " tasks in the list."});
            break;
        default:
            break;
        }
    }
}
