package duke.commands;

import duke.tasks.TaskList;

public class FindCommand implements Command{
    private TaskList tasklist;
    private String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        this.tasklist = taskList;
        this.keyword = keyword;
    }

    public String execute() {
        TaskList successList = tasklist.search(keyword);
        return successList.orderedToString();
    }
}
