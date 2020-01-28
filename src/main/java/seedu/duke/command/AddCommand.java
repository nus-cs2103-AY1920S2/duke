package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.exception.DukeIOException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.time.LocalDate;

public class AddCommand extends Command {

    private final Command.Type type;
    private final String taskDescription;
    private final LocalDate date;

    public AddCommand(Command.Type type, String taskDescription, LocalDate date) {
        this.type = type;
        this.taskDescription = taskDescription;
        this.date = date;
    }

    public AddCommand(Command.Type type, String taskDescription) {
        this(type, taskDescription, null);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIOException {
        Task newTask = null;

        switch (type) {
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
