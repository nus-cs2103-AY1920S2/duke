package duke.command;

import duke.Storage;
import duke.common.Message;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command and display an exit message.
     */
    public String execute(TaskList tasks, Storage storage) {
        return Message.EXIT_MESSAGE;
    }
}