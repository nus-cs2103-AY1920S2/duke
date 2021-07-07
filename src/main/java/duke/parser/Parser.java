package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TagsCommand;
import duke.enums.ErrorCodes;
import duke.enums.TaskTypes;
import duke.exceptions.DukeException;
import duke.tags.Tag;

/**
 * Class with methods to parse user input and responds accordingly.
 *
 * @author Firzan Armani
 */
public class Parser {
    /**
     * Parses the full command input from the user and returns the appropriate command to be execute by caller.
     *
     * @param fullCommand String of the full command input by the user.
     * @return A Command object based on the type of command parsed.
     * @throws DukeException DukeException thrown if missing task information or unknown commands.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command command = null;
        String[] splitCommand = fullCommand.split("\\s\\/");
        String commandType = getCommandWord(splitCommand[0]);
        String taskName = null;
        String[] otherArgs = null;
        LocalDate date = null;
        ArrayList<Tag> tags;
        int taskIndex = -1;

        switch (commandType) {
        case "list":
            otherArgs = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
            String tag = getTagName(otherArgs);
            if (tag.isEmpty()) {
                command = new ListCommand();
            } else {
                command = new ListCommand(tag);
            }
            break;
        case "tags":
            command = new TagsCommand();
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "find":
            taskName = getTaskName(splitCommand[0]);
            command = new FindCommand(taskName);
            break;
        case "done":
            taskIndex = getTaskIndex(splitCommand[0]);
            command = new DoneCommand(taskIndex);
            break;
        case "delete":
            taskIndex = getTaskIndex(splitCommand[0]);
            command = new DeleteCommand(taskIndex);
            break;
        case "deadline":
            taskName = getTaskName(splitCommand[0]);
            otherArgs = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
            date = getDate(otherArgs);
            tags = getTags(otherArgs);
            command = new AddCommand(TaskTypes.DEADLINE, taskName, tags, date);
            break;
        case "todo":
            taskName = getTaskName(splitCommand[0]);
            otherArgs = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
            tags = getTags(otherArgs);
            command = new AddCommand(TaskTypes.TODO, taskName, tags);
            break;
        case "event":
            taskName = getTaskName(splitCommand[0]);
            otherArgs = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
            date = getDate(otherArgs);
            tags = getTags(otherArgs);
            command = new AddCommand(TaskTypes.EVENT, taskName, tags, date);
            break;
        default:
            throw new DukeException(ErrorCodes.UNKNOWN_COMMAND);
        }

        return command;
    }

    /**
     * Get the command word from the command input given by user.
     *
     * @param command Command input given by user
     * @return The command word
     */
    public static String getCommandWord(String command) {
        return command.split("\\s", 2)[0];
    }

    /**
     * Get the name of the task from the command input given by user.
     *
     * @param command The input given by the user
     * @return The task name specified by the user
     * @throws DukeException DukeException thrown when the task name is not specified
     */
    public static String getTaskName(String command) throws DukeException {
        try {
            return command.split("\\s", 2)[1];
        } catch (PatternSyntaxException e) {
            throw new DukeException(ErrorCodes.MISSING_TASK_NAME);
        }
    }

    /**
     * Get the task index from the input given by the user.
     * @param command The input given by the user
     * @return The index of the task as specified by user
     * @throws DukeException DukeException thrown when the index is not specified
     */
    public static int getTaskIndex(String command) throws DukeException {
        try {
            return Integer.parseInt(command.split("\\s", 2)[1]) - 1;
        } catch (PatternSyntaxException | NumberFormatException e) {
            throw new DukeException(ErrorCodes.MISSING_TASK_NAME);
        }
    }

    /**
     * Get and parse the input date into a LocalDate object.
     *
     * @param otherArgs Input arguments that have been split into an array
     * @return A LocalDate object of the parsed input date
     * @throws DukeException DukeException thrown when the date is of an invalid format
     */
    public static LocalDate getDate(String[] otherArgs) throws DukeException {
        LocalDate date = null;
        try {
            for (String args : otherArgs) {
                if (args.split("\\s")[0].equals("by") || args.split("\\s")[0].equals("at")) {
                    date = LocalDate.parse(args.split("\\s")[1]);
                }
            }
            // TODO: Assert that there is a date
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException(ErrorCodes.INVALID_DATE_FORMAT);
        }
    }

    /**
     * Get the tags as specified in the input arguments.
     *
     * @param otherArgs Input arguments that have been split into an array
     * @return An ArrayList of Tag objects
     * @throws DukeException DukeException thrown when the tag is more than a single word
     */
    public static ArrayList<Tag> getTags(String[] otherArgs) throws DukeException {
        ArrayList<Tag> tags = new ArrayList<Tag>();
        for (String args : otherArgs) {
            if (args.split("\\s").length > 2) {
                throw new DukeException(ErrorCodes.INVALID_TAG);
            }
            if (args.split("\\s")[0].equals("tag")) {
                // Prevent duplicate tags
                if (!tags.contains(new Tag(args.split("\\s")[1]))) {
                    tags.add(new Tag(args.split("\\s")[1]));
                }
            }
        }
        return tags;
    }

    /**
     * Get the name of the tag (for ListCommand).
     *
     * @param otherArgs Input arguments that have been split into an array
     * @return The name of tag given by the user
     * @throws DukeException DukeException thrown when multiple tags are input for the ListCommand
     */
    public static String getTagName(String[] otherArgs) throws DukeException {
        String tagName = "";
        for (String args : otherArgs) {
            if (args.split("\\s")[0].equals("tag")) {
                if (!tagName.isEmpty()) {
                    throw new DukeException(ErrorCodes.LIST_ONE_TAG);
                } else {
                    tagName = args.split("\\s")[1];
                }
            }
        }
        return tagName;
    }
}