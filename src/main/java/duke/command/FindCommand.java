package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TasksList;

import java.util.ArrayList;

public class FindCommand extends Command {
    String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) throws DukeException {
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
