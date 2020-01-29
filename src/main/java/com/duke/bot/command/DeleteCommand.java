package com.duke.bot.command;

import java.util.List;
import com.duke.bot.DukeException;
import com.duke.bot.TaskList;

public class DeleteCommand extends Command {
    private final int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public ExecuteResult execute(TaskList tasks) throws DukeException {
        if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
            return new ExecuteResult(
                    tasks.remove(deleteIndex),
                    List.of(
                            "Noted. I've removed this task:",
                            "  " + tasks.get(deleteIndex),
                            String.format("Now you have %d tasks in the list", tasks.size() - 1)
                    ),
                    true
            );
        } else {
            throw new DukeException("Oops, delete index is out of bounds");
        }
    }
}
