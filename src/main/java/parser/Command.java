package main.java.parser;

import main.java.exceptions.NoDescriptionException;
import main.java.model.TaskList;

public class Command {
    protected TaskList taskList;

    public Command() {}

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public String execute() throws NoDescriptionException {
        return "";
    }
}
