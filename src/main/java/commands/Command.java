package commands;

import storage.Storage;
import taskList.TaskList;

public class Command {
    protected TaskList tasks;
    protected int targetIndex;

    /**
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    private void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    protected Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public void execute(TaskList tasks, Storage storage) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    public boolean isExit() {
        return false;
    }

}
