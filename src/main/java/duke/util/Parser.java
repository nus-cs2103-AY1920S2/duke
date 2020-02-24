package duke.util;

import duke.command.*;
import duke.exception.CommandNotFoundException;
import duke.exception.InvalidDukeArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Handles the logic of parsing the user input.
 */
public class Parser {
    private static HashMap<String, CommandIdentifier> COMMAND_IDENTIFIERS = new HashMap<>();

    static {
        COMMAND_IDENTIFIERS.put("bye", CommandIdentifier.BYE);
        COMMAND_IDENTIFIERS.put("list", CommandIdentifier.LIST);
        COMMAND_IDENTIFIERS.put("done", CommandIdentifier.DONE);
        COMMAND_IDENTIFIERS.put("todo", CommandIdentifier.TODO);
        COMMAND_IDENTIFIERS.put("deadline", CommandIdentifier.DEADLINE);
        COMMAND_IDENTIFIERS.put("event", CommandIdentifier.EVENT);
        COMMAND_IDENTIFIERS.put("delete", CommandIdentifier.DELETE);
        COMMAND_IDENTIFIERS.put("find", CommandIdentifier.FIND);
        COMMAND_IDENTIFIERS.put("sort", CommandIdentifier.SORT);
    }

    /**
     * Parses the user input and returns the corresponding command.
     * @param fullCommand User input which is in String format.
     * @param tasklist The current list of tasks.
     * @return The command corresponds to the user input.
     * @throws CommandNotFoundException If the command is not found.
     * @throws InvalidDukeArgumentException If the argument is invalid, either not a number or out of range.
     */
    public Command parse(String fullCommand, TaskList tasklist)
            throws CommandNotFoundException, InvalidDukeArgumentException {
        StringTokenizer st = new StringTokenizer(fullCommand);
        String identifier = st.nextToken();
        try {
            CommandIdentifier commandIdentifier = getCommandIdentifier(identifier);
            switch (commandIdentifier) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case SORT:
                return new SortCommand();
            case DONE:
                int doneIndex = getCommandIndex(st, tasklist);
                return new DoneCommand(doneIndex);
            case DELETE:
                int deleteIndex = getCommandIndex(st, tasklist);
                return new DeleteCommand(deleteIndex);
            case TODO:
                String todoDescription = getNextCommandArgument(st);
                return new AddCommand(new Todo(todoDescription));
            case DEADLINE:
                String[] deadlineDescriptionAndTime = getTaskDescriptionAndTime(st);
                return new AddCommand(new Deadline(deadlineDescriptionAndTime[0], deadlineDescriptionAndTime[1]));
            case EVENT:
                String[] eventDescriptionAndTime = getTaskDescriptionAndTime(st);
                return new AddCommand(new Event(eventDescriptionAndTime[0], eventDescriptionAndTime[1]));
            case FIND:
                String keyword = getNextCommandArgument(st);
                return new FindCommand(keyword);
            default:
                assert false : commandIdentifier;
                return new ExitCommand();
            }
        } catch (CommandNotFoundException | InvalidDukeArgumentException e) {
            throw e;
        }
    }

    /**
     * Gets the command identifier from the user input.
     * @param identifier The String that acts as a key to find the command identifier.
     * @return The corresponding command identifier.
     * @throws CommandNotFoundException When the command identifier is not found.
     */
    public CommandIdentifier getCommandIdentifier(String identifier) throws CommandNotFoundException {
        if (COMMAND_IDENTIFIERS.get(identifier) == null) {
            throw new CommandNotFoundException("OOPS!!! I don't understand this command. ☹\n");
        }
        return COMMAND_IDENTIFIERS.get(identifier);
    }

    public int getCommandIndex(StringTokenizer st, TaskList tasklist) throws InvalidDukeArgumentException {
        int index = 0;
        try {
            index = Integer.parseInt(st.nextToken());
        } catch (NumberFormatException e) {
            throw new InvalidDukeArgumentException("OOPS!!! Please enter a valid number.\n");
        }
        if (index <= 0 || index > tasklist.getTasks().size()) {
            throw new InvalidDukeArgumentException("OOPS!!! Please enter a number in the list.\n");
        }
        return index;
    }

    public String getNextCommandArgument(StringTokenizer st) throws InvalidDukeArgumentException {
        String description = "";
        try {
            description = st.nextToken("").trim();
        } catch (NoSuchElementException e) {
            throw new InvalidDukeArgumentException("OOPS!!! The task description cannot be empty. ☹\n");
        }
        return description;
    }

    public String[] getTaskDescriptionAndTime(StringTokenizer st) {
        String description = st.nextToken("/").trim();
        String time = st.nextToken("/").trim().substring(3);
        String[] descriptionAndTime = new String[]{description, time};
        return descriptionAndTime;
    }
}
