package parser;

import exceptions.NoDescriptionException;
import model.TaskList;

/**
 * Abstract class implementing part of the command object.
 */
public abstract class Command {
    protected TaskList taskList;

    public Command() {

    }

    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract String execute() throws NoDescriptionException;
}
