package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Parser handles making sense of input given. It divides the input into 3 main parts: command, description and date.
 */
public class Parser {
    private Command command;
    private String parameter;
    private String description;
    private LocalDate date;

    /**
     * Creates a Parser object.
     *
     * @param input Input given from user.
     * @throws DukeException If the input given is invalid.
     */
    public Parser(String input) throws DukeException {
        this.command = extractCommand(input);

        if (this.command == Command.DONE || this.command == Command.DELETE) {
            this.parameter = getFirstParameter(input);
            try {
                Integer.valueOf(this.parameter);
            } catch (NumberFormatException e) {
                throw new DukeException("Task number is not an integer.");
            }
        } else if (this.command == Command.TODO) {
            this.description = getFirstParameter(input);
        } else if (this.command == Command.DEADLINE || this.command == Command.EVENT) {
            this.description = getFirstParameter(input);
            this.date = getDate(input);
        } else if (this.command == Command.FIND) {
            this.parameter = getFirstParameter(input);
        }
    }

    /**
     * Returns the command from input.
     *
     * @return Command.
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Returns the parameter from input. Parameter is used for "Done" and "Delete" commands
     *
     * @return Parameter from input.
     */
    public String getParameter() {
        return this.parameter;
    }

    /**
     * Returns the description from input. Description is used for Tasks related commands.
     *
     * @return Description from input.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the date from input. Date is used for "Deadline" and "Event" commands.
     *
     * @return Date from input.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Extracts the command given from the input.
     *
     * @param input User input.
     * @return Command.
     * @throws DukeException If user input is invalid.
     */
    public static Command extractCommand(String input) throws DukeException {
        // if command doesnt exist
        String command = input.split(" ")[0].toLowerCase();

        switch (command) {
            case "bye":
                return Command.BYE;
            case "done":
                return Command.DONE;
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "list":
                return Command.LIST;
            case "delete":
                return Command.DELETE;
            case "find":
                return Command.FIND;
            case "archive":
                return Command.ARCHIVE;
            case "help":
                return Command.HELP;
            default:
                throw new DukeException("Invalid Command.");
        }
    }

    /**
     * Extracts the parameter from user input.
     *
     * @param input User input.
     * @return Parameter from user input.
     * @throws DukeException If parameter was not provided.
     */
    public static String getFirstParameter(String input) throws DukeException {
        String description = input.split(" /")[0];
        String[] descArr = description.split(" ");
        if (descArr.length == 1) {
            throw new DukeException("☹ Why did you not provide any description?");
        }
        // remove verb
        String[] cleaned = Arrays.copyOfRange(descArr, 1, descArr.length);
        return String.join(" ", cleaned);
    }

    /**
     * Extract the date from user input.
     *
     * @param input User Input.
     * @return Date from user input.
     * @throws DukeException If date was not provided.
     */
    public static LocalDate getDate(String input) throws DukeException {
        String[] tryGetDate = input.split(" /");
        if (tryGetDate.length == 1) {
            throw new DukeException("Why did you not provide the date?\n Date should be in format DD/MM/YYYY");
        }
        String time = tryGetDate[1];
        String[] timeArr = time.split(" ");
        // remove verb
        String[] cleaned = Arrays.copyOfRange(timeArr, 1, timeArr.length);
        return convertToLocalDate(String.join(" ", cleaned));
    }

    /**
     * Converts date in String format to LocalDate format.
     * Date in String format must be of dd/mm/yyyy.
     * @param input Date in String format.
     * @return Converted LocalDate.
     * @throws DukeException If date given is in wrong format.
     */
    public static LocalDate convertToLocalDate(String input) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(input, formatter);
    }
}
