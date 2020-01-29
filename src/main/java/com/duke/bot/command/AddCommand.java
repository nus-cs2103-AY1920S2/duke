package com.duke.bot.command;

import java.util.List;
import com.duke.bot.TaskList;
import com.duke.bot.task.Task;

public class AddCommand extends Command {
    private final Task addTask;

    public AddCommand(Task addTask) {
        this.addTask = addTask;
    }

    @Override
    public ExecuteResult execute(TaskList tasks) {
        return new ExecuteResult(
                tasks.add(addTask),
                List.of(
                        "Got it. I've added this task:",
                        "  " + addTask,
                        String.format("Now you have %d tasks in the list.", tasks.size() + 1)
                ),
                true
        );
    }
}
