public class CommandResult {
    String feedbackToUser;
    TaskList taskList;

    CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
