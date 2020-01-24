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
}
