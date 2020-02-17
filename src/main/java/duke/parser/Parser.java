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
 * @version 1.2
 * @since 17/2/2020
 */
public class Parser {

    /**
     * Parses user input.
     *
     * @param input refers to the user input.
     * @return string array consisting of the parsed input.
     * @throws Exception is an error thrown when input is incorrect.
     */
    public String[] parseUserInput(String input) throws Exception {

        String[] outputArr = new String[3];

         boolean isValidInput = checkValidInput(input);

         if(!isValidInput) {

             throw new InvalidInputError();

         }

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


        int index = input.indexOf("/");
        int whiteSpaceIndex = input.indexOf(" ");


        if (whiteSpaceIndex == -1) {

                throw new EmptyDescriptionError(input);

        } else if (input.startsWith("todo")) {

            outputArr[0] = Task.Types.T.toString();
            outputArr[1] = input.substring(whiteSpaceIndex + 1);
            outputArr[2] = "";

        } else if (index == -1) {

            throw new EmptyDateError(input.substring(0, whiteSpaceIndex));

        } else {

            int indexAt = input.indexOf("/at ");

            if (input.startsWith("deadline")) {

                int indexBy = input.indexOf("/by ");

                if (indexBy == -1) {

                    throw new EmptyDateError(input.substring(0, whiteSpaceIndex));

                } else {

                    outputArr[0] = Task.Types.D.toString();
                    outputArr[1] = input.substring(whiteSpaceIndex + 1, index);
                    outputArr[2] = input.substring(index + 4);
                }

            } else {

                assert input.startsWith("event") : "task type is not event";

                if (indexAt == -1) {

                    throw new EmptyDateError(input.substring(0, whiteSpaceIndex));

                } else {

                    outputArr[0] = Task.Types.E.toString();
                    outputArr[1] = input.substring(whiteSpaceIndex + 1, index);
                    outputArr[2] = input.substring(index + 4);
                }
            }
        }

        return outputArr;
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

                throw new InvalidDateError("event", "format");

            }

            boolean isValid = checkDateTimeValidity(parsed, "E");

            if (!isValid) {

                throw new InvalidDateError("event", "Date");

            }

        } else if (type.equals("D")) {

            try {

                parsed[0] = LocalDateTime.parse(dateTime, formatter);
                parsed[1] = LocalDateTime.parse("12/12/1212 12:12", formatter);

            } catch (Exception e) {

                throw new InvalidDateError("deadline", "format");

            }

            boolean isValid = checkDateTimeValidity(parsed, "D");

            if (!isValid) {

                throw new InvalidDateError("deadline", "Date");

            }

        }

        assert parsed[0] != null && parsed[1] != null : "Dates are empty";

        return parsed;


    }

    boolean checkValidInput(String input) {

        boolean isList = input.equals("list");
        boolean isBye = input.equals("bye");
        boolean isDone = input.startsWith("done");
        boolean isDelete = input.startsWith("delete");
        boolean isFind = input.startsWith("find");
        boolean isEvent = input.startsWith("todo") || input.startsWith("event") || input.startsWith("deadline");

        return isList || isBye || isDone || isDelete || isFind || isEvent;

    }

    /**
     * Checks if date parsed is valid.
     *
     * @param times refers to the start and end dateTime of the task.
     * @param type refers to the type of event.
     * @return true if date is valid and false, otherwise.
     */

    boolean checkDateTimeValidity(LocalDateTime[] times, String type) {

        boolean isEndLaterThanStart = false;
        boolean isStartLaterThanNow = false;

        if(type.equals("D")) {

            isStartLaterThanNow = times[0].isAfter(LocalDateTime.now());

            return isStartLaterThanNow;

        } else {

            assert type.equals("E") : "Wrong event type!";

            isStartLaterThanNow = times[0].isAfter(LocalDateTime.now());
            isEndLaterThanStart = times[1].isAfter(times[0]);

            return isEndLaterThanStart && isStartLaterThanNow;

        }

    }

}
