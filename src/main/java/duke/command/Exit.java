package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class Exit extends Command {

    public Exit(Task task) {
        super(task);
    }

    public Exit() {
    }

    public String getHelpFormat() {
        return "Type command 'bye' in lower case to kill me :'(";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
