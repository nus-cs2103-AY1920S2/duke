/**
 * Command is an abstract class, that represents an executable.
 */
public abstract class Command {

    abstract void execute(TaskList tasks, UI ui, Storage storage);

    abstract boolean isExit();

}
