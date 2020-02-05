package duke.command;

import java.time.LocalDateTime;
import java.util.HashMap;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.common.ErrorMessage;
import duke.common.Message;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

public class AddCommand extends Command {

    private TaskType type;
    private String description;
    private HashMap<String, Object> details;

    public AddCommand(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.details = new HashMap<>();
    }

    public AddCommand(TaskType type, String description, HashMap<String, Object> details) {
        this.type = type;
        this.description = description;
        this.details = details;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;

        switch (this.type) {
        case TODO:
            task = new Todo(false, description);
            break;
        case DEADLINE:
            task = new Deadline(false, description, (LocalDateTime)details.get("datetime"));
            break;
        case EVENT:
            task = new Event(false, description, (LocalDateTime)details.get("datetime"));
            break;
        default:
            throw new DukeException(ErrorMessage.COMMAND_NOT_FOUND);
        }

        tasks.add(task);
        storage.save(tasks);
        
        String output = Message.TASK_ADDED + "\n"
                + Message.DIVIDER + "\n"
                + "  " + task + "\n"
                + Message.DIVIDER + "\n"
                + Message.showNumberOfTasks(tasks.getLength());
        ui.showMessage(output);
    }
}