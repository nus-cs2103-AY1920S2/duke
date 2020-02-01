package duke.entity.command;

import duke.entity.task.Task;
import duke.entity.TaskList;
import duke.handler.Storage;
import duke.handler.Ui;

import java.io.IOException;
import java.util.List;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        Task toBeDeleted = tasks.remove(index);
        try {
            storage.saveTasksToMemory(tasks);
            ui.confirmDeleteTask(toBeDeleted, taskList);

        } catch (IOException e) {
            tasks.add(index, toBeDeleted);
            ui.errorSavingChanges();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
