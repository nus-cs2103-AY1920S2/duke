import java.text.ParseException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    protected String command;

    public AddCommand(String command) {
        super(command);
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = null;
        //System.out.println("execute add command");
        if (command.contains("todo")) {
            // add Todo task
            t = createTodo(command);
        } else if (command.contains("deadline")) {
            // add Deadline task
            t = createDeadline(command);
        } else if (command.contains("event")) {
            // add Event task
            t = createEvent(command);
        }

        if (t != null) {
            tasks.addTask(t);
            storage.saveTask(t);
            ui.showLine();
            ui.print("This task has been added successfully:");
            ui.print(t.toString());
            ui.print("Now you have " + tasks.size() + " tasks in the list");
        }
    }

    private Task createEvent(String command) throws DukeException {
        if (command.equals("event")) {
            throw new DukeException("Please enter more details.");
        }
        String taskDescription = command.substring(6); // removes event word
        int slashIdx = taskDescription.indexOf("/");
        if (slashIdx == -1) {
            throw new DukeException("Wrong event format. Please try again.");
        }
        String taskTitle = taskDescription.substring(0, slashIdx).trim();
        String dateTime = taskDescription.substring(slashIdx + 4).trim();

        try {
            return new Event(taskTitle, Event.parseDateTime(dateTime));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter deadline in the following format: YYYY-MM-DD HHMM");
        }
    }

    private Task createDeadline(String command) throws DukeException {
        if (command.equals("deadline")) {
            throw new DukeException("Please enter more details.");
        }
        String taskDescription = command.substring((9)); // removes deadline word
        int slashIdx = taskDescription.indexOf("/");
        if (slashIdx == -1) {
            throw new DukeException("Wrong deadline format. Please try again.");
        }

        String taskTitle = taskDescription.substring(0, slashIdx).trim();
        String deadline = taskDescription.substring(slashIdx + 4).trim();
        
        try {
            return new Deadline(taskTitle, Deadline.parseDate(deadline));
        } catch (ParseException e) {
            throw new DukeException("Please enter deadline in the following format: YYYY-MM-DD");
        }
    }

    private Task createTodo(String command) throws DukeException {
        if (command.equals("todo")) {
            throw new DukeException("Please enter something to do.");
        }
        String taskDescription = command.substring(5); // removes todo word
        return new Todo(taskDescription);
    }
}
