public class EmptyDescriptionException extends DukeException {
    String taskType;
    EmptyDescriptionException(String taskType) {
        this.taskType = taskType;
    }
    public String getTaskType() {
        return this.taskType;
    }
}
