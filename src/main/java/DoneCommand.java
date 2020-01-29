

public class DoneCommand extends Command {

    private int numDone;

    public DoneCommand(String response, int numDone) {

        super(response);
        this.numDone = numDone;

    }

    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        Task doneTask = taskList.getTask(this.numDone - 1);
        doneTask.completedTask();
        System.out.println("      Nice! I've marked this task as done: ");
        System.out.println("        " + doneTask);

    }

}
