import java.io.IOException;

/**
 * Represents a deadline task. A <code>Deadline/code> object
 * corresponds to a command represented by the command and a description e.g.,
 * <code>"delete", "2"</code>
 */
public class DeleteCommand extends Command {
    Task deletedTask;
    int deletedTaskNum;

    TaskList tasks;

    public DeleteCommand(String command, String description) {
        super(command, description);
    }

    /**
     * Executes delete command, updates storage and returns output to user.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @return A string containing the output
     * @throws DukeException throws a DukeException if description is not added
     *
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        boolean isDescEmpty = description.equals("");

        if (isDescEmpty) {
            throw new DukeException("delete");
        } else {
            int num = Integer.parseInt(description);

            this.tasks = tasks;
            deletedTask = tasks.getRecord().get(num - 1);
            deletedTaskNum = num - 1;

            tasks.setLastCommand(this);

            String output = tasks.delete(num);

            try {
                storage.write(tasks);
            } catch (IOException e) {
                return ui.showError(e);
            }

            return output;
        }
    }

    @Override
    public String undo() {

       String output = "";

       if (deletedTask instanceof ToDo) {
           output = tasks.addToDo(deletedTask.getDescription(), deletedTaskNum);

       } else if (deletedTask instanceof Deadline) {
           String desc = deletedTask.getDescription();
           String by = ((Deadline) deletedTask).getBy();

           output = tasks.addDeadline(desc, by, deletedTaskNum);
       } else {
           String desc = deletedTask.getDescription();
           String at = ((Event) deletedTask).getAt();

           output = tasks.addEvent(desc, at, deletedTaskNum);
       }

       return output;
    }
}
