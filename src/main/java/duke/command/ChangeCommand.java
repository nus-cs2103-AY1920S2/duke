package duke.command;

import duke.exception.DukeException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * A command to change the description or date (or both) in Duke.
 */
public class ChangeCommand extends Command {
    int idx;
    String desc;
    LocalDate date = null;

    /**
     * Changing only the description of a task.
     * @param idx Task index.
     * @param desc New description.
     */
    public ChangeCommand(int idx, String desc) {
        this.idx = idx;
        this.desc = desc;
    }

    /**
     * Changing only the date of a task.
     * @param idx Task index.
     * @param date New date.
     */
    public ChangeCommand(int idx, LocalDate date) {
        this.idx = idx;
        this.date = date;
    }

    /**
     * Changing both the description and date of a task.
     * @param idx Task index.
     * @param desc New description.
     * @param date New date.
     */
    public ChangeCommand(int idx, String desc, LocalDate date) {
        this(idx, desc);
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task oldTask;
        String field;
        if (this.date == null) {
            assert this.desc != null;
            oldTask = tasks.editTask(this.idx, this.desc);
            field = "description";
        } else {
            Task t = tasks.getTask(this.idx);
            if (t instanceof Deadline || t instanceof Event) {
                if (this.desc == null) {
                    assert this.date != null;
                    oldTask = tasks.editTask(this.idx, this.date);
                    field = "date";
                } else {
                    oldTask = tasks.editTask(this.idx, this.desc, this.date);
                    field = "description and date";
                }
            } else {
                oldTask = tasks.editTask(this.idx, this.desc);
                field = "description";
            }
        }
        storage.save(tasks.getTasks());
        String response = "Swee la! I've edited the " + field + " of this task:\n" + oldTask + '\n';
        response += "This is the modified version of the task:\n" + tasks.getTask(this.idx);
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}