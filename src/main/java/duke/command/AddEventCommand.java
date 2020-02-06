package duke.command;

import duke.task.Event;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime time;

    public AddEventCommand(String description, LocalDateTime time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Event task = new Event(description, time);
        taskList.addTask(task);
        ui.showAdd(task, taskList.getTaskList());
    }
}