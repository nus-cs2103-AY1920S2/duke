package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.IncorrectArgumentException;
import duke.exceptions.InvalidCommandException;
import duke.task.Task;

/**
 * Class which deals with making sense of the user command. Deconstructs a line
 * into a String[] of arguments, with the first item being the cmd and the rest
 * the arg. Afterwards, performs checks for the validity of cmd and arg and
 * throws exceptions if not valid. If no exceptions are thrown, it calls the
 * respective classes to execute the command.
 */
class Parser {
    Storage storage;
    Ui ui;
    TaskList tasks;

    /**
     * Public constructor which creates a Parser. As it passes valid commands to be
     * executed by the respective classes, a reference to the corresponding classes
     * is passed in during construction.
     * 
     * @param storage a reference to the underlying Storage object.
     * @param ui      a reference to the underlying Ui object.
     * @param tasks   a reference to the TaskList that the current application uses
     *                to hold Task entities.
     */
    public Parser(Storage storage, Ui ui, TaskList tasks) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Parses the entire comment line by verifying the command and arguments, and
     * then passes on the command to the corresponding methods.
     * 
     * @param line the entire command line retrieved from Scanner.nextLine()
     * @return A string describing duke's response.
     * @throws IncorrectArgumentException when the wrong number or type of arguments
     *                                    are supplied to the command.
     * @throws InvalidCommandException    when a command is entered that does not
     *                                    exist.
     * @throws IOException                when the Storage object fails in saving
     *                                    the TaskList to file.
     */
    public String[] parse(String line) throws IncorrectArgumentException, InvalidCommandException, IOException {
        String[] cmd = line.split(" ", 2);
        String[] response;
        assert !line.equals("");
        try {
            switch (cmd[0].toLowerCase()) {
            case "list":
                response = parseList();
                break;
            case "done":
                response = parseDone(Integer.parseInt(cmd[1]));
                break;
            case "todo":
                response = parseAddTodo(cmd[1]);
                break;
            case "deadline":
                response = parseAddDeadline(cmd[1]);
                break;
            case "event":
                response = parseAddEvent(cmd[1]);
                break;
            case "delete":
                response = parseDelete(Integer.parseInt(cmd[1]));
                break;
            case "clear":
                response = new String[] { "All Tasks Cleared!" };
                tasks.load(new ArrayList<Task>());
                break;
            case "find":
                response = parseFind(cmd[1]);
                break;
            default:
                throw new InvalidCommandException("invalid command:\n " + line + "\nplease try again");
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new IncorrectArgumentException("missing arguments!");
        }
        return response;
    }

    private String[] parseList() {
        return tasks.list();
    }

    private String[] parseDone(int i) {
        return tasks.done(i);
    }

    private String[] parseAddTodo(String args) throws IncorrectArgumentException {
        String[] argv = args.split("/");
        if (argv[0].equals("")) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: Task Description");
        } else if (argv.length > 1) {
            throw new IncorrectArgumentException("Too many arguments!");
        }
        return tasks.addTodo(argv[0]);
    }

    private String[] parseAddDeadline(String args) throws IncorrectArgumentException {
        String[] argv = args.split(" /by ");
        if (argv[0].equals("")) {
            throw new IncorrectArgumentException(
                "Oops! Missing required arguments: "
                .concat(argv.length == 1 
                    ? "Task Description, by.." 
                    : "Task Description"));
        } else if (argv.length < 2) {
            throw new IncorrectArgumentException("Oops! Missing required arguments: /by yyyy-MM-dd HHmm");
        } else if (argv.length > 2) {
            throw new IncorrectArgumentException("Too many arguments!");
        }

        String[] resp;
        try {
            String[] textDateTime = argv[1].split(" ");
            LocalDate date = toDate(textDateTime[0]);
            LocalTime time = toTime(textDateTime[1]);
            resp = tasks.addDeadline(argv[0], date, time);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Incorrect Date/Time format!\n     Correct format: yyyy-MM-dd HHmm");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncorrectArgumentException("Missing Date/Time!");
        }

        return resp;
    }

    private String[] parseAddEvent(String args) throws IncorrectArgumentException {
        String[] argv = args.split(" /at ");
        if (argv[0].equals("")) {
            throw new IncorrectArgumentException(
                "Oops! Missing required arguments: "
                .concat(argv.length == 1 
                    ? "Task Description, at.."
                    : "Task Description"));
        } else if (argv.length < 2) {
            System.out.println(args);
            throw new IncorrectArgumentException("Oops! Missing required arguments:\n/at yyyy-MM-dd HHmm");
        } else if (argv.length > 2) {
            throw new IncorrectArgumentException("Too many arguments!");
        }

        String[] resp;
        try {
            String[] textDateTime = argv[1].split(" ");
            LocalDate date = toDate(textDateTime[0]);
            LocalTime time = toTime(textDateTime[1]);
            resp = tasks.addEvent(argv[0], date, time);
        } catch (DateTimeParseException e) {
            throw new IncorrectArgumentException("Incorrect Date/Time format!\n     Correct format: yyyy-MM-dd HHmm");
        }
        return resp;
    }

    private String[] parseDelete(int i) {
        if (tasks.numberOfTasks() + 1 > i) {
            return tasks.delete(i);
        } else {
            return new String[]{"No Such Task Found!"};
        }
    }

    private String[] parseFind(String query) {
        return tasks.find(query);
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