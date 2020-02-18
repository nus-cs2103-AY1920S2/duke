import java.util.Scanner;

/**
 * Parser class. This class manages the input of the user.
 */
public class Parser {
    private Scanner sc;

    /**
     * Constructor. Initialises the Scanner class.
     */
    Parser() {
        this.sc = new Scanner(System.in);
    }

    /**
     * This method reads the input of the users and subsequently does the appropriate actions via the
     * methods in the TaskList class.
     *
     * @param fullCommand This is the full input from the user.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command command = null;
        String[] commandSplit = fullCommand.split(" ", 2);
        String rest;
        String action = commandSplit[0];
        switch (action.toLowerCase()) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "todo":
            rest = commandSplit[1];
            if (rest.isBlank()) {
                throw new DukeException("Sorry! Description cannot be empty");
            } else {
                command = new AddCommand(new ToDos(rest.trim()));
            }
            break;
        case "event":
            rest = commandSplit[1];
            if (rest.isBlank()) {
                throw new DukeException("Sorry! Description cannot be empty");
            } else {
                String[] restArr = rest.split("/by");
                if (restArr.length == 1) {
                    throw new DukeException("Sorry! Please enter a date.");
                } else {
                    command = new AddCommand(new Event(restArr[0].trim(), restArr[1].trim()));
                }
            }
            break;
        case "deadline":
            rest = commandSplit[1];
            if (rest.isBlank()) {
                throw new DukeException("Sorry! Description cannot be empty");
            } else {
                String[] restArr = rest.split("/by");
                if (restArr.length == 1) {
                    throw new DukeException("Sorry! Please enter a date.");
                } else {
                    command = new AddCommand(new Deadline(restArr[0].trim(), restArr[1].trim()));
                }
            }
            break;
        case "done":
            if (commandSplit.length < 2) {
                throw new DukeException("Sorry! Please enter a task number");
            }
            rest = commandSplit[1];
            if (rest.isBlank()) {
                throw new DukeException("Sorry! Please enter a task number");
            } else {
                int taskNum = Integer.parseInt(rest.trim());
                command = new DoneCommand(taskNum);
            }
            break;
        case "delete":
            if (commandSplit.length < 2) {
                throw new DukeException("Sorry! Please enter a task number");
            }
            rest = commandSplit[1];
            if (rest.isBlank()) {
                throw new DukeException("Sorry! Please enter a task number");
            } else {
                int taskNum = Integer.parseInt(rest.trim());
                command = new DeleteCommand(taskNum);
            }
            break;
        case "find":
            if (commandSplit.length < 2) {
                throw new DukeException("Sorry! Please enter a keyword");
            }
            rest = commandSplit[1];
            if (rest.isBlank()) {
                throw new DukeException("Sorry! Please enter a keyword");
            } else {
                command = new FindCommand(rest);
            }
            break;
        case "help":
            command = new HelpCommand();
            break;
        default:
            throw new DukeException("Sorry! Please enter a valid command.\n"
                    + "Type 'help' to list available commands");
        }
        return command;
    }
}