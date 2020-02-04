package duke.utilities;

import duke.commands.*;
import duke.tasks.*;
import duke.exceptions.*;

import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * A general parser to parse user input as well as tasks to and from tasks.txt.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Parses task into a string to load into tasks.txt.
     *
     * @param task the task to parse
     * @return string representation of the task in the form (TYPE)|(0 or 1)|(description)|task time (if any)
     */
    public static String parseTask(Task task) {
        String parsed;
        TaskType taskType = task.getTaskType();
        switch (taskType) {
            case TODO:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription();
                break;
            case EVENT:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription() + "|" + ((Event) task).getTaskTime();
                break;
            case DEADLINE:
                parsed = taskType.toString() + "|" + task.getDoneInt() + "|" + task.getDescription() + "|" + ((Deadline) task).getTaskTime();
                break;
            default:
                parsed = ""; // TODO add error message, change duke.exceptions.DukeException to such task exists
        }
        return parsed;
    }

    /**
     * Parses line from tasks.txt into a Task object.
     *
     * @param line String representation of task
     * @return a Task object
     */
    public static Task parseFile(String line) { // parses line from tasks.txt into a task
        String[] split = line.split(Pattern.quote("|"));
        switch (split[0]) {
            case "T":
                return new ToDo(split[1], split[2]);
            case "E":
                return new Event(split[1], split[2], split[3]);
            case "D":
                return new Deadline(split[1], split[2], split[3]);
            default:
                System.out.println("Error in parser parse method"); // TODO add error message and duke.exceptions.DukeException
                return null;
        }
    }

    /**
     * Parses user input into a Command object.
     *
     * @param input user input eg. "deadline blah /by 9/2/2020"
     * @return a Command representing the action to be taken as directed by user
     * @throws DukeException if insufficient commands given, wrong command given or wrong date format given
     */
    public static Command parseInput(String input) throws DukeException {
        try {
            input = input.trim(); // trim any opening and trailing whitespace
            if (input.equals("bye")) { // parse exit command
                return new ExitCommand();
            } else if (input.equals("list")) { // parse list command
                return new ListCommand();
            } else if (input.startsWith("done")) { // parse done command
                int taskNumber = Integer.parseInt(input.split(" ", 2)[1]) - 1;
                return new DoneCommand(taskNumber);
            } else if (input.startsWith("delete")) { // parse delete command
                int taskNumber = Integer.parseInt(input.split(" ", 2)[1]) - 1;
                return new DeleteCommand(taskNumber);
            } else if (input.startsWith("find")) { // parse find by keyword command
                String[] split = input.split(" ");
                if (split.length > 2) { // if more than one keyword given, throw error
                    throw new DukeException(DukeError.KEYWORDS);
                } else {
                    return new FindCommand(split[1]);
                }
            } else { // parse task command
                String[] split = input.split(" ", 2);
                TaskType taskType = TaskType.valueOf(split[0].toUpperCase());
                String taskDetails = split[1];
                Task task = new Task();
                switch (taskType) {
                    case TODO:
                        task = new ToDo(taskDetails);
                        break;
                    case EVENT:
                        String[] eventDetails = taskDetails.split("/at");
                        String eventDescription = eventDetails[0].trim();
                        String eventTime = eventDetails[1].trim();
                        task = new Event(eventDescription, eventTime);
                        break;
                    case DEADLINE:
                        String[] deadlineDetails = taskDetails.split("/by");
                        String deadlineDescription = deadlineDetails[0].trim();
                        String deadline = deadlineDetails[1].trim();
                        task = new Deadline(deadlineDescription, deadline);
                        break;
                }

                return new AddCommand(task);

            }
        } catch (IndexOutOfBoundsException e) { // catch insufficient commands given
            throw new DukeException(DukeError.INSUFFICIENT);
        } catch (IllegalArgumentException e) { // catch wrong command given
            throw new DukeException(DukeError.COMMAND);
        } catch (DateTimeParseException e) { // catch wrong date format given
            throw new DukeException(DukeError.DATEFORMAT);
        }
    }

}