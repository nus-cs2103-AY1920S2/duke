import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class GetCommand extends Command {
    private LocalDate date;

    public GetCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if ((task instanceof Deadline && ((Deadline)task).getDate().equals(date)) || 
                    (task instanceof Event && ((Event)task).getDate().equals(date))) {
                filteredTasks.add(task);
            }
        }
        ui.showGetTasks(filteredTasks, date);
    }
}
