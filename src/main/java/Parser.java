import java.util.Arrays;
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
    public static void handleTasks(TaskList taskList, Ui ui) {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        do {
            try {
                String input = scanner.nextLine();
                String commandType = extractCommandType(input);
                String[] inputSplit = input.split(" ", 2);

                switch (commandType) {
                    case "todo":
                        taskList.addTodo(prepareTodo(inputSplit));
                        break;
                    case "deadline":
                        taskList.addDeadline(prepareDeadline(inputSplit));
                        break;
                    case "event":
                        taskList.addEvent(prepareEvent(inputSplit));
                        break;
                    case "done":
                        taskList.markDone(prepareDoneDelete(inputSplit[1]));
                        break;
                    case "delete":
                        taskList.deleteTask(prepareDoneDelete(inputSplit[1]));
                        break;
                    case "list":
                        ui.listTasks(taskList);
                        break;
                    case "find":
                        taskList.findTask(inputSplit[1]);
                        break;
                    case "bye":
                        ui.exitDuke();
                        loop = false;
                        break;
                    default:
                        throw new InvalidCommandException("");
                    }
            } catch (InvalidCommandException | InvalidTodoException
                    | InvalidDeadlineException | InvalidEventException ex) {
                System.out.println(ex);
            }
        } while (loop);
    }

    /**
     * Processes command line input to obtain the command's type
     * and throws an Exception if the command is invalid.
     *
     * @param input String of the initial command line input.
     * @return String representing the command type.
     * @throws InvalidCommandException DukeException for invalid commands.
     */
    public static String extractCommandType(String input) throws InvalidCommandException{
        String[] inputSplit = input.split(" ", 2);
        boolean isValidCommand = isValidCommand(inputSplit[0]);

        if (!isValidCommand) {
            throw new InvalidCommandException("");
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
    public static String prepareTodo(String[] input) throws InvalidTodoException {
        if (!isValidTodo(input)) {
            throw new InvalidTodoException("");
        } else {
            return input[1];
        }
    }

    /**
     * Processes String array of input to check if it is valid for Deadline task.
     *
     * @param input String array of the command line input after splitting by " ".
     * @return String array of description and date for Deadline task.
     * @throws InvalidDeadlineException DukeException for invalid Deadline task.
     */
    public static String[] prepareDeadline(String[] input) throws InvalidDeadlineException {
        String[] fieldDetails = input[1].split("/", 2);

        if (!isValidDeadline(fieldDetails)) {
            throw new InvalidDeadlineException("");
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
    public static String[] prepareEvent(String[] input) throws InvalidEventException {
        String[] fieldDetails = input[1].split("/", 2);

        if (!isValidEvent(fieldDetails)) {
            throw new InvalidEventException("");
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
    public static int prepareDoneDelete(String input) {
        return Integer.parseInt(input);
    }


    /**
     * Checks if input is of valid command type.
     *
     * @param type String representing command type.
     * @return boolean dictating if command is valid or invalid.
     */
    public static boolean isValidCommand(String type) {
        if (type.equals("todo")
                || type.equals("deadline")
                || type.equals("event")
                || type.equals("list")
                || type.equals("find")
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
    public static boolean isValidTodo(String[] input) {
        if (input.length == 2) {
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
    public static boolean isValidTodo(String[] input) {
        return input.length == 2;
    }

    /**
     * Checks if input array is three elements long and thus a valid Deadline task.
     *
     * @param input String array containing task's type, description, and do by date.
     * @return boolean dictating if array has enough elements for a valid Deadline task.
     */
    public static boolean isValidDeadline(String[] input) {
        return input.length == 2;
    }

    /**
     * Checks if input array is three elements long and thus a valid Event task.
     *
     * @param input String array containing task's type, description, and do at date.
     * @return boolean dictating if array has enough elements for a valid Event task.
     */
    public static boolean isValidEvent(String[] input) {
        return input.length == 2;
    }
}

