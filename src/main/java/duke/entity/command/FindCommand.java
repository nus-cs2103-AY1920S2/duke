package duke.entity.command;

import duke.entity.TaskList;
import duke.entity.task.Task;
import duke.handler.Storage;
import duke.handler.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        List<Task> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTaskName().contains(keyword)) {
                result.add(tasks.get(i));
            }
        }
        ui.listFoundTasks(result);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
