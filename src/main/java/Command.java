public abstract class Command {
    private String command;
    private boolean isExit;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    protected void setExit() {
        this.isExit = true;
    }
    public boolean isExit() {
        return this.isExit;
    }
}
