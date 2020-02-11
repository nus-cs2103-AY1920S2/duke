package duke.commands;

import duke.tasks.TaskList;

import java.util.List;

public class FindTagCommand implements Command {
    private TaskList taskList;
    private String tag;

    public FindTagCommand(TaskList taskList, List<String> details) {
        this.taskList = taskList;
        this.tag = details.get(0);
        System.out.print(details.size());
    }

    public String execute() {
        return taskList.findTag(tag).orderedToString();
    }
}
