package duke;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

/**
 * A command parser for {@code Duke}.
 */
class Parser {
    private static final int NUMBER_OF_COMMAND_SECTIONS = 2;
    private static final int COMMAND_POSITION = 0;
    private static final int ARGUMENT_POSITION = 1;
    private static final int NUMBER_OF_DATE_TIME_ARGUMENTS = 2;
    private static final int DATE_POSITION = 0;
    private static final int TIME_POSITION = 1;
    private static final int NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS = 3;
    private static final int DEADLINE_DESCRIPTION_POSITION = 0;
    private static final int DEADLINE_DUE_DATE_POSITION = 1;
    private static final int NUMBER_OF_EVENT_COMMAND_ARGUMENTS = 2;
    private static final int EVENT_DESCRIPTION_POSITION = 0;
    private static final int EVENT_TIME_POSITION = 1;
    private static final int NUMBER_OF_SNOOZE_COMMAND_ARGUMENTS = 3;
    private static final int SNOOZE_TASK_NUMBER_POSITION = 0;
    private static final int SNOOZE_AMOUNT_POSITION = 1;
    private static final int SNOOZE_TIME_UNIT_POSITION = 2;

    /**
     * Returns the type of {@code Duke} command parsed from the user input.
     *
     * @param input the user input
     * @return the type of {@code Duke} command
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
        case "clear":
            command = Duke.Command.CLEAR;
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
     * Returns {@code true} if the arguments in the user input can be parsed for the specified {@code Duke} command.
     *
     * @param command the type of {@code Duke} command
     * @param input the user input
     * @return {@code true} if the arguments in the user input can be parsed for the specified {@code Duke} command
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
                throw new InvalidCommandException("Oops! The description and due date are missing.");
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
        case CLEAR:
            if (hasArguments(input)) {
                throw new InvalidCommandException("Hmm... I don't understand. Try \"clear\" instead.");
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
     * Returns {@code true} if the user input contains arguments, {@code false} otherwise.
     *
     * @param input the user input
     * @return {@code true} if the user input contains arguments, {@code false} otherwise
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

    /**
     * Parses the todo description from the arguments in the user input for the todo command.
     *
     * @param arguments the arguments from the user input
     * @return the todo description
     */
    String parseTodoDescription(String arguments) {
        assert arguments != null;
        return arguments;
    }

    /**
     * Parses the deadline description from the arguments in the user input for the deadline command.
     *
     * @param arguments the arguments from the user input
     * @return the deadline description
     */
    String parseDeadlineDescription(String arguments) {
        assert arguments != null;
        return arguments.split("\\s+/by\\s+", NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS)[DEADLINE_DESCRIPTION_POSITION];
    }

    /**
     * Parses the date of the deadline from the arguments in the user input for the deadline command.
     *
     * @param arguments the arguments from the user input
     * @return the date of deadline
     */
    LocalDate parseDeadlineDate(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            String dateTime = arguments.split("\\s+/by\\s+",
                    NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS)[DEADLINE_DUE_DATE_POSITION];
            return LocalDate.parse(dateTime.split("\\s+", NUMBER_OF_DATE_TIME_ARGUMENTS)[DATE_POSITION]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oops! The date of the deadline is missing.");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(
                    "I don't understand the deadline. Please provide it YYYY-MM-DD (HH:mm) format.");
        }
    }

    /**
     * Parses the time of the deadline from the arguments in the user input for the deadline command.
     *
     * @param arguments the arguments from the user input
     * @return the time of the deadline
     */
    LocalTime parseDeadlineTime(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            String dateTime = arguments.split("\\s+/by\\s+",
                    NUMBER_OF_DEADLINE_COMMAND_ARGUMENTS)[DEADLINE_DUE_DATE_POSITION];
            return LocalTime.parse(dateTime.split("\\s+", NUMBER_OF_DATE_TIME_ARGUMENTS)[TIME_POSITION]);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            return LocalTime.parse("23:59");
        }
    }

    /**
     * Parses the event description from the arguments in the user input for the event command.
     *
     * @param arguments the arguments from the user input
     * @return the event description
     */
    String parseEventDescription(String arguments) {
        assert arguments != null;
        return arguments.split("\\s+/at\\s+", NUMBER_OF_EVENT_COMMAND_ARGUMENTS)[EVENT_DESCRIPTION_POSITION];
    }

    /**
     * Parses the date of the event from the arguments in the user input for the event command.
     *
     * @param arguments the arguments from the user input
     * @return the date of event
     */
    LocalDate parseEventDate(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            String dateTime = arguments.split("\\s+/at\\s+",
                    NUMBER_OF_EVENT_COMMAND_ARGUMENTS)[EVENT_TIME_POSITION];
            return LocalDate.parse(dateTime.split("\\s+", NUMBER_OF_DATE_TIME_ARGUMENTS)[DATE_POSITION]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oops! The date of the event is missing.");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(
                    "I don't understand when the event is. Please provide the time in YYYY-MM-DD HH:mm format.");
        }
    }

    /**
     * Parses the time of the event from the arguments in the user input for the event command.
     *
     * @param arguments the arguments from the user input
     * @return the time of the event
     */
    LocalTime parseEventTime(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            String dateTime = arguments.split("\\s+/at\\s+",
                    NUMBER_OF_EVENT_COMMAND_ARGUMENTS)[EVENT_TIME_POSITION];
            return LocalTime.parse(dateTime.split("\\s+", NUMBER_OF_DATE_TIME_ARGUMENTS)[TIME_POSITION]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Oops! The time of the event is missing.");
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(
                    "I don't understand when the event is. Please provide the time in YYYY-MM-DD HH:mm format.");
        }
    }

    /**
     * Parses the search term from the arguments in the user input for the find command.
     *
     * @param arguments the arguments from the user input
     * @return the search term
     */
    String parseFindSearchTerm(String arguments) {
        assert arguments != null;
        return arguments;
    }

    /**
     * Parses the task number from the arguments in the user input for the snooze command.
     *
     * @param arguments the arguments from the user input
     * @return the task number
     */
    int parseSnoozeTaskNumber(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return Integer.parseInt(
                    arguments.split("\\s+", NUMBER_OF_SNOOZE_COMMAND_ARGUMENTS)[SNOOZE_TASK_NUMBER_POSITION]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I don't know which task you mean :(");
        }
    }

    /**
     * Parses the snooze duration from the arguments in the user input for the snooze command.
     *
     * @param arguments the arguments from the user input
     * @return the snooze duration
     */
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

    /**
     * Parses the task number from the arguments in the user input for the done command.
     *
     * @param arguments the arguments from the user input
     * @return the task number
     */
    int parseDoneTaskNumber(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I don't know which task you mean :(");
        }
    }

    /**
     * Parses the task number from the arguments in the user input for the delete command.
     *
     * @param arguments the arguments from the user input
     * @return the task number
     */
    int parseDeleteTaskNumber(String arguments) throws InvalidCommandException {
        assert arguments != null;
        try {
            return Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Hmm... I don't know which task you mean :(");
        }
    }
}
