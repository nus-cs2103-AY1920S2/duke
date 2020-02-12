package duke.command;

import duke.History;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.Task;


/**
 * Represents a delete command.
 * The command deals with removing tasks from the list of tasks.
 */
public class DeleteCommand extends Command {

    protected int index;

    /**
     * Constructs a DeleteCommand with specified index of a task in the list of tasks.
     *
     * @param index The specified index of the task in the list of tasks.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Removes the task from the list of tasks and returns relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param history The History that deals with past commands.
     * @return The relevant messages in the form of String.
     * @throws DukeException If index of out of range of the list.
     */
    @Override
    public String execute(TaskList tasks, History history) throws DukeException {
        Task task = tasks.removeTask(index);
        history.updateHistory(History.COMMAND_DELETE + " " + index, task);
        String[] details = new String[] {" " + task.toString(), tasks.getSize() + ""};
        return Ui.generateDeleteMessage(details);
    }
}
