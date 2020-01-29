

public class ListCommand extends Command {

    public ListCommand(String response) {

        super(response);

    }

    public void execute(Storage tasksStorage, TaskList taskList, Ui ui) throws DukeException {

        printFormatting();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("     " + (i + 1) + ". " + taskList.getTask(i));
        }
        printFormatting();
    }
}
