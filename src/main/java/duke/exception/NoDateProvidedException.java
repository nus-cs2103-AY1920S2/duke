package duke.exception;

/**
 * NoDateProvidedException
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class NoDateProvidedException extends DukeException {
    String type;

    /**
     * Constructs a NoDateProvidedException.
     * @param type The type of task that has no date.
     */
    public NoDateProvidedException(String type) {
        this.type = type;
    }

    /**
     * Overrides the Object's toString method
     * and contains details of the error.
     * @return The String that containing reason for the error.
     */
    @Override
    public String toString() {
        String taskType;
        if (type.equals("at")) {
            taskType = "event";
        } else {
            taskType = "deadline";
        }
        return String.format("The task type %s requires a '%s' date of the format /%s {date} e.g. /%s Monday 2pm.",
                taskType, this.type, this.type, this.type);
    }
}
