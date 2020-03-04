package duke.task.command;

import duke.Storage;
import duke.common.message.Message;
import duke.task.TaskList;

public class ListTaskCommand extends TaskCommand {

    /**
     * Executes the command and lists all the tasks.
     */
    public String execute(TaskList tasks, Storage storage) {
        String output = Message.TASK_MESSAGE + "\n"
                + tasks.toString();
        return output;
    }
}