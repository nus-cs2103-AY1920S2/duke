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

import java.util.ArrayList;
import java.util.List;


public class Parser {
    public static Command parse(String input) throws DukeException {
        List<String> data = processUserInput(input);
        switch(CommandType.valueOf(data.get(0).toUpperCase())) {
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
            default:
                throw new DukeException("Are you sure you are giving the correct command?");
        }
    }

    public static List<String> processUserInput(String userInput) throws DukeException {
        List<String> result = new ArrayList<>();
        if (!userInput.equals(CommandType.BYE.getCommand())) {
            if (userInput.equals(CommandType.LIST.getCommand())) {
                result.add(CommandType.LIST.getCommand());
            } else if (userInput.startsWith(CommandType.DELETE.getCommand())) {
                result.add(CommandType.DELETE.getCommand());
                result.add(userInput.substring(7).strip());
            } else if (userInput.startsWith(CommandType.DONE.getCommand())) {
                result.add(CommandType.DONE.getCommand());
                result.add(userInput.substring(5).strip());
            } else {
                result = addTask(userInput);
            }
        } else {
            result.add(CommandType.BYE.getCommand());
        }
        return result;
    }


    public static List<String> addTask(String data) throws DukeException {
        int indexForSeparator;
        List<String> result = new ArrayList<>();

        if (data.startsWith(CommandType.TODO.getCommand())) {
            String info;
            try {
                if (!(data.charAt(4) == ' ')) {
                    throw new DukeException("Please indicate in this format: todo [description]");
                }
            } catch(StringIndexOutOfBoundsException e) {
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
            if(data.contains(" /by ")) {
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
            } catch(IndexOutOfBoundsException e) {
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
            if(data.contains(" /at ")) {
                try {
                    indexForSeparator = data.indexOf('/');
                    if (indexForSeparator <= 6) {
                        throw new DukeException("Please indicate in this format: event [description] /at [duration].");
                    }
                } catch(IndexOutOfBoundsException e) {
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
            } catch(IndexOutOfBoundsException e) {
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
