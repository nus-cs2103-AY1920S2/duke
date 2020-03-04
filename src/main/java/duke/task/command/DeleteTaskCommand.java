package duke.task.command;

import duke.DukeException;
import duke.Storage;
import duke.common.message.ErrorMessage;
import duke.common.message.Message;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteTaskCommand extends TaskCommand {

    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command and deletes the task with the given index.
     * Then, displays the response to the user.
     */
    public String execute(TaskList tasks, Storage storage)
            throws DukeException {
        if (index < 1 || index > tasks.getLength()) {
            throw new DukeException(ErrorMessage.INVALID_INDEX);
        }
        
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.save(tasks);
        
        String output = Message.DELETE_MESSAGE + "\n"
                + Message.DIVIDER + "\n"
                + "  " + task + "\n"
                + Message.DIVIDER + "\n"
                + Message.showNumberOfTasks(tasks.getLength()) + "\n";

        return output;
    }
}


