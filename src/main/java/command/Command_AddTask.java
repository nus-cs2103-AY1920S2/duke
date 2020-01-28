package command;

import core.Common;
import core.Ui;
import dukexception.DukeException;
import task.Task;

public abstract class Command_AddTask extends Command{

    private Task task;

    public Command_AddTask(Task task){
        this.task=task;
    }

    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.addTask(task));
    }
}
