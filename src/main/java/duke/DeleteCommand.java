package duke;

import java.io.IOException;

/**
 * The type Delete command.
 */
public class DeleteCommand implements Command {

    @Override
    public String execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String result = "";

        String[] split = task.split(" ");
        int num = Integer.parseInt(split[1]);
        if(num > taskList.getArraySize() || num <= 0) {
            throw new DukeException(ui.showTaskDoesNotExistMessage());
        }

        result += ui.showTaskDeletedMessage(taskList, num);
        taskList.deleteTask(num);
        storage.newSave(taskList);

        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
