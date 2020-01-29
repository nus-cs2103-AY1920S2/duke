

public class DeleteCommand extends Command {

    protected int numberToDelete;

    public DeleteCommand(String type, int numberToDelete) {

        super(type);
        this.numberToDelete = numberToDelete;

    }

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
