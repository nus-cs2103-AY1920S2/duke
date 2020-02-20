package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidIndexException;
import duke.gui.Gui;
import duke.task.Task;

import java.time.LocalDateTime;

public class UpdateTimedTaskCommand extends Command {
    protected int index;
    protected String description;
    protected LocalDateTime time;

    /**
     * Creates Update timed task command.
     * @param index index of timed task to update.
     * @param description updated description.
     * @param time updated time.
     */
    public UpdateTimedTaskCommand(int index, String description, LocalDateTime time) {
        this.index = index;
        this.description = description;
        this.time = time;
    }

    /**
     * Gets the task with index saved to update timed task.
     * @param taskList list of tasks.
     * @return tasks with index saved in command.
     */
    public Task getTask(TaskList taskList) {
        try {
            return taskList.getTaskList().get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(Integer.toString(index + 1));
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = getTask(taskList);
        task.setDescription(description);
        task.setTime(time);
        ui.showUpdate(task, taskList.getTaskList());
    }

    @Override
    public String execute(TaskList taskList, Gui gui) {
        Task task = getTask(taskList);
        task.setDescription(description);
        task.setTime(time);
        return gui.showUpdate(task, taskList.getTaskList());
    }
}
