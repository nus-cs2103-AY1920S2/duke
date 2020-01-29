package com.duke.bot.command;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.duke.bot.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public ExecuteResult execute(TaskList tasks) {
        return new ExecuteResult(
                tasks,
                Stream.concat(
                        Stream.of("Here are the matching tasks in your list:"),
                        tasks.getUnderlyingList().stream()
                                .map(Object::toString)
                                .filter(taskString -> taskString.matches(".*" + keyword + ".*"))
                ).collect(Collectors.toUnmodifiableList()),
                true
        );
    }
}
