package duke.parser;

import duke.exceptions.EmptyDateError;
import duke.exceptions.EmptyDescriptionError;
import duke.exceptions.InvalidDateError;
import duke.exceptions.InvalidInputError;
import duke.exceptions.MissingKeywordError;
import duke.exceptions.MissingTaskNumberError;
import duke.task.Task;

import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser program passes user input, date and time related to a task.
 *
 * @version 1.3
 * @since 19/2/2020
 */
public class Parser {

    /**
     * Calls relevant parser or throws error if
     * input is incorrect.
     *
     * @param input refers to the user input.
     * @return string array consisting of the parsed input.
     * @throws Exception is an error thrown when input is incorrect.
     */
    public String[] parseUserInput(String input) throws Exception {


        boolean isValidInput = checkValidInput(input);

        if (!isValidInput) {

            throw new InvalidInputError();

        }

        String typeOfCommand = getTypeOfCommand(input);

        if (typeOfCommand.equals("invalid")) {

            throw new InvalidInputError();

        } else if (typeOfCommand.equals("add")) {

            return parseAddCommand(input);

        } else if (typeOfCommand.equals("notAdd")) {

            return parseCommand(input);
        }

        return new String[3];
    }

    /**
     * Parses date and time of the task. If task do not have date, a dummy date
     * is used.
     *
     * @param dateTime the date and time of the task.
     * @return parsed date and time in form of LocalDateTime object.
     * @throws InvalidDateError is an error thrown when date has incorrect format.
     */
    public LocalDateTime[] parseDateTime(String dateTime, String type) throws InvalidDateError {

        String[] split = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        LocalDateTime[] parsed = new LocalDateTime[2];

        if (type.equals("T")) {

            parsed[0] = LocalDateTime.parse("12/12/1212 12:12", formatter);
            parsed[1] = LocalDateTime.parse("12/12/1212 12:12", formatter);

        } else if (type.equals("E")) {

            split = dateTime.split(" to ");

            try {

                parsed[0] = LocalDateTime.parse(split[0], formatter);
                parsed[1] = LocalDateTime.parse(split[1], formatter);

            } catch (Exception e) {

                throw new InvalidDateError(type, "format");

            }

            boolean isValid = checkDateTimeValidity(parsed, type);

            if (!isValid) {

                throw new InvalidDateError(type, "Date");

            }

        } else if (type.equals("D")) {

            try {

                parsed[0] = LocalDateTime.parse(dateTime, formatter);
                parsed[1] = LocalDateTime.parse("12/12/1212 12:12", formatter);

            } catch (Exception e) {

                throw new InvalidDateError(type, "format");

            }

            boolean isValid = checkDateTimeValidity(parsed, type);

            if (!isValid) {

                throw new InvalidDateError(type, "Date");

            }

        }

        assert parsed[0] != null && parsed[1] != null : "Dates are empty";

        return parsed;


    }

    /**
     * Parses user command relevant to
     * adding a new task.
     *
     * @param input refers to user input.
     * @return String[] containing parsed input.
     * @throws EmptyDescriptionError is thrown when task is missing task description.
     * @throws EmptyDateError is thrown when task is missing start or end date or both.
     * @throws InvalidDateError is thrown when date has incorrect format or has already past.
     */
    String[] parseAddCommand(String input) throws Exception {


        String[] outputArr = new String[3];

        int whiteSpaceIndex = input.indexOf(" ");


        if (input.startsWith("todo")) {

            String trimWhiteSpace = input.trim();

            if (trimWhiteSpace.equals("todo")) {

                throw new EmptyDescriptionError(Task.Types.T.toString());
            }

            if(whiteSpaceIndex != 4) {

                throw new InvalidInputError();
            }

            outputArr[0] = Task.Types.T.toString();
            outputArr[1] = input.substring(whiteSpaceIndex + 1);
            outputArr[2] = "";

        } else {

            String trimWhiteSpace = input.trim();

            if (input.startsWith("deadline")) {

                int indexBy = input.indexOf("/by ");

                if (trimWhiteSpace.equals("deadline")) {

                    throw new EmptyDescriptionError(Task.Types.D.toString());

                } else if (whiteSpaceIndex != 8) {

                    throw new InvalidInputError();

                } else if (indexBy == -1) {

                    throw new EmptyDateError(Task.Types.D.toString());

                } else {

                    outputArr[0] = Task.Types.D.toString();
                    outputArr[1] = input.substring(whiteSpaceIndex + 1, indexBy);
                    outputArr[2] = input.substring(indexBy + 4);
                }

            } else {

                assert input.startsWith("event") : "task type is not event";

                int indexAt = input.indexOf("/at ");

                if (trimWhiteSpace.equals("event")) {

                    throw new EmptyDescriptionError(Task.Types.E.toString());

                } else if(whiteSpaceIndex != 5) {

                    throw new InvalidInputError();

                } else if (indexAt == -1) {

                    throw new EmptyDateError(Task.Types.E.toString());

                } else {

                    outputArr[0] = Task.Types.E.toString();
                    outputArr[1] = input.substring(whiteSpaceIndex + 1, indexAt);
                    outputArr[2] = input.substring(indexAt + 4);
                }
            }
        }

        return outputArr;
    }

