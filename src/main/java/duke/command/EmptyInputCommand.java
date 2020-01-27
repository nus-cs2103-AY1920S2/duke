package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class EmptyInputCommand extends Command {

    public EmptyInputCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.commandNotFound();
    }
}
