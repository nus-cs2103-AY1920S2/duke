package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

/**
 * <h1>DeadlineCommand Class</h1>
 * A subclass of Command class. Add new deadline into taskList and update the data into the
 * hard disk by calling storage.saveTask(taskList). Trigger ui to reply to the user
 * that the task has been added.
 *
 * @author Eng Xuan En
 */
public class DeadlineCommand extends Command {

    /**
     * Class constructor for DeadlineCommand which using command type and List of Strings.
     *
     * @param type    type of the command
     * @param details data required for the deadline task
     */
    public DeadlineCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    /**
     * Add new Deadline into taskList and update the hard disk. Furthermore, return the
     * message back in String format.
     *
     * @param taskList Stored the tasks when the program runs
     * @param storage  Stored the tasks when task listing being edit
     * @param ui       Print the message out to console
     * @return Reply message to user
     * @throws DukeException throws when problem saving task to hard disk and invalid date format
     */
    @Override
    public String executeWithoutReply(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task task = new Deadline(details.get(1), details.get(2));
        taskList.addTask(task);
        storage.saveTasks(taskList);

        assert taskList.getTask(taskList.getAmountOfTask()).equals(task) : "Task is not added into taskList";

        return ui.replyAdded(taskList.getAmountOfTask(), task);
    }
}
