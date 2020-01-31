package duke.parser;

import duke.DukeException;
import duke.command.CommandType;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.FindCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Parser Class</h1>
 * This class process the data in String format and give back a Command depending on the
 * data processed.
 *
 * @author Eng Xuan En
 */
public class Parser {

    /**
     * Process the data and return a command depending on the data processed.
     *
     * @param input String input to be processed
     * @return Command to be executed
     * @throws DukeException throws when the invalid input occurs
     */
    public static Command parse(String input) throws DukeException {
        List<String> data = processUserInput(input);
        switch (CommandType.valueOf(data.get(0).toUpperCase())) {
        case LIST:
            return new ListCommand(CommandType.LIST);
        case DELETE:
            return new DeleteCommand(CommandType.DELETE, data);
        case DONE:
            return new DoneCommand(CommandType.DONE, data);
        case TODO:
            return new TodoCommand(CommandType.TODO, data);
        case EVENT:
            return new EventCommand(CommandType.EVENT, data);
        case DEADLINE:
            return new DeadlineCommand(CommandType.DEADLINE, data);
        case BYE:
            return new ExitCommand(CommandType.BYE);
        case FIND:
            return new FindCommand(CommandType.FIND, data);
        default:
            throw new DukeException("Are you sure you are giving the correct command?");
        }
    }

    /**
     * Process the user input and store the relevant data into List of Strings and return it back.
     *
     * @param userInput String input to be processed
     * @return List of Strings which relevant data required for the command
     * @throws DukeException throws when invalid String input format
     */
    public static List<String> processUserInput(String userInput) throws DukeException {
        List<String> result = new ArrayList<>();
        if (!userInput.equals(CommandType.BYE.getCommand())) {
            if (userInput.equals(CommandType.LIST.getCommand())) {
                result.add(CommandType.LIST.getCommand());
            } else if (userInput.startsWith(CommandType.DELETE.getCommand())) {
                try {
                    result.add(CommandType.DELETE.getCommand());
                    result.add(userInput.substring(7).strip());
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please input the index of the task to delete!");
                }
            } else if (userInput.startsWith(CommandType.DONE.getCommand())) {
                try {
                    if (userInput.charAt(4) == ' ') {
                        result.add(CommandType.DONE.getCommand());
                        result.add(userInput.substring(5));
                    } else {
                        throw new DukeException("Please input in this format: done [number]");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please input in this format: done [number]");
                }
            } else if (userInput.startsWith(CommandType.FIND.getCommand())) {
                try {
                    if (userInput.charAt(4) == ' ') {
                        String keyword = userInput.substring(5).stripLeading();
                        if (keyword.equals("")) {
                            throw new DukeException("I cannot find the task without any keyword!");
                        }
                        result.add(CommandType.FIND.getCommand());
                        result.add(keyword);
                    } else {
                        throw new DukeException("Please input in this format: find [keyword]");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please input in this format: find [keyword]");
                }
            } else {
                result = addTask(userInput);
            }
        } else {
            result.add(CommandType.BYE.getCommand());
        }
        return result;
    }


    /**
     * Process the string input and store the data into their respective index.
     * Index           Info
     * 0              Command type
     * 1              Description of the task
     * 2              Due date of deadline task / Duration of the event task
     *
     * @param data String input to be processed
     * @return info which is store in List of Strings for the relevant task
     * @throws DukeException throws when input is in invalid String format
     */
    public static List<String> addTask(String data) throws DukeException {
        int indexForSeparator;
        List<String> result = new ArrayList<>();

        if (data.startsWith(CommandType.TODO.getCommand())) {
            String info;
            try {
                if (!(data.charAt(4) == ' ')) {
                    throw new DukeException("Please indicate in this format: todo [description]");
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please indicate in this format: todo [description]");
            }
            info = data.substring(5);
            if (info.strip().equals("")) {
                throw new DukeException("I cannot find the description of Todo!");
            }
            result.add(CommandType.TODO.getCommand());
            result.add(info);
        } else if (data.startsWith(CommandType.DEADLINE.getCommand())) {
            String due;
            if (data.contains(" /by ")) {
                try {
                    indexForSeparator = data.indexOf('/');
                    if (indexForSeparator <= 9) {
                        throw new DukeException("Please indicate in this format: event [description] /at [duration].");
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please indicate in this format: deadline [description] /by [due date].");
                }
            } else {
                throw new DukeException("Please indicate in this format: deadline [description] /by [due date].");
            }

            String description = data.substring(8, indexForSeparator - 1);
            if (!description.startsWith(" ")) {
                throw new DukeException("Please indicate in this format: deadline [description] /by [due date].");
            }
            if (description.strip().equals("")) {
                throw new DukeException("I cannot find description of a deadline!");
            }
            try {
                due = data.substring(indexForSeparator + 4);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("I cannot find the due date of deadline!");
            }
            if (due.strip().equals("")) {
                throw new DukeException("I cannot find the due date of deadline!");
            }
            result.add(CommandType.DEADLINE.getCommand());
            result.add(description.substring(1));
            result.add(due);
        } else if (data.startsWith(CommandType.EVENT.getCommand())) {
            String duration;
            if (data.contains(" /at ")) {
                try {
                    indexForSeparator = data.indexOf('/');
                    if (indexForSeparator <= 6) {
                        throw new DukeException("Please indicate in this format: event [description] /at [duration].");
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please indicate in this format: event [description] /at [duration].");
                }
            } else {
                throw new DukeException("Please indicate in this format: event [description] /at [duration].");
            }

            String description = data.substring(5, indexForSeparator - 1);
            if (!description.startsWith(" ")) {
                throw new DukeException("Please indicate in this format: event [description] /at [duration].");
            }
            if (description.strip().equals("")) {
                throw new DukeException("I cannot find the description of the event!");
            }
            try {
                duration = data.substring(indexForSeparator + 4);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("I cannot find the duration of the event!");
            }
            if (duration.strip().equals("")) {
                throw new DukeException("I cannot find the duration of the event!");
            }
            result.add(CommandType.EVENT.getCommand());
            result.add(description.substring(1));
            result.add(duration);
        } else {
            throw new DukeException("Are you sure you are giving the correct command?");
        }
        return result;
    }
}
