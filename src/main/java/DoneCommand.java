public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    int taskNumberToComplete;

    DoneCommand(String[] words) {
        this.taskNumberToComplete = Integer.parseInt(words[1]);
    }

    /**
     * complete the task at taskNumberToComplete
     * @return CommandResult with success message
     */
    @Override
    public CommandResult execute() {
        Task completedTask = taskList.markAsDone(taskNumberToComplete);
        return new CommandResult(getDoneSuccessMessage(completedTask));
    }

    public String getDoneSuccessMessage(Task completedTask) {
        return "Marvelous! I've marked this task as done:\n"
                + completedTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }
}
