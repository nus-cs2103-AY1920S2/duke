/**
 * Encapsulates a syntatically valid command input from the user.
 * The `Command` must be implemented by any command supported by Duke.
 * However, the actual command parsing is done in `Parser`, as required in A-MoreOOP increment specification.
 */
interface Command {
    /**
     * Executes the command, mutating `tasks` and writing to the UI as needed.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns if Duke should exit after this command
     * @return boolean, true if the command is an exit command
     */
    public boolean isExit();
}
