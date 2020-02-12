package parser;

import exceptions.NoDescriptionException;
import model.Task;
import model.ToDoTask;

public class AddToDoCommand extends Command {
    private String description;

    AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new ToDoTask(description);
        return this.taskList.add(taskToAdd);
    }
}
