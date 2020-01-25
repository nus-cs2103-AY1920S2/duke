/**
 * This type of tasks do not have any date/time attached to it.
 */
public class ToDo extends Task {

    /**
     * Class constructor.
     *
     * @param description Task description.
     * @param taskType type of task: to-do.
     */
    public ToDo(String description, char taskType) {
        super(description, taskType);
    }

    /**
     * Class constructor to be used when loading data from hard disk during start-up.
     *
     * @param description Task description.
     * @param taskType Type of task: to-do.
     * @param isDone Status of task, whether completed or not.
     */
    public ToDo(String description, char taskType, boolean isDone) {
        super(description, taskType, isDone);
    }
}
