package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private Duke() {
        storage = Storage.empty();
        tasks = new TaskList();
        ui = new Ui(new Scanner(System.in));
        parser = new Parser();
    }

    private Duke(String filepath) {
        storage = new Storage(filepath);
        ui = new Ui(new Scanner(System.in));
        parser = new Parser();
        try {
            tasks = storage.load();
        } catch (StorageException e) {
            ui.printException(e);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    private void run() {
        ui.greet();
        while (true) {
            try {
                String input = ui.getInput();
                Command command = parser.parseCommand(input);
                String info;
                int taskNumber;
                switch (command) {
                case TODO:
                    info = parser.parseTaskInfo(input);
                    Todo todo = createTodo(info);
                    tasks.add(todo);
                    ui.printTask(todo, tasks.size());
                    break;
                case DEADLINE:
                    info = parser.parseTaskInfo(input);
                    Deadline deadline = createDeadline(info);
                    tasks.add(deadline);
                    ui.printTask(deadline, tasks.size());
                    break;
                case EVENT:
                    info = parser.parseTaskInfo(input);
                    Event event = createEvent(info);
                    tasks.add(event);
                    ui.printTask(event, tasks.size());
                    break;
                case LIST:
                    if (tasks.size() == 0) {
                        ui.print("There are no tasks now.");
                    } else {
                        ui.print(tasks.list());
                    }
                    break;
                case FIND:
                    String searchTerm = parser.parseSearchTerm(input);
                    TaskList matchingTasks = tasks.find(searchTerm);
                    if (matchingTasks.size() == 0) {
                        ui.print("I didn't manage to find any matching tasks in your list :(");
                    } else {
                        ui.print("Here are the matching tasks in your list:\n" + matchingTasks.list());
                    }
                    break;
                case DONE:
                    taskNumber = parser.parseTaskNumber(input);
                    Task completedTask = tasks.complete(taskNumber);
                    ui.print("Nice! I've marked this task as done:\n  " + completedTask);
                    break;
                case DELETE:
                    taskNumber = parser.parseTaskNumber(input);
                    Task deletedTask = tasks.delete(taskNumber);
                    ui.print("Noted. I've removed this task:\n  " + deletedTask);
                    break;
                case BYE:
                    ui.exit();
                    return;
                default:
                    throw new DukeException("Hmm... I'm not sure what's happening.");
                }
                storage.save(tasks);
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
    }

    private Todo createTodo(String description) {
        return new Todo(description);
    }

    private Deadline createDeadline(String info) throws InvalidCommandException {
        String description = parser.parseDeadlineDescription(info);
        LocalDate date = parser.parseDeadlineDate(info);
        return new Deadline(description, date);
    }

    private Event createEvent(String info) throws InvalidCommandException {
        String description = parser.parseEventDescription(info);
        String time = parser.parseEventTime(info);
        return new Event(description, time);
    }
}
