package duke.command;

import duke.Storage;
import duke.common.Message;
import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Executes the command and lists all the tasks.
     */
    public String execute(TaskList tasks, Storage storage) {
        String output = Message.LIST_MESSAGE + "\n"
                + tasks.toString();
        return output;
    }
}