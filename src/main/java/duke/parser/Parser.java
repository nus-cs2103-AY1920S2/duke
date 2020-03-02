package duke.parser;

import duke.command.*;
import duke.task.Priority;
import exception.IllegalTextException;

import java.util.regex.PatternSyntaxException;

public class Parser {

    public static Command parse(String stringToParse) throws IllegalTextException {
        stringToParse = removeExtraWhitespaces(stringToParse);
        String commandType = parseCommandType(stringToParse);

        switch (commandType.toLowerCase()) {
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(parseItemNumber(stringToParse));
        case "delete":
            return new DeleteCommand(parseItemNumber(stringToParse));
        case "todo":
            return new TodoCommand(parseTodoArgs(stringToParse));
        case "event":
            return new EventCommand(parseEventArgs(stringToParse));
        case "deadline":
            return new DeadlineCommand(parseDeadlineArgs(stringToParse));
        case "priority":
            return new PriorityCommand(parsePriorityArgs(stringToParse));
        case "update":
            return new UpdatePriorityCommand(parseUpdateArgs(stringToParse));
        }
        return null;
    }

    private static String[] parseUpdateArgs(String stringToParse) throws IllegalTextException {
        // e.g. "update 2 HIGH"
        try {
            String[] splittedString = stringToParse.split("\\s+");
            if (splittedString.length < 3) {
                throw new IllegalTextException("Wrong Format, please follow this format: "
                        + System.lineSeparator() + "update [command number from list] [priority]");
            } else if (!Priority.isValidPriority(splittedString[2])) {
                throw new IllegalTextException("You typed an invalid priority level");
            }
            System.out.printf("%s %s", splittedString[1].trim(), splittedString[2].trim());
            return new String[] {splittedString[1].trim(), splittedString[2].trim()};
        } catch (IllegalTextException e) {
            throw e;
        }
    }

    private static String parsePriorityArgs(String stringToParse) throws IllegalTextException {
        try {
            String[] splittedString = stringToParse.split("\\s+");
            if (splittedString.length <= 1) {
                throw new IllegalTextException("Specify LOW, MEDIUM or HIGH priority");
            } else if (!Priority.isValidPriority(splittedString[1])) {
                throw new IllegalTextException("You typed an invalid priority level");
            }
            return splittedString[1];
        } catch (IllegalTextException e) {
            throw e;
        }
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
