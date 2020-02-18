package duke.common.command;

import duke.common.message.Message;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command and display an exit message.
     */
    public String execute() {
        return Message.EXIT_MESSAGE;
    }
}