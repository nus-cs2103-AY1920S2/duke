package duke.command;

import duke.exceptions.InvalidArgumentException;
import duke.main.TaskList;
import duke.main.Ui;

public class DoneCommand extends Command {
    public static boolean run(TaskList taskList, String index) {
        try {
            Ui.taskMarkedAsDone(taskList.markAsDone(index));
        } catch (InvalidArgumentException e) {
            Ui.print(e);
        }
        return true;
    }
}
