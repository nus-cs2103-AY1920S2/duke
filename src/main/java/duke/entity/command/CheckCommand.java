package duke.entity.command;

import duke.entity.task.Task;
import duke.entity.TaskList;
import duke.handler.Storage;
import duke.handler.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckCommand extends Command {

    private Date dueDate;

    public CheckCommand(Date date) {
        this.dueDate = date;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        List<Task> dueTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isDue(dueDate)) {
                dueTasks.add(tasks.get(i));
            }
        }
        ui.listDueTasks(dueTasks, dueDate);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
