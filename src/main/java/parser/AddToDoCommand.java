package parser;

import exceptions.NoDescriptionException;
import model.Task;
import model.ToDoTask;

public class AddToDoCommand extends Command {
    protected String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute() throws NoDescriptionException {
        Task taskToAdd = new ToDoTask(description);
        String commandResult = this.taskList.add(taskToAdd);
        return commandResult;
    }
}
