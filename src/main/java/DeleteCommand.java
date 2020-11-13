import java.util.ArrayList;

/**
 * Represents a command that executes the deletion of a task into the task list.
 */
public class DeleteCommand extends Command {
    protected int[] options;

    public DeleteCommand(int[] options) {
        this.options = options;
    }

    /**
     * Executes the main method of deleting the task into the task list, while the Ui replies the user
     * with the corresponding messages. The updated task list is then written and saved into the storage file.
     * @param tasks Task list that is updated.
     * @param ui Ui to display interaction messages with the user.
     * @param storage Storage where the updated list is saved into.
     * @return The reply by the Ui as a String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int originalSize = tasks.size();
        String string = "";
        ArrayList<Task> deleted = new ArrayList<>();

        for (int i = 0; i < options.length; i++) {
            deleted.add(tasks.get(options[i] - 1));
        }

        for (int i = 0; i < deleted.size(); i++) {
           tasks.deleteTask(deleted.get(i));
        }

        storage.writeFile(tasks);
        return ui.showDeletedTask(deleted, originalSize);
    }

    /**
     * Returns if this command is an ExitCommand.
     * @return The result if this command gives an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
