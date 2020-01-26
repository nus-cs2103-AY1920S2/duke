package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

public class DeleteCommand extends Command {
    private String[] inpArr;

    public DeleteCommand(String[] inpArr) {
        this.inpArr = inpArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (inpArr.length == 1) {
            ui.sayLine(LineName.DELETE_EMPTY);
        } else {
            try {
                int taskInd = Integer.parseInt(inpArr[1]) - 1;
                Task task = taskList.deleteTask(taskInd);
                if (task == null) {
                    ui.sayLine(LineName.DELETE_OUT_OF_INDEX);
                } else {
                    ui.deleteSuccess(task);
                    storage.saveToFile(taskList);
                }
            } catch (NumberFormatException e) {
                ui.sayLine(LineName.NOT_A_NUMBER);
            } catch (DukeException e) {
                ui.sayLine(e.getErrorLineName());
            }
        }
    }
}
