package duke.pack;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    protected int taskNum;

    /**
     * Creates a delete command.
     * @param taskNum number of task to be deleted
     */
    public DeleteCommand(int taskNum) {
        assert taskNum > 0: "task number must be 1 or more!";
        this.taskNum = taskNum;
    }

    /**
     * Executes the delete command and removes tasks from TaskList
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @throws DukeException if tasks cannot be updated to hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum > tasks.getSize()) {
            throw new DukeException("    Oh no! That task does not exist!");
        }

        Task task = tasks.deleteTask(taskNum);
        storage.save(tasks);
        ui.showDelete(task);
        ui.showCount(tasks);
    }


    /**
     * Executes delete command and gets response for the GUI
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @return String response for GUI
     * @throws DukeException if tasks cannot be saved to hard disk
     */
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum > tasks.getSize()) {
            throw new DukeException("Oh no! That task does not exist!");
        }
        Task task = tasks.deleteTask(taskNum);
        storage.save(tasks);

        String resp = "Yeet! I have tossed the task: \n" + task;

        return resp;
    }

    /**
     * Indicates whether command is exit.
     * @return boolean true if it is an exit command, else false
     */
    public Boolean isExit() {
        return false;
    }

}
