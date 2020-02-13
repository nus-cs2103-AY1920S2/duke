package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class Parser {
    private static final int COMMAND_POSITION = 0;
    private static final int ARGUMENT_POSITION = 1;
    private static final int DESCRIPTION_POSITION = 0;
    private static final int TIME_POSITION = 1;
    private static final int MIN_NUMBER_OF_COMMAND_SECTIONS = 1;
    private static final int MAX_NUMBER_OF_COMMAND_SECTIONS = 2;
    private static final int NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS = 2;
    private static final int NUMBER_OF_EVENT_COMMAND_ARGUMENTS = 2;

    Command parseCommand(String input) throws InvalidCommandException {
        Command command;
        String[] parsedInput = input.strip().split("\\s+", MAX_NUMBER_OF_COMMAND_SECTIONS);
        switch (parsedInput[COMMAND_POSITION]) {
        case "todo":
            if (parsedInput.length < MAX_NUMBER_OF_COMMAND_SECTIONS) {
                throw new InvalidCommandException("Oops! The description of a todo cannot be empty.");
            }
            command = Command.TODO;
            break;
        case "deadline":
            if (parsedInput.length < MAX_NUMBER_OF_COMMAND_SECTIONS) {
                throw new InvalidCommandException("Oops! The description of a deadline cannot be empty.");
            }
            command = Command.DEADLINE;
            break;
        case "event":
            if (parsedInput.length < MAX_NUMBER_OF_COMMAND_SECTIONS) {
                throw new InvalidCommandException("Oops! The description of an event cannot be empty.");
            }
            command = Command.EVENT;
            break;
        case "list":
            if (parsedInput.length > MIN_NUMBER_OF_COMMAND_SECTIONS) {
                throw new InvalidCommandException("Hmm... I don't understand. Try \"list\" instead.");
            }
            command = Command.LIST;
            break;
        case "find":
            if (parsedInput.length < MAX_NUMBER_OF_COMMAND_SECTIONS) {
                throw new InvalidCommandException("Hmm... I'm not sure what you're looking for.");
            }
            command = Command.FIND;
            break;
        case "done":
            if (parsedInput.length < MAX_NUMBER_OF_COMMAND_SECTIONS) {
                throw new InvalidCommandException("Oh no! The task number is missing.");
            }
            command = Command.DONE;
            break;
        case "delete":
            if (parsedInput.length < MAX_NUMBER_OF_COMMAND_SECTIONS) {
                throw new InvalidCommandException("Oh no! The task number is missing.");
            }
            command = Command.DELETE;
            break;
        case "bye":
            if (parsedInput.length > MIN_NUMBER_OF_COMMAND_SECTIONS) {
                throw new InvalidCommandException("Hmm... I don't understand. Try \"bye\" instead.");
            }
            command = Command.BYE;
            break;
        default:
            throw new InvalidCommandException("Hmm... I don't know what that means :(");
        }
        return command;
    }

    String parseTaskInfo(String input) {
        return input.strip().split("\\s+", MAX_NUMBER_OF_COMMAND_SECTIONS)[ARGUMENT_POSITION];
    }

    String parseDeadlineDescription(String info) {
        return info.split("\\s*/by\\s*", NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS)[DESCRIPTION_POSITION];
    }

    LocalDate parseDeadlineDate(String info) throws InvalidCommandException {
        try {
            return LocalDate.parse(info.split("\\s*/by\\s*", NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS)[TIME_POSITION]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oh no! The deadline of the task is missing.");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("I don't understand the deadline. Please provide it yyyy-mm-dd format.");
        }
    }

    String parseEventDescription(String info) {
        return info.split("\\s*/at\\s*", NUMBER_OF_EVENT_COMMAND_ARGUMENTS)[DESCRIPTION_POSITION];
    }

    String parseEventTime(String info) throws InvalidCommandException {
        try {
            return info.split("\\s*/at\\s*", NUMBER_OF_EVENT_COMMAND_ARGUMENTS)[TIME_POSITION];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oh no! The time of the event is missing.");
        }
    }

    String parseSearchTerm(String input) {
        return input.strip().split("\\s+", MAX_NUMBER_OF_COMMAND_SECTIONS)[ARGUMENT_POSITION];
    }

    int parseTaskNumber(String input) throws InvalidCommandException {
        try {
            String[] parsedInput = input.strip().split("\\s+", MAX_NUMBER_OF_COMMAND_SECTIONS);
            int taskNumber = Integer.parseInt(parsedInput[ARGUMENT_POSITION]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I don't know which task you mean :(");
        }
    }
}
