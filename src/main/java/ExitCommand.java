/**
 * Exit Command allows the user to exit from the program.
 */
public class ExitCommand extends Command {

    /**
     * Creates an exit command to be executed.
     * @param response full instruction that is given.
     */
    public ExitCommand(String response) {

        super(response);

    }

    /**
     * Executes the exit command, allowing the program to be stopped.
     * @param tasksStorage storage for the task, deals with storing data and retrieving data from hard disk.
     * @param taskList the list where all the tasks is being stored.
     * @param ui ui that is responsible for interaction with the user.
     * @throws DukeException NIL
     */
    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        this.isExit = true;
        System.out.println("     Bye. Hope to see you again soon!");
        tasksStorage.storeToStorage(taskList.getList());

    }
}
