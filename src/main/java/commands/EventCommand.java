package commands;

import java.io.IOException;
import java.time.LocalDateTime;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

public class EventCommand extends Command {
    private String taskAction;
    private LocalDateTime eventDate;

    /**
     * Constructs an event command.
     *
     * @param taskAction is the task action.
     * @param eventDate is the time and date of the event.
     */
    public EventCommand(String taskAction, LocalDateTime eventDate) {
        super();
        this.taskAction = taskAction;
        this.eventDate = eventDate;
    }

    /**
     * Executes the event command.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return added event response.
     * @throws IOException is exception for file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Event event = new Event(taskAction, eventDate);
        if (tasks.checkDuplicate(event)) {
            String s = "Note!! This task action already exists in the list!\n";
            return s + String.format("Now you have %d tasks in the list.\n", tasks.getTaskListSize());
        } else {
            tasks.addTask(event);
            storage.saveTasksIntoFile(tasks);
            return ui.acknowledgeTaskAdded(tasks, event);
        }
    }
}
