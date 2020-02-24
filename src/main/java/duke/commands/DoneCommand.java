package duke.commands;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.TagList;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command object specifically for marking a task as Completed.
 *
 * @author Firzan Armani
 */
public class DoneCommand extends Command {
    private int index = -1;

    /**
     * DoneCommand constructor.
     *
     * @param index The index of the task in the TaskList to be marked as completed
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException(ErrorCodes.INVALID_TASK_INDEX);
        } else {
            Task currentTask = tasks.setDone(this.index);
            ui.dukePrompt(new String[]{"Got it boss! Just to confirm, this is the one I marked as done",
                "\n",
                currentTask.toString(),
                "\n",
                tasks.printTasksTotal()});
            storage.save(tasks);
            return "Got it boss! Just to confirm, this is the one I marked as done"
                + "\n"
                + currentTask.toString()
                + "\n"
                + tasks.printTasksTotal();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}