/**
 * DeleteCommand allows task to be removed from the list.
 */
public class DeleteCommand extends Command {

    protected int numberToDelete;

    /**
     * Creates a DeleteCommand object.
     * @param description the full instruction including the command and number to delete.
     * @param numberToDelete the object's ID that is to be deleted, parsed from the Parser class.
     */
    public DeleteCommand(String description, int numberToDelete) {

        super(description);
        this.numberToDelete = numberToDelete;

    }

    /**
     * Executes the delete command to remove the task.
     * @param tasksStorage storage for the task, deals with storing data to hard disk and retrieving data from hard disk.
     * @param taskList the list where all the tasks is being stored.
     * @param ui ui that is responsible for interaction with the user.
     * @throws DukeException if number keyed in is bigger than 0 or number does not exist in the list.
     */
    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        if (numberToDelete <= 0) {
            throw new DukeException("     ☹ OOPS!!! Please key in value bigger than 0.");
        }

        if (taskList.getSize() < numberToDelete) {
            throw new DukeException("     ☹ OOPS!!! number does not exist in the list.");
        }

        Task removed = taskList.removeTaskFromList(this.numberToDelete - 1);
        System.out.println("      Noted. I've removed this task: ");
        System.out.println("       " + removed);
        System.out.printf("      Now you have %d tasks in the list.\n", taskList.getSize());

    }
}
