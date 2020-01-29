/**
 * List Command allows user to see what is currently in Duke.
 */
public class ListCommand extends Command {

    /**
     * Creates a list command to be executed.
     * @param response full instruction that is given.
     */
    public ListCommand(String response) {

        super(response);

    }

    /**
     * Executes the list command, showing all task that are currently in the hard disk and program.
     * @param tasksStorage storage for the task, deals with storing data to hard disk and retrieving data from hard disk.
     * @param taskList the list where all the tasks is being stored.
     * @param ui ui that is responsible for interaction with the user.
     * @throws DukeException NIL
     */
    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("     " + (i + 1) + ". " + taskList.getTask(i));
        }
    }
}
