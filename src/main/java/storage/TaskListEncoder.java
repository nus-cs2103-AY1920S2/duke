package storage;

import model.Task;
import model.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements methods to encode tasks from the inner-tasklist to a list of string.
 */
public class TaskListEncoder {
    public static final String FINISHED_STATUS = "1";

    private static final String SEPARATOR = " | ";
    private static final String UNFINISHED_STATUS = "0";

    /**
     * Encode tasks from inner-tasklist to a list of strings.
     * @param taskList to be encoded.
     * @return A list of strings with each string representing one task.
     */
    public static List<String> encodeTask(TaskList taskList) {
        final ArrayList<String> encodedTasks = new ArrayList<String>();
        taskList.forEach((task) -> encodedTasks.add(
                encodeTaskToString(task)
        ));

        return encodedTasks;
    }

    private static String encodeTaskToString(Task task) {
        StringBuilder taskStringBuilder = new StringBuilder();

        taskStringBuilder.append(SEPARATOR);
        taskStringBuilder.append(task.getTaskType());
        taskStringBuilder.append(SEPARATOR);
        taskStringBuilder.append(task.isDone() ? FINISHED_STATUS : UNFINISHED_STATUS);
        taskStringBuilder.append(SEPARATOR);
        taskStringBuilder.append(String.join(SEPARATOR, task.getDetails()));

        return taskStringBuilder.toString();
    }
}
