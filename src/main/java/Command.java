public abstract class Command {
    public boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
