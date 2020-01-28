package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.exception.DukeIoException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.time.LocalDate;

public class AddCommand extends Command {

    private final Command.Type taskType;
    private final String taskDescription;
    private final LocalDate date;

    /**
     * Constructor for AddCommand that return new Task of specified type when execute.
     *
     * @param taskType Type of task to create.
     * @param taskDescription Description of task.
     * @param date Date of task.
     */
    public AddCommand(Command.Type taskType, String taskDescription, LocalDate date) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.date = date;
    }

    /**
     * Constructor for AddCommand return new To_do task when execute.
     *
     * @param taskType Type of task to create.
     * @param taskDescription Description of task.
     */
    public AddCommand(Command.Type taskType, String taskDescription) {
        // TODO: catch exception when type is deadline or event but date not given
        this(taskType, taskDescription, null);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException {
        Task newTask = null;

        switch (taskType) {
        case todo:
            newTask = new Todo(taskDescription);
            break;
        case deadline:
            newTask = new Deadline(taskDescription, date);
            break;
        case event:
            newTask = new Event(taskDescription, date);
            break;
        default:
            break;
        }

        tasks.add(newTask);
        storage.save(tasks);
        ui.printAddMessage(tasks, newTask);
    }
}
