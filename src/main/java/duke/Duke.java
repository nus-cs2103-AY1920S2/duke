package duke;

import duke.task.CannotSnoozeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Scanner sc;

    enum Command {
        TODO {
            @Override
            String execute(Duke duke, String input) throws StorageException {
                String arguments = duke.parser.parseArguments(input);
                String description = duke.parser.parseTodoDescription(arguments);
                Todo todo = new Todo(description);
                duke.tasks.add(todo);
                duke.storage.save(duke.tasks);
                return duke.ui.outputTask(todo, duke.tasks.size());
            }
        },

        DEADLINE {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, StorageException {
                String arguments = duke.parser.parseArguments(input);
                String description = duke.parser.parseDeadlineDescription(arguments);
                LocalDate date = duke.parser.parseDeadlineDate(arguments);
                LocalTime time = duke.parser.parseDeadlineTime(arguments);
                Deadline deadline = new Deadline(description, LocalDateTime.of(date, time));
                duke.tasks.add(deadline);
                duke.storage.save(duke.tasks);
                return duke.ui.outputTask(deadline, duke.tasks.size());
            }
        },

        EVENT {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, StorageException {
                String arguments = duke.parser.parseArguments(input);
                String description = duke.parser.parseEventDescription(arguments);
                String time = duke.parser.parseEventTime(arguments);
                Event event = new Event(description, time);
                duke.tasks.add(event);
                duke.storage.save(duke.tasks);
                return duke.ui.outputTask(event, duke.tasks.size());
            }
        },

        LIST {
            @Override
            String execute(Duke duke, String input) {
                if (duke.tasks.isEmpty()) {
                    return "There are no tasks now.";
                } else {
                    return duke.tasks.list();
                }
            }
        },

        FIND {
            @Override
            String execute(Duke duke, String input) {
                String arguments = duke.parser.parseArguments(input);
                String searchTerm = duke.parser.parseFindSearchTerm(arguments);
                TaskList matchingTasks = duke.tasks.find(searchTerm);
                if (matchingTasks.isEmpty()) {
                    return "I didn't manage to find any matching tasks in your list :(";
                } else {
                    return "Here are the matching tasks in your list:\n" + matchingTasks.list();
                }
            }
        },

        SNOOZE {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, TaskNumberOutOfBoundsException, CannotSnoozeException, StorageException {
                String arguments = duke.parser.parseArguments(input);
                int taskNumber = duke.parser.parseSnoozeTaskNumber(arguments);
                TemporalAmount duration = duke.parser.parseSnoozeDuration(arguments);
                Task snoozedTask = duke.tasks.snooze(taskNumber, duration);
                duke.storage.save(duke.tasks);
                return "Noted. Here's the updated task:\n  " + snoozedTask;
            }
        },

        DONE {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, TaskNumberOutOfBoundsException, StorageException {
                String arguments = duke.parser.parseArguments(input);
                int taskNumber = duke.parser.parseDoneTaskNumber(arguments);
                Task completedTask = duke.tasks.complete(taskNumber);
                duke.storage.save(duke.tasks);
                return "Nice! I've marked this task as done:\n  " + completedTask;
            }
        },

        DELETE {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, TaskNumberOutOfBoundsException, StorageException {
                String arguments = duke.parser.parseArguments(input);
                int taskNumber = duke.parser.parseDeleteTaskNumber(arguments);
                Task deletedTask = duke.tasks.delete(taskNumber);
                duke.storage.save(duke.tasks);
                return "Noted. I've removed this task:\n  " + deletedTask;
            }
        },

        BYE {
            @Override
            String execute(Duke duke, String input) {
                System.exit(0);
                return null;
            }
        };

        abstract String execute(Duke duke, String input) throws DukeException;
    }

    public String getResponse(String input) {
        assert input != null;
        try {
            Command command = parser.parseCommand(input);
            parser.checkArguments(command, input);
            return command.execute(this, input);
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
}
