package com.duke.bot.command;

import java.util.List;
import com.duke.bot.DukeException;
import com.duke.bot.TaskList;
import com.duke.bot.task.Task;

public class DoneCommand extends Command {
    private final int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public ExecuteResult execute(TaskList tasks) throws DukeException {
        if (doneIndex >= 0 && doneIndex < tasks.size()) {
            Task doneTask = tasks.get(doneIndex).setDone(true);
            return new ExecuteResult(
                    tasks.set(doneIndex, doneTask),
                    List.of(
                            "Nice! I've marked this task as done:",
                            "  " + doneTask
                    ),
                    true
            );
        } else {
            throw new DukeException("Oops, done index is out of bounds");
        }
    }
}
