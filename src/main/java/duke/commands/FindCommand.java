package duke.commands;

import duke.tasks.TaskList;

import java.util.List;

public class FindCommand implements Command {
    private TaskList tasklist;
    private String keyword;

    /**
     * Creates a FindCommand that finds tasks with the corresponding keyword.
     * @param taskList List of all the tasks saved by the user.
     * @param details Keyword to search.
     */
    public FindCommand(TaskList taskList, List<String> details) {
        this.tasklist = taskList;
        this.keyword = details.get(0);
    }

    public String execute() {
        TaskList successList = tasklist.search(keyword);
        return successList.orderedToString();
    }
}
