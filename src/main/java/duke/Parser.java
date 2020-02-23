package duke;

import duke.command.FindCommand;
import duke.command.Command;
import duke.command.WhatsupCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ByeCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidDeleteException;
import duke.exception.InvalidDoneException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidFindException;
import duke.exception.InvalidNoteException;
import duke.exception.InvalidTodoException;
import duke.exception.InvalidWhatsupException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser object reads user input strings and informs main Duke object of what command to execute.
 */
public class Parser {
    /**
     * returns the Command to execute, based on user input.
     * @param instruction User input.
     * @return Command to execute.
     * @throws DukeException If user input is invalid or does not follow specified format.
     */
    public static Command parse(String instruction) throws DukeException {
        if (instruction.equals("")) {
            throw new EmptyCommandException();
        }

        String keyword = instruction.split(" ")[0].toLowerCase();

        switch (keyword) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "done":
            String[] doneTokens = instruction.split(" ");
            int completedTaskNumber;

            if (doneTokens.length != 2) {
                throw new InvalidDoneException();
            }

            try {
                completedTaskNumber = Integer.parseInt(doneTokens[1]);
            } catch (NumberFormatException ne) {
                throw new InvalidDoneException();
            }
            return new DoneCommand(completedTaskNumber);

        case "delete":
            String[] delTokens = instruction.split(" ");
            int deletedTaskNumber;

            if (delTokens.length != 2) {
                throw new InvalidDeleteException();
            }

            try {
                deletedTaskNumber = Integer.parseInt(delTokens[1]);
            } catch (NumberFormatException ne) {
                throw new InvalidDeleteException();
            }
            return new DeleteCommand(deletedTaskNumber);

        case "whatsup":
            String[] whatsupTokens = instruction.split("/on ");
            LocalDate queryDate;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                queryDate = LocalDate.parse(whatsupTokens[1].trim(), formatter);
            } catch (Exception e) {
                throw new InvalidWhatsupException();
            }
            return new WhatsupCommand(queryDate);

        case "todo":
            String taskName;

            try {
                taskName = instruction.trim().split("todo ")[1];
            } catch (Exception e) {
                throw new InvalidTodoException();
            }
            return new AddCommand("todo", taskName);

        case "event":
            String[] eventTokens = instruction.split("/at ");
            String timePeriod;
            String eventName;

            try {
                timePeriod = eventTokens[1];
                eventName = eventTokens[0].trim().split("event ")[1];
            } catch (Exception e) {
                throw new InvalidEventException();
            }
            return new AddCommand("event", eventName, timePeriod);

        case "deadline":
            String[] deadlineTokens = instruction.split("/by ");
            String dueDate;
            String deadlineName;

            try {
                dueDate = deadlineTokens[1];
                deadlineName = deadlineTokens[0].trim().split("deadline ")[1];
            } catch (Exception e) {
                throw new InvalidDeadlineException();
            }
            return new AddCommand("deadline", deadlineName, dueDate);

        case "note":
            String note;

            try {
                note = instruction.trim().split("note ")[1];
            } catch (Exception e) {
                throw new InvalidNoteException();
            }
            return new AddCommand("note", note);

        case "find":
            String[] findTokens = instruction.trim().split(" ");
            String keyWord;

            try {
                keyWord = findTokens[1];
            } catch (Exception e) {
                throw new InvalidFindException();
            }
            return new FindCommand(keyWord);

        default:
            throw new EmptyCommandException();
        }
    }
}
