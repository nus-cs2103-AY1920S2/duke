package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exceptions.IncorrectArgumentException;
import duke.exceptions.InvalidCommandException;

/** 
 * Class which deals with making sense of the user command. 
 * Deconstructs a line into a String[] of arguments, with the first item being the cmd and the rest the arg.
 * Afterwards, performs checks for the validity of cmd and arg and throws exceptions if not valid.
 * If no exceptions are thrown, it calls the respective classes to execute the command. 
 */
class Parser {
    private static final boolean SHUTDOWN = true;
    Storage storage;
    Ui ui;
    TaskList tasks;
    
    /**
     * Public constructor which creates a Parser. As it passes valid commands to be executed by the respective
     * classes, a reference to the corresponding classes is passed in during construction.
     * @param storage a reference to the underlying Storage object.
     * @param ui a reference to the underlying Ui object.
     * @param tasks a reference to the TaskList that the current application uses to hold Task entities.
     */
    public Parser(Storage storage, Ui ui, TaskList tasks) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Parses the entire comment line by verifying the command and arguments, and then passes on the command 
     * to the corresponding methods.
     * @param line the entire command line retrieved from Scanner.nextLine()
     * @return true if the last command is a termination command, or 'bye', and false otherwise.
     * @throws IncorrectArgumentException when the wrong number or type of arguments are supplied to the command.
     * @throws InvalidCommandException when a command is entered that does not exist.
     * @throws IOException when the Storage object fails in saving the TaskList to file.
     */
    public boolean parse(String line) throws IncorrectArgumentException, InvalidCommandException, IOException {
        String[] cmd = line.split(" ", 2);
        boolean isBye = false;
        switch (cmd[0].toLowerCase()) {
        case "bye":
            isBye = bye();
            break;
        case "list":
            isBye = list();
            break;
        case "done":
            isBye = done(Integer.parseInt(cmd[1]));
            break;
        case "todo":
            isBye = todo(cmd[1]);
            break;
        case "deadline":
            isBye = deadline(cmd[1]);
            break;
        case "event":
            isBye = event(cmd[1]);
            break;
        case "delete":
            isBye = delete(Integer.parseInt(cmd[1]));
            break;
        default:
            throw new InvalidCommandException("invalid command:\n      " + line + "\n    please try again");
        }
        storage.saveToFile(tasks);
        return isBye;
    }

    private boolean bye() {
        ui.out("Bye. Hope to see you again soon!");
        ui.close();
        return SHUTDOWN;
    }

    private boolean list() {
        ui.out(tasks.list());
        return !SHUTDOWN;
    }

    private boolean done(int i) {
        ui.out(tasks.done(i));
        return !SHUTDOWN;
    }

    private boolean todo(String args) throws IncorrectArgumentException {
        String[] argv = args.split("/");
        if (argv[0].equals("")) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: Task Description");
        } else if (argv.length > 1) {
            throw new IncorrectArgumentException("Too many arguments!");
        }
        ui.out(tasks.addTodo(argv[0]));
        return !SHUTDOWN;
    }

    private boolean deadline(String args) throws IncorrectArgumentException {
        String[] argv = args.split(" /by ");
        if (argv[0].equals("")) {
            throw new IncorrectArgumentException(
                "Oops! Missing required arguments: "
                .concat(argv.length == 1 
                    ? "Task Description, by.." 
                    : "Task Description"));
        } else if (argv.length < 2) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: /by..");
        } else if (argv.length > 2) {
            throw new IncorrectArgumentException("Too many arguments!");
        }

        try {
            String[] textDateTime = argv[1].split(" ");
            LocalDate date = toDate(textDateTime[0]);
            LocalTime time = toTime(textDateTime[1]);
            ui.out(tasks.addDeadline(argv[0], date, time));
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Incorrect Date/Time format!\n     Correct format: yyyy-MM-dd HHmm");
        }

        return !SHUTDOWN;
    }

    private boolean event(String args) throws IncorrectArgumentException {
        String[] argv = args.split(" /at ");
        if (argv[0].equals("")) {
            throw new IncorrectArgumentException(
                "Oops! Missing required arguments: "
                .concat(argv.length == 1 
                    ? "Task Description, at.."
                    : "Task Description"));
        } else if (argv.length < 2) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: /at..");
        } else if (argv.length > 2) {
            throw new IncorrectArgumentException("Too many arguments!");
        }

        try {
            String[] textDateTime = argv[1].split(" ");
            LocalDate date = toDate(textDateTime[0]);
            LocalTime time = toTime(textDateTime[1]);
            ui.out(tasks.addEvent(argv[0], date, time));
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Incorrect Date/Time format!\n     Correct format: yyyy-MM-dd HHmm");
        }
        return !SHUTDOWN;
    }

    private boolean delete(int i) {
        ui.out(tasks.delete(i));
        return !SHUTDOWN;
    }

    /** Accepts a date in the format provided in the default formatter, DateTimeFormatter.ISO_LOCAL_DATE */ 
    private static LocalDate toDate(String s) throws DateTimeParseException {
        return LocalDate.parse(s);
    }
    
    /**
     * Accepts a date according to the following 24H format: HHmm.
     * @param s a string representing the time in 24H format
     * @return a LocalTime object representing the time given.
     * @throws DateTimeParseException when an invalid time is passed.
     */
    private static LocalTime toTime(String s) throws DateTimeParseException {
        int time = Integer.parseInt(s);
        return LocalTime.of(time / 100, time % 100);
    }

}