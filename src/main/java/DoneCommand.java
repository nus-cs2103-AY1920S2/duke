/**
 *  DoneCommand allows user to mark a task as done.
 */
public class DoneCommand extends Command {

    private int taskID;

    /**
     * Creates a DoneCommand to mark a task as done.
     * @param response full instruction that is to be carried out, including the task number to be considered done.
     * @param taskID Task's id to be marked as done, parsed by Parser class.
     */
    public DoneCommand(String response, int taskID) {

        super(response);
        this.taskID = taskID;

    }

    /**
     * Executes the done command to mark the task as done.
     * @param tasksStorage storage for the task, deals with storing data and retrieving data from hard disk.
     * @param taskList the list where all the tasks is being stored.
     * @param ui ui that is responsible for interaction with the user.
     * @throws DukeException NIL
     */
    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        Task doneTask = taskList.getTask(this.taskID - 1);
        doneTask.completedTask();
        System.out.println("      Nice! I've marked this task as done: ");
        System.out.println("        " + doneTask);

    }

}
