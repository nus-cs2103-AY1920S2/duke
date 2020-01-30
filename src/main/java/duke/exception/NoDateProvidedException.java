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

    public NoDateProvidedException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String taskType;
        if (type.equals("at")) {
            taskType = "event";
        } else {
            taskType = "deadline";
        }
        return String.format("The task type %s requires a '%s' date of the format /%s {date} e.g. /%s Monday 2pm.\n",
                taskType, this.type, this.type, this.type);
    }
}
