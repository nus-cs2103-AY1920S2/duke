package duke;

/**
 * Delete Command class that inherits from Command.
 */
public class DeleteCommand extends Command {
    protected String done;

    /**
     * Delete Command constructor.
     * @param done index of task to remove
     */
    public DeleteCommand(String done) {
        this.done = done;
    }

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in taskList class
     */
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        String num = getIndex();
        System.out.println("Noted! I've removed this task:\n");
        System.out.println(num + ". " + "[" + taskList.getTask(Integer.parseInt(num) - 1).getStatusIcon() + "] "
                + taskList.getTask(Integer.parseInt(num) - 1).getDescription());
        taskList.removeTask(Integer.parseInt(num) - 1);
        storage.store(taskList.getEntireList());
        System.out.println("Now you have " + taskList.getTaskListSize() + " tasks in the list");
    }

    /**
     * Method to return the index.
     * @return returns the index in the class
     */
    public String getIndex() {
        return done;
    }
}
