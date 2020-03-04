package processor;

import commands.CommandType;
import commands.Commander;
import commands.Command;
import exceptions.DukeException;
import main.Duke;

/**
 * Handles the parsing of user input.
 */
public class Parser {

    DukeProcessor processor;

    public Parser(DukeProcessor processor) {
        this.processor = processor;
    }

    /**
     * Parses the user input and returns the Command he has requested for. Invalid command is returned for an invalid
     * command.
     *
     * @param input User input.
     * @return Command that user has requested for.
     */
    public Command parseInput(String input) throws DukeException {
        String[] inputArgs = input.split(" ", 2);
        Command command;

        switch (inputArgs[0]) {
        case "bye":
            command = Commander.createCommand(CommandType.BYE);
            break;
        case "list":
            command = Commander.createCommand(CommandType.LIST);
            break;
        case "liston":
            command = Commander.createCommand(CommandType.LISTON);
            break;
        case "done":
            checkDoneFormat(input);
            command = Commander.createCommand(CommandType.DONE);
            break;
        case "todo":
            checkTodoFormat(input);
            command = Commander.createCommand(CommandType.TODO);
            break;
        case "deadline":
            checkDeadlineFormat(input);
            command = Commander.createCommand(CommandType.DEADLINE);
            break;
        case "event":
            checkEventFormat(input);
            command = Commander.createCommand(CommandType.EVENT);
            break;
        case "delete":
            checkDeleteFormat(input);
            command = Commander.createCommand(CommandType.DELETE);
            break;
        case "find":
            checkFindFormat(input);
            command = Commander.createCommand(CommandType.FIND);
            break;
        case "stats":
            command = Commander.createCommand(CommandType.STATS);
            break;
        default:
            command = Commander.createCommand(CommandType.INVALID);
        }
        return command;
    }

    private void checkDeadlineFormat(String args) throws DukeException {
        if (!args.contains(" /by ")) {
            throw new DukeException("Your deadline command is incorrect! Please follow the format: deadline <item> "
                    + "/by <time>");
        }

        String[] inputArgs = args.split(" ", 2)[1].split(" /by ");

        if (inputArgs.length < 2) {
            throw new DukeException("Your deadline command is incorrect! Please follow the format: deadline <item> "
                    + "/by <time>");
        }
    }

    private void checkDeleteFormat(String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if (argsArray.length < 2) {
            throw new DukeException("Your 'delete' command is incorrect! Use the following format: delete <number>");
        } else if (Integer.parseInt(argsArray[1]) > processor.getTaskList().size()) {
            throw new DukeException("You've selected a non-existent task to delete! Please try again!");
        } else if (Integer.parseInt(argsArray[1]) < 0) {
            throw new DukeException("You've entered an index below the number of tasks in the list! Please try again!");
        }
    }

    private void checkDoneFormat(String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if (argsArray.length < 2) {
            throw new DukeException("Your 'done' command is incorrect! Use the following format: done <number>");
        } else if (Integer.parseInt(argsArray[1]) > processor.getTaskList().size()) {
            throw new DukeException("You've selected a non-existent task to complete! Please try again!");
        } else if (Integer.parseInt(argsArray[1]) < 0) {
            throw new DukeException("You've entered an index below the number of tasks in the list! Please try again!");
        }
    }

    private void checkEventFormat(String args) throws DukeException {
        String[] inputArgs = args.split(" ", 2)[1].split(" /at ");

        if (inputArgs.length < 2) {
            throw new DukeException("Your 'event' command is incorrect! Please follow the format: event <item> "
                    + "/at <time> to <time>");
        }

        if (!args.contains(" /at ")) {
            throw new DukeException("Your 'event' command is incorrect! Please follow the format: event <item> "
                    + "/at <time> to <time>");
        }
    }

    private void checkFindFormat(String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);

        if (argsArray.length < 2) {
            throw new DukeException("You've entered an invalid format! Please use find <string>.");
        }
    }

    private void checkTodoFormat(String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if (argsArray.length < 2) {
            throw new DukeException("Your 'todo' command is incorrect! Please follow the following format: todo "
                    + "<item>");
        }
    }
}
