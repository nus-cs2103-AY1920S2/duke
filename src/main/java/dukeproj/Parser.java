package dukeproj;

import dukeproj.command.*;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.CommandType;
import dukeproj.exception.BadDateException;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;
import dukeproj.exception.InvalidCommandException;
import dukeproj.tasks.Deadline;
import dukeproj.tasks.Event;
import dukeproj.tasks.Task;
import dukeproj.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents a parser with static methods to interpret Strings into actionable data for DukeProject.
 */
public class Parser {
    /** Date formatter when reading (to print from dukeproj.Duke) the date. */
    public static DateTimeFormatter DATE_READ_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM uuuu");
    /** Date formatter when writing (to write into dukeproj.Duke) the date.*/
    public static DateTimeFormatter DATE_WRITE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MM uu");

    /** Primary data structure to store the tasks. */
    private TaskList taskList;
    /** Storage to read/write task list from/into files. */
    private Storage storage;
    /** Data structure to store tasks aligned by dates.*/
    private Calender calender;
    /** Primary I/O object used. */
    private Scanner sc;
    /** User Interface to return user-input*/
    private Ui ui;

    /**
     * Returns the date in LocalDate form parsed from the String entered.
     * If the String format is wrong, will throw BadDateException.
     * @param str String to be parsed into a date.
     * @return date in LocalDate format.
     * @throws BadDateException if format of string is not aligned with write formatter.
     */
    public static LocalDate dateParser(String str) throws BadDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uu");
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new BadDateException("Bad date format");
        }
    }

    /**
     * Returns the command in the form of enum class Command parsed from the String entered.
     * If String does not coincide with any command, will throw InvalidCommandException.
     * @param str String to be parsed into a command.
     * @return command in enum class Command format.
     * @throws InvalidCommandException String entered does not coincide with any command in enum class Command.
     */
    public static CommandType commandParser(String str) throws InvalidCommandException {
        try {
            return CommandType.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidCommandException(str + " is an invalid command");
        }
    }

    public String getCommandResponse(CommandType commandType, String description)
            throws DukeDescriptionException, BadDescriptionException, BadDateException {
        Command command = new ListCommand(); //default command
        switch (commandType) {
        case DONE:
            command = new DoneCommand(description);
            break;
        case TODO:
            //fallthrough
        case EVENT:
            //fallthrough
        case DEADLINE:
            command = new AddCommand(description, commandType);
            break;
        case DELETE:
            command = new DeleteCommand(description);
            break;
        case SEARCH:
            command = new SearchCommand(description);
            break;
        case FIND:
            command = new FindCommand(description);
            break;
        case LIST:
            //fallthrough
        default:
            break;
        }
        return command.execute(ui, taskList, storage, calender);
    }

    /**
     * Execute commands to manipulate task list and calender. The method will create/delete task objects as required
     * and store/remove them from all data structures as needed.
     * @param commandType Command to be executed.
     * @throws DukeDescriptionException If command requires a description and it not given.
     * @throws BadDescriptionException If description provided does not match the format required by command.
     * @throws BadDateException If date provided does not match format required by command.
     */
    public void readCommand(CommandType commandType) throws DukeDescriptionException,
            BadDescriptionException, BadDateException {
        switch (commandType) {
        case LIST:
            System.out.println("Here are all your tasks:");
            taskList.printTask();
            break;
        case DONE:
            try {
                String strDone = sc.nextLine();
                if (strDone.isEmpty()) {
                    throw new DukeDescriptionException("Empty Description");
                }
                int done = Integer.parseInt(strDone.substring(1)); //there must be a space between command and input
                if (done <= 0 || done > taskList.getSize()) {
                    throw new BadDescriptionException("Description for done cannot be "
                            + done);
                }
                taskList.getTask(done - 1).setDone(true);
                System.out.println("Nice! I've marked this task as done: \n" +
                        "  " + taskList.getTask(done - 1));
                storage.writeListIntoFile(taskList.getList());
            } catch (NumberFormatException e) {
                throw new BadDescriptionException("Non-Integer");
            }
            break;
        case TODO:
            String todo = sc.nextLine();
            if (todo.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            Task taskToDo = new Todo(todo.substring(1));
            taskList.addTask(taskToDo);
            System.out.println("I've added this task: \n" +
                    "  " + taskToDo + "\nNow you have " +
                    taskList.getSize() + " tasks in the list." );
            storage.writeListIntoFile(taskList.getList());
            break;
        case EVENT:
            String event = sc.nextLine();
            if (event.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            int eventDate = event.indexOf("/");
            if (eventDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            Task taskEvent = new Event(event.substring(1, eventDate),
                    event.substring(eventDate + 4));
            taskList.addTask(taskEvent);
            calender.addDate(taskEvent);
            System.out.println("I've added this task: \n" +
                    "  " + taskEvent + "\nNow you have " +
                    taskList.getSize() + " tasks in the list." );
            storage.writeListIntoFile(taskList.getList());
            break;
        case DEADLINE:
            String deadline = sc.nextLine();
            if (deadline.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            int dLineDate = deadline.indexOf("/");
            if (dLineDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            Task taskDLine = new Deadline(deadline.substring(1, dLineDate),
                    deadline.substring(dLineDate + 4));
            taskList.addTask(taskDLine);
            calender.addDate(taskDLine);
            System.out.println("I've added this task: \n" +
                    "  " + taskDLine + "\nNow you have " +
                    taskList.getSize() + " tasks in the list." );
            storage.writeListIntoFile(taskList.getList());
            break;
        case DELETE:
            try {
                String strDelete = sc.nextLine();
                if (strDelete.isEmpty()) {
                    throw new DukeDescriptionException("Empty Description");
                }
                int delete = Integer.parseInt(strDelete.substring(1)); //there must be a space between command and input
                if (delete <= 0 || delete > taskList.getSize()) {
                    throw new BadDescriptionException("Description for delete cannot be " + delete);
                }
                Task deletedTask = taskList.getTask(delete - 1);
                taskList.removeTask(delete - 1);
                calender.removeTask(deletedTask, deletedTask.getDate());
                System.out.println("Okay! I have deleted this task:\n" +
                        "  " + deletedTask + "\nNow you have " +
                        taskList.getSize() + " tasks in the list.");
                storage.writeListIntoFile(taskList.getList());
            } catch (NumberFormatException e) {
                throw new BadDescriptionException("Non-Integer");
            }
            break;
        case SEARCH:
            String search = sc.nextLine();
            if (search.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            LocalDate date = Parser.dateParser(search.substring(1));
            System.out.println("Here are the events on " +
                    date.format(Parser.DATE_READ_FORMATTER) + ":");
            System.out.println(calender.searchDate(date));
            break;
        case FIND:
            String find = sc.nextLine();
            if (find.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            TaskList outputList = new TaskList(taskList.find(find.substring(1).split(" ")));
            System.out.println("Here are the matching tasks in your list:");
            outputList.printTask();
            break;
        default:
            break;
        }
    }

    public Parser(Ui ui, TaskList taskList, Calender calender, Storage storage) {

    }

    public Parser(TaskList taskList, Calender calender, Storage storage, Scanner sc) {
        this.taskList = taskList;
        this.calender = calender;
        this.storage = storage;
        this.sc = sc;
    }
}
