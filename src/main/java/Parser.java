import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

    public Parser(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public boolean parse(String userInput) {
        if (userInput.equals("bye")) {
            return false;
        }
        String data;

        String[] inputs = userInput.trim().split(" ", 2);
        switch (inputs[0]) {
        case "list":
            tasks.list();
            break;
        case "done":
        case "delete":
            try {
                int taskNumber = Integer.parseInt(inputs[1].trim()) - 1;
                if (inputs[0].equals("done")) {
                    if (tasks.get(taskNumber).isTaskDone()) {
                        ui.exceptionMessage(new DukeException("☹ OOPS!!! Task is already done!"));
                    }
                    tasks.get(taskNumber).markAsDone();
                    ui.userMessage("Nice! I've marked this task as done:\n    " + tasks.get(taskNumber));
                } else {
                    Task deletedTask = tasks.remove(taskNumber);
                    ui.userMessage("Noted. I've removed this task: \n    " + deletedTask
                            + "\nNow you have " + tasks.size() + " tasks in the list.");
                }
                storage.writeToFile(inputs[0], taskNumber);
            } catch (Exception e) {
                ui.exceptionMessage(new DukeException("☹ OOPS!!! Please provide a task number within range."));
            }
            break;
        case "todo":
            try {
                Task todo = new Todo(inputs[1]);
                tasks.add(todo);
                data = "T | 0 | " + inputs[1] + "\n";
                storage.writeToFile(data);
                ui.userMessage("Got it. I've added this task:\n    "
                        + todo + "\nNow you have " + tasks.size() + " tasks in the list.");
            } catch (IndexOutOfBoundsException e) {
                ui.exceptionMessage(new DukeException("☹ OOPS!!! The description of a todo cannot be empty."));
            }
            break;
        case "deadline":
            try {
                String[] taskDetails = inputs[1].split("/by ");
                if (taskDetails[0].trim().isEmpty()) {
                    throw new IndexOutOfBoundsException();
                }
                try {
                    LocalDate date = LocalDate.parse(taskDetails[1]);
                    Task deadline = new Deadline(taskDetails[0].trim(), date);
                    tasks.add(deadline);
                    data = "D | 0 | " + taskDetails[0].trim() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n";
                    storage.writeToFile(data);
                    ui.userMessage("Got it. I've added this task:\n    " +
                            deadline + "\nNow you have " + tasks.size() + " tasks in the list.");
                } catch (DateTimeParseException e) {
                    ui.exceptionMessage(new DukeException("☹ OOPS!!! Please provide a valid date with the format yyyy-mm-dd."));
                } catch (Exception e) {
                    ui.exceptionMessage(new DukeException("☹ OOPS!!! Please provide a date using '/by ' with the format yyyy-mm-dd."));
                }
            } catch (IndexOutOfBoundsException e) {
                ui.exceptionMessage(new DukeException("☹ OOPS!!! The description of a deadline cannot be empty."));
            }
            break;
        case "event":
            try {
                String[] taskDetails = inputs[1].split("/at ");
                if (taskDetails[0].trim().isEmpty()) {
                    throw new IndexOutOfBoundsException();
                }
                try {
                    LocalDate date = LocalDate.parse(taskDetails[1]);
                    Task event = new Event(taskDetails[0].trim(), date);
                    tasks.add(event);
                    data = "E | 0 | " + taskDetails[0].trim() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n";
                    storage.writeToFile(data);
                    ui.userMessage("Got it. I've added this task:\n    " +
                            event + "\nNow you have " + tasks.size() + " tasks in the list.");
                } catch (DateTimeParseException e) {
                    ui.exceptionMessage(new DukeException("☹ OOPS!!! Please provide a valid date with the format yyyy-mm-dd."));
                } catch (Exception e) {
                    ui.exceptionMessage(new DukeException("☹ OOPS!!! Please provide a date using '/at ' with the format yyyy-mm-dd.."));
                }
            } catch (IndexOutOfBoundsException e) {
                ui.exceptionMessage(new DukeException("☹ OOPS!!! The description of a event cannot be empty."));
            }
            break;
        default:
            ui.exceptionMessage(new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            break;
        }
        return true;
    }
}