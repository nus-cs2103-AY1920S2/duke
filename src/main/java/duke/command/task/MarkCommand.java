package duke.command.task;

import duke.DukeException;
import duke.Storage;
import duke.common.ErrorMessage;
import duke.common.Message;
import duke.task.TaskList;

public class MarkCommand extends TaskCommand {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    
    /**
     * Executes the command and mark the index in the list as done.
     * Then, displays the response to the user.
     */
    public String execute(TaskList tasks, Storage storage)
            throws DukeException {
        if (index < 1 || index > tasks.getLength()) {
            throw new DukeException(ErrorMessage.INVALID_INDEX);
        }
        
        tasks.markDone(index);
        storage.save(tasks);
        
        String output = Message.MARK_DONE + "\n"
                + Message.DIVIDER + "\n"
                + tasks.getTask(index) + "\n"
                + Message.DIVIDER + "\n";
        return output;
    }
}


