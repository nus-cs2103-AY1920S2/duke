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
    private static final String TODO_TASK_ARGS = "\\s\\|\\sT\\s\\|\\s(1|0)\\s\\|(.*)";
    private static final String EVENT_TASK_ARGS = "\\s\\|\\sE\\s\\|\\s(1|0)\\s\\|(.*)\\|\\s"
            + DATE_TIME_KEY;
    private static final String DEADLINE_TASK_ARGS = "\\s\\|\\sD\\s\\|\\s(1|0)\\s\\|(.*)\\|\\s"
            + DATE_TIME_KEY;

    private static final Pattern TODO_TASK_ARGS_PATTERN = Pattern.compile(TODO_TASK_ARGS);
    private static final Pattern EVENT_TASK_ARGS_PATTERN = Pattern.compile(EVENT_TASK_ARGS);
    private static final Pattern DEADLINE_TASK_ARGS_PATTERN = Pattern.compile(DEADLINE_TASK_ARGS);

    private static final int POSITION_TASK_IS_DONE = 1;
    private static final int POSITION_TASK_DESCRIPTION = 2;
    private static final int POSITION_DATE = 3;
    private static final int POSITION_TIME = 4;

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

        if (isTodoTask(encodedTask)) {
            boolean isFinished = decodeTaskIsDone(TODO_TASK_ARGS_PATTERN, encodedTask);
            String description = decodeTaskDescription(TODO_TASK_ARGS_PATTERN, encodedTask);
            return new ToDoTask(description, isFinished);
        } else if (isEventTask(encodedTask)) {
            boolean isFinished = decodeTaskIsDone(EVENT_TASK_ARGS_PATTERN, encodedTask);
            String description = decodeTaskDescription(EVENT_TASK_ARGS_PATTERN, encodedTask);
            LocalDateTime at = decodeTaskDateTime(EVENT_TASK_ARGS_PATTERN, encodedTask);
            return new EventTask(description, at, isFinished);
        } else if (isDeadlineTask(encodedTask)) {
            boolean isFinished = decodeTaskIsDone(DEADLINE_TASK_ARGS_PATTERN, encodedTask);
            String description = decodeTaskDescription(DEADLINE_TASK_ARGS_PATTERN, encodedTask);
            LocalDateTime by = decodeTaskDateTime(DEADLINE_TASK_ARGS_PATTERN, encodedTask);
            return new DeadLineTask(description, by, isFinished);
        } else {
            throw new StorageOperationException("Encoded task in invalid format. Unable to decode");
        }
    }

    private static boolean isTodoTask(String encodedTask) {
        Matcher matcher = TODO_TASK_ARGS_PATTERN.matcher(encodedTask);
        return matcher.matches();
    }

    private static boolean isEventTask(String encodedTask) {
        Matcher matcher = EVENT_TASK_ARGS_PATTERN.matcher(encodedTask);
        return matcher.matches();
    }

    private static boolean isDeadlineTask(String encodedTask) {
        Matcher matcher = DEADLINE_TASK_ARGS_PATTERN.matcher(encodedTask);
        return matcher.matches();
    }

    private static boolean decodeTaskIsDone(Pattern pattern, String encodedTask) {
        Matcher matcher = pattern.matcher(encodedTask);
        matcher.find();
        String taskIsDone = matcher.group(POSITION_TASK_IS_DONE);
        return FINISHED_STATUS.equals(taskIsDone);
    }

    private static String decodeTaskDescription(Pattern pattern, String encodedTask) {
        Matcher matcher = pattern.matcher(encodedTask);
        matcher.find();
        return matcher.group(POSITION_TASK_DESCRIPTION).trim();
    }

    private static LocalDateTime decodeTaskDateTime(Pattern pattern, String encodedTask) throws
            IllegalDateTimeFormatException {
        Matcher matcher = pattern.matcher(encodedTask);
        matcher.find();
        String dateString = matcher.group(POSITION_DATE);
        String timeString = matcher.group(POSITION_TIME);
        return parseDateTime(dateString, timeString);
    }
}
