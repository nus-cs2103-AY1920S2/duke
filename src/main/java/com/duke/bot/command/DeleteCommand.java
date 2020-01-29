package com.duke.bot.command;

import java.util.ArrayList;
import java.util.List;
import com.duke.bot.Task;

public class DeleteCommand extends Command {
    private final int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public ExecuteResult execute(List<Task> tasks) {
        Task deleteTask = tasks.get(deleteIndex);
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.remove(deleteIndex);
        return new ExecuteResult(newTasks, List.of(
                "Noted. I've removed this task:",
                "  " + deleteTask,
                String.format("Now you have %d tasks in the list", tasks.size())
        ));
    }
}
