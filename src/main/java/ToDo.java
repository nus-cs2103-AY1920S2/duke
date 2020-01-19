public class ToDo extends Task {

    // public constructor
    public ToDo(String taskname) {
        super(taskname, "T");
    }

    @Override
    // Overide the method to return task name plus whether is it done or not
    public String toString() {
        String message = "";
        if (this.getDone()) {
            message += "[" + this.Tasktype + "]" + "[" + "\u2713" + "] " + this.taskname;
        } else {
            message += "[" + this.Tasktype + "]" + "[" + "\u2718" + "] " + this.taskname;
        }
        return message;
    }
}
