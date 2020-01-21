public class Task {

    private String command;
    private String horizontalLine = "____________________________________________________________\n";

    public Task(String command) {
        this.command = command;
    }

    // Prints the Task
    public void print() {
        System.out.println(horizontalLine + this + "\n" + horizontalLine);
    }

    public String toString() {
        return this.command;
    }

    public String getCommand() {
        return this.command;
    }

}
