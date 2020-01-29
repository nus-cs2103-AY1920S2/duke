package command;

import exception.DukeException;
import io.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

public class AddCommand extends Command {

    protected Task.TaskType taskType;
    protected String inputArgs;

    public AddCommand(Task.TaskType type, String args) {
        this.taskType = type;
        this.inputArgs = args;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        DukeException.throwIf(inputArgs.equals(""), String.format("The description of a task cannot be empty!"));
        Task taskToAdd = null;

        switch (taskType) {
        case TASK_TYPE_TODO:
            taskToAdd = new Todo(inputArgs);
            break;
        case TASK_TYPE_EVENT:
            String[] splitEventArgs = inputArgs.split(" /at ");
            DukeException.throwIf(splitEventArgs.length < 2, String.format("Input is missing a '/at' argument!"));
            taskToAdd = new Event(splitEventArgs[0], splitEventArgs[1]);
            break;
        case TASK_TYPE_DEADLINE:
            String[] splitDeadlineArgs = inputArgs.split(" /by ");
            DukeException.throwIf(splitDeadlineArgs.length < 2, String.format("Input is missing a '/by' argument!"));
            taskToAdd = new Deadline(splitDeadlineArgs[0], splitDeadlineArgs[1]);
            break;
        default:
            throw new DukeException("Unsupported task type selected. Please try again.");
        }

        assert (taskToAdd != null);

        taskList.addTask(taskToAdd);
        ui.stylizedPrint(
                "New task added:",
                "\t" + taskToAdd.toString(),
                String.format("You now have %d task(s) in the list.", taskList.size())
        );
    }
}
