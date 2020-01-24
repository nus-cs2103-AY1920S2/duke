/**
 * This type of tasks have a specific start and end timing.
 */
public class Event extends Task {
    private String timing;

    /**
     * Class constructor.
     *
     * @param description Task description.
     * @param taskType Type of task: Event.
     * @param timing Timing of event.
     */
    public Event(String description, char taskType, String timing) {
        super(description, taskType);
        this.timing = timing;
    }

    /**
     * Class constructor to be used when loading data from hard disk during start-up.
     *
     * @param description Task description.
     * @param taskType Type of task: Event.
     * @param timing Timing of event.
     * @param isDone Status of event, when done or not.
     */
    public Event(String description, char taskType, String timing, boolean isDone) {
        super(description, taskType, isDone);
        this.timing = timing;
    }

    /**
     * Returns the timing of the event.
     *
     * @return timing of event.
     */
    public String getTiming() {
        return timing;
    }

    /**
     * Returns all information about the task formatted in a single string.
     *
     * @return Information about task.
     */
    public String obtainTaskInfo() {
        String taskInfo = super.obtainTaskInfo();
        return taskInfo + " " + this.getTiming();
    }
}
