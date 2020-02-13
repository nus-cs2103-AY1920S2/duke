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
    private Scanner sc;

    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            String info;
            int taskNumber;
            switch (command) {
            case TODO:
                info = parser.parseTaskInfo(input);
                Todo todo = createTodo(info);
                tasks.add(todo);
                storage.save(tasks);
                return ui.outputTask(todo, tasks.size());
            case DEADLINE:
                info = parser.parseTaskInfo(input);
                Deadline deadline = createDeadline(info);
                tasks.add(deadline);
                storage.save(tasks);
                return ui.outputTask(deadline, tasks.size());
            case EVENT:
                info = parser.parseTaskInfo(input);
                Event event = createEvent(info);
                tasks.add(event);
                storage.save(tasks);
                return ui.outputTask(event, tasks.size());
            case LIST:
                if (tasks.isEmpty()) {
                    return "There are no tasks now.";
                } else {
                    return tasks.list();
                }
            case FIND:
                String searchTerm = parser.parseSearchTerm(input);
                TaskList matchingTasks = tasks.find(searchTerm);
                if (matchingTasks.isEmpty()) {
                    return "I didn't manage to find any matching tasks in your list :(";
                } else {
                    return "Here are the matching tasks in your list:\n" + matchingTasks.list();
                }
            case DONE:
                taskNumber = parser.parseTaskNumber(input);
                Task completedTask = tasks.complete(taskNumber);
                storage.save(tasks);
                return "Nice! I've marked this task as done:\n  " + completedTask;
            case DELETE:
                taskNumber = parser.parseTaskNumber(input);
                Task deletedTask = tasks.delete(taskNumber);
                storage.save(tasks);
                return "Noted. I've removed this task:\n  " + deletedTask;
            case BYE:
                System.exit(0);
            default:
                throw new DukeException("Hmm... I'm not sure what's happening.");
            }
        } catch (DukeException e) {
            return ui.outputException(e);
        }
    }

    public Duke() {
        storage = Storage.empty();
        tasks = new TaskList();
        ui = new Ui();
        parser = new Parser();
        sc = new Scanner(System.in);
    }

    public Duke(String filepath) {
        storage = new Storage(filepath);
        ui = new Ui();
        parser = new Parser();
        sc = new Scanner(System.in);
        try {
            tasks = storage.load();
        } catch (StorageException e) {
            ui.outputException(e);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    private void run() {
        ui.greet();
        while (true) {
            String input = ui.getInput(sc);
            getResponse(input);
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
