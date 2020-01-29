package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a deadline command. Upon execution, updates data/duke.txt and produces some feedback to the user.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime dateTime;

    /**
     * @param description Description of the deadline task
     * @param dateTime Date and time of the deadline task
     */
    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(description, dateTime);
        tasks.addTask(task);
        storage.saveSingle(task);
        ui.showAddTask(task, tasks.getSize());
    }
}