    /**
     * Parses all other command that
     * are not adding new task.
     *
     * @param input refers to user input
     * @return String[] containing parsed input.
     * @throws MissingTaskNumberError is thrown when command is missing task index.
     * @throws MissingKeywordError    is thrown when command is missing keyword.
     */
    String[] parseCommand(String input) throws Exception {

        String[] outputArr = new String[3];

        if (input.equals("list") || input.equals("bye")) {

            outputArr[0] = input;
            outputArr[1] = "";
            outputArr[2] = "";

            return outputArr;

        } else if (input.startsWith("done") || input.startsWith("delete")) {

            String[] splitInput = input.split(" ");

            if (splitInput.length < 2) {

                throw new MissingTaskNumberError();

            } else {

                return splitInput;

            }

        } else if (input.startsWith("find")) {

            String[] splitInput = input.split(" ");
            if (splitInput.length < 2) {

                throw new MissingKeywordError();

            } else {

                return splitInput;

            }
        }

        return outputArr;
    }


    /**
     * Checks if input has a valid command
     * before parsing it.
     *
     * @param input refers to the user input/\.
     * @return true if input is valid and false otherwise.
     */
    boolean checkValidInput(String input) {

        boolean isList = input.equals("list");
        boolean isBye = input.equals("bye");
        boolean isDone = input.startsWith("done ");
        boolean isDelete = input.startsWith("delete ");
        boolean isFind = input.startsWith("find ");
        boolean isEvent = input.startsWith("todo") || input.startsWith("event") || input.startsWith("deadline");


        return isList || isBye || isDone || isDelete || isFind || isEvent;

    }

    /**
     * Checks if date parsed is valid.
     *
     * @param times refers to the start and end dateTime of the task.
     * @param type  refers to the type of event.
     * @return true if date is valid and false, otherwise.
     */
    boolean checkDateTimeValidity(LocalDateTime[] times, String type) {

        if (type.equals("D")) {

            boolean isStartLaterThanNow = false;
            isStartLaterThanNow = times[0].isAfter(LocalDateTime.now());

            return isStartLaterThanNow;

        } else {

            assert type.equals("E") : "Wrong event type!";

            boolean isEndLaterThanNow = false;
            boolean isEndLaterThanStart = false;

            isEndLaterThanNow = times[1].isAfter(LocalDateTime.now());
            isEndLaterThanStart = times[1].isAfter(times[0]);

            return isEndLaterThanStart && isEndLaterThanNow;

        }

    }

    /**
     * Gets the type of command input is.
     *
     * @param input refers to user input
     * @return String telling the type of command.
     */
    String getTypeOfCommand(String input) {

        boolean isList = input.equals("list");
        boolean isBye = input.equals("bye");
        boolean isDone = input.startsWith("done ");
        boolean isDelete = input.startsWith("delete ");
        boolean isFind = input.startsWith("find ");
        boolean isEvent = input.startsWith("todo") || input.startsWith("event") || input.startsWith("deadline");

        if (isEvent) {

            return "add";

        } else if (isList || isBye || isDone || isDelete || isFind) {

            return "notAdd";

        } else {

            return "invalid";

        }

    }

}
