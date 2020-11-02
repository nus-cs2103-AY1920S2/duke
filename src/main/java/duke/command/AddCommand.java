package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Note;
import duke.Ui;

/**
 * Represents the Command used to add a new Task.
 */
public class AddCommand extends Command {
    private String taskDescriptor;
    private String taskName;
    private String timePeriod;

    public AddCommand(String descriptor, String taskName) {
        this.taskDescriptor = descriptor;
        this.taskName = taskName;
    }

    /**
     * Constructor used for Events and Deadlines.
     * @param descriptor Refers to type of Task.
     * @param eventName Refers to name of Task.
     * @param timePeriod Refers to time and date of Task.
     */
    public AddCommand(String descriptor, String eventName, String timePeriod) {
        this.taskDescriptor = descriptor;
        this.taskName = eventName;
        this.timePeriod = timePeriod;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String result = "";
        switch (taskDescriptor) {
        case "todo":
            Task newTodo = new Todo(taskName);
            taskList.addTask(newTodo);
            result = ui.showAddTaskMessage(newTodo);
            break;

        case "event":
            Event newEvent = new Event(taskName, timePeriod);
            taskList.addTask(newEvent);
            result = ui.showAddTaskMessage(newEvent);
            break;

        case "deadline":
            Deadline newDeadline = new Deadline(taskName, timePeriod);
            taskList.addTask(newDeadline);
            result = ui.showAddTaskMessage(newDeadline);
            break;

        case "note":
            Note newNote = new Note(taskName);
            taskList.addTask(newNote);
            result = ui.showAddTaskMessage(newNote);
            break;

        default:
            break;
        }
        return result;
    }
}