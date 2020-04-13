package duke.command;

import duke.other.DukeException;
import duke.task.TaskList;
import duke.other.Ui;


/**
 * Represents a command that deletes a Task from the TaskList that extends the Command class. A DeleteCommand object
 * corresponds to a command type represented by a String followed by the necessary details of the command (e.g.
 * delete 3).
 */
public class DeleteCommand extends Command {
    private String instruction;
    private String[] replyArr;

    public DeleteCommand(String instruction, String[] replyArr) {
        this.instruction = instruction;
        this.replyArr = replyArr;
    }

    /**
     * Runs the method deleteInstruction in the TaskList by passing in the details of the delete command(i.e. task
     * number of the task to be deleted).
     *
     * @param taskList Overall TaskList of all the Tasks
     * @param ui       Overall Ui handling the ui of Duke
     */
    public String execute(TaskList taskList, Ui ui) {
        return taskList.deleteTask(replyArr);
    }
}
