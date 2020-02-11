package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;

public class FindCommand extends Command {

    /**
     * Searches through existing list for matching keyword
     * @param keyword Keyword of tasks to search
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If keyword is not in list of tasks
     */
    public static String execute(String keyword, TaskList tasks, Storage storage) {
        TaskList matchingTasks = tasks.findKeyWord(keyword);
        return matchingTasks.printTaskList();
    }
}


