public abstract class Command {

    abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
    abstract boolean isExit();

}
