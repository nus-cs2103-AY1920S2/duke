package duke.task.command;

import duke.DukeException;
import duke.Storage;
import duke.common.message.ErrorMessage;
import duke.common.message.Message;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.util.HashMap;

public class AddTaskCommand extends TaskCommand {

    private TaskType type;
    private String description;
    private HashMap<String, Object> details;

    /**
     * Creates an AddCommand object given the type and description of the task.
     * @param type The type of task to be added.
     * @param description The description of the task.
     */
    public AddTaskCommand(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.details = new HashMap<>();
    }

    /**
     * Creates an AddCommand object given the type, description and details of the task.
     * @param type The type of task to be added.
     * @param description The description of the task.
     * @param details The details specific to the task type.
     */
    public AddTaskCommand(TaskType type, String description,
            HashMap<String, Object> details) {
        this.type = type;
        this.description = description;
        this.details = details;
    }

    /**
     * Executes the command. Check for the type of task to be added, and add
     * the relevant task to task list. The current list of tasks are then saved.
     */
    public String execute(TaskList tasks, Storage storage)
            throws DukeException {
        Task task;

        switch (this.type) {
        case TODO:
            task = new Todo(false, description);
            break;
        case DEADLINE:
            task = new Deadline(false, description,
                    (LocalDateTime) details.get("deadline"));
            break;
        case EVENT:
            task = new Event(false, description,
                    (LocalDateTime) details.get("start"),
                    (LocalDateTime) details.get("end"));
            break;
        default:
            assert false : "Task type should already be valid.";
            throw new DukeException(ErrorMessage.getRandomCommandErrorMessage());
        }

        tasks.add(task);
        storage.save(tasks);
        
        String output = Message.TASK_ADDED + "\n"
                + Message.DIVIDER + "\n"
                + "  " + task + "\n"
                + Message.DIVIDER + "\n"
                + Message.showNumberOfTasks(tasks.getLength());
        return output;
    }
}