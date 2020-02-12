package storage;

import static parser.Parser.parseDateTime;
import static storage.TaskListEncoder.FINISHED_STATUS;
import static parser.Parser.DATE_TIME_KEY;

import exceptions.IllegalDateTimeFormatException;
import exceptions.NoDescriptionException;
import exceptions.StorageOperationException;

import model.DeadLineTask;
import model.EventTask;
import model.Task;
import model.TaskList;
import model.ToDoTask;

import java.awt.event.MouseAdapter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains method to decode a list of string representing tasks
 * to real task objects.
 */
public class TaskListDecoder {
    private static final String PERSON_DATA_ARGS = "\\s\\|\\s(T|E|D)\\s\\|\\s(1|0)\\s\\|\\s(\\S+)\\s?\\|?\\s?"
            + DATE_TIME_KEY;
    private static final Pattern PERSON_DATA_ARGS_FORMAT = Pattern.compile(PERSON_DATA_ARGS);

    private static final int POSITION_TASK_TYPE = 1;
    private static final int POSITION_TASK_IS_DONE = 2;
    private static final int POSITION_TASK_DESCRIPTION = 3;
    private static final int POSITION_DATE = 4;
    private static final int POSITION_TIME = 5;

    /**
     * Decode task from a list of string to TaskList object.
     * @param encodedTaskList A list of string representing tasks
     * @return TaskList object
     * @throws StorageOperationException If encoded task in invalid format.
     * @throws NoDescriptionException If there is no description for any task.
     * @throws IllegalDateTimeFormatException If date time string is in incorrect format.
     */
    static TaskList decodeTaskList(List<String> encodedTaskList) throws
            StorageOperationException, NoDescriptionException, IllegalDateTimeFormatException {
        final ArrayList<Task> tasks = new ArrayList<>();
        for (String t : encodedTaskList) {
            tasks.add(decodeTask(t));
        }
        return new TaskList(tasks);
    }

    /**
     * Decode a string to task object.
     * @param encodedTask task to be decode.
     * @return Task object.
     * @throws StorageOperationException If encoded task string is in invalid format.
     * @throws NoDescriptionException If there is no description for any task.
     * @throws IllegalDateTimeFormatException If the date time string is in invalid format.
     */
    private static Task decodeTask(String encodedTask) throws
            StorageOperationException, NoDescriptionException, IllegalDateTimeFormatException {

        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new StorageOperationException("Encoded task in invalid format. Unable to decode");
        }
        String taskType = decodeTaskType(matcher);
        boolean isFinished = decodeTaskIsDone(matcher);
        String description = decodeTaskDescription(matcher);

        if (ToDoTask.TASK_TYPE_CHA.equals(taskType)) {
            return new ToDoTask(description, isFinished);
        } else if (EventTask.TASK_TYPE_CHA.equals(taskType)) {
            return new EventTask(description, decodeTaskDateTime(matcher), isFinished);
        } else if (DeadLineTask.TASK_TYPE_CHA.equals(taskType)) {
            return new DeadLineTask(description, decodeTaskDateTime(matcher), isFinished);
        } else {
            throw new StorageOperationException("Encoded task in invalid format. Unable to decode");
        }
    }

    private static String decodeTaskType(Matcher matcher) {
        return matcher.group(POSITION_TASK_TYPE);
    }

    private static boolean decodeTaskIsDone(Matcher matcher) {
        String taskIsDone = matcher.group(POSITION_TASK_IS_DONE);
        return FINISHED_STATUS.equals(taskIsDone);
    }

    private static String decodeTaskDescription(Matcher matcher) {
        return matcher.group(POSITION_TASK_DESCRIPTION);
    }

    private static LocalDateTime decodeTaskDateTime(Matcher matcher) throws
            IllegalDateTimeFormatException {
        String dateString = matcher.group(POSITION_DATE);
        String timeString = matcher.group(POSITION_TIME);
        return parseDateTime(dateString, timeString);
    }
}
