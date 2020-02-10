package duke;

import java.io.IOException;

/**
 * The type Done command.
 */
public class DoneCommand implements Command {
    @Override
    public String execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String[] split = task.split(" ");
        int num = Integer.parseInt(split[1]);
        Task toComplete = taskList.getTask(num);
        toComplete.setCompleted(true);
        storage.newSave(taskList);

        return ui.showTaskDoneMessage(toComplete, num);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
