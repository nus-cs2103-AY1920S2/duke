package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

/**
 * <h1>EventCommand Class</h1>
 * A subclass of Command class. Add new event into taskList and update the data into the
 * hard disk by calling storage.saveTask(taskList). Trigger ui to reply to the user
 * that the task has been added.
 *
 * @author  Eng Xuan En
 */
public class EventCommand extends Command {

    /**
     * Class constructor for EventCommand which using command type and List of Strings.
     * @param type type of the command
     * @param details data required for the event task
     */
    public EventCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    /**
     * Add new Event into taskList and update the hard disk. Furthermore, trigger ui
     * to reply to the user that the task has been added.
     * @param taskList Stored the tasks when the program runs
     * @param storage Stored the tasks when task listing being edit
     * @param ui Print the message out to console
     * @throws DukeException throws when problem saving task to hard disk and invalid date/time format
     */
    @Override
    public void execute (TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task task = new Event(details.get(1), details.get(2));
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.replyAdded(taskList.getAmountOfTask(), task);
    }
}
