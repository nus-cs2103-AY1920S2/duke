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

    /**
     * Constructs a DeadlineCommand instance.
     *
     * @param taskAction is the task action.
     * @param deadlineDate is the deadline of the task.
     */
    public DeadlineCommand(String taskAction, LocalDateTime deadlineDate) {
        super();
        this.taskAction = taskAction;
        this.deadlineDate = deadlineDate;
    }

    /**
     * Executes the deadline command.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return deadline task added response to user.
     * @throws IOException is exception for file.
     */
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
