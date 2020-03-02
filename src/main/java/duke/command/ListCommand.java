package duke.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import duke.task.Task;

public class ListCommand extends Command {
    @Override
    public ExecuteResult execute(List<Task> tasks) {
        assert tasks != null;

        if (tasks.size() == 0) {
            return new ExecuteResult(
                tasks,
                "You don't have any tasks for the moment!\n",
                true
            );
        }

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
