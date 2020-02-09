package duke;

import java.io.IOException;

/**
 * The type Delete command.
 */
public class DeleteCommand implements Command {

    @Override
    public void execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String[] split = task.split(" ");
        int num = Integer.parseInt(split[1]);
        if(num > taskList.getArraySize() || num <= 0) {
            throw new DukeException(ui.showTaskDoesNotExistMessage());
        }
        ui.showTaskDeletedMessage(taskList, num);
        taskList.deleteTask(num);

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
