/**
 * An exception thrown when there is missing information.
 */
public class MissingInfoException extends DukeException {
    String taskType;
    boolean hasDescription;

    MissingInfoException(String taskType, boolean hasDescription) {
        this.taskType = taskType;
        this.hasDescription = hasDescription;
    }

    /**
     * Returns a description of the item that is missing.
     * @return Name of missing item.
     */
    public String getMissingInfoName() {
        if (!this.hasDescription)
            return "description";
        else
            return "date/time";
    }

    /**
     * Returns the task type.
     * @return Task type.
     */
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String getMessage() {
        String message = "The " + this.getMissingInfoName() + " of a " + this.getTaskType() + " cannot be empty.";
        return message;
    }
}
