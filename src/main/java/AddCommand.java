import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class AddCommand extends Command {

    private final DukeCommand type;
    private final String taskDescription;
    private final LocalDate date;

    public AddCommand(DukeCommand type, String taskDescription, LocalDate date) {
        this.type = type;
        this.taskDescription = taskDescription;
        this.date = date;
    }

    public AddCommand(DukeCommand type, String taskDescription) {
        this(type, taskDescription, null);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task newTask = null;

        switch (type) {
            case todo:
                newTask = new Todo(taskDescription);
                break;
            case deadline:
                newTask = new Deadline(taskDescription, date);
                break;
            case event:
                newTask = new Event(taskDescription, date);
                break;
            default:
                break;
        }

        tasks.add(newTask);
        ui.printAddMessage(tasks, newTask);
    }
}
