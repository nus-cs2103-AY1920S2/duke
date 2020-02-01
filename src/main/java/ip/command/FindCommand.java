package ip.command;

import ip.Ui;
import ip.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayFoundTasks(tasks.find(keyword));
    }
}
