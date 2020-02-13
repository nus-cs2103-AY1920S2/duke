/**
 * Represents a command that marks a task to be done before updating it into the task list.
 */
public class DoneCommand extends Command {
    protected int[] options;

    public DoneCommand(int[] options) {
        this.options = options;
    }

    /**
     * Executes the main method of updating the task's done status into the task list, while the Ui replies the user
     * with the corresponding messages. The updated task list is then written and saved into the storage file.
     * @param tasks Task list that is updated.
     * @param ui Ui to display interaction messages with the user.
     * @param storage Storage where the updated list is saved into.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String string = "";
        for (int i = 0; i < options.length; i++) {
            tasks.markAsDone(options[i]);
            String s = ui.showDoneTask(tasks.arr.get(options[i] - 1)) + "\n";
            storage.writeFile(tasks);
            string += s;
        }
        return string;
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

