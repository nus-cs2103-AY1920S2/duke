package duke.commands;

import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskType;
import duke.utilities.Storage;
import duke.utilities.TaskList;

public class UpdateCommand extends Command {

    int updateStage;
    String updateItem;
    int taskNumber;
    String update;
    Task task;

    public UpdateCommand(int taskNumber) {
        this.updateStage = 1;
        this.taskNumber = taskNumber;
    }

    public UpdateCommand nextUpdateStage(String updateItem, String update) {
        assert updateStage == 1; // to get to the next update stage, current stage must be 1 first
        this.updateStage++;
        this.updateItem = updateItem;
        this.update = update;
        return this;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        switch (this.updateStage) {
            case 1: // updating at stage 1 - user indicates which task to update
                this.task = tasks.getTask(this.taskNumber); // get the task needed to update
                break;
            case 2: // updating at stage 2 - user gives the details of update
                assert this.task != null;
                if (updateItem.equals("description")) {
                    task.changeDescription(this.update);
                } else if (updateItem.equals("date")) {
                    task.changeDate(this.update);
                }
                tasks.updateTask(taskNumber, task); // update the task in taskList
                storage.update(tasks.getTaskList()); // update the storage after updating the taskList
                break;
        }
        return ui.updateMsg(updateStage, task);
    }
}
