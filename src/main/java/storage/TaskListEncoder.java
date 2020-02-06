package main.java.storage;

import main.java.model.Task;
import main.java.model.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskListEncoder {
    static final String SEPARATOR = " | ";
    static final String FINISHED_STATUS = "1";
    static final String UNFINISHED_STATUS = "0";

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
