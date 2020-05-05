public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * list all the tasks in taskList
     * @return CommandResult with a String representing the taskList
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(taskList.getAllTasks());
    }
}
