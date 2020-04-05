package sharadhr.duke.command;

import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidCommandException;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * ListCommand
 */
public class ListCommand extends Command {
    /**
     * @param commandTokens
     * @throws DukeInvalidCommandException
     */
    public ListCommand(String[] commandTokens) throws DukeInvalidArgumentException {
        super(commandTokens);
        this.commandName = CommandName.LIST;

        if (commandTokens.length != 0) {
            throw new DukeInvalidArgumentException("List command must have no arguments.",
                                                   commandTokens, this.commandName,
                                                   this.getClass().getSimpleName());
        }

    }

    @Override public void execute(TaskList tasks, Storage storage, Output output) {
        tasks.listTasks(output);
    }
}