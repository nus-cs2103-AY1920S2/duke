public class Deadline extends Task {
    protected String deadline;

    public Deadline(String command, int index, String deadline) {
        super(command, index);
        this.deadline = deadline;
    }

    protected String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getDoneSymbol() + "] " + getCommand() + " (by: " + deadline + ")";
    }
}
