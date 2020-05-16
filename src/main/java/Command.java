public abstract class Command {
    /**
     * Run the command on console.
     * @param tasks Task list
     * @param storage Handle file storage
     * @param ui Show interaction
     */

    public abstract String run(TaskList tasks, Storage storage, UI ui);

    /**
     * Indicate end command.
     * @return true if it is an end command
     */

    public abstract boolean isExit();
}
