/**
 * Parent class for all commands.
 */
public abstract class Command {

    protected String response;
    protected boolean isExit = false;

    /**
     * Creates a command.
     * @param response instruction to be executed.
     */
    public Command(String response) {

        this.response = response;

    }

    /**
     * Abstract execute for the child classes to implement.
     * @param tasksStorage storage for the task, deals with storing data to hard disk and retrieving data from hard disk.
     * @param taskList the list where all the tasks is being stored.
     * @param ui ui that is responsible for interaction with the user.
     * @throws DukeException if it is not a valid instruction.
     */
    public abstract void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException;


    /**
     * Return whether the program is to be stopped.
     * @return true if the program is to be exited, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
