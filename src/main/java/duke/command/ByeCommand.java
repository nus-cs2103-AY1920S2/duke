package duke.command;

import duke.main.TaskList;
import duke.main.Ui;

public class ByeCommand extends Command {
    public static boolean run(TaskList taskList) {
        taskList.saveTasks();
        Ui.end();
        return true;
    }
}
