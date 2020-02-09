package duke.commands;

import duke.tasks.TaskList;

import java.util.List;

public class FindCommand implements Command {
    private TaskList tasklist;
    private String keyword;

    public FindCommand(TaskList taskList, List<String> details) {
        this.tasklist = taskList;
        this.keyword = details.get(0);
    }

    public String execute() {
        TaskList successList = tasklist.search(keyword);
        return successList.orderedToString();
    }
}
