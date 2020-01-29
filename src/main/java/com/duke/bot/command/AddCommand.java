package com.duke.bot.command;

import java.util.ArrayList;
import java.util.List;
import com.duke.bot.Task;

public class AddCommand extends Command {
    private final Task addTask;

    public AddCommand(Task addTask) {
        this.addTask = addTask;
    }

    @Override
    public ExecuteResult execute(List<Task> tasks) {
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.add(addTask);
        return new ExecuteResult(
                newTasks,
                List.of(
                        "Got it. I've added this task:",
                        "  " + addTask,
                        String.format("Now you have %d tasks in the list.", tasks.size())
                ),
                true
        );
    }
}
