package command;
import task.*;
import ui.*;
import storage.*;
import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
    public abstract boolean isExit();
}