package duke.command.task;

import duke.Storage;
import duke.common.Message;
import duke.task.TaskList;

public class ListCommand extends TaskCommand {

    /**
     * Executes the command and lists all the tasks.
     */
    public String execute(TaskList tasks, Storage storage) {
        String output = Message.LIST_MESSAGE + "\n"
                + tasks.toString();
        return output;
    }
}