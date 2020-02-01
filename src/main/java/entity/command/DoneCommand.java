package entity.command;

import entity.task.Task;
import entity.TaskList;
import handler.Storage;
import handler.Ui;

import java.io.IOException;
import java.util.List;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        if (!tasks.get(index).isDone()) {
            try {
                tasks.get(index).markAsDone(true);
                storage.saveTasksToMemory(tasks);
                ui.confirmDoneTask(index, tasks.get(index).printTask());
            } catch (IOException e) {
                tasks.get(index).markAsDone(false);
                ui.errorSavingChanges();
            }
        } else {
            ui.printCustomMessage("Task is already done!");
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
