public class Task {

    protected String description;
    protected boolean isDone;
    protected String tasktype; // is T, D or E depending on what task it is
    protected String raw_user_input;

    public Task (String description, String tasktype, String raw_user_input) {
        this.description = description;
        this.isDone = false;
        this.tasktype = tasktype;
        this.raw_user_input = raw_user_input;
    }

    public String getDescription() {
        return this.description;
    }

    public void updateisDone(boolean update) {
        this.isDone = update;
    }

    public String getTaskType() {
        return this.tasktype;
    }

    public String getRawInput() { return this.raw_user_input; }

    public String checkIfComplete() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or x symbols
    }

    public String getCompletionStatus() {
        return "[" + checkIfComplete() + "]"; // return [x] or [tick]
    }

    public String getDeadline() {
        return "";
    }
}
