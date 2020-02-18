package duke.command;

import duke.main.TaskList;
import duke.main.Ui;

public class ListCommand extends Command {
    public static boolean run(TaskList taskList) {
        if (taskList.size() == 0) {
            Ui.printNoTasks();
        } else {
            Ui.printTasks(taskList.printTaskList());
        }
        return true;
    }
}
