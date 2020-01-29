package command;

import exception.DukeException;
import io.Serializer;
import io.Ui;
import task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Serializer.serialize(taskList);
        ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
