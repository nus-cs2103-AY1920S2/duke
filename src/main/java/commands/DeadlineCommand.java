package commands;

import java.io.IOException;
import java.time.LocalDateTime;

import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    private String taskAction;
    private LocalDateTime deadlineDate;

    public DeadlineCommand(String taskAction, LocalDateTime deadlineDate) {
        super();
        this.taskAction = taskAction;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Deadline deadline = new Deadline(taskAction, deadlineDate);
        if (tasks.checkDuplicate(deadline)) {
            String s = "Note!! This task action already exists in the list!\n";
            return s + String.format("Now you have %d tasks in the list.\n", tasks.getTaskListSize());
        } else {
            tasks.addTask(deadline);
            storage.saveTasksIntoFile(tasks);
            return ui.acknowledgeTaskAdded(tasks, deadline);
        }
    }
}
