package duke.command;

import duke.History;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.Messages;
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
     * @param history The History that deals with past commands.
     * @return The relevant messages in the form of String.
     * @throws DukeException If description of the task is missing or in wrong format.
     */
    @Override
    public String execute(TaskList tasks, History history) throws DukeException {
        Task task = null;
        String[] taskAndDate;
        String[] details;
        String output = "OOPS!!! Something went wrong.";
        switch (type) {
        case "todo":
            task = new ToDo(description);
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = Ui.generateAddMessage(details);
            break;
        case "deadline":
            taskAndDate = description.split(" /by ");
            boolean isEmptyDescription = taskAndDate[0].trim().equals("");
            boolean isEmptyDate = taskAndDate.length == 1 || taskAndDate[1].trim().equals("");
            if (isEmptyDescription || isEmptyDate) {
                throw new DukeMissingArgumentException(String.format(
                        Messages.MESSAGE_MISSING_TASK_DETAILS, "by", "deadline"));
            }
            task = new Deadline(taskAndDate[0].trim(), taskAndDate[1].trim());
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = Ui.generateAddMessage(details);
            break;
        case "event":
            taskAndDate = description.split(" /at ");
            isEmptyDescription = taskAndDate[0].trim().equals("");
            isEmptyDate = taskAndDate.length == 1 || taskAndDate[1].trim().equals("");
            if (isEmptyDescription || isEmptyDate) {
                throw new DukeMissingArgumentException(String.format(
                        Messages.MESSAGE_MISSING_TASK_DETAILS, "at", "event"));
            }
            task = new Event(taskAndDate[0].trim(), taskAndDate[1].trim());
            tasks.addTask(task);
            details = new String[] {" " + task.toString(), "" + tasks.getSize()};
            output = Ui.generateAddMessage(details);
            break;
        default:
            break;
        }
        history.updateHistory(History.COMMAND_ADD, task);
        return output;
    }
}
