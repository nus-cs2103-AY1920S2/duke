package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents an event command. Upon execution, updates data/duke.txt and produces some feedback to the user.
 */
public class EventCommand extends Command{
    private String description;
    private LocalDateTime dateTime;

    /**
     * @param description Description of the event task
     * @param dateTime Date and time of the event task
     */
    public EventCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(description, dateTime);
        tasks.addTask(task);
        storage.saveSingle(task);
        ui.showAddTask(task, tasks.getSize());
    }
}
