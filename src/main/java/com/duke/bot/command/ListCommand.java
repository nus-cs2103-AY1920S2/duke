package com.duke.bot.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import com.duke.bot.TaskList;

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
                ).collect(Collectors.toList()),
                true
        );
    }
}
