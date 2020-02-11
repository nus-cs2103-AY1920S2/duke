/**
 * FindCommand finds the corresponding tasks in the task list
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

public class FindCommand extends Command {

    public FindCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("Here are the matching tasks in your list:");
        List<Task> matchList = new ArrayList<Task>();
        String arr[] = command.split(" ", 2);
        String word = arr[1];

        List<Task> list = tasks.getList();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(word)) {
                matchList.add(task);
            }
        }
        ui.printList(matchList);
    }
}