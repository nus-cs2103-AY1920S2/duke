package command;

import storage.*;
import task.*;
import ui.*;
import java.io.IOException;

public class DoneCommand extends Command {
    protected int id;

    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.get(id - 1).setCheck();
        ui.displayDoneTask(taskList.get(id - 1));
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}