public class Task {

    // main task that user wants to log
    private String command;
    // for utility
    private String horizontalLine = "____________________________________________________________";

    public Task(String command) {
        this.command = command;
    }

    // Prints the indication that the Task's command has been added
    public void taskAddedMessage() {
        printLine();
        System.out.println("==> Added unique task: " + this);
        printLine();
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
