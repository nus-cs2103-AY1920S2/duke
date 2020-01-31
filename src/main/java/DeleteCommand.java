/**
 * A command to delete a task from Duke.
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.removeTask(this.idx);
            storage.save(tasks.getTasks());
            String response = "Noted. I've removed this task:\n" + t + '\n';
            response += "Now you have " + tasks.getNumTasks() + " tasks in the list.";
            ui.showMsg(response);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
