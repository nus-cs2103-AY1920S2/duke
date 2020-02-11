package duke.command;

import java.util.List;
import java.util.stream.Collectors;
import duke.task.Task;

/**
 * Represents a 'find' command.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a 'find' command.
     * 
     * @param keyword Keyword to be found
     */
    public FindCommand(String keyword) {
        assert keyword != null;

        this.keyword = keyword;
    }

    @Override
    public ExecuteResult execute(List<Task> tasks) {
        assert tasks != null;

        return new ExecuteResult(
                tasks,
                "Here are the matching tasks in your list:\n"
                + tasks.stream()
                        .map(Object::toString)
                        .filter(taskString -> taskString.matches(".*" + keyword + ".*"))
                        .collect(Collectors.joining("\n")),
                true
        );
    }
}
