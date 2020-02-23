package duke;

import java.time.LocalDateTime;

/**
 * DeadLine Command class that inherits from Command.
 */
public class DeadLineCommand extends Command {
    protected String spli;
    protected String description;
    protected LocalDateTime fin;

    /**
     * Deadline command constructor.
     * @param spli Takes in /by statement
     * @param description takes in the description
     * @param fin Takes in the finishing date
     */
    public DeadLineCommand(String spli, String description, LocalDateTime fin) {
        this.spli = spli;
        this.description = description;
        this.fin = fin;
    }

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in TaskList class
     */
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task t = new Deadline(getIndex(), fin);
        taskList.addTask(t);
        storage.store(taskList.getEntireList());
        ui.printTodoComplete(t, taskList.getTaskListSize());
    }

    /**
     * Method to return the index.
     * @return returns the index in the class
     */
    public String getIndex() {
        return description;
    }
}
