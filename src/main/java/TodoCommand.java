/**
 * Adds a to-do to the taskList
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    String taskName;

    /**
     * Creates a TodoCommand
     * @param argumentText supplied by the user
     * @throws DukeException if the argumentText is empty
     */
    TodoCommand(String argumentText) throws DukeException {
        if (argumentText.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            this.taskName = argumentText;
        }
    }

    /**
     * Creates a to-do and adds it to the taskList
     * @return CommandResult with the success message
     */
    @Override
    public CommandResult execute() {
        Task newTodo = new Todo(taskName, false);
        taskList.addToList(newTodo);
        return new CommandResult(getAddSuccessMessage(newTodo));
    }

}
