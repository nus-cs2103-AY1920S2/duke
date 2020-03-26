package duke.commands;

import duke.model.Storage;
import duke.model.TaskList;

/**
 * The <code>ListCommand</code> class represents a list command to list the tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String MESSAGE_HEADER = "Here are your tasks:\n";
    public static final String MESSAGE_NO_RESULT = "There are no tasks!";
    
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String list = taskList.list();
        String messageBody = list.isEmpty() ? MESSAGE_NO_RESULT : list;
        return MESSAGE_HEADER + messageBody;
    }
}
