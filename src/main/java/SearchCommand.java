import java.time.LocalDate;
import java.time.LocalTime;

public class SearchCommand extends Command {

    private LocalDate date;
    private LocalTime time;

    public SearchCommand(LocalDate date) {
        this.date = date;
        this.time = time;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Here are the tasks on " + this.date + ":";
        for (Task t : tasks.getTasks()) {
            if (t instanceof Deadline) {
                if (((Deadline) t).getDate().isEqual(date)) {
                    msg += "\n" + t;
                }
            }
            else if (t instanceof Event) {
                if (((Event) t).getDate().isEqual(date)) {
                    msg += "\n" + t;
                }
            }
        }
        ui.printMsg(msg);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
