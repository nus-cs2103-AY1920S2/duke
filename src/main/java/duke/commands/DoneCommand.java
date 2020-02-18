/**
 * DoneCommand updates an event in the task from undone to done
 */

package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;

import java.io.IOException;

public class DoneCommand extends Command {

    public DoneCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];

        if (arr.length > 1) { //check for errors
            //which task done
            String secNum = arr[1];
            int doneTask = Integer.parseInt(secNum) - 1;

            assert doneTask > tasks.getList().size(): "number not valid";

            //CALL TASKLIST: set that task to done
            tasks.setDone(doneTask);
            //CALL STORAGE: write new file
            storage.updateTask(doneTask, tasks);
            //CALL UI: print output
            return ui.printDone(tasks, doneTask);
        } else {
            throw new DukeException("☹ OOPS!!! Please specify which task you've done.");
        }
    }
}