package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.tasklist.Todo;
import dukebot.ui.LineName;
import dukebot.ui.LineNameWithTask;
import dukebot.ui.Ui;

import java.util.Arrays;

/**
 * Command to create a new todo.
 */
public class NewTodoCommand extends Command {
    private final String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public NewTodoCommand(String[] inpArr) {
        assert inpArr != null;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        TaskList taskList = appStorage.getTaskList();
        String description = String.join(" ", Arrays.copyOfRange(inpArr, 1, inpArr.length));
        if (description.length() == 0) {
            ui.sayLine(LineName.TODO_EMPTY);
            return;
        }
        Task task = new Todo(description);
        taskList.addTask(task);
        ui.sayLineWithTask(LineNameWithTask.NEW_TASK_SUCCESS, task);

        try {
            storage.saveTaskList(taskList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}