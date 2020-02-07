package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class Parser {
    Command parseCommand(String input) throws InvalidCommandException {
        Command command;
        String[] parsedInput = input.strip().split("\\s+", 2);
        switch (parsedInput[0]) {
        case "todo":
            if (parsedInput.length < 2) {
                throw new InvalidCommandException("Oops! The description of a todo cannot be empty.");
            }
            command = Command.TODO;
            break;
        case "deadline":
            if (parsedInput.length < 2) {
                throw new InvalidCommandException("Oops! The description of a deadline cannot be empty.");
            }
            command = Command.DEADLINE;
            break;
        case "event":
            if (parsedInput.length < 2) {
                throw new InvalidCommandException("Oops! The description of an event cannot be empty.");
            }
            command = Command.EVENT;
            break;
        case "list":
            if (parsedInput.length > 1) {
                throw new InvalidCommandException("Hmm... I don't understand. Try \"list\" instead.");
            }
            command = Command.LIST;
            break;
        case "find":
            if (parsedInput.length < 2) {
                throw new InvalidCommandException("Hmm... I'm not sure what you're looking for.");
            }
            command = Command.FIND;
            break;
        case "done":
            if (parsedInput.length < 2) {
                throw new InvalidCommandException("Oh no! The task number is missing.");
            }
            command = Command.DONE;
            break;
        case "delete":
            if (parsedInput.length < 2) {
                throw new InvalidCommandException("Oh no! The task number is missing.");
            }
            command = Command.DELETE;
            break;
        case "bye":
            if (parsedInput.length > 1) {
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
        return input.strip().split("\\s+", 2)[1];
    }

    String parseDeadlineDescription(String info) {
        return info.split("\\s*/by\\s*", 2)[0];
    }

    LocalDate parseDeadlineDate(String info) throws InvalidCommandException {
        try {
            return LocalDate.parse(info.split("\\s*/by\\s*", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oh no! The deadline of the task is missing.");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("I don't understand the deadline. Please provide it yyyy-mm-dd format.");
        }
    }

    String parseEventDescription(String info) {
        return info.split("\\s*/at\\s*", 2)[0];
    }

    String parseEventTime(String info) throws InvalidCommandException {
        try {
            return info.split("\\s*/at\\s*", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oh no! The time of the event is missing.");
        }
    }

    String parseSearchTerm(String input) {
        return input.strip().split("\\s+", 2)[1];
    }

    int parseTaskNumber(String input) throws InvalidCommandException {
        try {
            String[] parsedInput = input.strip().split("\\s+", 2);
            int taskNumber = Integer.parseInt(parsedInput[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I don't know which task you mean :(");
        }
    }
}
