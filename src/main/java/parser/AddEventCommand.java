package parser;

import exceptions.NoDescriptionException;
import model.EventTask;
import model.Task;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime at;

    AddEventCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new EventTask(description, at);
        return this.taskList.add(taskToAdd);
    }
}
