package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String taskSearchKey;

    public FindCommand(String taskAction) {
        super();
        this.taskSearchKey = taskAction;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.acknowledgeFound(tasks, this.taskSearchKey);
    }
}
