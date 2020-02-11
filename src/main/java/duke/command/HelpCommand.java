package duke.command;

import java.util.List;
import duke.task.Task;

public class HelpCommand extends Command {
    @Override
    public ExecuteResult execute(List<Task> tasks) {
        return new ExecuteResult(
                tasks,
                "Usage: [command] [args...]\n"
                + "Where command includes:\n"
                + "  todo <title> : Adds a new todo to the list of tasks.\n"
                + "  deadline <title> /by <YYYY-MM-DD> : Adds a new deadline\n"
                + "      to the list of tasks.\n"
                + "  event <title> /at <YYYY-MM-DD> : Adds a new event to\n"
                + "      the list of tasks.\n"
                + "  done <index> : Marks the task at the specified index as\n"
                + "      done.\n"
                + "  find <keyword> : Finds tasks containing the specified\n"
                + "      keyword.\n"
                + "  delete <index> : Removes the task at the specified\n"
                + "      index.\n"
                + "  list : Lists out all the tasks available.\n"
                + "  help : Prints help.",
                true
        );
    }
}
