package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

/**
 * <h1>DeleteCommand Class</h1>
 * A subclass of Command class. Delete task from taskList and update the data in the
 * hard disk by calling storage.saveTask(taskList). Trigger ui to reply to the user
 * that the task has been deleted.
 *
 * @author  Eng Xuan En
 */
public class DeleteCommand extends Command {

    /**
     * Class constructor for DeleteCommand which using command type and List of Strings.
     * @param type type of the command
     * @param details index of which the task should be deleted in taskList
     */
    public DeleteCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    /**
     * Delete the task in the taskList and update the hard disk. Furthermore, trigger ui
     * to reply to the user that the task has been deleted.
     * @param taskList Stored the tasks when the program runs
     * @param storage Stored the tasks when task listing being edit
     * @param ui Print the message out to console
     * @throws DukeException throws when problem saving task to hard disk and invalid index of the task
     * located in taskList
     */
    @Override
    public void execute (TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            int num = Integer.parseInt(details.get(1));
            Task task = taskList.deleteTask(num);
            storage.saveTasks(taskList);
            ui.replyDelete(task);
        } catch(NumberFormatException e) {
            throw new DukeException("Please give a valid number in this format: delete [number]");
        }
    }
}
