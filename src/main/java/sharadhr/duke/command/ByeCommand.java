package sharadhr.duke.command;

import sharadhr.duke.Duke;
import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * ByeCommand
 */
public class ByeCommand extends Command {
    private final CommandName commandName = CommandName.BYE;

    public ByeCommand(String[] argumentTokens) throws DukeInvalidArgumentException {
        if (argumentTokens.length != 0) {
            throw new DukeInvalidArgumentException("Bye command should have no arguments.",
                                                   argumentTokens, this.commandName,
                                                   this.getClass().getSimpleName());
        }
    }

    @Override public void execute(TaskList tasks, Storage storage, Output output) {
        Duke.output.sayGoodBye();
    }

    @Override public boolean willTerminate() {
        return true;
    }
}