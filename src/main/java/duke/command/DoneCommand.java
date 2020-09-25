package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TasksList;


/**
 * The handler for the done command
 */
public class DoneCommand extends Command{
    public static final String DONE_COMMAND_FORMAT = "The done command format is: done <taskDescription>";
    public static final String TASK_NOT_FOUND = "Task not found";
    String taskName;


    /**
     * Constructs a DoneCommand and sets the name of the task to look for
     *
     * @param taskName The string to search for when looking for what to mark as done
     */
    public DoneCommand (String taskName){
        this.taskName = taskName;
    }

    /**
     * Marks the first instance of a task with a matching description to 'description'
     * isDone field as true.
     *
     * @param tasksList the tasksList to delete from
     * @param ui used to print succesful marking of done of a task from tasksList
     * @param storage for saving the updated tasks list to the save file
     *
     * @exception DukeException when there is a failed find file or was unable to save, this method
     * relays it.
     * */
    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) throws DukeException{
        if (taskName == null) {
            throw new DukeException(DONE_COMMAND_FORMAT);
        }

        // Looks for a task with matching the description,  and marks it as done in tasksList
        for (Task task : tasksList.tasks) {
            if (task.description.equals(taskName) && !task.isDone) {
                task.isDone = true;

                ui.print("Nice! I've marked this task as done:" + System.lineSeparator() +
                        "\t" + task);
                ui.printLineSeparator();

                storage.saveTasksList(tasksList);
                return;
            }
        }

        throw new DukeException(TASK_NOT_FOUND);
    }
}
