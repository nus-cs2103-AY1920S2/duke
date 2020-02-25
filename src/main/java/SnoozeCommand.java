import java.io.InvalidClassException;
import java.util.InvalidPropertiesFormatException;

public class SnoozeCommand extends Command {
    private int index;
    private String time;

    public SnoozeCommand(int index, String time) {
        this.index = index;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task originalTask = tasks.getTask(index);

            try {
                Task snoozedTask = tasks.snoozeTask(index, time);
                ui.showSnoozedTask(snoozedTask);
                storage.save(tasks);
            } catch (InvalidClassException e) {
                ui.showInvalidSnooze(originalTask);
            } catch (InvalidPropertiesFormatException e) {
                ui.showInvalidFormatMessage();
            }
        } catch (IndexOutOfBoundsException e) {
            new InvalidIndexCommand().execute(tasks, ui, storage);
        }
    }
}
