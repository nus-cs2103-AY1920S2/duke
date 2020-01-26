package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.*;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

import java.util.Arrays;

public class NewTodoCommand extends Command {
    private String[] inpArr;

    public NewTodoCommand(String[] inpArr) {
        this.inpArr = inpArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String description;
        Task event;
        description = String.join(" ", Arrays.copyOfRange(inpArr, 1, inpArr.length));
        if (description.length() == 0) {
            ui.sayLine(LineName.TODO_EMPTY);
            return;
        }
        event = new Todo(description);

        try {
            taskList.addTask(event);
            storage.saveToFile(taskList);
            ui.newTask(event);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}