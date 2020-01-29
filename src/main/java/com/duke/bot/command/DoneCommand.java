package com.duke.bot.command;

import java.util.ArrayList;
import java.util.List;
import com.duke.bot.DukeException;
import com.duke.bot.Task;

public class DoneCommand extends Command {
    private final int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public ExecuteResult execute(List<Task> tasks) throws DukeException {
        if (doneIndex >= 0 && doneIndex < tasks.size()) {
            Task doneTask = tasks.get(doneIndex).setDone(true);
            List<Task> newTasks = new ArrayList<>(tasks);
            newTasks.set(doneIndex, doneTask);
            return new ExecuteResult(tasks, List.of(
                    "Nice! I've marked this task as done:",
                    "  " + doneTask
            ));
        } else {
            throw new DukeException("Oops, done index is out of bounds");
        }
    }
}
