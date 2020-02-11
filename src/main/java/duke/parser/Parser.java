package duke.parser;

import duke.command.*;
import exception.IllegalTextException;

public class Parser {

    public static Command parse(String stringToParse) throws IllegalTextException {
        stringToParse = removeExtraWhitespaces(stringToParse);
        String commandType = parseCommandType(stringToParse);

        switch (commandType) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new listCommand();
        case "done":
            return new doneCommand(parseItemNumber(stringToParse));
        case "delete":
            return new deleteCommand(parseItemNumber(stringToParse));
        case "todo":
            return new TodoCommand(stringToParse);
        case "event":
            //return new EventCommand(stringToParse);
        case "deadline":
            //return new DeadlineCommand(stringToParse);
        }
        return null;
    }

    private static String removeExtraWhitespaces(String stringToParse) {
        return stringToParse.replaceAll("\\s+", " ").trim();
    }

    private static int parseItemNumber(String stringToParse) throws IllegalTextException {
        try {
            return Integer.parseInt(stringToParse.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new IllegalTextException("Incorrect task number entered.");
        }
    }

    private static String parseCommandType(String stringToParse) throws IllegalTextException {
        try {
            String command = stringToParse.split(" ")[0];
            return command;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalTextException("Illegal command format entered.");
        }
    }
}
