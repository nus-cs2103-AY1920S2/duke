/**
 * Edits a task in the taskList
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    int taskNumberToEdit;
    String newName;

    EditCommand(String argumentText) {
        String[] editParts = argumentText.split("/to");
        this.taskNumberToEdit = Integer.parseInt(editParts[0].trim());
        this.newName = editParts[1].trim();
    }

    /**
     * Edit the task at taskNumberToEdit
     * @return CommandResult with success message
     */
    @Override
    public CommandResult execute() {
        Task editedTask = taskList.edit(taskNumberToEdit, newName);
        return new CommandResult(getEditSuccessMessage(editedTask));
    }

    public String getEditSuccessMessage(Task editedTask) {
        return "As it should be! The task has been successfully updated to:\n"
                + editedTask;
    }
}
