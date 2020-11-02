package duke.command;

import java.time.LocalDate;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.Ui;

import java.util.ArrayList;

/**
 * Represents the Command used to find tasks that occur on a specified date.
 */
public class WhatsupCommand extends Command {
    private LocalDate date;

    public WhatsupCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        ArrayList<Task> queryTasks = new ArrayList<>();
        for (Task task: taskList.getList()) {
            if (task instanceof Deadline
                    && ((Deadline) task).getDueDate().toLocalDate().equals(date)) {
                queryTasks.add(task);
            } else if (task instanceof Event
                    && ((Event) task).getTimePeriod().toLocalDate().equals(date)) {
                queryTasks.add(task);
            }
        }
        return ui.showWhatsupMessage(queryTasks);
    }
}