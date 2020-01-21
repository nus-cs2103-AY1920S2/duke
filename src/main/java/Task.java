public class Task {

    // whether or not user has completed the task
    private boolean isDone;

    // main task that user wants to log
    private String command;

    // for utility
    private String horizontalLine = "____________________________________________________________";

    public Task(String command) {
        this.command = command;
        this.isDone = false; // all tasks are created incomplete
    }

    // Prints the indication that the Task's command has been added
    public void taskAddedMessage() {
        printLine();
        print("==> Added unique task: " + this);
        printLine();
    }

    // Marks the Task as completed
    public void doTask() {
        this.isDone = true;
    }

    // Obtains tick or cross status icon dependent on completion
    public String obtainStatusIcon() {
        return (this.isDone) ? "\u2713" : "\u2718";
    }

    private void printLine() {
        print(horizontalLine);
    }

    private void print(String s) {
        System.out.println(s);
    }

    public String toString() {
        return this.command;
    }

    public String getCommand() {
        return this.command;
    }

}
