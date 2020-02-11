package duke.command;

import java.util.stream.Collectors;
import duke.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public ExecuteResult execute(TaskList tasks) {
        return new ExecuteResult(
                tasks,
                "Here are the matching tasks in your list:\n"
                + tasks.getUnderlyingList().stream()
                        .map(Object::toString)
                        .filter(taskString -> taskString.matches(".*" + keyword + ".*"))
                        .collect(Collectors.joining("\n")),
                true
        );
    }
}
