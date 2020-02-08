package duke.command;

import duke.exception.UnableToSaveException;
import duke.main.Constant;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class ClearAllCompleteCommand extends Command {
    public ClearAllCompleteCommand(String[] inputArr) {
        this.inputArr = inputArr;
    }

    /**
     * Returns a string all the tasks with the specified the keyword.
     * 
     * @param tasks   Existing Tasklist
     * @param ui      Ui for user interaction
     * @param storage Storage to save tasks in local storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UnableToSaveException {
        String reply = "I have deleted all the completed tasks as shown below";
        int numbering = 1;
        for (int i = 0; i < tasks.size(); i++) { 
            Task task = tasks.getTask(i);
            if (task.completed) {    
                reply += ("\n  " + Constant.SPACE + numbering++ + ".");
                reply += (task);
                tasks.removeTask(i);
            }
        }
        storage.saveToSave(tasks);
        return ui.reply(reply);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}