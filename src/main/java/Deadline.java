public class Deadline extends Task {
    protected String deadline;

    public Deadline(String command, String deadline) {
        super(command);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getDoneSymbol() + "] " + getCommand() + "(by: " + deadline + ")";
    }
}
