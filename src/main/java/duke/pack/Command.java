package duke.pack;

/**
 * Represents a command.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract Boolean isExit();

}
