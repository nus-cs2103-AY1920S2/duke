package entity.command;

import entity.TaskList;
import handler.Storage;
import handler.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();

}
