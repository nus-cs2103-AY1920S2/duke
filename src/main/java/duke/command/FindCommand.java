package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TasksList;

import java.util.ArrayList;

/**
 * The handler for the find command
 */
public class FindCommand extends Command {
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all instances of a task with a matching keyword to 'keyword' in this class
     *
     * @param tasksList the tasksList to find from
     * @param ui used to print tasks found
     * @param storage not used
     *
     * */
    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage){
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasksList.tasks){
            if (task.description.contains(keyword)){
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()){
            ui.printNoFoundItems();
        } else {
            ui.printFoundItems(matchingTasks);
        }
    }
}
