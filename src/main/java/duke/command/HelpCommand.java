package duke.command;

import java.util.List;
import duke.task.Task;

public class HelpCommand extends Command {
    @Override
    public ExecuteResult execute(List<Task> tasks) {
        return new ExecuteResult(
                tasks,
                "Usage: [command] <args...>\n\n"
                + "Command includes:\n"
                + "    1. todo <title> : Adds a new todo to the list of tasks.\n"
                + "    2. deadline <title> /by <YYYY-MM-DD> : Adds a new\n"
                + "              deadline to the list of tasks.\n"
                + "    3. event <title> /at <YYYY-MM-DD> : Adds a new event\n"
                + "              to the list of tasks.\n"
                + "    4. done <index> : Marks the task at the specified index\n"
                + "              as done.\n"
                + "    5. find <keyword> : Finds tasks containing the specified\n"
                + "              keyword.\n"
                + "    6. delete <index> : Removes the task at the specified\n"
                + "              index.\n"
                + "    7. list : Lists out all the tasks available.\n"
                + "    8. help : Prints help.",
                true
        );
    }
}
