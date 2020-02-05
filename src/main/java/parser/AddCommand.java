package main.java.parser;

import main.java.exceptions.NoDescriptionException;
import main.java.model.Task;

public class AddCommand extends Command {
    protected Task taskToAdd;
    protected String[] params;

    public AddCommand(Task taskToAdd, String... params) {
        this.taskToAdd = taskToAdd;
        this.params = params;
    }

    @Override
    public String execute() throws NoDescriptionException {
        this.taskToAdd.setParams(this.params);
        String commandResult = this.taskList.add(this.taskToAdd);
        return commandResult;
    }
}
