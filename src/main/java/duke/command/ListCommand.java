package duke.command;

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
     * Return the list back in String format.
     *
     * @param taskList Stored the tasks when the program runs
     * @param storage  Stored the tasks when task listing being edit
     * @param ui       Print the message out to console
     * @return Return list back
     */
    @Override
    public String executeWithoutReply(TaskList taskList, Storage storage, Ui ui) {
        return ui.replyListing(taskList.getListing());
    }
}
