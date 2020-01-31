import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import DukeException.DukeMissingDescriptionException;
import DukeException.DukeUnknownInputException;

public class Parser {
    static String command = "";

    public static String getCommandType(String command) {
        command = command;
        String[] splitted = command.split(" ", 2);
        return splitted[0];
    }

    public static int markDoneNum() {
        if (command.length() <= 5) {
            throw new DukeMissingDescriptionException("Task number missing.");
        }
        String num = command.substring(5);
        try {
            return Integer.valueOf(num);
        } catch(NumberFormatException e) {
            throw new DukeUnknownInputException("Need task NUMBER.");
        }
    }

    public static String todoDescription() {
        if (command.length() <= 5) {
            throw new DukeMissingDescriptionException("Todo description missing.");
        }
        return command.substring(5);
    }

    public static String[] deadlineParams() {
        if (command.length() <= 9) {
            throw new DukeMissingDescriptionException("Deadline description and time missing.");
        }
        String[] splitted = command.substring(9).split(" /by ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException("Need format deadline <description> /by <time>.");
        }
        // Check if date is parsable
        String byWhen;
        try {
            LocalDate date = LocalDate.parse(splitted[1]);
            byWhen = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            byWhen = splitted[1];
        }
        String[] descriptionAndBy = {splitted[0], byWhen};
        return descriptionAndBy;
    }

    public static String[] eventParams() {
        if (command.length() <= 6) {
            throw new DukeMissingDescriptionException("Event description and time missing.");
        }
        String[] splitted = command.substring(6).split(" /at ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException("Need format event <description> /at <time>.");
        }
        return splitted;
    }

    public static int deleteNum() {
        if (command.length() <= 7) {
            throw new DukeMissingDescriptionException("Task number missing.");
        }
        String num = command.substring(7);
        try {
            return Integer.valueOf(num);
        } catch(NumberFormatException e) {
            throw new DukeUnknownInputException("Need task NUMBER.");
        }
    }
}