package duke.command;

import duke.main.Constant;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.exception.NoSuchTaskException;
import duke.exception.NotNumberException;
import duke.exception.UnableToSaveException;

public class DoneCommand extends Command {
    public DoneCommand(String[] inputArr) {
        this.inputArr = inputArr;
    }

    /**
     * This method marks the task specified in inputArr as done/completed and saves
     * changes into storage. Ui sends a reply to the user, stating checking done is
     * successful.
     * 
     * @param tasks   Existing Tasklist
     * @param ui      Ui for user interaction
     * @param storage Storage to save tasks in local storage
     * @return reply to user the indicated task has been mark as done
     * @throws UnableToSaveException If unable to save to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) 
        throws UnableToSaveException, NoSuchTaskException, NotNumberException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(inputArr[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NotNumberException();
        }
        if (taskNo > tasks.size()) {
            throw new NoSuchTaskException();
        }
        tasks.setDone(taskNo);
        storage.saveToSave(tasks);
        return ui.reply("Okcan, I mark this task as done:\n  " + Constant.SPACE + tasks.getTask(taskNo));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}