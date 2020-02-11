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
    }

    public Command parse(String fullCommand, TaskList tasklist) throws CommandNotFoundException, InvalidDukeArgumentException {
        StringTokenizer st = new StringTokenizer(fullCommand);
        String identifier = st.nextToken();
        try {
            CommandIdentifier commandIdentifier = getCommandIdentifier(identifier);
            switch (commandIdentifier) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case DONE:
                int doneIndex = getCommandIndex(st, tasklist);
                return new DoneCommand(doneIndex);
            case DELETE:
                int deleteIndex = getCommandIndex(st, tasklist);
                return new DeleteCommand(deleteIndex);
            case TODO:
                String todoDescription = getTaskDescription(st);
                return new AddCommand(new Todo(todoDescription));
            case DEADLINE:
                String[] deadlineDescriptionAndTime = getTaskDescriptionAndTime(st);
                return new AddCommand(new Deadline(deadlineDescriptionAndTime[0], deadlineDescriptionAndTime[1]));
            case EVENT:
                String[] eventDescriptionAndTime = getTaskDescriptionAndTime(st);
                return new AddCommand(new Event(eventDescriptionAndTime[0], eventDescriptionAndTime[1]));
            default:
                return new ExitCommand();
            }
        } catch (CommandNotFoundException | InvalidDukeArgumentException e) {
            throw e;
        }
    }

    public CommandIdentifier getCommandIdentifier(String identifier) throws CommandNotFoundException {
        if (COMMAND_IDENTIFIERS.get(identifier) == null) {
            throw new CommandNotFoundException("OOPS!!! I don't understand this command. ☹\n");
        }
        return COMMAND_IDENTIFIERS.get(identifier);
    }

    private int getCommandIndex(StringTokenizer st, TaskList tasklist) throws InvalidDukeArgumentException {
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

    private String getTaskDescription(StringTokenizer st) throws InvalidDukeArgumentException {
        String description = "";
        try {
            description = st.nextToken("").trim();
        } catch (NoSuchElementException e) {
            throw new InvalidDukeArgumentException("OOPS!!! The task description cannot be empty. ☹\n");
        }
        return description;
    }

    private String[] getTaskDescriptionAndTime(StringTokenizer st) {
        String description = st.nextToken("/").trim();
        String time = st.nextToken("/").trim().substring(3);
        String[] descriptionAndTime = new String[]{description, time};
        return descriptionAndTime;
    }
}
