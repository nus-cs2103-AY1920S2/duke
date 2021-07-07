package parser;

/**
 * Presents a command to view all the tasks from task lists.
 */
public class ViewListCommand extends Command {

    public ViewListCommand() {

    }

    /**
     * Convert the task list to string representation.
     * @return the string representation of the task list.
     */
    @Override
    public String execute() {
        return this.taskList.toString();
    }
}

