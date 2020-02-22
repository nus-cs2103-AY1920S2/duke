package duke;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

/**
 * A command parser for Duke.
 */
class Parser {
    private static final int NUMBER_OF_COMMAND_SECTIONS = 2;
    private static final int COMMAND_POSITION = 0;
    private static final int ARGUMENT_POSITION = 1;
    private static final int NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS = 3;
    private static final int DEADLINE_DESCRIPTION_POSITION = 0;
    private static final int DEADLINE_DATE_POSITION = 1;
    private static final int DEADLINE_TIME_POSITION = 2;
    private static final int NUMBER_OF_EVENT_COMMAND_ARGUMENTS = 2;
    private static final int EVENT_DESCRIPTION_POSITION = 0;
    private static final int EVENT_TIME_POSITION = 1;
    private static final int NUMBER_OF_SNOOZE_COMMAND_ARGUMENTS = 3;
    private static final int SNOOZE_TASK_NUMBER_POSITION = 0;
    private static final int SNOOZE_AMOUNT_POSITION = 1;
    private static final int SNOOZE_TIME_UNIT_POSITION = 2;

    /**
     * Returns the type of Duke command parsed from the user input.
     *
     * @param input the user input
     * @return the type of Duke command
     */
    Duke.Command parseCommand(String input) throws InvalidCommandException {
        assert input != null;
        Duke.Command command;
        String[] parsedInput = input.strip().split("\\s+");
        switch (parsedInput[COMMAND_POSITION]) {
        case "todo":
            command = Duke.Command.TODO;
            break;
        case "deadline":
            command = Duke.Command.DEADLINE;
            break;
        case "event":
            command = Duke.Command.EVENT;
            break;
        case "list":
            command = Duke.Command.LIST;
            break;
        case "find":
            command = Duke.Command.FIND;
            break;
        case "snooze":
            command = Duke.Command.SNOOZE;
            break;
        case "done":
            command = Duke.Command.DONE;
            break;
        case "delete":
            command = Duke.Command.DELETE;
            break;
        case "bye":
            command = Duke.Command.BYE;
            break;
        default:
            throw new InvalidCommandException("Hmm... I don't know what that means :(");
        }
        assert command != null;
        return command;
    }

    /**
     * Returns <code>true</code> if the arguments in the user input can be parsed for the specified Duke command.
     *
     * @param command the type of Duke command
     * @param input the user input
     * @return <code>true</code> if the arguments in the user input can be parsed for the specified Duke command
     */
    boolean checkArguments(Duke.Command command, String input) throws InvalidCommandException {
        assert command != null;
        assert input != null;
        switch (command) {
        case TODO:
            if (!hasArguments(input)) {
                throw new InvalidCommandException("Oops! The description is missing.");
            }
            break;
        case DEADLINE:
            if (!hasArguments(input)) {
                throw new InvalidCommandException("Oops! The description and deadline are missing.");
            }
            break;
        case EVENT:
            if (!hasArguments(input)) {
                throw new InvalidCommandException("Oops! The description and time are missing.");
            }
            break;
        case LIST:
            if (hasArguments(input)) {
                throw new InvalidCommandException("Hmm... I don't understand. Try \"list\" instead.");
            }
            break;
        case FIND:
            if (!hasArguments(input)) {
                throw new InvalidCommandException("Oops! The search term is missing.");
            }
            break;
        case SNOOZE:
            if (!hasArguments(input)) {
                throw new InvalidCommandException("Oops! The task number and snooze duration are missing.");
            }
            break;
        case DONE:
            if (!hasArguments(input)) {
                throw new InvalidCommandException("Oops! The task number is missing.");
            }
            break;
        case DELETE:
            if (!hasArguments(input)) {
                throw new InvalidCommandException("Oops! The task number is missing.");
            }
            break;
        case BYE:
            if (hasArguments(input)) {
                throw new InvalidCommandException("Hmm... I don't understand. Try \"bye\" instead.");
            }
            break;
        default:
            throw new InvalidCommandException("Oh no! There's something wrong.");
        }
        return true;
    }

