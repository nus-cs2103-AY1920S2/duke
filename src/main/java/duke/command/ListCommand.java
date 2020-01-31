package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * <h1>ListCommand Class</h1>
 * A subclass of Command class. Trigger ui to list the tasks in the taskListing to the user.
 *
 * @author Eng Xuan En
 */
public class ListCommand extends Command {

    /**
     * Class constructor of the ListCommand which takes in command type.
     *
     * @param type type of the command
     */
    public ListCommand(CommandType type) {
        super(type);
    }

    /**
     * Trigger ui to list the tasks in the taskListing to the user.
     *
     * @param taskList Stored the tasks when the program runs
     * @param storage  Stored the tasks when task listing being edit
     * @param ui       Print the message out to console
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.replyListing(taskList.getListing());
    }
}
