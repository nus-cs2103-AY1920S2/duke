package duke;

/**
 * Event Command class that inherits from Command.
 */
public class EventCommand extends Command {
    protected String spli;
    protected String description;
    protected String date;

    /**
     * Deadline command constructor.
     * @param spli Takes in /at statement
     * @param description takes in the description
     * @param date Takes in the finishing date in string
     */
    public EventCommand(String spli, String description, String date) {
        this.spli = spli;
        this.description = description;
        this.date = date;
    }

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in TaskList class
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        Task t = new Event(getIndex(), date);
        taskList.addTask(t);
        storage.store(taskList.getEntireList());
        return ui.printTodoComplete(t, taskList.getTaskListSize());
    }

    public String execute(Ui ui, Storage storage, ContactList contacts) {
        return "";
    }
    /**
     * Method to return the index.
     * @return returns the index in the class
     */
    public String getIndex() {
        return description;
    }
}
