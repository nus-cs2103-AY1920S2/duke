package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class FindCommand extends Command {
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.displayTasks(taskList.find(keyword));
    }
}
