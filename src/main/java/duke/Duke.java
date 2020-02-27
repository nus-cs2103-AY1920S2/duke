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

/**
 * A chatbot that keeps track of various tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Scanner sc;

    /**
     * The commands that {@code Duke} can understand.
     */
    enum Command {
        TODO(false) {
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

        DEADLINE(false) {
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

        EVENT(false) {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, StorageException {
                String arguments = duke.parser.parseArguments(input);
                String description = duke.parser.parseEventDescription(arguments);
                LocalDate date = duke.parser.parseEventDate(arguments);
                LocalTime time = duke.parser.parseEventTime(arguments);
                Event event = new Event(description, LocalDateTime.of(date, time));
                duke.tasks.add(event);
                duke.storage.save(duke.tasks);
                return duke.ui.outputTask(event, duke.tasks.size());
            }
        },

        LIST(false) {
            @Override
            String execute(Duke duke, String input) {
                if (duke.tasks.isEmpty()) {
                    return "There are no tasks now.";
                } else {
                    return duke.tasks.toString();
                }
            }
        },

        FIND(false) {
            @Override
            String execute(Duke duke, String input) {
                String arguments = duke.parser.parseArguments(input);
                String searchTerm = duke.parser.parseFindSearchTerm(arguments);
                TaskList matchingTasks = duke.tasks.find(searchTerm);
                if (matchingTasks.isEmpty()) {
                    return "I didn't manage to find any matching tasks in your list :(";
                } else {
                    return "Here are the matching tasks in your list:\n" + matchingTasks;
                }
            }
        },

        SNOOZE(false) {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, TaskNumberOutOfBoundsException,
                    CannotSnoozeException, StorageException {
                String arguments = duke.parser.parseArguments(input);
                int taskNumber = duke.parser.parseSnoozeTaskNumber(arguments);
                TemporalAmount duration = duke.parser.parseSnoozeDuration(arguments);
                Task snoozedTask = duke.tasks.snooze(taskNumber, duration);
                duke.storage.save(duke.tasks);
                return "Noted. Here's the updated task:\n  " + snoozedTask;
            }
        },

        DONE(false) {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, TaskNumberOutOfBoundsException,
                    StorageException {
                String arguments = duke.parser.parseArguments(input);
                int taskNumber = duke.parser.parseDoneTaskNumber(arguments);
                Task completedTask = duke.tasks.complete(taskNumber);
                duke.storage.save(duke.tasks);
                return "Nice! I've marked this task as done:\n  " + completedTask;
            }
        },

        DELETE(false) {
            @Override
            String execute(Duke duke, String input) throws InvalidCommandException, TaskNumberOutOfBoundsException,
                    StorageException {
                String arguments = duke.parser.parseArguments(input);
                int taskNumber = duke.parser.parseDeleteTaskNumber(arguments);
                Task deletedTask = duke.tasks.delete(taskNumber);
                duke.storage.save(duke.tasks);
                return "Noted. I've removed this task:\n  " + deletedTask;
            }
        },

        CLEAR(false) {
            @Override
            String execute(Duke duke, String input) throws StorageException {
                duke.tasks.clear();
                duke.storage.save(duke.tasks);
                return "Noted. I've removed all the tasks in your list.";
            }
        },

        BYE(true) {
            @Override
            String execute(Duke duke, String input) {
                return duke.ui.sayBye();
            }
        };

        private final boolean isTerminating;

        private Command(boolean isTerminating) {
            this.isTerminating = isTerminating;
        }

        /**
         * Executes the command.
         *
         * @return the output of the command
         */
        abstract String execute(Duke duke, String input) throws DukeException;

        /**
         * Returns {@code true} if this command should terminate {@code Duke}, {@code false} otherwise.
         *
         * @return {@code true} if this command should terminate {@code Duke}, {@code false} otherwise
         */
        boolean isTerminating() {
            return isTerminating;
        }
    }

    /**
     * Constructs a new instance of {@code Duke} with no database.
     */
    public Duke() {
        storage = Storage.empty();
        tasks = new TaskList();
        ui = new Ui();
        parser = new Parser();
        sc = new Scanner(System.in);
    }

    /**
     * Constructs a new instance of {@code Duke} using the file with the specified path as the database.
     *
     * @param path the path to the database
     */
    public Duke(String path) {
        storage = new Storage(path);
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

    /**
     * Constructs a new instance of {@code Duke} using {@code "data/duke.txt"} as the database and runs it.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs {@code Duke}.
     */
    private void run() {
        printWelcomeMessage();
        while (true) {
            try {
                String input = getInput(sc);
                Command command = getCommand(input);
                String response = getResponse(command, input);
                printResponse(response);
                if (command.isTerminating()) {
                    break;
                }
            } catch (DukeException e) {
                printException(e);
            }
        }
    }

    public String getGreeting() {
        return ui.sayHello();
    }

    /**
     * Returns the command indicated in the user input.
     *
     * @param input the user input
     * @return the command indicated in the user input
     */
    public Command getCommand(String input) throws InvalidCommandException {
        assert input != null;
        Command command = parser.parseCommand(input);
        parser.checkArguments(command, input);
        return command;
    }

    /**
     * Returns the response to the user.
     *
     * @param command the command
     * @param input the user input
     * @return the response to the user
     */
    public String getResponse(Command command, String input) throws DukeException {
        assert command != null;
        assert input != null;
        return command.execute(this, input);
    }

    public String getExceptionMessage(DukeException e) {
        return ui.outputException(e);
    }

    private void printWelcomeMessage() {
        ui.print(ui.getLogo());
        ui.print(ui.format(ui.sayHello()));
    }

    private String getInput(Scanner sc) {
        return ui.getInput(sc);
    }

    private void printResponse(String response) {
        ui.print(ui.format(response));
    }

    private void printException(DukeException e) {
        ui.print(ui.format(ui.outputException(e)));
    }
}
