package parser;

import exceptions.NoDescriptionException;
import model.TaskList;

public class Command {
    protected TaskList taskList;

    public Command() {

    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public String execute() throws NoDescriptionException {
        return "";
    }
}
