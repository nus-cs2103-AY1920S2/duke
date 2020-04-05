package sharadhr.duke.command;

import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * A user
 */
public class DeleteCommand extends Command {
    private int position;

    /**
     * Creates a Command that deletes a {@link sharadhr.duke.task.TaskList}.
     *
     * @param argumentTokens The arguments provided to this {@link sharadhr.duke.command.Command}
     * @throws DukeInvalidArgumentException If more than one argument is supplied, or the
     *                                      supplied argument is not strictly numerical and
     *                                      positive.
     */
    public DeleteCommand(String[] argumentTokens) throws DukeInvalidArgumentException {
        super(argumentTokens);
        if (argumentTokens.length != 1 || !argumentTokens[0].matches("\\d+")) {
            throw new DukeInvalidArgumentException(
                "Delete command should have only one positive number as argument.", argumentTokens,
                commandName, this.getClass().getSimpleName());
        }
        this.position = Integer.parseInt(this.argumentTokens[0]);
    }

    @Override public void execute(TaskList tasks, Storage storage, Output output) throws DukeInvalidArgumentException
    {
        if (this.position >= 1 && this.position <= tasks.numberOfTasks())
        {
            output.sayTaskDeleted(tasks.getTaskAtPosition(position));
            tasks.deleteTaskAtPosition(position);
        }
        else
            throw new DukeInvalidArgumentException("This item cannot be deleted.", argumentTokens, commandName,
                    this.getClass().getSimpleName());
    }
}