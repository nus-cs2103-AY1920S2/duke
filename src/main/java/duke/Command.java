package duke;

/**
 * Abstract class for Command.
 */
public abstract class Command {
    protected Ui ui;
    protected Storage storage;
    protected TaskList taskList;

    /**
     * Abstract method for executing commands.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in TaskList class
     */
    public abstract void execute(Ui ui, Storage storage, TaskList taskList);
}
