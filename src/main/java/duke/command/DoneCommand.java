package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TasksList;

public class DoneCommand extends Command{
    public static final String DONE_COMMAND_FORMAT = "The done command format is: done <taskDescription>";
    public static final String TASK_NOT_FOUND = "Task not found";
    String taskName;

    public DoneCommand (String taskName){
        this.taskName = taskName;
    }

    @Override
    public void execute(TasksList tasks, Ui ui, Storage storage) throws DukeException{
        if (taskName == null) {
            throw new DukeException(DONE_COMMAND_FORMAT);
        }

        // Looks for a task with matching the description,  and marks it as done in tasksList
        for (Task task : tasks.tasks) {
            if (task.description.equals(taskName) && !task.isDone) {
                task.isDone = true;

                ui.print("Nice! I've marked this task as done:" + System.lineSeparator() +
                        "\t" + task);
                ui.printLineSeparator();

                storage.saveTasksList(tasks);
                return;
            }
        }

        throw new DukeException(TASK_NOT_FOUND);
    }
}
