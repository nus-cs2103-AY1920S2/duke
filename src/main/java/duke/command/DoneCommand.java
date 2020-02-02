package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

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
     * @param tasks   TaskList of Duke.
     * @param storage To load from and save to the disk.
     * @return Acknowledgement message sent by Duke.
     * @throws DukeException if the given task number is out of bound of the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            return tasks.getTasks().get(taskNo).markAsDone();
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Which task is done?");
        }
    }
}
