package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;

public class GetCommand extends Command {
    private LocalDate date;

    public GetCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if ((task instanceof Deadline && ((Deadline)task).getDate().equals(date))
                    || (task instanceof Event && ((Event)task).getDate().equals(date))) {
                filteredTasks.add(task);
            }
        }
        ui.showGetTasks(filteredTasks, date);
    }
}
