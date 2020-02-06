package main.java.parser;

import main.java.exceptions.NoDescriptionException;
import main.java.model.DeadLineTask;
import main.java.model.EventTask;
import main.java.model.Task;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    protected String description;
    protected LocalDateTime at;

    public AddDeadlineCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new DeadLineTask(description, at);
        String commandResult = this.taskList.add(taskToAdd);
        return commandResult;
    }
}
