package duke.pack;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    protected int taskNum;

    /**
     * Creates a command to mark a task as done.
     * @param taskNum the task to be marked as done
     */
    public DoneCommand(int taskNum) {
        assert taskNum > 0: "task number must be 1 or more!";
        this.taskNum = taskNum;
    }

    /**
     * Executes the done command and marks task as done.
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @throws DukeException if task cannot be updated to hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum > tasks.getSize()) {
            throw new DukeException("    Oh no! That task does not exist!");
        }

        Task task = tasks.markAsDone(taskNum);
        storage.save(tasks);
        ui.showDone(task);

    }


    /**
     * Executes done command and gets response for the GUI
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @return String response for GUI
     * @throws DukeException if task cannot be updated to hard disk
     */
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum > tasks.getSize()) {
            throw new DukeException("Oh no! That task does not exist!");
        }
        Task task = tasks.markAsDone(taskNum);
        storage.save(tasks);

        String resp = "Fantastic job! I have marked this task as done: \n" + task;

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
