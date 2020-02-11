package duke.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import duke.TaskList;

public class ListCommand extends Command {
    @Override
    public ExecuteResult execute(TaskList tasks) {
        return new ExecuteResult(
                tasks,
                Stream.concat(
                        Stream.of("Here are the tasks in your list:"),
                        IntStream.range(0, tasks.size())
                                .boxed()
                                .map(index -> String.format("%d.%s", index + 1, tasks.get(index)))
                ).collect(Collectors.toUnmodifiableList()),
                true
        );
    }
}
