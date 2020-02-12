package storage;

import static parser.Parser.parseDateTime;
import static storage.TaskListEncoder.FINISHED_STATUS;
import static parser.Parser.DATE_TIME_KEY;

import jdk.jfr.Event;
import exceptions.IllegalDateTimeFormatException;
import exceptions.NoDescriptionException;
import exceptions.StorageOperationException;
import model.*;

import java.lang.reflect.Array;
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
    static final String PERSON_DATA_ARGS = "\\s\\|\\s(T|E|D)\\s\\|\\s(1|0)\\s\\|\\s(\\S+)\\s?\\|?\\s?" + DATE_TIME_KEY;
    static final Pattern PERSON_DATA_ARGS_FORMAT = Pattern.compile(PERSON_DATA_ARGS);

    /**
     * Decode task from a list of string to TaskList object.
     * @param encodedTaskList A list of string representing tasks
     * @return TaskList object
     * @throws StorageOperationException If encoded task in invalid format.
     * @throws NoDescriptionException If there is no description for any task.
     * @throws IllegalDateTimeFormatException If date time string is in incorrect format.
     */
    public static TaskList decodeTaskList(List<String> encodedTaskList) throws
            StorageOperationException, NoDescriptionException, IllegalDateTimeFormatException {
        final ArrayList<Task> tasks = new ArrayList<>();
        for (String t : encodedTaskList) {
            tasks.add(decodeTask(t));
        }
        return new TaskList(tasks);
    }

    /**
     * Decode a string to task object.
     * @param encodedTask
     * @return Task object.
     * @throws StorageOperationException If encoded task string is in invalid format.
     * @throws NoDescriptionException If there is no description for any task.
     * @throws IllegalDateTimeFormatException If the date time string is in invalid format.
     */
    public static Task decodeTask(String encodedTask) throws
            StorageOperationException, NoDescriptionException, IllegalDateTimeFormatException {
        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new StorageOperationException("Encoded task in invalid format. Unable to decode");
        }
        String taskType = matcher.group(1);
        String isFinished = matcher.group(2);
        String description = matcher.group(3);
        //TODO: replace the if-else with a task factory
        Task currentTask;
        if ("T".equals(taskType)) {
            currentTask = new ToDoTask(matcher.group(3));
        } else if ("E".equals(taskType)) {
            LocalDateTime dateTime = parseDateTime(matcher.group(4), matcher.group(5));
            currentTask = new EventTask(description, dateTime);
        } else {
            LocalDateTime dateTime = parseDateTime(matcher.group(4), matcher.group(5));
            currentTask = new DeadLineTask(description, dateTime);
        }

        if (FINISHED_STATUS.equals(isFinished)) {
            currentTask.markAsDone();
        }
        return currentTask;
    }
}
