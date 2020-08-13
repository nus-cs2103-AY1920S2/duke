package duke.util;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.DukeBadDateException;
import duke.exceptions.DukeBadDateTimeException;
import duke.exceptions.DukeBadFileException;
import duke.exceptions.DukeBadIndexException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeNoDateException;
import duke.exceptions.DukeNoDescriptionException;
import duke.exceptions.DukeNoIndexException;
import duke.exceptions.DukeNoKeywordException;
import duke.exceptions.DukeUnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import java.util.Scanner;

public class Parser {
    protected String command;

    /**
     * Parses single command.
     * @param s string to be parsed.
     * @return parsed command as a Command object.
     * @throws DukeException if the command is invalid.
     */
    public static Command parseCommand(String s) throws DukeException {
        Scanner sc = new Scanner(s);
        String w1 = sc.next();
        switch (w1) {
        case "bye":
            return new ByeCommand();
        case "find":
            if (sc.hasNext()) {
                return new FindCommand(sc.next());
            } else {
                throw new DukeNoKeywordException();
            }
        case "list":
            return new ListCommand();
        case "done":
            if (sc.hasNextInt()) {
                int index = sc.nextInt();
                isValidIndex(index);
                return new DoneCommand(index);
            } else {
                throw new DukeNoIndexException(w1);
            }
        case "delete":
            if (sc.hasNextInt()) {
                int index = sc.nextInt();
                isValidIndex(index);
                return new DeleteCommand(index);
            } else {
                throw new DukeNoIndexException(w1);
            }
        case "todo":
            if (!sc.hasNextLine()) {
                throw new DukeNoDescriptionException(w1);
            } else {
                return new ToDoCommand(sc.nextLine());
            }
        case "event":
            if (!sc.hasNextLine()) {
                throw new DukeNoDescriptionException(w1);
            } else {
                String task = sc.nextLine().strip();
                String[] details = task.split(" /at ");
                if (details.length < 2) {
                    throw new DukeNoDateException(w1);
                } else {
                    isValidDateTime(details[1]);
                    return new EventCommand(details[0], details[1]);
                }
            }
        case "deadline":
            if (!sc.hasNextLine()) {
                throw new DukeNoDescriptionException(w1);
            } else {
                String task = sc.nextLine().strip();
                String[] details = task.split(" /by ");
                if (details.length < 2) {
                    throw new DukeNoDateException(w1);
                } else {
                    isValidDateTime(details[1]);
                    return new DeadlineCommand(details[0], details[1]);
                }
            }
        default:
            throw new DukeUnknownCommandException();
        }
    }

    /**
     * Parses a line in the storage file.
     * @param e string to be parsed.
     * @return parsed data in storage file as a Task object.
     * @throws DukeException if the data in the file is in the wrong format and cannot be parsed.
     */
    public static Task parseFileLine(String e) throws DukeException {
        try {
            assert !e.isEmpty() : "FILE CONTAINS EMPTY STRING!";
            String taskType = e.substring(0, 3);
            String taskStatus = e.substring(3, 6);
            String taskDetails = e.substring(7);
            Task t;
            switch (taskType) {
            case "[T]":
                t = new ToDo(taskDetails);
                break;
            case "[E]": {
                String[] details = taskDetails.split(" /at ");
                t = new Event(details[0], details[1]);
                break;
            }
            case "[D]": {
                String[] details = taskDetails.split(" /by ");
                t = new Deadline(details[0], details[1]);
                break;
            }
            default:
                throw new DukeBadFileException();
            }
            if (taskStatus.equals("[X]")) {
                t.setDone();
            }
            return t;
        } catch (Exception t) {
            throw new DukeBadFileException();
        }
    }

    /*
    IMPORTANT: parseFileLine incomplete, not all edge cases accounted for.
     */

    /**
     * Checks if index input by user is within a valid range.
     * @param index to be checked.
     * @throws DukeBadIndexException if the index is out of range.
     */
    public static void isValidIndex(int index) throws DukeBadIndexException {
        if (index <= 0) {
            throw new DukeBadIndexException(index);
        }
    }

    /**
     * Checks if date and time input by user is in the correct format.
     * @param dateTime string to be checked
     * @throws DukeBadDateException if the date is in the wrong format.
     * @throws DukeBadDateTimeException if the date and time are in the wrong format.
     */
    private static void isValidDateTime(String dateTime) throws DukeBadDateException, DukeBadDateTimeException {
        String[] p = dateTime.split(" ");
        if (p.length < 2) {
            throw new DukeBadDateTimeException();
        } else {
            String date = p[0];
            String time = p[1];
            int timeInt = Integer.parseInt(time);
            assert (timeInt >= 0 && timeInt < 2400) : "Time is in the wrong format";
            String[] dateParsed = date.split("-");
            if (dateParsed.length < 3) {
                throw new DukeBadDateException();
            }
        }
    }
}
