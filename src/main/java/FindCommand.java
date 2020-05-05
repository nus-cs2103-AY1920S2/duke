/**
 * Finds a list of task that matches the user's keyword
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    String keyword;

    /**
     * Creates a FindCommand
     * @param argumentText the text supplied by the user
     * @throws DukeException if the argumentText is empty
     */
    FindCommand(String argumentText) throws DukeException {
        if (argumentText.equals("")) {
            throw new DukeException("The find command must be followed by a keyword to search.");
        } else {
            this.keyword = argumentText;
        }
    }

    /**
     * Find a list of task that matches the keyword
     * @return CommandResult with the list of matching tasks
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(taskList.findTasks(keyword));
    }
}
