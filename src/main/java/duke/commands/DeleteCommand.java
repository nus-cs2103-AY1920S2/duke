package duke.commands;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.Tag;
import duke.tags.TagList;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command object specifically for adding a task to the TaskList.
 *
 * @author Firzan Armani
 */
public class DeleteCommand extends Command {
    int index = -1;

    /**
     * DeleteCommand constructor.
     *
     * @param index Index of the task to be removed from the TaskList
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException(ErrorCodes.INVALID_TASK_INDEX);
        } else {
            Task deletedTask = tasks.deleteTask(index);
            ui.dukePrompt(new String[]{"Aaaaand deleted! Don't kill me if it's the wrong one, boss",
                deletedTask.toString(),
                tasks.printTasksTotal()});
            for (Tag tag : deletedTask.getTags()) {
                tagList.removeTag(tag);
            }
            storage.save(tasks);
            return "Aaaaand deleted! Don't kill me if it's the wrong one, boss"
                + deletedTask.toString()
                + tasks.printTasksTotal();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}