package duke;

/**
 * Done Command class that inherits from Command.
 */
public class DoneCommand extends Command {

    protected String done;

    /**
     * Done Command constructor.
     * @param done index of task to complete
     */
    public DoneCommand(String done) {
        this.done = done;
    }

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in taskList class
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        String response;
        String num = getIndex();
        response = "Nice! I've marked this task as done:\n";
        taskList.getTask(Integer.parseInt(num) - 1).markAsDone();
        storage.store(taskList.getEntireList());
        response += num + ". " + "[" + taskList.getTask(Integer.parseInt(num) - 1).getStatusIcon() + "] "
                + taskList.getTask(Integer.parseInt(num) - 1).getDescription();
        return response;
    }

    public String execute(Ui ui, Storage storage, ContactList contacts) {
        return "";
    }
    /**
     * Method to return the index.
     * @return returns the index in the class
     */
    public String getIndex() {
        return done;
    }
}
