package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeleteCommand that inherits Command.
 * Implements abstract method execute that deletes the task from a TaskList.
 * @author Lua Jia Zheng.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;
    static final String SUCCESS = "Task has been removed";

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public DeleteCommand(Boolean isExit, int taskIndex) {
        super(isExit);
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes task base on taskIndex.
     * Saves task in txt file.
     * @param taskList taskList is updated with the deleted task.
     * @param ui duke.Duke.duke.ui.Ui prints delete task message back to user.
     * @param storage duke.storage.Storage updates the txt file with updated taskList.
     * @return returns duke.command.CommandResult.
     */
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task temp = taskList.deleteTask(taskIndex);
            storage.saveTask(taskList);
            taskList.updateList(storage);
            ui.deleteTaskSuccess(temp);
            return new CommandResult(SUCCESS);
        } catch (Exception ex) {
            return new CommandResult(ex.getMessage());
        }
    }

}
