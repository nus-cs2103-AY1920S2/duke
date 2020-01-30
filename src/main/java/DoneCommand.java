/**
 * A command to mark a task as done.
 */
public class DoneCommand extends Command {
    private int idx;
    public DoneCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.markTaskAsDone(this.idx);
            storage.save(tasks.getTasks());
            String response = "Nice! I've marked this task as done:\n" + t;
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
