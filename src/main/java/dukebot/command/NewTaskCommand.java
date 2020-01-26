package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.ui.Ui;

public class NewTaskCommand extends Command {
    private String[] inpArr;

    public NewTaskCommand(String[] inpArr) {
        this.inpArr = inpArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task newTask = Task.makeTask(inpArr);
            taskList.addTask(newTask);
            storage.saveToFile(taskList);
            ui.newTask(newTask);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}
