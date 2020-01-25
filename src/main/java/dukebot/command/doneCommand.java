package dukebot.command;

import dukebot.DukeException;
import dukebot.LineName;
import dukebot.Storage;
import dukebot.Ui;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;

public class doneCommand extends Command {
    private String[] inpArr;

    public doneCommand(String[] inpArr) {
        this.inpArr = inpArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (inpArr.length == 1) {
            ui.sayLine(LineName.DONE_EMPTY);
        } else {
            try {
                int taskInd = Integer.parseInt(inpArr[1]) - 1;
                if (taskInd >= taskList.size() || taskInd < 0) {
                    ui.sayLine(LineName.DONE_OUT_OF_INDEX);
                } else {
                    Task doneTask = taskList.getTask(taskInd);
                    if (doneTask.getDone()) {
                        ui.sayLine(LineName.DONE_ALREADY);
                    } else {
                        ui.doneSuccess(doneTask);
                        doneTask.setDone();
                        storage.saveToFile(taskList);
                    }
                }
            } catch (NumberFormatException e) {
                ui.sayLine(LineName.NOT_A_NUMBER);
            } catch (DukeException e) {
                ui.sayLine(e.getErrorLineName());
            }
        }
    }

}
