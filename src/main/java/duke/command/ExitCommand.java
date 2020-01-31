package duke.command;

import duke.DukeException;
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
     * Trigger ui to reply to the user that the task has been added.
     *
     * @param taskList Stored the tasks when the program runs
     * @param storage  Stored the tasks when task listing being edit
     * @param ui       Print the message out to console
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.reply("Alright! See you next time!");
    }
}
