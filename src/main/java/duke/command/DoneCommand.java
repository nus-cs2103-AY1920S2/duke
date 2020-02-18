package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeNoSuchTaskException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks task as done.
 */
public class DoneCommand extends Command {

    private int taskNo;

    /**
     * Constructs a DoneCommand object.
     *
     * @param taskNo the index of the task that is completed.
     */
    public DoneCommand(int taskNo) {
        super(false);
        this.taskNo = taskNo;
    }

    /**
     * Executes DoneCommand by marking the task as done.
     *
     * @param taskList   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage To load from and save to the disk.
     * @return Acknowledgement message sent by Duke.
     * @throws DukeException if the given task number is out of bound of the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            return taskList.getTasks().get(taskNo).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeNoSuchTaskException();
        }
    }
}
