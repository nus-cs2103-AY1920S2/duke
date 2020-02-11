package duke.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import duke.TaskList;

public class ListCommand extends Command {
    @Override
    public ExecuteResult execute(TaskList tasks) {
        return new ExecuteResult(
                tasks,
                "Here are the tasks in your list:\n"
                + IntStream.range(0, tasks.size())
                        .boxed()
                        .map(index -> String.format("%d.%s", index + 1, tasks.get(index)))
                        .collect(Collectors.joining("\n")),
                true
        );
    }
}
