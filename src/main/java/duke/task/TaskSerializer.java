package duke.task;

/**
 * Creates serial strings for the different types of tasks.
 */
public class TaskSerializer {
    private static final String SERIAL_DELIMITER = " | ";
    private static final String SERIAL_TRUE = "1";
    private static final String SERIAL_FALSE = "0";

    /**
     * Returns a text representation of a task for storage purposes.
     *
     * @param task the task to serialize.
     * @param taskId the unique id for the type of this task.
     * @param args optional arguments that need to be serialized.
     * @return a text representation of this task for storage purposes.
     */
    static String serialize(Task task, String taskId, String... args) {
        StringBuilder serial = new StringBuilder();

        serial.append(taskId)
                .append(SERIAL_DELIMITER)
                .append(task.isDone() ? SERIAL_TRUE : SERIAL_FALSE)
                .append(SERIAL_DELIMITER)
                .append(task.getDescription());

        for (int i = 0; i < args.length; i++) {
            serial.append(SERIAL_DELIMITER).append(args[i]);
        }

        // TODO: potential for assertion here

        return serial.toString();
    }
}
