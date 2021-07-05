package parser;

import data.TaskManager;
import data.task.DeadlineTask;
import data.task.EventTask;
import data.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encode task information into a data file.
 */
public class EncodeToFile {

    /**
     * Encodes all the {@code TaskManager} in the {@code taskManager} into a list of readable string presentation
     * for storage.
     */
    public static List<String> encodeAllTasks(TaskManager taskManager) {
        final List<String> encodedTasks = new ArrayList<>();
        taskManager.getTaskList().forEach(person -> encodedTasks.add(encodeTaskToString(person)));
        return encodedTasks;
    }

    /**
     * Encodes the {@code task} into a readable string representation.
     */
    private static String encodeTaskToString (Task task){
        StringBuilder encodedTaskBuilder = new StringBuilder();
        getStringBuilder(task, encodedTaskBuilder);
        //encode start/end time (optional)
        switch (task.getTaskType()){
        case 'D':
            encodeDeadlineToStting((DeadlineTask) task, encodedTaskBuilder);
            break;
        case 'E':
            encodeEventToString((EventTask) task, encodedTaskBuilder);
            break;
        default:
        case 'T':
            break;
        }
        return encodedTaskBuilder.toString();
    }

    /**
     * Parses user input into command for execution.
     *
     * @param task
     * @param encodedTaskBuilder
     */
    private static void encodeEventToString(EventTask task, StringBuilder encodedTaskBuilder) {
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(task.getTaskStartTime());
        encodedTaskBuilder.append(task.getTaskEndTime());
    }

    /**
     * Parses user input into command for execution.
     *
     * @param task
     * @param encodedTaskBuilder
     */
    private static void encodeDeadlineToStting(DeadlineTask task, StringBuilder encodedTaskBuilder) {
        encodedTaskBuilder.append("|");
        //up-casting
        encodedTaskBuilder.append(task.getTaskDeadline());
    }

    /**
     * Parses user input into command for execution.
     *
     * @param task
     * @param encodedTaskBuilder
     */
    private static void getStringBuilder(Task task, StringBuilder encodedTaskBuilder) {
        encodedTaskBuilder.append(String.valueOf(task.getTaskType()));
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(String.valueOf(task.getChar()));
        encodedTaskBuilder.append("|");
        encodedTaskBuilder.append(task.getTaskDescription());
    }

}
