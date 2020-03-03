package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class UndoCommand extends Command {

    public UndoCommand(){
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String lastMessage = ui.getLastMessage();
        if (lastMessage == null) {
            throw new DukeException("\n No command has been made that can be undone.\n");
        }
        Task mostRecentTask = taskList.getMostRecentTask();
        if (lastMessage.contains("added")) {
            taskList.undoAdd();
        } else if (lastMessage.contains("done")) {
            taskList.undoDone();
        } else if (lastMessage.contains("removed")) {
            int removedIndex = Integer.parseInt(lastMessage.substring(15, 16));
            taskList.undoDelete(removedIndex);
        }
        storage.overwrite(taskList.getTasks());
        return ui.showUndoMessage();
    }
}
