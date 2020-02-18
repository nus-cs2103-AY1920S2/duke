package duke.command;

import duke.exceptions.InvalidArgumentException;
import duke.main.TaskList;
import duke.main.Ui;

public class DeleteCommand extends Command {
    public static boolean run(TaskList taskList, String index) {
        try {
            Ui.taskDeleted(taskList.deleteTask(index), taskList);
        } catch (InvalidArgumentException e) {
            Ui.print(e);
        }
        return true;
    }
}
