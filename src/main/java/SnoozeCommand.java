import java.io.InvalidClassException;

public class SnoozeCommand extends Command {
    private int index;
    private String time;

    public SnoozeCommand(int index, String time) {
        this.index = index;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task originalTask = tasks.getTask(index);

        try {
            Task snoozedTask = tasks.snoozeTask(index, time);
            ui.showSnoozedTask(snoozedTask);
            storage.save(tasks);
        } catch (InvalidClassException e) {
            ui.showInvalidSnooze(originalTask);
        }
    }
}
