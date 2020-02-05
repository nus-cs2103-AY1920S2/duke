package main.java.storage;

import main.java.model.Task;
import main.java.model.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskListEncoder {
    static final String separator = " | ";

    public static List<String> encodeTask(TaskList taskList) {
        final List<String> encodedPersons = new ArrayList();
        taskList.forEach((task) -> encodedPersons.add(
                encodeTaskToString(task)
        ));

        return encodedPersons;
    }

    private static String encodeTaskToString(Task task) {
        final StringBuilder taskStringBuilder = new StringBuilder();

        taskStringBuilder.append(separator);

        taskStringBuilder.append(task.getTaskType());
        taskStringBuilder.append(separator);
        taskStringBuilder.append(task.isDone() ? "1" : "0");
        taskStringBuilder.append(separator);
        taskStringBuilder.append(String.join(separator, task.getDetails()));

        return taskStringBuilder.toString();
    }
}
