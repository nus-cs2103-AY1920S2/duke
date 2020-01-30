import java.io.IOException;

public class DeleteCommand extends Command {
    protected String command;

    DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] commands = command.split(" ");
        Task toBeRemoved = tasks.getTaskList().get(Integer.parseInt(commands[1])-1);
        System.out.println("TASK TO BE REMOVED " + toBeRemoved);
        tasks.taskList.remove(Integer.parseInt(commands[1])-1);
        storage.save(tasks);
        System.out.println(Duke.LINE + "\n" + "Alright, I've removed this task:" + "\n");
        System.out.println(toBeRemoved + "\n");
        System.out.println("You currently have " + tasks.getTaskList().size() + " task(s) in the list.");
    }
}
