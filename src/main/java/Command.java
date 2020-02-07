public abstract class Command {
    protected Ui ui;
    protected Storage storage;
    protected TaskList taskList;

    public Command() {
        //this.ui = ui;
        //this.storage = storage;
        //this.taskList = tasklist;
    }

    public abstract void execute(Ui ui, Storage storage, TaskList taskList);
}
