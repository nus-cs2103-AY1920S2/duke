package duke.parser;

import duke.command.*;
import exception.IllegalTextException;

import java.util.regex.PatternSyntaxException;

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
            return new TodoCommand(parseTodoArgs(stringToParse));
        case "event":
            return new EventCommand(stringToParse);
        case "deadline":
            //return new DeadlineCommand(stringToParse);
        }
        return null;
    }

    private static String parseTodoArgs(String stringToParse) throws IllegalTextException {
        try {
            return stringToParse.split(" ", 2)[1];
        } catch (PatternSyntaxException e) {
            throw new IllegalTextException("Todo command must have a valid description.");
        }
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
