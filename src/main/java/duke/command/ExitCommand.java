package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * <h1>ExitCommand Class</h1>
 * A subclass of Command class. Trigger ui to say goodbye to the user.
 *
 * @author Eng Xuan En
 */
public class ExitCommand extends Command {

    /**
     * Class constructor of ExitCommand which takes in the CommandType.
     *
     * @param type type of the command
     */
    public ExitCommand(CommandType type) {
        super(type);
        isExit = true;
    }

    /**
     * Return goodbye message back in String format.
     *
     * @param taskList Stored the tasks when the program runs
     * @param storage  Stored the tasks when task listing being edit
     * @param ui       Print the message out to console
     * @return Return goodbye message back
     */
    @Override
    public String executeWithoutReply(TaskList taskList, Storage storage, Ui ui) {
        return ui.replyInString("Alright! See you next time!");
    }
}
