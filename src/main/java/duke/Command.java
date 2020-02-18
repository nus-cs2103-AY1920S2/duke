package duke;

public interface Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit();
}
