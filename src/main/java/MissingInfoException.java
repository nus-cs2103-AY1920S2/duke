public class MissingInfoException extends DukeException {
    String taskType;
    boolean hasDescription;

    MissingInfoException(String taskType, boolean hasDescription) {
        this.taskType = taskType;
        this.hasDescription = hasDescription;
    }

    public String getMissingInfoName() {
        if (!this.hasDescription)
            return "description";
        else
            return "date/time";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
