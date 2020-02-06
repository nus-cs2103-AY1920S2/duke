package duke.command;

import duke.task.Deadline;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime time;

    public AddDeadlineCommand(String description, LocalDateTime time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Deadline task = new Deadline(description, time);
        taskList.addTask(task);
        ui.showAdd(task, taskList.getTaskList());
    }
}
