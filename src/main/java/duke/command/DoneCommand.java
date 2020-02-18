package duke.command;

import duke.History;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.Task;


/**
 * Represents a done command.
 * The command deals with marking tasks as done.
 */
public class DoneCommand extends Command {

    protected int index;

    /**
     * Constructs a DoneCommand with specified index of a task in the list of tasks.
     *
     * @param index The specified index of the task in the list of tasks.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done and returns relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param history The History that deals with past commands.
     * @return The relevant messages in the form of String.
     * @throws DukeException If index is out of range of the list.
     */
    @Override
    public String execute(TaskList tasks, History history) throws DukeException {
        Task task = tasks.getTask(index);
        task.markAsDone();
        history.updateHistory(History.COMMAND_DONE, task);
        return Ui.generateDoneMessage(" " + task.toString());
    }
}
