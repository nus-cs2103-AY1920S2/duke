import java.io.IOException;

/**
 * The DoneCommand inherits from Command and is used whenever the markAsDone() method
 * from Task is called. This updates the status of the specified task in the TaskList
 * and saves it.
 */
public class DoneCommand extends Command {

    private int taskNum;

    /**
     * The constructor for a DoneCommand takes in a task number specified by the user
     * to update the status of the specific task in the TaskList.
     * @param taskNum
     */
    DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * The execute method in DoneCommand serves to update the TaskList with the updated status
     * of the specified task and saves it in the duke.txt file.
     *
     * @param tasks This is the saved TaskList in duke.txt.
     * @param ui This is the created Ui in Duke.
     * @param storage This is responsible for loading and saving the updated TaskList.
     * @throws IOException if file cannot be written to or closed.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.save(tasks);
        Task doneTask = tasks.getTaskList().get(taskNum - 1);
        ui.showLine();
        ui.showDoneMessage();
        doneTask.markAsDone();
        System.out.println(doneTask + "\n");
    }
}
