public abstract class Command {
    public abstract boolean execute (Storage storage, TaskList tasks, Ui ui) throws DukeException;
}