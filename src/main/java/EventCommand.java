import java.io.IOException;

public class EventCommand extends Command {
    private String[] args = null;

    public EventCommand(String[] args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (args.length < 2) {
                throw new InvalidCommandException("     ☹ OOPS!!! The date of a "
                        + "event cannot be empty.");
            }
            Task newEventTask = new Event(args[0], args[1]);
            tasks.addTask(newEventTask);
            ui.printMessage(String.format("     Got it. I've added this task:\n       %s\n"
                    + "     Now you have %d tasks in the list.\n", newEventTask,tasks.getSize()));
            storage.saveTasks(tasks.getList());
        } catch (InvalidCommandException e) {
            ui.printException(e);
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
