package ip.command;

import ip.Ui;
import ip.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(){
        this.isExit = true;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayGoodbye();
    }
}
