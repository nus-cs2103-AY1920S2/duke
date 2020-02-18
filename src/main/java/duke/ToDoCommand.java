package duke;

/**
 * ToDoCommand class that inherits from Command.
 */
public class ToDoCommand extends Command {
    protected String description;

    /**
     * Delete Command constructor.
     * @param description description of todo
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in taskList class
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        Task t = new Todo(getIndex());
        taskList.addTask(t);
        storage.store(taskList.getEntireList());
        return ui.printTodoComplete(t, taskList.getTaskListSize());
    }

    /**
     * Method to return the index.
     * @return returns the index in the class
     */
    public String getIndex() {
        return description;
    }
}
