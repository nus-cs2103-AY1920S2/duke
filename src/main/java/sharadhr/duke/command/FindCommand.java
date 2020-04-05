package sharadhr.duke.command;

import sharadhr.duke.Duke;
import sharadhr.duke.exception.DukeEmptyDetailException;
import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidDateTimeException;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.Task;
import sharadhr.duke.task.TaskList;

import java.util.stream.Stream;

/**
 * FindCommand
 */
public class FindCommand extends Command {
    public FindCommand(String[] argumentTokens) throws DukeInvalidArgumentException {
        super(argumentTokens);

        if (argumentTokens.length != 1) {
            throw new DukeInvalidArgumentException(
                "Find command should have only one word as argument.", argumentTokens, commandName,
                this.getClass().getSimpleName());
        }
    }

    @Override public void execute(TaskList tasks, Storage storage, Output output)
        throws DukeEmptyDetailException, DukeInvalidDateTimeException {
        Duke.output.add(
            String.format("These tasks match or contain the keyword '%s':", argumentTokens[0]));
        Duke.output.say(
            Stream.of(Duke.tasks.findTasksWithKeyword(argumentTokens[0])).map(Task::toString)
                  .toArray(String[]::new));

    }
}