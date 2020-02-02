package commands;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class FindCommand extends Command {

    private String str;

    public FindCommand(String str) {
        this.str = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Here are the matching tasks in your list:";
        int counter = 0;
        for (Task t : tasks.getTasks()) {
            if (t.getDescription().contains(this.str)) {
                counter++;
                msg += "\n" + counter + ". " + t;
            }
        }
        ui.printMsg(msg);
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
