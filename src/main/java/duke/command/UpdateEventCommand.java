package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.gui.Gui;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDateTime;

public class UpdateEventCommand extends UpdateTimedTaskCommand {
    public UpdateEventCommand(int index, String description, LocalDateTime time) {
        super(index, description, time);
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!(super.getTask(taskList) instanceof Event)) {
            throw new DukeException("Task is not an Event");
        }
        super.execute(taskList, ui);
    }
    @Override
    public String execute(TaskList taskList, Gui gui) {
        if (!(super.getTask(taskList) instanceof Deadline)) {
            throw new DukeException("Task is not an Deadline");
        }
        return super.execute(taskList, gui);
    }
}