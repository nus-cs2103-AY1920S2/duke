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
            return new ListCommand();
        case "done":
            return new doneCommand(parseItemNumber(stringToParse));
        case "delete":
            return new deleteCommand(parseItemNumber(stringToParse));
        case "todo":
            return new TodoCommand(parseTodoArgs(stringToParse));
        case "event":
            return new EventCommand(parseEventArgs(stringToParse));
        case "deadline":
            return new DeadlineCommand(parseDeadlineArgs(stringToParse));
        }
        return null;
    }

    private static String[] parseDeadlineArgs(String stringToParse) throws IllegalTextException {
        // Consider an example: "deadline return book /by Sunday"
        // deadlineArgs = "return book /by Sunday"
        String deadlineArgs = removeActionKeyword(stringToParse, "deadline");
        // deadlineArgsArray = ["return book", "Sunday"]
        String[] deadlineArgsArray = deadlineArgs.split("/by");
        for (String s : deadlineArgsArray) {
            s = s.trim();
        }

        return deadlineArgsArray;
    }

    private static String[] parseEventArgs(String stringToParse) throws IllegalTextException {
        // Consider an example: "event project meeting /at mon 2-4pm"
        // eventArgs = "project meeting /at mon 2-4pm"
        String eventArgs = removeActionKeyword(stringToParse, "event");
        // eventArgsArray = ["Project meeting", "mon 2-4pm"]
        String[] eventArgsArray = eventArgs.split("/at");
        for (String s : eventArgsArray) {
            s = s.trim();
        }

        return eventArgsArray;
    }

    private static String removeActionKeyword(String stringToParse, String commandType) throws IllegalTextException {
        try {
            return stringToParse.split(" ", 2)[1];
        } catch (PatternSyntaxException e) {
            throw new IllegalTextException(commandType + " command must have a valid description.");
        }
    }

    private static String parseTodoArgs(String stringToParse) throws IllegalTextException {
        return removeActionKeyword(stringToParse, "todo");
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
