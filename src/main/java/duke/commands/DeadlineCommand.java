package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime dateTime;

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
