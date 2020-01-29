package duke.parser;

import duke.exceptions.EmptyDateError;
import duke.exceptions.EmptyDescriptionError;
import duke.exceptions.InvalidDateError;
import duke.exceptions.InvalidInputError;
import duke.exceptions.MissingKeywordError;
import duke.exceptions.MissingTaskNumberError;

import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser program passes user input, date and time related to a task.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class Parser {

    String[] commandArr;
    String command;
    String date;

    public Parser() {

    }

    /**
     * Parses user input.
     *
     * @param input refers to the user input.
     * @return string array consisting of the parsed input.
     * @throws Exception is an error thrown when input is incorrect.
     */
    public String[] parseUserInput(String input) throws Exception {

        String[] outputArr = new String[3];

        if (input.equals("list") || input.equals("bye")) {

            outputArr[0] = input;
            outputArr[1] = "";
            outputArr[2] = "";

            return outputArr;

        } else if (input.startsWith("done") || input.startsWith("delete")) {

            String[] splitInput = input.split(" ");

            if (splitInput.length < 2) {

                outputArr[0] = "MissingTaskNumber";
                outputArr[1] = "";
                outputArr[2] = "";

                throw new MissingTaskNumberError();

            } else {

                return splitInput;

            }

        } else if (input.startsWith("find")) {

            String[] splitInput = input.split(" ");
            if (splitInput.length < 2) {

                outputArr[0] = "MissingKeyWord";
                outputArr[1] = "";
                outputArr[2] = "";

                throw new MissingKeywordError();

            } else {

                return splitInput;

            }
        }

        int index = input.indexOf("/");
        int whiteSpaceIndex = input.indexOf(" ");


        if (whiteSpaceIndex == -1) {

            if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {

                outputArr[0] = input;
                outputArr[1] = "EmptyDescription";
                outputArr[2] = "";

                throw new EmptyDescriptionError(input);
            } else {

                outputArr[0] = input;
                outputArr[1] = "InvalidInput";
                outputArr[2] = "";

                throw new InvalidInputError();
            }

        } else if (input.startsWith("todo")) {

            outputArr[0] = input.substring(0, whiteSpaceIndex);
            outputArr[1] = input.substring(whiteSpaceIndex + 1);
            outputArr[2] = "";

        } else if (index == -1) {

            outputArr[0] = input.substring(0, whiteSpaceIndex);
            outputArr[1] = "EmptyDate";
            outputArr[2] = "";

            throw new EmptyDateError(input.substring(0, whiteSpaceIndex));

        } else {

            int indexAt = input.indexOf("/at ");

            if (input.startsWith("deadline")) {

                int indexBy = input.indexOf("/by ");

                outputArr[0] = input.substring(0, whiteSpaceIndex);

                if (indexBy == -1) {
                    outputArr[1] = "EmptyDate";
                    outputArr[2] = "";

                    throw new EmptyDateError(input.substring(0, whiteSpaceIndex));

                } else {

                    outputArr[1] = input.substring(whiteSpaceIndex + 1, index);
                    outputArr[2] = input.substring(index + 4);

                }

            } else {

                outputArr[0] = input.substring(0, whiteSpaceIndex);

                if (indexAt == -1) {

                    outputArr[1] = "EmptyDate";
                    outputArr[2] = "";

                    throw new EmptyDateError(input.substring(0, whiteSpaceIndex));
                } else {

                    outputArr[0] = input.substring(0, whiteSpaceIndex);
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

        try {

            String[] split =  null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
            LocalDateTime[] parsed = new LocalDateTime[2];

            if (type.equals("todo")) {

                parsed[0] = LocalDateTime.parse("12/12/1212 12:12", formatter);
                parsed[1] = LocalDateTime.parse("12/12/1212 12:12", formatter);

            } else if (type.equals("event")) {

                split = dateTime.split(" to ");
                parsed[0] = LocalDateTime.parse(split[0], formatter);
                parsed[1] = LocalDateTime.parse(split[1], formatter);

            } else if (type.equals("deadline")) {

                parsed[0] = LocalDateTime.parse(dateTime, formatter);
                parsed[1] = LocalDateTime.parse("12/12/1212 12:12", formatter);

            }
            return parsed;

        } catch (Exception e) {

            throw new InvalidDateError(type);

        }

    }


}
