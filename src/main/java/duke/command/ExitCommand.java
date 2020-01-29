package duke.command;

import duke.exception.DukeException;
import duke.io.Serializer;
import duke.io.Ui;
import duke.task.TaskList;

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
