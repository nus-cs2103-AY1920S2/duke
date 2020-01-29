package com.duke.bot.command;

import java.util.List;
import com.duke.bot.task.Task;

public class ExitCommand extends Command {
    @Override
    public ExecuteResult execute(List<Task> tasks) {
        return new ExecuteResult(tasks, List.of("Bye. Hope to see you again soon!"), false);
    }
}
