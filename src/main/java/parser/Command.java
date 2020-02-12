package parser;

import exceptions.NoDescriptionException;
import model.TaskList;

/**
 * Abstract class implementing part of the command object.
 */
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
