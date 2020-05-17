package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class FindCommand implements Command {

    /**
     * Searches through existing list for matching keyword
     * @param keyword Keyword of tasks to search
     * @param tasks List of current tasks
     * @throws DukeException If keyword is not in list of tasks
     */
    public static String execute(String keyword, TaskList tasks) throws DukeException {
        StringBuilder output = new StringBuilder();

        TaskList matchingTasks = tasks.findKeyWord(keyword);
        output.append("Okay! These tasks contain '"
                + keyword + "':\n");
        output.append(matchingTasks.printTaskList());

        return output.toString();
    }
}


