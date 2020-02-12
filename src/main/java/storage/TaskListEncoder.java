package storage;

import model.Task;
import model.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements methods to encode tasks from the inner-tasklist to a list of string.
 */
public class TaskListEncoder {
    static final String SEPARATOR = " | ";
    static final String FINISHED_STATUS = "1";
    static final String UNFINISHED_STATUS = "0";

    /**
     * Encode tasks from inner-tasklist to a list of strings.
     * @param taskList
     * @return A list of strings with each string representing one task.
     */
    public static List<String> encodeTask(TaskList taskList) {
        final List<String> encodedPersons = new ArrayList();
        taskList.forEach((task) -> encodedPersons.add(
                encodeTaskToString(task)
        ));

        return encodedPersons;
    }

    private static String encodeTaskToString(Task task) {
        final StringBuilder taskStringBuilder = new StringBuilder();

        taskStringBuilder.append(SEPARATOR);

        taskStringBuilder.append(task.getTaskType());
        taskStringBuilder.append(SEPARATOR);
        taskStringBuilder.append(task.isDone() ? FINISHED_STATUS : UNFINISHED_STATUS);
        taskStringBuilder.append(SEPARATOR);
        taskStringBuilder.append(String.join(SEPARATOR, task.getDetails()));

        return taskStringBuilder.toString();
    }
}
