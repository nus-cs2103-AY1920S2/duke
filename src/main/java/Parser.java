import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Parser class to process input.
 */
public class Parser {

    /**
     * Handles various permitted commands by calling relevant methods to process after parsing.
     *
     * @param taskList TaskList that contains ArrayList<Task>.
     * @param ui Ui to print out user interface text.
     */
    public static String handleTasks(String input, TaskList taskList, Ui ui) {
        try {
            String commandType = extractCommandType(input);
            String[] inputSplit = input.split(" ", 2);
            String[] arr = new String[2];

            switch (commandType) {
                case "todo":
                    return taskList.addTodo(prepareTodo(inputSplit));
                case "deadline":
                    arr = prepareDeadline(inputSplit);
                    return taskList.addDeadline(arr[0], parseDate(arr[1]));
                case "event":
                    arr = prepareEvent(inputSplit);
                    return taskList.addEvent(arr[0], parseDate(arr[1]));
                case "done":
                    return taskList.markDone(prepareDoneDelete(inputSplit[1]));
                case "delete":
                    return taskList.deleteTask(prepareDoneDelete(inputSplit[1]));
                case "list":
                    return taskList.listTasks(taskList);
                case "find":
                    return taskList.findTask(inputSplit[1]);
                case "tag":
                    arr = prepareAddTag(inputSplit);
                    return taskList.tagTask(Integer.parseInt(arr[0]), arr[1]);
                case "bye":
                    return ui.exitDuke();
                default:
                    throw new InvalidCommandException("Uhh... You're gonna have to say that again, Red.");
                }
        } catch (InvalidCommandException | InvalidTodoException
                | InvalidDeadlineException | InvalidEventException ex) {
            return (ui.showError(ex.toString()));
        }
    }

    public static LocalDate parseDate(String stringDate) {
        return LocalDate.parse(stringDate);
    }

    public static ArrayList<Tag> parseTag(String tags) {
        List<String> tagDetailsList = new ArrayList<String>();
        tagDetailsList = Arrays.asList(tags.split("|"));

        ArrayList<Tag> tagList = new ArrayList<Tag>();
        tagDetailsList.forEach(detail -> tagList.add(new Tag(detail)));

        return tagList;
    }

    /**
     * Processes command line input to obtain the command's type
     * and throws an Exception if the command is invalid.
     *
     * @param input String of the initial command line input.
     * @return String representing the command type.
     * @throws InvalidCommandException DukeException for invalid commands.
     */
    private static String extractCommandType(String input) throws InvalidCommandException{
        String[] inputSplit = input.split(" ", 2);
        boolean isValidCommand = isValidCommand(inputSplit[0]);

        if (!isValidCommand) {
            throw new InvalidCommandException(
                    "Uhh... You're gonna have to say that again, Red.");
        }
        return inputSplit[0];
    }

    /**
     * Processes String array of input to check if it is valid for Todo task.
     *
     * @param input String array of the command line input after splitting by " ".
     * @return String of description for Todo task.
     * @throws InvalidTodoException DukeException for invalid Todo tasks.
     */
    private static String prepareTodo(String[] input) throws InvalidTodoException {
        if (isValidTodo(input)) {
            return input[1];
        } else {
            throw new InvalidTodoException(
                    "You're usually not that taciturn, Red. Explain things to me.");
        }
    }

    /**
     * Processes String array of input to check if it is valid for Deadline task.
     *
     * @param input String array of the command line input after splitting by " ".
     * @return String array of description and date for Deadline task.
     * @throws InvalidDeadlineException DukeException for invalid Deadline task.
     */
    private static String[] prepareDeadline(String[] input) throws InvalidDeadlineException {
        String[] fieldDetails = input[1].split("/", 2);

        if (!isValidDeadline(fieldDetails)) {
            throw new InvalidDeadlineException(
                    "We're running short of time, so make sure you note it down."
                            + "Give a description of what we gotta do,"
                            + "and the date as /YYYY-MM-DD");
        } else {
            return fieldDetails;
        }
    }

    /**
     * Processes String array of input to check if it is valid for Event task.
     * @param input String array of the command line input after splitting by " ".
     * @return String array of description and date for Event task.
     * @throws InvalidEventException DukeException for invalid Event task.
     */
    private static String[] prepareEvent(String[] input) throws InvalidEventException {
        String[] fieldDetails = input[1].split("/", 2);

        if (!isValidEvent(fieldDetails)) {
            throw new InvalidEventException(
                    "We're running short of time, so make sure you note it down."
                            + "Give a description of what we gotta do,"
                            + "and the date as /YYYY-MM-DD");
        } else {
            return fieldDetails;
        }
    }

    /**
     * Processes String input for purpose of Done or Delete commands
     *
     * @param input String of command line input after splitting by " ".
     * @return int value representing index + 1 of desired item to be marked as done or deleted.
     */
    private static int prepareDoneDelete(String input) {
        return Integer.parseInt(input);
    }

    private static String[] prepareAddTag(String[] input) {
        String[] fieldDetails = input[1].split(" ", 2);
        return fieldDetails;
    }

    /**
     * Checks if input is of valid command type.
     *
     * @param type String representing command type.
     * @return boolean dictating if command is valid or invalid.
     */
    private static boolean isValidCommand(String type) {
        if (type.equals("todo")
                || type.equals("deadline")
                || type.equals("event")
                || type.equals("list")
                || type.equals("find")
                || type.equals("tag")
                || type.equals("done")
                || type.equals("delete")
                || type.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if input array is two elements long and thus a valid Todo task.
     *
     * @param input String array containing task's type and description.
     * @return boolean dictating if array has enough elements for a valid Todo task.
     */
    private static boolean isValidTodo(String[] input) {
        return input.length == 2;
    }

    /**
     * Checks if input array is three elements long and thus a valid Deadline task.
     *
     * @param input String array containing task's type, description, and do by date.
     * @return boolean dictating if array has enough elements for a valid Deadline task.
     */
    private static boolean isValidDeadline(String[] input) {
        return input.length == 2;
    }

    /**
     * Checks if input array is three elements long and thus a valid Event task.
     *
     * @param input String array containing task's type, description, and do at date.
     * @return boolean dictating if array has enough elements for a valid Event task.
     */
    private static boolean isValidEvent(String[] input) {
        return input.length == 2;
    }
}

