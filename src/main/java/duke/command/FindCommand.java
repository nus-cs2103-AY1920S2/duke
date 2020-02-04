package duke.command;

import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    public static boolean run(TaskList taskList, String... searchTerms) {
        List<Task> tasks = new ArrayList<>();
        for (String searchTerm : searchTerms) {
            for (Task task : taskList.getTasks()) {
                if (task.getDescription().contains(searchTerm)) {
                    if (!tasks.contains(task)) {
                        tasks.add(task);
                    }
                }
            }
        }
        Ui.printFindResults(tasks);
        return true;
    }
}
