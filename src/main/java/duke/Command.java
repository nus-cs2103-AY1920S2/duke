package duke;

public interface Command {
    public String execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit();
}
