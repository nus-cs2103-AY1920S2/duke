package parser;

import commands.Command;
import commands.AddCommand;
import commands.DoneCommand;
import commands.ListCommand;
import commands.ByeCommand;
import commands.FindCommand;
import commands.DeleteCommand;
import commands.UndoCommand;
import exceptions.DukeException;
import exceptions.InvalidCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;


/**
 * Parser object used to parse user input into format to be displayed or saved in file.
 * Parser object can parse date input by user into LocalDate format.
 */
public class Parser {

    /**
     * Constructor for Parser object.
     */
    public Parser(){

    }

    /**
     * Parses user input for Duke and calls the respective methods for the different commands.
     * @param input User input String.
     * @return Duke's response to user input.
     * @throws DukeException Error messages by Duke should user input have insufficient details.
     * @throws InvalidCommandException Error message by Duke when user has input a invalid command.
     */
    public static Command parse(String input) throws DukeException, InvalidCommandException {
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
        case "list" :
            assert input.equals("list") : "Command is just 'list.";
            return new ListCommand();
        case "done" :
            return new DoneCommand(Integer.parseInt(splitInput[1]));
        case "bye" :
            return new ByeCommand();
        case "delete" :
            return new DeleteCommand(Integer.parseInt(splitInput[1]));
        case "find" :
            return new FindCommand(input.substring(4));
        case "todo" :
            return new AddCommand(parseTodo(input.substring(5)));
        case "deadline" :
            return new AddCommand(parseDeadline(input));
        case "event" :
            return new AddCommand(parseEvent(input));
        case "undo":
            return new UndoCommand();
        default :
            throw new InvalidCommandException("\nMy apologies, I do not recognise the command. :(\n");
        }
    }

    /**
     * Parses duke user input into date using LocalDate.
     * @param unformattedDate unformatted date input by user.
     * @return Formatted date input.
     */
    public static String parseDate(String unformattedDate) {
        LocalDate localDate = LocalDate.parse(unformattedDate);
        int day = localDate.getDayOfMonth();
        Month month = localDate.getMonth();
        int year = localDate.getYear();
        return day + " " + month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " "
                + year + " ";
    }

    protected static Todo parseTodo(String taskDetails) {
        return new Todo(taskDetails);
    }

    /**
     * Parses Deadline Task from file.
     * @param taskDetails Deadline task details read from file.
     * @throws DukeException Duke's error message when there are missing details in the task being read from file.
     */
    private static Deadline parseDeadline(String taskDetails) throws DukeException {

        String[] splitDetails = taskDetails.split("/by ");

        // Check if given description has sufficient details.
        if (splitDetails.length < 2) {
            throw new DukeException("\nMy apologies, "
                    + "deadline description has insufficient details. :(\n");
        }

        // Parse date details into LocalDate format.
        String[] dateTime = splitDetails[1].split(" ");
        assert dateTime.length == 2 : "Details of time are insufficient";
        String taskBy = Parser.parseDate(dateTime[0]);

        parseTime(dateTime[1].trim());

        // Add deadline to list.
        return new Deadline(splitDetails[0].substring(9), taskBy + dateTime[1]);
    }

    private static void parseTime(String timeString) throws DukeException {
        int time = Integer.parseInt(timeString);
        if(time > 2359 || time <0 || time % 100 > 59) {
            throw new DukeException("\n My apologies, please enter a valid time between 0000-2359.\n");
        }
    }

    /**
     * Parses Event Task from file.
     * @param taskDetails Event task details read from file.
     * @throws DukeException Duke's error message when there are missing details in the task being read from file.
     */
    private static Event parseEvent(String taskDetails) throws DukeException {
        String[] splitDetails = taskDetails.split("/at ");

        // Check if given description has sufficient details.
        if (splitDetails.length < 2) {
            throw new DukeException("\n My apologies, "
                    + "event description has insufficient details. :(\n");
        }

        // Parse date details into LocalDate format.
        String[] dateTime = splitDetails[1].split(" ");
        assert dateTime.length == 2 : "Details of time are insufficient";
        String eventAt = Parser.parseDate(dateTime[0]);
        parseTime(dateTime[1].trim());

        // Add deadline to list.
        return new Event(splitDetails[0].substring(6), eventAt + dateTime[1]);
    }

    /**
     * Parses line read from file.
     * @param readFile Line read from file.
     */
    public Task parseLine(String readFile) throws DukeException {
        switch (readFile.charAt(0)) {
        case 'T':
            Todo todo = new Todo(readFile.substring(7));
            if (readFile.charAt(4) == '1') {
                todo.markAsDone();
            }
            return todo;
        case 'D':
            String[] splitDetails = readFile.substring(7).split("/");
            Deadline deadline = new Deadline(splitDetails[0].trim(), splitDetails[1].trim());
            if (readFile.charAt(4) == '1') {
                deadline.markAsDone();
            }
            return deadline;
        case 'E':
            String[] splitEventDetails = readFile.substring(7).split("/");
            Event event = new Event(splitEventDetails[0].trim(), splitEventDetails[1].trim());
            if (readFile.charAt(4) == '1') {
                event.markAsDone();
            }
            return event;
        default:
            assert false : "Invalid task type stored in storage";
            throw new DukeException("\nCorrupt File! Unidentified file type)");
        }
    }
}
