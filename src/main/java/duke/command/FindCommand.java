package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

/**
 * <h1>FindCommand Class</h1>
 * A subclass of Command class. It uses one keyword to find any
 * tasks' names with the keyword and list it to the user via ui.
 *
 * @author Eng Xuan En
 */
public class FindCommand extends Command {
    /**
     * Class constructor of Find. It takes in the Command type and the keyword.
     *
     * @param type    type of the command
     * @param details keyword that required to find
     */
    public FindCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    /**
     * Search any task with the keyword and return list back in String.
     *
     * @param taskList Stored the tasks when the program runs
     * @param storage  Stored the tasks when task listing being edit
     * @param ui       Print the message out to console
     * @return Return list back
     */
    @Override
    public String executeWithoutReply(TaskList taskList, Storage storage, Ui ui) {
        List<Task> result = taskList.findTask(details.get(1));
        return ui.replyTaskFound(result);
    }
}
