

public class DeleteCommand extends Command {

    protected int numberToDelete;

    public DeleteCommand(String type, int numberToDelete) {

        super(type);
        this.numberToDelete = numberToDelete;

    }

    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        if (numberToDelete <= 0) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! Please key in value bigger than 0.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");
        }

        if (taskList.getSize() < numberToDelete) {
            throw new DukeException("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! number does not exist in the list.\n" +
                    "    ____________________________________________________________\n" +
                    "\n");
        }

        Task removed = taskList.removeTaskFromList(this.numberToDelete);
        printFormatting();
        System.out.println("      Noted. I've removed this task: ");
        System.out.println("       " + removed);
        System.out.printf("      Now you have %d tasks in the list.\n", taskList.getSize());
        printFormatting();

    }
}
