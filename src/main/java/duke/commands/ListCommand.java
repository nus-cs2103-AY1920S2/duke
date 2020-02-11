/**
 * ListCommand outputs the task list
 */

package duke.commands;

import duke.tasks.Task;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("Here are the tasks in your list:");
        List<Task> list = tasks.getList();
        ui.printList(list);
    }
}