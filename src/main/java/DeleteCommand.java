import java.util.List;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    int taskNumber;

    DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public CommandResult execute() {
        int index = taskNumber - 1;
        Task removedTask = taskList.delete(index);
        return new CommandResult(getDeleteSuccessMessage(removedTask), taskList);
    }

    public String getDeleteSuccessMessage(Task removedTask) {
        return "Noted. I've removed this task:\n"
                + removedTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }
}
