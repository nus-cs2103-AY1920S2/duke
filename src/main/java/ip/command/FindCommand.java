package ip.command;

import ip.Ui;
import ip.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.displayFoundTasks(tasks.find(keyword));
    }
}
