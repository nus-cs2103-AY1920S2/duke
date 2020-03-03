package commands;

import storage.Storage;
import exceptions.DukeException;
import tasks.TaskList;
import ui.Ui;

public class DoneCommand extends Command {

    private int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(doneIndex - 1);
        storage.overwrite(taskList.getTasks());
        String response = ui.showTaskDoneMessage(taskList.getTask(doneIndex - 1));
        System.out.println(response);
        return response;
    }
}
