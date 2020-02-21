package duke.utilities;

import duke.commands.*;
import duke.tasks.*;
import duke.exceptions.*;

import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * A general parser to parse user input as well as tasks to and from tasks.txt.
 */
public class Parser {
    public Parser() {

    }

    /**
     * A boolean to indicate if the Parser is currently at the updating stage (as update contains two stages). Cannot move on to other commands if the update is not complete, just in case the user passes another command without completing the update stage.
     */
    static boolean isUpdating = false;
    /**
     * The current update command, if the user is updating a task.
     */
    static UpdateCommand updateCommand;

    /**
     * Parses task into a string to load into tasks.txt.
     *
     * @param task the task to parse
     * @return string representation of the task in the form (TYPE)|(0 or 1)|(description)|task time (if any)
     */
    public static String parseTask(Task task) throws DukeException {
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
                throw new DukeException(DukeError.TASKPARSE);
        }
        assert parsed != null : "parser unable to parse task, returns null";
        return parsed;
    }

    /**
     * Parses line from tasks.txt into a Task object.
     * Line is in the form of (TYPE)|(0 or 1)|(description)|(task time, if any).
     *
     * @param line String representation of task
     * @return a Task object
     */
    public static Task parseFile(String line) throws DukeException { // parses line from tasks.txt into a task
        String[] split = line.split(Pattern.quote("|"));
        Task task;
        switch (split[0]) {
            case "T":
                task = new ToDo(split[1], split[2]);
                break;
            case "E":
                task = new Event(split[1], split[2], split[3]);
                break;
            case "D":
                task = new Deadline(split[1], split[2], split[3]);
                break;
            default:
                throw new DukeException(DukeError.FILEPARSE);
        }
        assert task != null : "error in parsing file to task object";
        return task;
    }

    /**
     * Parses user input into a Command object. The user input is received from the main Duke class.
     *
     * @param input user input eg. "date blah /by 9/2/2020"
     * @return a Command representing the action to be taken as directed by user
     * @throws DukeException if insufficient commands given, wrong command given or wrong date format given
     */
    public static Command parseInput(String input) throws DukeException {
        try {
            input = input.trim(); // trim any opening and trailing whitespace
            if (input.equals("list")) { // list command
                return new ListCommand();
            } else if (input.equals("help")) {
                return new HelpCommand();
            } else if (input.startsWith("done")) { // done command
                int taskNumber = Integer.parseInt(input.split(" ", 2)[1]) - 1;
                return new DoneCommand(taskNumber);
            } else if (input.startsWith("delete")) { // delete command
                String[] split = input.split(" ", 2);
                String[] numberStrings = split[1].split(" ");
                int[] taskNumbers = Stream.of(numberStrings).mapToInt(x -> Integer.parseInt(x) - 1).toArray(); // split into int[] of indices
                return new DeleteCommand(taskNumbers);
            } else if (input.startsWith("find")) { // find command
                String[] split = input.split(" ");
                if (split.length > 2) { // if more than one keyword supplied by user, throw DukeException
                    throw new DukeException(DukeError.KEYWORDS);
                }
                return new FindCommand(split[1]);
            } else if (input.startsWith("update")) { // first update stage
                assert isUpdating == false : "isUpdating boolean should be false!";
                int taskNumber = Integer.parseInt(input.split(" ", 2)[1]) - 1;
                isUpdating = true;
                updateCommand = new UpdateCommand(taskNumber);
                return updateCommand;
            } else if (isUpdating) { // second update stage
                assert updateCommand != null; // the updateCommand cannot be null if parser is set to update
                String[] split = input.split(" ", 2); // user input here will be "<item to update> <update>"
                if (!split[0].equals("des") && !split[0].equals("date")) {
                    throw new DukeException(DukeError.UPDATE);
                }
                isUpdating = false;
                Command nextStage = updateCommand.nextUpdateStage(split[0], split[1]);
                updateCommand = null;
                return nextStage;
            } else { // parse task command
                String[] split = input.split(" ", 2);
                TaskType taskType = TaskType.valueOf(split[0].toUpperCase());
                String taskDetails = split[1];
                Task task;
                switch (taskType) {
                    case TODO:
                        task = new ToDo("0", taskDetails);
                        break;
                    case EVENT:
                        String[] eventDetails = taskDetails.split("/at");
                        String eventDescription = eventDetails[0].trim();
                        String eventTime = eventDetails[1].trim();
                        task = new Event("0", eventDescription, eventTime);
                        break;
                    case DEADLINE:
                        String[] deadlineDetails = taskDetails.split("/by");
                        String deadlineDescription = deadlineDetails[0].trim();
                        String deadline = deadlineDetails[1].trim();
                        task = new Deadline("0", deadlineDescription, deadline);
                        break;
                    default:
                        throw new DukeException(DukeError.COMMAND);
                }

                assert task != null : "task at the end of parseInput method cannot be null!";

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