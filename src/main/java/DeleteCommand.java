import java.io.IOException;

/**
 * The DeleteCommand inherits from Command and is used to delete tasks in the saved TaskList.
 */
public class DeleteCommand extends Command {
    protected String command;

    /**
     * The constructor for DeleteCommand which takes in a String command that starts with "delete".
     * @param command
     */
    DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * The execute method of DeleteCommand is used to
     * @param tasks This is the saved TaskList in duke.txt.
     * @param ui This is the created Ui in Duke.
     * @param storage This is responsible for loading and saving the updated TaskList.
     * @throws IOException if file cannot be written to or closed.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] commands = command.split(" ");
        Task toBeRemoved = tasks.getTaskList().get(Integer.parseInt(commands[1])-1);
        tasks.taskList.remove(Integer.parseInt(commands[1])-1);
        storage.save(tasks);
        ui.showLine();
        System.out.println("\n" + "Alright, I've removed this task:" + "\n");
        System.out.println(toBeRemoved + "\n");
        System.out.println("You currently have " + tasks.getTaskList().size() + " task(s) in the list.");
    }
}
