package ip.command;

import ip.Ui;
import ip.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.displayGoodbye();
    }
}
