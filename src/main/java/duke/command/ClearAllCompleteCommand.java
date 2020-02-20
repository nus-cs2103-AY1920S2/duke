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
     * Returns a string of completed tasks that are deleted
     * and saves to storage.
     * 
     * @param tasks   Existing Tasklist
     * @param ui      Ui for user interaction
     * @param storage Storage to save tasks in local storage
     * @return reply to user the completed tasks are deleted
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UnableToSaveException {
        String reply = getCompletedTaskAndDelete(tasks);
        storage.saveToSave(tasks);
        return ui.reply(reply);
    }

    private String getCompletedTaskAndDelete(TaskList tasks) {
        String reply = "I have deleted all the completed tasks as shown below";
        int numbering = 1;
        for (int i = 0; i < tasks.size(); i++) { 
            Task task = tasks.getTask(i);
            if (task.completed) {    
                reply += ("\n  " + Constant.SPACE + numbering++ + ".");
                reply += (task);
                tasks.removeTask(i);
                i = i - 1;
            }
        }
        return reply;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}