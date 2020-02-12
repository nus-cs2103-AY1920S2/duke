package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.time.LocalDateTime;

public class UpdateDeadlineCommand extends UpdateTimedTaskCommand {
    public UpdateDeadlineCommand(int index, String description, LocalDateTime time) {
        super(index, description, time);
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!(super.getTask(taskList) instanceof Deadline)) {
            throw new DukeException("Task is not an Deadline");
        }
        super.execute(taskList, ui);
    }
}
