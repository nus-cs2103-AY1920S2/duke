public class Task {
    protected boolean isDone;
    protected String taskName;

    public Task(String tN) {
        this.taskName = tN;
        this.isDone = false;
    }

    public void done() {
        isDone = true;
    }


    public String toString() {
        String output = "";

        if(isDone) {
            output = "[O] " + taskName;
        } else {
            output = "[X] " + taskName;
        }
        return output;
    }
}
