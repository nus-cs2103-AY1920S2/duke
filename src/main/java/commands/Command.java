package commands;

import storage.Storage;
import taskList.TaskList;

/**
 * Represents the parent class of a more specific command.
 */
public class Command {
    protected TaskList tasks;
    protected int targetIndex;

    /**
     * @param targetIndex last visible listing index of the target person.
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
     * Executes the command.
     *
     * @param tasks the tasks that is currently being referenced in Duke.
     * @param storage the storage tool for loading and saving the state of the list after every command.
     */
    public void execute(TaskList tasks, Storage storage) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    /**
     * Checks if this is an exit command.
     *
     * @return false since it is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

}
