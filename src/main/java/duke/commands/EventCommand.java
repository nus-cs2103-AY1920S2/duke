package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command{
    private String description;
    private LocalDateTime dateTime;

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
