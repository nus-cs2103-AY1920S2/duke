/**
 * The parent Command class from which the different kinds of commands extends from.
 * For each different kind of command that extends from this class,
 * they will implement the execute method according to their functionality.
 * The boolean isExit is an indicator as to whether the command will cause the program to stop.
 */
public abstract class Command {
    private String command;
    private boolean isExit;

    public Command(String command) {
        this.command = command;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    protected void setExit() {
        this.isExit = true;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public String getCommandString() {
        return this.command;
    }
}
