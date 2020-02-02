public abstract class Command {
    public abstract boolean execute (Storage storage, TaskList tasks, Squirtle ui) throws DukeException;
}