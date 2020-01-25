package dukebot.command;

import dukebot.DukeException;
import dukebot.LineName;
import dukebot.Storage;
import dukebot.Ui;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;

public class deleteCommand extends Command {
    private String[] inpArr;

    public deleteCommand(String[] inpArr) {
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
