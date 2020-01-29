import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    String command = "";

    public String getCommandType(String command) {
        this.command = command;
        String[] splitted = command.split(" ", 2);
        return splitted[0];
    }

    public int markDoneNum() {
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

    public String toDoDescription() {
        if (command.length() <= 5) {
            throw new DukeMissingDescriptionException("To Do description missing.");
        }
        return command.substring(5);
    }

    public String[] deadlineParams() {
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

    public String[] eventParams() {
        if (command.length() <= 6) {
            throw new DukeMissingDescriptionException("Event description and time missing.");
        }
        String[] splitted = command.substring(6).split(" /at ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException("Need format event <description> /at <time>.");
        }
        return splitted;
    }

    public int deleteNum() {
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