package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

/**
 * <h1>DoneCommand Class</h1>
 * A subclass of Command class. Mark the task as done in the taskList and update the data into the
 * hard disk by calling storage.saveTask(taskList). Trigger ui to reply to the user
 * that the task has been marked done.
 *
 * @author  Eng Xuan En
 */
public class DoneCommand extends Command {

    /**
     * Class constructor for DoneCommand which using command type and List of Strings.
     * @param type type of the command
     * @param details index where the task is located in taskList to be marked done
     */
    public DoneCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    /**
     * Mark the task as done in the taskList and update the hard disk. Furthermore, trigger ui
     * to reply to the user that the task has been marked done.
     * @param taskList Stored the tasks when the program runs
     * @param storage Stored the tasks when task listing being edit
     * @param ui Print the message out to console
     * @throws DukeException throws when problem saving task to hard disk and invalid index where the task located
     */
    @Override
    public void execute (TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            int num = Integer.parseInt(details.get(1));
            taskList.markDone(num);
            storage.saveTasks(taskList);
            ui.replyDone(taskList.getTask(num));
        } catch(NumberFormatException e) {
            throw new DukeException("Please input in this format: done [number]");
        }
    }
}
