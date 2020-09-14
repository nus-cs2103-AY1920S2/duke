package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;
import dukeproj.tasks.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /** The index of the task deleted. */
    private String index;

    /**
     * Executes the delete command to delete a specified task from the task list.
     *
     * @param ui The user interface of Duke, used to return Duke's response.
     * @param taskList The list of tasks to delete from.
     * @param storage The object to assist in writing the task list into the storage file.
     * @param schedule Duke's schedule to be modified if deleted task is date sensitive.
     * @return Duke's response in the form of a String.
     * @throws BadDescriptionException If the description is not in the form of an integer.
     * @throws DukeDescriptionException If the description is empty.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule)
            throws BadDescriptionException, DukeDescriptionException {
        try {
            if (index.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }

            int delete = Integer.parseInt(index);

            if (delete <= 0 || delete > taskList.getSize()) {
                throw new BadDescriptionException("Description for delete cannot be " + delete);
            }

            Task deletedTask = taskList.getTask(delete - 1);
            taskList.removeTask(delete - 1);
            schedule.removeTask(deletedTask, deletedTask.getDate());
            storage.writeListIntoFile(taskList.getList());

            return ui.say(SayType.DELETE) + "\n" + deletedTask;
        } catch (NumberFormatException e) {
            throw new BadDescriptionException("Description for delete cannot be Non-Integer");
        }
    }

    /**
     * Constructs a delete command object with a description of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

}
