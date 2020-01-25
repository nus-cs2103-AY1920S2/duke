package dukebot.command;

import dukebot.DukeException;
import dukebot.Storage;
import dukebot.Ui;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;

public class newTaskCommand extends Command {
    private String[] inpArr;

    public newTaskCommand(String[] inpArr) {
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
