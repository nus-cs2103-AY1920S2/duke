/**
 * DeleteCommand deletes an event from the task list
 */

package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];

        if (arr.length > 1) { //check for errors
            //which task to delete
            String secNum = arr[1];
            int deleteTask = Integer.parseInt(secNum) - 1;

            //CALL UI: print output
            ui.printDelete(tasks, deleteTask);
            //CALL STORAGE: write new file
            storage.deleteTask(deleteTask, tasks);
            //CALL TASKLIST: delete task
            tasks.deleteTask(deleteTask);

        } else {
            throw new DukeException("☹ OOPS!!! Please specify which task to delete.");
        }
    }
}