    /**
     * Returns <code>true</code> if the user input contains arguments, <code>false</code> otherwise.
     *
     * @param input the user input
     * @return <code>true</code> if the user input contains arguments, <code>false</code> otherwise
     */
    boolean hasArguments(String input) {
        return input.strip().split("\\s+", NUMBER_OF_COMMAND_SECTIONS).length == NUMBER_OF_COMMAND_SECTIONS;
    }

    /**
     * Returns a string containing the arguments from the user input.
     *
     * @param input the user input
     * @return the arguments from the user input
     */
    String parseArguments(String input) {
        if (!hasArguments(input)) {
            return null;
        }
        return input.strip().split("\\s+", NUMBER_OF_COMMAND_SECTIONS)[ARGUMENT_POSITION];
    }

    String parseTodoDescription(String arguments) {
        assert arguments != null;
        return arguments;
    }

    String parseDeadlineDescription(String arguments) {
        assert arguments != null;
        return arguments.split("\\s+/by\\s+", NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS)[DEADLINE_DESCRIPTION_POSITION];
    }

    LocalDate parseDeadlineDate(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return LocalDate.parse(
                    arguments.split("\\s+/by\\s+", NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS)[DEADLINE_DATE_POSITION]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oops! The date of the deadline is missing.");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(
                    "I don't understand the deadline. Please provide it YYYY-MM-DD (HH:mm) format.");
        }
    }

    LocalTime parseDeadlineTime(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return LocalTime.parse(
                    arguments.split("\\s+/by\\s+", NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS)[DEADLINE_TIME_POSITION]);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            return LocalTime.parse("23:59");
        }
    }

    String parseEventDescription(String arguments) {
        assert arguments != null;
        return arguments.split("\\s+/at\\s+", NUMBER_OF_EVENT_COMMAND_ARGUMENTS)[EVENT_DESCRIPTION_POSITION];
    }

    String parseEventTime(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return arguments.split("\\s+/at\\s+", NUMBER_OF_EVENT_COMMAND_ARGUMENTS)[EVENT_TIME_POSITION];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oops! The time of the event is missing.");
        }
    }

    String parseFindSearchTerm(String arguments) {
        assert arguments != null;
        return arguments;
    }

    int parseSnoozeTaskNumber(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return Integer.parseInt(
                    arguments.split("\\s+", NUMBER_OF_SNOOZE_COMMAND_ARGUMENTS)[SNOOZE_TASK_NUMBER_POSITION]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I don't know which task you mean :(");
        }
    }

    TemporalAmount parseSnoozeDuration(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            long amount = Long.parseLong(
                    arguments.split("\\s+", NUMBER_OF_SNOOZE_COMMAND_ARGUMENTS)[SNOOZE_AMOUNT_POSITION]);
            String timeUnit = arguments.split("\\s+", NUMBER_OF_SNOOZE_COMMAND_ARGUMENTS)[SNOOZE_TIME_UNIT_POSITION];
            ChronoUnit chronoUnit;
            if (timeUnit.matches("(min)(ute)?s?")) {
                chronoUnit = ChronoUnit.MINUTES;
            } else if (timeUnit.matches("h((our)|r)?s?")) {
                chronoUnit = ChronoUnit.HOURS;
            } else if (timeUnit.matches("d(ay)?s?")) {
                chronoUnit = ChronoUnit.DAYS;
            } else {
                throw new InvalidCommandException("Hmm... I'm not sure how long you want to snooze the task for.");
            }
            return Duration.of(amount, chronoUnit);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I'm not sure how long you want to snooze the task for.");
        }
    }

    int parseDoneTaskNumber(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I don't know which task you mean :(");
        }
    }

    int parseDeleteTaskNumber(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I don't know which task you mean :(");
        }
    }
}
