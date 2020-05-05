public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    int taskNumberToDelete;

    DeleteCommand(String[] words) {
        this.taskNumberToDelete = Integer.parseInt(words[1]);
    }

    @Override
    public CommandResult execute() {
        Task removedTask = taskList.delete(taskNumberToDelete);
        return new CommandResult(getDeleteSuccessMessage(removedTask));
    }

    public String getDeleteSuccessMessage(Task removedTask) {
        return "Noted! I've removed this task:\n"
                + removedTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }
}
