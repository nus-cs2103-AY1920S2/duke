package sharadhr.duke.command;

import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.Task;
import sharadhr.duke.task.TaskList;

/**
 * DoneCommand
 */
public class DoneCommand extends Command {
    private int position;

    /**
     * @param argumentTokens
     * @throws DukeInvalidArgumentException
     */
    public DoneCommand(String[] argumentTokens) throws DukeInvalidArgumentException {
        super(argumentTokens);
        if (argumentTokens.length != 1 || !argumentTokens[0].matches("\\d+"))
        {
            throw new DukeInvalidArgumentException("Done command should have only one positive number as argument.",
                    argumentTokens, commandName, this.getClass().getSimpleName());
        }
        this.position = Integer.parseInt(this.argumentTokens[0]);
    }

    @Override public void execute(TaskList tasks, Storage storage, Output output) throws DukeInvalidArgumentException
    {
        if ((this.position >= 1) && this.position <= tasks.numberOfTasks())
        {
            Task toComplete = tasks.getTaskAtPosition(position);
            if (toComplete.markComplete())
            {
                output.sayTaskMarkedComplete(toComplete);
            }
        }
        throw new DukeInvalidArgumentException("There is no such item in the list.", argumentTokens, commandName,
                this.getClass().getSimpleName());
    }
}