package duke.commands;

import duke.model.Storage;
import duke.model.TaskList;

/**
 * The <code>ByeCommand</code> class represents a bye command to return a bye message.
 */
public class ByeCommand extends Command {
    private static final String MESSAGE = "('A')/'' Bye! Come again soon.";
    
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return MESSAGE;
    }
}
