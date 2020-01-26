import java.io.IOException;

public class TodoCommand extends Command {
    private String arg = null;

    public TodoCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTodoTask = new Todo(arg);
            tasks.addTask(newTodoTask);
            ui.printMessage(String.format("     Got it. I've added this task:\n       %s\n"
                    + "     Now you have %d tasks in the list.\n", newTodoTask,tasks.getSize()));
            storage.saveTasks(tasks.getList());
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
