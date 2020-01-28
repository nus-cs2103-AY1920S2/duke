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
 *
 * @author  Eng Xuan En
 */
public class FindCommand extends Command {
    /**
     * Class constructor of Find. It takes in the Command type and the keyword.
     * @param type type of the command
     * @param details keyword that required to find
     */
    public FindCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    /**
     * Search any task with the keyword and list it out to the user via ui.
     * @param taskList list where the task stored at
     * @param storage save tasks to the file in hard disk
     * @param ui display the list of tasks with the keyword to user
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        List<Task> result = taskList.findTask(details.get(1));
        ui.replyTaskFound(result);
    }
}
